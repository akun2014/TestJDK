package com.gk.jvm.outofmemeooy;

import java.util.ArrayList;
import java.util.List;
/**
 * VM arg :-Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError
 * @author gk
 *
 */
public class Heapoom {

	static class OOMObject{
		
	}
	public static void main(String[] args) {

		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
/**运行结果,堆内存溢出
 * java.lang.OutOfMemoryError: Java heap space
   Dumping heap to java_pid5856.hprof ...
   Heap dump file created [27908422 bytes in 0.187 secs]
 */
}
