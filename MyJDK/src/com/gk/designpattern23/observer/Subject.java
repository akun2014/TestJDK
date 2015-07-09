package com.gk.designpattern23.observer;

public interface Subject {

	public void registerObserver(Observer o);

	public void removeObserver(Observer o);

	public void notifyObserver();
}
