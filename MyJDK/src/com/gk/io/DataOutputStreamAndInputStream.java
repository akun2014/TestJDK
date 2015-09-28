package com.gk.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class DataOutputStreamAndInputStream {

	@Test
	public void testDataOutputStream() throws IOException{
		DataOutputStream outputStream = new DataOutputStream(
				new FileOutputStream("D:\\dataOutputstream.txt"));
		
		//outputStream.writeInt(10);
		//outputStream.writeLong(10L);
	//	outputStream.writeFloat(10.5f);
		//outputStream.writeDouble(10.5);
		
		outputStream.writeChar(98);
		outputStream.writeUTF("中国");
		
		
		outputStream.flush();
		outputStream.close();
	}
	@Test
	public void testDataInoutStream() throws IOException{
		DataInputStream inputStream = new DataInputStream(
				new FileInputStream("D:\\dataOutputstream.txt"));
		
		System.out.println(inputStream.readInt());
		System.out.println(inputStream.readLong());
		System.out.println(inputStream.readFloat());
		System.out.println(inputStream.readDouble());
		System.out.println(inputStream.readChar());
		System.out.println(inputStream.readUTF());
		System.out.println();
		
		inputStream.close();
	}
}
