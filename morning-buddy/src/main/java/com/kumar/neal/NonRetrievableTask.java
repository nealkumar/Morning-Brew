package com.kumar.neal;

public abstract class NonRetrievableTask implements Task{

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " 
				+ Thread.currentThread().getId()
				+ " is running...");
		this.execute();
	}
	
	protected abstract void execute();

	@Override
	public <T> T getVal() throws InterruptedException {
		throw new UnsupportedOperationException();
	}

}
