package org.mdk.jdental.dataobjects;

import java.util.ArrayList;

public class SelectList {
	private ArrayList<String[]> rows = new ArrayList<String[]>();
	private ArrayList<String> labels = new ArrayList<String>();
	public ArrayList<String[]> getRows() {
		return rows;
	}
	public void setRows(ArrayList<String[]> rows) {
		this.rows = rows;
	}
	public ArrayList<String> getLabels() {
		return labels;
	}
	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}
	public SelectList(ArrayList<String[]> rows, ArrayList<String> labels) {
		super();
		this.rows = rows;
		this.labels = labels;
	}
	
	
}
