package org.mdk.jdental.components;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.mdk.jdental.exceptions.TopLevelException;
import org.mdk.jdental.transactions.Derbymanager;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Controller {

	public List<SyndEntryImpl> getFeed() throws IOException, IllegalArgumentException,
	FeedException {

		List<SyndEntryImpl> rssEntries = null;

		URL feedUrl = new URL("http://rss.slashdot.org/slashdot/classic");

		SyndFeedInput input = new SyndFeedInput();

		XmlReader xmlr = new XmlReader(feedUrl);

		SyndFeed feed = input.build(xmlr);

		System.out.println(feed);

		rssEntries = feed.getEntries();
		
		
		
		return rssEntries;

	}
	
	public SyndEntryImpl getLatestEntry() throws IllegalArgumentException, IOException, FeedException{
		List<SyndEntryImpl> entries = getFeed();
		SyndEntryImpl syndEntry = entries.get(0);
		return syndEntry;
	}

	public void SaveEmotion(int emotion, String uri) throws TopLevelException {
		Derbymanager.getInstance().saveEmotion(emotion, uri);
	}

	public int getMyFeelingToday(){
		
		int tH = 0;
		int tM = 0;
		int tS = 0;
		
		tH = Derbymanager.getInstance().getTodayFeeling(2);
		tM = Derbymanager.getInstance().getTodayFeeling(1);
		tS = Derbymanager.getInstance().getTodayFeeling(0);
		
		return 0;
	}
	
}
