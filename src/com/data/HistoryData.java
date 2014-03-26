package com.data;

public class HistoryData {
	private int mPageRank, mAlexaRank;
	private String mURL;
	
	public HistoryData(String URL, int pageRank, int alexaRank) {
		mURL = URL;
		mPageRank = pageRank;
		mAlexaRank = alexaRank;
	}
	
	public int getPageRank() {
		return mPageRank;
	}
	
	public int getAlexaRank() {
		return mAlexaRank;
	}
	
	public String getURL() {
		return mURL;
	}
	
	public String toString() {
		return mURL + " | PageRank=" +  mPageRank + " | AlexaRank=" + mAlexaRank + "\n";
	}
}
