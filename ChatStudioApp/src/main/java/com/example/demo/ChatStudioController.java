package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Controller with the Resources 
@RestController
public class ChatStudioController {

	@Autowired
	private ChatStudioService messageService;

	// Controller method to get all the sent messages of a user
	@RequestMapping(method = RequestMethod.GET, value = "/chatStudio/users/{userName}/sentMessages")
	public List<MessageDetails> getAllSentMessages(@PathVariable String userName) {
		return messageService.getSentMessages(userName);
	}

	// Controller method to get all the received messages of a user
	@RequestMapping(method = RequestMethod.GET, value = "/chatStudio/users/{userName}/receivedMessages")
	public List<MessageDetails> getAllReceivedMessages(@PathVariable String userName) {
		return messageService.getReceivedMessages(userName);
	}

	// Controller method to send a message from one user to another
	// One to One Chat
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{sender}/sendMessage/{receiver}")
	public void oneToOneChat(@RequestBody MessageDetails message, @PathVariable String sender,
			@PathVariable String receiver) throws Exception {
		message.setSender(new UserDetails(sender, "", "", 0, "", "", ""));
		message.setReceiver(new UserDetails(receiver, "", "", 0, "", "", ""));
		message.setMessageContent(message.getMessageContent());
		messageService.addMessage(message);
	}

	// Controller method to create a group of users for group chat
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{userName}/createGroup/{groupName}")
	public void createGroup(@PathVariable String userName, @PathVariable String groupName) throws Exception {
		messageService.createGroup(userName, groupName);
	}

	// Controller method to add user to a group
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{userName}/{adminUser}/addUserToGroup/{groupName}/{groupId}/{adminInd}")
	public void addUserToGroup(@PathVariable String userName, @PathVariable String groupName,
			@PathVariable String adminUser, @PathVariable int groupId, @PathVariable String adminInd) throws Exception {
		messageService.addUserToGroup(userName, groupName, adminUser, groupId, adminInd);
	}

	// Controller method to remove user from a group
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{userName}/{adminUser}/removeUserFromGroup/{groupName}/{groupId}/{adminInd}")
	public void removeUserFromGroup(@PathVariable String userName, @PathVariable String groupName,
			@PathVariable String adminUser, @PathVariable int groupId, @PathVariable String adminInd) throws Exception {
		messageService.removeUserFromGroup(userName, groupName, adminUser, groupId, adminInd);
	}

	// Controller method to assign admin role to a user
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{userName}/{adminUser}/assignUserRoleAsAdmin/{groupName}/{groupId}/{adminInd}")
	public void assignUserRoleAsAdmin(@PathVariable String userName, @PathVariable String groupName,
			@PathVariable String adminUser, @PathVariable int groupId, @PathVariable String adminInd) throws Exception {
		messageService.assignAdminRoleToUser(userName, groupName, adminUser, groupId, adminInd);
	}

	// Controller method to send a message from one user to group of users
	@RequestMapping(method = RequestMethod.POST, value = "/chatStudio/users/{sender}/oneToGroupChat/{groupName}/{groupId}")
	public void oneToGroupChat(@RequestBody MessageDetails message, @PathVariable String sender,
			@PathVariable String groupName, @PathVariable int groupId) throws Exception {
		messageService.addMessageToGroup(sender, groupName, groupId, message);
	}

	// Delete a message by its messageID & userName
	@RequestMapping(method = RequestMethod.DELETE, value = "/chatStudio/users/{userName}/deleteMessage/{messageId}")
	public void deleteMessage(@PathVariable String userName, @PathVariable int messageId) {
		messageService.deleteMessage(messageId);
	}

	// Delete a complete conversation of sender and receiver
	@RequestMapping(method = RequestMethod.DELETE, value = "/chatStudio/users/{sender}/deleteConversation/{receiver}")
	public void deleteConversation(@PathVariable String sender, @PathVariable String receiver) {
		messageService.deleteConversation(sender, receiver);
	}

}
