package com.gk.jvm.outofmemeooy;

/**
 * vm Args:-Xss128k
 * @author gk
 *
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args) throws Throwable {

		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:"+ oom.stackLength);
			throw e;
		}
	}
/**运行结果，虚拟机和本地方法栈内存溢出
 * stack length:992
   Exception in thread "main" java.lang.StackOverflowError
	at com.outofmemeooy.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:7)
	at com.outofmemeooy.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:8)
 */
}
