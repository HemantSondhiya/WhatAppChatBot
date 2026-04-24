package com.example.whatsappchatbot.model;

public class OutgoingMessage {

	private String reply;

	public OutgoingMessage(String reply) {
		this.reply = reply;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
}
