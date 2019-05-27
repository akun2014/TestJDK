package com.ownerkaka.testjdk.designpattern23.chain.responsibility;

public class Client {
	public static void handler(int request){
		Handler handlera = new ConcreteHandlerA();
		Handler handlerb = new ConcreteHandlerB();
		Handler handlerc = new ConcreteHandlerC();
		
		handlera.setSuccessor(handlerb);
		handlerb.setSuccessor(handlerc);
		
		handlera.HandleRequest(request);
	}

}
