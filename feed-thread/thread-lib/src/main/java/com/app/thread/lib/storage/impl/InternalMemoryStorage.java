package com.app.thread.lib.storage.impl;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author taufuk.muliahadi (&copy;Jul 7, 2019) 
 */
public class InternalMemoryStorage {
	private static InternalMemoryStorage INSTANCE;
	private final ConcurrentMap<String, ConcurrentHashMap<?, ?>> map = new ConcurrentHashMap<String, ConcurrentHashMap<?, ?>>();
	private final ConcurrentMap<String, ConcurrentLinkedQueue<?>> queue = new ConcurrentHashMap<String, ConcurrentLinkedQueue<?>>();
	
	private InternalMemoryStorage() { }
	
	public static synchronized InternalMemoryStorage getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InternalMemoryStorage();
		}
		return INSTANCE;
	}
	
	public Set<String> getMapInstants() {
		return map.keySet();
	}
	
	public Set<String> getQueueInstants() {
		return queue.keySet();
	}
	
	public ConcurrentMap<String, ConcurrentHashMap<?, ?>> getAllMap() {
		return map;
	}
	
	public ConcurrentMap<String, ConcurrentLinkedQueue<?>> getAllQueue() {
		return queue;
	}
	
	public ConcurrentHashMap<?, ?> getMap(String mapKey) {
		return map.get(mapKey);
	}
	
	public ConcurrentLinkedQueue<?> getQueue(String qKey) {
		return queue.get(qKey);
	}
	
	public void destroyQueue(String qKey) {
		queue.remove(qKey);
	}
	
	public void destroyMap(String mapKey) {
		map.remove(mapKey);
	}
}
