package com.kumar.neal;

public abstract class NonRetrievableTask implements Task{

	@Override
	public final synchronized void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread " 
				+ Thread.currentThread().getId()
				+ " is running...");
		this.execute();
	}
	
	protected abstract void execute();

	@Override
	public final <T> T getVal() throws InterruptedException {
		throw new UnsupportedOperationException();
	}

}
