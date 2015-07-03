package com.gk.designpattern23.chain.responsibility;

public class ConcreteHandlerB extends Handler {

	@Override
	public void HandleRequest(int request) {

		if(request==2){
			System.out.println(this.getClass().getCanonicalName()+"处理了请求"+request);
		}else{
			if(this.handler!=null){
				System.out.println(this.getClass().getCanonicalName()+"自身无法满足条件，转入下一个处理者");
				this.handler.HandleRequest(request);
			}
		}
	}

}
