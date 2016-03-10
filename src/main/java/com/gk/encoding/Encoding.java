package com.gk.encoding;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Encoding {

	@Test
	public void testEncoding() throws UnsupportedEncodingException{
		String str = "我";
		
		System.out.println("default:"+str.getBytes().length);
		System.out.println("utf-8:"+str.getBytes("UTF-8").length);
		System.out.println("gbk:"+str.getBytes("gbk").length);
		//System.out.println("gbk2312:"+str.getBytes("gbk2312").length);
		System.out.println("iso8859-1:"+str.getBytes("iso-8859-1").length);
		System.out.println("utf-16be:"+str.getBytes("UTF-16be").length);
	}
}
