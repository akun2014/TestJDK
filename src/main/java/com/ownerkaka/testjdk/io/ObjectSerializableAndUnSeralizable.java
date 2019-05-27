package com.ownerkaka.testjdk.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ownerkaka.testjdk.common.entity.Son1;
import com.ownerkaka.testjdk.common.entity.Son2;

import junit.framework.TestCase;

public class ObjectSerializableAndUnSeralizable extends TestCase {

	public void testObjectSerialzable() throws IOException{
		ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obj.bat"));
//		Son1 name = new Son1("parents","son",21);
		Son2 name = new Son2("parents","son1","son2",21);
		outputStream.writeObject(name);
		outputStream.close();
	}
	
	public void testObjectUnSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obj.bat"));
		
		Son1 name = (Son1)inputStream.readObject();
		
	//	Parents p = (Parents)inputStream.readObject();
		System.out.println("son2"+name.toString());
		System.out.println("parents:"+name.getName());
		
		inputStream.close();
	}
}
