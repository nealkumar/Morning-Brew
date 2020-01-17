package com.kumar.neal;

public interface Task extends Runnable{
	
	/**
	 * <p>Returns the thread-safe value of Type &lt?&gt</p>
	 * 
	 * @param <T>
	 * @return - value of Type &lt?&gt
	 * @throws InterruptedException
	 * @author nealk
	 */
	@ThreadSafe
	public abstract <T extends Object> T getVal() throws InterruptedException;
	
}
