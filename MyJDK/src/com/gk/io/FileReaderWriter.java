package com.gk.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;

import org.junit.Test;

public class FileReaderWriter {

	@Test
	public void testFileReader() throws IOException {
		FileReader reader = new FileReader("D:\\text.txt");

		char[] cbuf = new char[10];

		while (reader.read(cbuf, 0, cbuf.length) != -1) {
			System.out.println(cbuf);
		}

		reader.close();
	}

	@Test
	public void testFileWriter() throws IOException {
		FileWriter writer = new FileWriter("D:\\text_copy.txt");

		String str = "你好啊，我是filewriter类";
		
		writer.write(str);
		
		writer.flush();
		writer.close();
	}

	@Test
	public void testFileReaderWriter() throws IOException {
		FileReader reader = new FileReader("D:\\text.txt");
		FileWriter writer = new FileWriter("D:\\text_copy.txt");
		
		 
		char[] cbuf = new char[20];
		while(reader.read(cbuf) != -1){
			writer.write(cbuf);
			writer.flush();
		}
		
		writer.close();
		reader.close();

	}
}
