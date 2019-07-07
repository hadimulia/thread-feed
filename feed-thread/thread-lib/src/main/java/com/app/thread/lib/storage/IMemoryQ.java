package com.app.thread.lib.storage;

/**
 *
 * @author taufuk.muliahadi (&copy;Jul 7, 2019) 
 * @param <DATA>
 */
public interface IMemoryQ<DATA> {
	/**
	 * pull queue Object
	 *
	 * @author taufuk.muliahadi (&copy;Jul 7, 2019)
	 * @return
	 */
	DATA pull();
	DATA waitPull();
	DATA waitPull(long timeout);
	boolean push(DATA data);
	boolean pushNotify(DATA data);
	void destroy();
	String getQKey();
}
