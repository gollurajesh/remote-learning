package com.mouritech.remotelearning.model;

import java.io.Serializable;

public class RequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private int requestId;

	private String description;

	private int status;
	
	private ContentDTO content;

	private UserDTO user;

	private int modifiedBy;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ContentDTO getContent() {
		return content;
	}

	public void setContent(ContentDTO content) {
		this.content = content;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
