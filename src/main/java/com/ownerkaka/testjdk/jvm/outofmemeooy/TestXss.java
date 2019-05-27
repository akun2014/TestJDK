package com.ownerkaka.testjdk.jvm.outofmemeooy;

public class TestXss {
	public static class MyThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int count = 0;
		try {
			for (int i = 0; i < 10000; i++) {
				new MyThread().start();
				count++;
			}
		} catch (OutOfMemoryError e) {
			System.out.println(count);
			System.out.println(e.getMessage());
		}
	}
}
