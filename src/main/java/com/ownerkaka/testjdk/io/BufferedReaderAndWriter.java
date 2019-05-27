package com.ownerkaka.testjdk.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class BufferedReaderAndWriter {

	@Test
	public void testBufferedReader() throws IOException, FileNotFoundException{
		
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream("D:\\text.txt"), "utf-8"),10000);
		
		String str = null;
		long start = System.currentTimeMillis();
		while((str = reader.readLine()) != null){
			
			System.out.println(str);
		}
        long end = System.currentTimeMillis();
		long total = end -start;
        System.out.println("一共耗时:"+total);
		reader.close();
	}
	@Test
	public void testBufferedWriter() throws IOException{
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("text_copy.txt")
						));
		writer.write("你好啊啊,bufferedwriter");
		
		writer.flush();
		writer.close();
	}
	@Test
	public void testBufferedReaderWriter() throws IOException{
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream("D:\\text.txt"), "utf-8"),10000);
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("D:\\text_copy.txt")
						));
		String str;
		while((str = reader.readLine()) != null){
			writer.write(str);
		}
		writer.close();
		reader.close();
	}
}
