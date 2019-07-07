package com.app.thread.lib.storage.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app.thread.lib.dto.BaseRssFeed;
import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.util.UtilString;
import com.app.thread.lib.util.Validation;

/**
 * !place your description here!
 *
 * @author taufuk.muliahadi (&copy;Jul 7, 2019) 
 * @param <DATA>
 */
public class InternalQ<DATA> implements IMemoryQ<DATA> {

	private static String MSG_EXCEPTION = "Got Error....!";
	private final Logger logger = LogManager.getLogger(InternalQ.class);
	private static final InternalMemoryStorage memoryStore = InternalMemoryStorage.getInstance(); 
	private ConcurrentLinkedQueue<DATA> queue;
	private String qId;
	private static Map<String, String> rssFeedQueueThread ;
	
	public InternalQ(String qId) {
		this.qId = qId;
		prepareQueue();
	}
	
	@SuppressWarnings("unchecked")
	private void prepareQueue() {
		queue = (ConcurrentLinkedQueue<DATA>) memoryStore.getQueue(qId);
		if (queue == null) {
			queue = new ConcurrentLinkedQueue<DATA>();
			memoryStore.getAllQueue().put(qId, queue);
		}
		if (rssFeedQueueThread == null) {
			rssFeedQueueThread = new HashMap<String, String>();
		}
	}
	
	public ConcurrentLinkedQueue<DATA> getQueue() {
		return queue;
	}
	public DATA pull() {
		return queue.poll();
	}

	public DATA waitPull() {
		DATA data = null;
		try {
			synchronized (queue) {
				while (queue.isEmpty()) {
					queue.wait();
				}
			}
			data = pull();
		} catch (Exception e) {
			logger.error(MSG_EXCEPTION, e);
		}
		return data;
	}

	public DATA waitPull(long timeout) {
		DATA data = null;
		try {
			synchronized (queue) {
				while (queue.isEmpty()) {
					queue.wait(timeout);
				}
			}
			data = pull();
		} catch (Exception e) {
			logger.error(MSG_EXCEPTION, e);
		}
		return data;
	}

	public boolean push(DATA data) {
		return queue.offer(data);
	}

	public boolean pushNotify(DATA data) {
		if(data instanceof BaseRssFeed) {
			if(rssFeedQueueThread.get(((BaseRssFeed) data).getLinkRssFed()) == null)
				rssFeedQueueThread.put(((BaseRssFeed) data).getLinkRssFed(), "in queue");
			else
				return false;
				
		}
		boolean res = push(data);
		synchronized (queue) {
			queue.notifyAll();
		}
		return res;
	}

	public void destroy() {
		if (Validation.hasValue(qId)) {
			memoryStore.destroyQueue(qId);
		}
	}
	
	public static void destroyQueue(String qKey) {
		if (Validation.hasValue(qKey)) {
			memoryStore.destroyQueue(qKey);
		}
	}

	public String getQKey() {
		return qId;
	}
	
	public void dumpQueue() {
		if (logger.isTraceEnabled()) {
			List<Object> log = new ArrayList<Object>();
			log.add(UtilString.keyValue("Queue", getQKey()));
			log.add(UtilString.keyValue("length", queue.size()));
			logger.trace(UtilString.toString(log));
		}
	}

	public static Map<String, String> getRssFeedQueueThread() {
		return rssFeedQueueThread;
	}

	public Map<String, String> rssThread() {
		return rssFeedQueueThread;
	}
}
