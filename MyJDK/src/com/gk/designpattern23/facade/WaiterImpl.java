/**
 * 
 */
package com.gk.designpattern23.facade;

/**
 * @author gk
 * @date 2015年10月23日
 * @time 下午4:10:57
 * 
 *       服务生部门
 */
public class WaiterImpl {
	public void serve(String food) {
		System.out.println("服务员正在端菜：" + food);
	}
}
