package com.gk.designpattern23.chain.responsibility;

public abstract class Handler {
	protected Handler handler;
	
	public void setSuccessor(Handler handler){
		this.handler = handler;
	}

	public abstract void HandleRequest(int request);
}
