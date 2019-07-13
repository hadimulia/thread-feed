package com.app.thread.lib.storage;

import java.util.Map;

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
	/**
	 * waiting object from queue thread
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @return
	 */
	DATA waitPull();
	/**
	 * waiting object from queue thread with timeout
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param timeout
	 * @return
	 */
	DATA waitPull(long timeout);
	/**
	 * push data to queue thread
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param data
	 * @return
	 */
	boolean push(DATA data);
	/**
	 * push data with notify to thread pull
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param data
	 * @return
	 */
	boolean pushNotify(DATA data);
	/**
	 * Destroy queue thread
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 */
	void destroy();
	/**
	 * get key queue thread
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @return
	 */
	String getQKey();
	/**
	 * list domain in process
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @return
	 */
	Map<String, String> rssThread();
}
