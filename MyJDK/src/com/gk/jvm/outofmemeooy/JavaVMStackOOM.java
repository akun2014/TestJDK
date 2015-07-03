package com.gk.jvm.outofmemeooy;
/**
 * VM args: -Xss2M
 * @author gk
 *！！！运行后会出现死机！！！
 */
public class JavaVMStackOOM {

	private void dontStop(){
		while(true){
			
		}
	}
	public void stackLeakByThread() {

		while(true){
			Thread thread = new Thread(new Runnable(){

				@Override
				public void run() {

					dontStop();
				}
				
			});
			thread.start();
		}
	}
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
/**
 * Exception in thread "main" java.lang.StackOverflowError
	at com.outofmemeooy.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:12)
 */
}
