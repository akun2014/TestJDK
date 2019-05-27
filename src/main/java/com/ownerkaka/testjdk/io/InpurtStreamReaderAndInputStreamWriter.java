package com.ownerkaka.testjdk.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class InpurtStreamReaderAndInputStreamWriter {

	@Test
	public void testInputStreamReader(){
		try {
			FileInputStream in = new FileInputStream("D:\\text.txt");
			InputStreamReader reader = new InputStreamReader(in,"utf-8");
			
			
			char ch[] = new char[3];
			while (reader.read(ch) != -1) {
				System.out.println(ch);
			}
			
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	
	@Test
	public void testOutPutStreamWriter(){
		try {
			FileOutputStream out = new FileOutputStream("D:\\text_copy.txt");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "utf-8");
			
			
			outputStreamWriter.write("hello你好");
			//输出asci码对应的字符
            outputStreamWriter.write(97);
            
            char[] cbuf = new char[]{'a','s','们'};
            outputStreamWriter.write(cbuf);
			
			
			outputStreamWriter.flush();
			outputStreamWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInOutPutStream() throws IOException{
		
		File file = new File("D:\\text.txt");
		FileInputStream in = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(in);
		
		File copy_file = new File("D:\\text_copy.txt");
		FileOutputStream out = new FileOutputStream(copy_file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "utf-8");
		
		char[] cbuf = new char[10];
		while(inputStreamReader.read(cbuf) != -1){
			outputStreamWriter.write(cbuf);
		}
		
		outputStreamWriter.close();
		inputStreamReader.close();
	}
}
