/**
 * 
 */
package com.ownerkaka.testjdk.io;

import java.io.File;

import junit.framework.TestCase;

/**
 * @author gk
 * @date  2015年9月29日
 * @time  下午5:09:24
 */
public class TestFile extends TestCase{

	public void testFileCreate(){
		File file = new File("D:\\afile");
		boolean isFileExist = file.exists();
		boolean isDirectory = file.isDirectory();
		
		if(!isFileExist){
			System.out.println("目录不存在");
			if(!isDirectory){
				System.out.println("创建目录...");
				file.mkdir();
			}
		}
	}
	public void testFileDel(){
		
	}
	public void testFileIterator(){
		
	}
}
