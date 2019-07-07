package com.app.thread.lib.storage.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.util.UtilString;

/**
 *
 * @author taufuk.muliahadi (&copy;Jul 7, 2019) 
 * @param <DATA>
 */
public class MemoryQFactory<DATA> {

	private final Logger logger = LogManager.getLogger(MemoryQFactory.class);
	
	public IMemoryQ<DATA> getMemQ(String instanceId, String qId) {
		IMemoryQ<DATA> res = null;
		res = new InternalQ<DATA>(UtilString.build(qId, "@", instanceId));
		return res;
	}
}
