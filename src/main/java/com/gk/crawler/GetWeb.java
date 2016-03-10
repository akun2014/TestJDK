package com.gk.crawler;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;

/**
 * 网络爬虫程序
 * @author gk
 *
 */
public class GetWeb {
	private int webDepth = 2; //
	private int intThreadNum = 10; // 
	private String strHomePage = "http://www.zongheng.com/";
	private String myDomain;// 
	private String fPath = "web"; //
	private ArrayList<String> arrUrls = new ArrayList<String>();//
	private ArrayList<String> arrUrl = new ArrayList<String>();// 
	private Hashtable<String, Integer> allUrls = new Hashtable<String, Integer>();// 
	private Hashtable<String, Integer> deepUrls = new Hashtable<String, Integer>();// 
	private int intWebIndex = 0;// ��ҳ��Ӧ�ļ��±꣬��0��ʼ
	private String charset = "GB2312";
	private String report = "";
	private long startTime = 0;
	private int webSuccessed = 0;
	private int webFailed = 0;

	public GetWeb(String s) {
		this.strHomePage = s;
	}

	public GetWeb(String s, int i) {
		this.strHomePage = s;
		this.webDepth = i;
	}

	public synchronized void addWebSuccessed() {
		webSuccessed++;
	}

	public synchronized void addWebFailed() {
		webFailed++;
	}

	public synchronized void addReport(String s) {
		try {
			report += s;
			PrintWriter pwReport = new PrintWriter(new FileOutputStream(
					"report.txt"));
			pwReport.println(report);
			pwReport.close();
		} catch (Exception e) {
			System.out.println("��ɱ����ļ�ʧ��");
		}
	}

	public synchronized String getAUrl() {
		String tmpAUrl = arrUrls.get(0);
		arrUrls.remove(0);
		return tmpAUrl;
	}

	public synchronized String getUrl() {
		String tmpUrl = arrUrl.get(0);
		arrUrl.remove(0);
		return tmpUrl;
	}

	public synchronized Integer getIntWebIndex() {
		intWebIndex++;
		return intWebIndex;
	}

	public static void main(String[] args) {
		/*if (args.length == 0 || args[0].equals("")) {
			System.out.println("NO Input!");
			System.exit(1);
		} else if (args.length == 1) {
			GetWeb gw = new GetWeb(args[0]);
			gw.getWebByHomePage();
		} else {
			GetWeb gw = new GetWeb(args[0], Integer.parseInt(args[1]));
			gw.getWebByHomePage();
		}*/
		String home = "http://www.zongheng.com";
		int depth = 2;
		GetWeb gw = new GetWeb(home,depth);
		gw.getWebByHomePage();
	}

	public void getWebByHomePage() {
		startTime = System.currentTimeMillis();
		this.myDomain = getDomain();
		if (this.myDomain == null) {
			System.out.println("Wrong input!");
			return;
		}
		System.out.println("Homepage=" + strHomePage);
		addReport("Homepage=" + strHomePage + "!\n");
		System.out.println("Domain=" + myDomain);
		addReport("Domain=" + myDomain + "!\n");
		arrUrl.add(strHomePage);
		arrUrls.add(strHomePage);
		allUrls.put(strHomePage, 1);
		deepUrls.put(strHomePage, 1);
		File fDir = new File(fPath);
		if (!fDir.exists()) {
			fDir.mkdir();
		}
		System.out.println("Start!");
		this.addReport("Start!\n");
		String tmp = getAUrl();
		this.getWebByUrl(tmp, charset, allUrls.get(tmp) + "");
		int i = 0;
		for (i = 0; i < intThreadNum; i++) {
			new Thread(new Processer(this)).start();
		}
		while (true) {
			if (arrUrls.isEmpty() && Thread.activeCount() == 1) {
				long finishTime = System.currentTimeMillis();
				long costTime = finishTime - startTime;
				System.out.println("\n\n\n\nFinished!");
				addReport("\n\n\n\nFinished!\n");
				System.out.println("StartTime=" + startTime + "FinishTime="
						+ finishTime + "CostTime=" + costTime + "ms");
				addReport("StartTime=" + startTime + "FinishTime=" + finishTime
						+ "CostTime=" + costTime + "ms" + "\n");
				System.out.println("Total url number="
						+ (webSuccessed + webFailed) + "Successed:"
						+ webSuccessed + "Failed:" + webFailed);
				addReport("Total url number=" + (webSuccessed + webFailed)
						+ "Successed:" + webSuccessed + "Failed:" + webFailed
						+ "\n");

				String strIndex = "";
				String tmpUrl = "";
				while (!arrUrl.isEmpty()) {
					tmpUrl = getUrl();
					strIndex += "Web depth:" + deepUrls.get(tmpUrl)
							+ "   Filepath:" + fPath + "/web"
							+ allUrls.get(tmpUrl) + ".htm" + "   url:" + tmpUrl
							+ "\n\n";
				}
				System.out.println(strIndex);
				try {
					PrintWriter pwIndex = new PrintWriter(new FileOutputStream(
							"fileindex.txt"));
					pwIndex.println(strIndex);
					pwIndex.close();
				} catch (Exception e) {
					System.out.println("��������б�ʧ�ܣ�");
				}
				break;
			}
		}
	}

	public void getWebByUrl(String strUrl, String charset, String fileIndex) {
		try {
			// if(charset==null||"".equals(charset)="utf-8");
			System.out.println("Getting web by url:" + strUrl);
			addReport("Getting web by url:" + strUrl + "\n");
			URL url = new URL(strUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			InputStream is = null;
			is = url.openStream();
			String filePath = fPath + "/web" + fileIndex + ".htm";
			PrintWriter pw = null;
			FileOutputStream fos = new FileOutputStream(filePath);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			pw = new PrintWriter(writer);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					is));
			StringBuffer sb = new StringBuffer();
			String rLine = null;
			String tmp_rLine = null;
			while ((rLine = bReader.readLine()) != null) {
				tmp_rLine = rLine;
				int str_len = tmp_rLine.length();
				if (str_len > 0) {
					sb.append("\n" + tmp_rLine);
					pw.println(tmp_rLine);
					pw.flush();
					if (deepUrls.get(strUrl) < webDepth)
						getUrlByString(tmp_rLine, strUrl);
				}
				tmp_rLine = null;
			}
			is.close();
			pw.close();
			System.out.println("Get web successfully!" + strUrl);
			addReport("Get web successfully!" + strUrl + "\n");
			addWebSuccessed();
		} catch (Exception e) {
			System.out.println("Get web failed!   " + strUrl);
			addReport("Get web failed!   " + strUrl + "\n");
			addWebFailed();
		}
	}

	public String getDomain() {
		String reg = "(?<=http\\://[a-zA-Z0-9]{0,100}[.]{0,1})[^.\\s]*?\\.(com|cn|net|org|info|cc|tv)";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(strHomePage);
		boolean blnp = m.find();
		if (blnp == true) {
			return m.group(0);
		}
		return null;
	}

	public void getUrlByString(String inputArgs, String strUrl) {
		String tmpStr = inputArgs;
		String regUrl = "(?<=(href=)[\"]?[\']?)[http://][^\\s\"\'\\?]*("
				+ myDomain + ")[^\\s\"\'>]*";
		Pattern p = Pattern.compile(regUrl, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(tmpStr);
		boolean blnp = m.find();
		while (blnp == true) {
			if (!allUrls.containsKey(m.group(0))) {
				System.out.println("Find a new url,depth:"
						+ (deepUrls.get(strUrl) + 1) + "" + m.group(0));
				addReport("Find a new url,depth:" + (deepUrls.get(strUrl) + 1)
						+ "" + m.group(0) + "\n");
				arrUrls.add(m.group(0));
				arrUrl.add(m.group(0));
				allUrls.put(m.group(0), getIntWebIndex());
				deepUrls.put(m.group(0), (deepUrls.get(strUrl) + 1));
			}
			tmpStr = tmpStr.substring(m.end(), tmpStr.length());
			m = p.matcher(tmpStr);
			blnp = m.find();
		}
	}

	class Processer implements Runnable {
		GetWeb gw;

		public Processer(GetWeb g) {
			this.gw = g;
		}

		public void run() {
			while (!arrUrls.isEmpty()) {
				String tmp = getAUrl();
				getWebByUrl(tmp, charset, allUrls.get(tmp) + "");
			}
		}
	}
}
