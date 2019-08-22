package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Object class that stores the information of the message
@Table(name="message_details")
@Entity
public class MessageDetails {

	//Autoincremented ID
	@Id
	@GeneratedValue
	private int messageId;
	
	@Lob
	private String messageContent;
	
	//Member variable, mapped with the userName of UserDetails class
	@ManyToOne
	private UserDetails sender;
	
	//Member variable, mapped with the userName of UserDetails class
	@ManyToOne
	private UserDetails receiver;

	public MessageDetails(int messageId, String messageContent, String sender, String receiver) {
		super();
		this.messageId = messageId;
		this.messageContent = messageContent;
		
		this.sender = new UserDetails(sender,"","",0,"","","");
		this.receiver = new UserDetails(receiver,"","",0,"","","");
	}
	
	

	public MessageDetails() {
		super();
	}



	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	public UserDetails getSender() {
		return sender;
	}

	public void setSender(UserDetails sender) {
		this.sender = sender;
	}

	public UserDetails getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDetails receiver) {
		this.receiver = receiver;
	}

}
