package com.main;

public abstract class Operator {
	protected String mFileName;
	public Operator(String fileName) {
		mFileName = fileName;
	}
	public abstract void getPageRank();
}
