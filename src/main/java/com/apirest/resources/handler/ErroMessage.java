package com.apirest.resources.handler;

import java.io.Serializable;

public class ErroMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgUser;
	private String msgDev;
	
	public ErroMessage(String msgUser, String msgDev) {
		this.msgUser = msgUser;
		this.msgDev = msgDev;
	}

	public ErroMessage() {}

	/**
	 * @return the msgUser
	 */
	public String getMsgUser() {
		return msgUser;
	}

	/**
	 * @param msgUser the msgUser to set
	 */
	public void setMsgUser(String msgUser) {
		this.msgUser = msgUser;
	}

	/**
	 * @return the msgDev
	 */
	public String getMsgDev() {
		return msgDev;
	}

	/**
	 * @param msgDev the msgDev to set
	 */
	public void setMsgDev(String msgDev) {
		this.msgDev = msgDev;
	}
}