package org.mdk.jdental.transactions;

import org.mdk.jdental.exceptions.TopLevelException;


public interface DatabaseAdaptor {
	public void saveEmotion(int emotion, String uri) throws TopLevelException;
}
