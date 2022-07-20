package com.jclemente.devouring.capabilities;

public class SpawnerTaggableDefault implements SpawnerTaggable {
	
	String tag;

	public SpawnerTaggableDefault() {
		this.tag = "";
	}
	
	@Override
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String getTag() {
		return this.tag;
	}

}
