package com.excilys.formation.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by loic on 04/09/2014.
 */
@JsonAutoDetect
public class ThumbnailResponse implements Serializable{
	@JsonProperty
	private int processed;
	@JsonProperty
	private List<String> thumbnails;

	public ThumbnailResponse() {}

	public ThumbnailResponse(int processed, List<String> thumbnails) {
		this.processed = processed;
		this.thumbnails = thumbnails;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public List<String> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(List<String> thumbnails) {
		this.thumbnails = thumbnails;
	}

	@Override
	public String toString() {
		return "ThumbnailResponse{" +
				"processed=" + processed +
				", thumbnails=" + thumbnails +
				'}';
	}
}
