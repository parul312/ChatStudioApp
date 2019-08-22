package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatStudioService {

	@Autowired
	private ChatStudioRepository chatStudioRepository;

	// method to add a single message onto the database
	public void addMessage(MessageDetails message) throws Exception {
		chatStudioRepository.save(message);
	}

	// method to add a group message onto the database
	public void addMessageToGroup(String sender, String groupName, int groupId, MessageDetails message)
			throws Exception {
		MessageDetails messageDetail = null;

		List<UserGroup> userGroupList = new ArrayList<UserGroup>();
		userGroupList = chatStudioRepository.getUserGroupDetails(groupName, groupId);

		for (UserGroup userGroup : userGroupList) {
			messageDetail = new MessageDetails();
			messageDetail.setSender(new UserDetails(sender, "", "", 0, "", "", ""));
			messageDetail.setReceiver(new UserDetails(userGroup.getId().getUserName(), "", "", 0, "", "", ""));
			messageDetail.setMessageContent(message.getMessageContent());
			chatStudioRepository.save(messageDetail);
		}
	}

	// method to delete one message from the database
	public void deleteMessage(int messageId) {
		chatStudioRepository.delete(messageId);
	}

	// method to delete complete conversation between sender and receiver from the
	// database
	public void deleteConversation(String sender, String receiver) {
		chatStudioRepository.deleteConversation(sender, receiver);
	}

	// method to retrieve all sent messages of a particular userName
	public List<MessageDetails> getSentMessages(String username) {
		List<MessageDetails> sentMessagesList = new ArrayList<>();
		sentMessagesList = chatStudioRepository.getSentMessages(username);
		return sentMessagesList;
	}

	// method to retrieve all received messages of a particular userName
	public List<MessageDetails> getReceivedMessages(String username) {
		List<MessageDetails> receivedMessagesList = new ArrayList<>();
		receivedMessagesList = chatStudioRepository.getReceivedMessages(username);
		return receivedMessagesList;
	}

	// method to create a group
	public void createGroup(String userName, String groupName) {
		UserGroupId userGroupId = new UserGroupId();
		UserGroup userGroup = new UserGroup();
		userGroup.setAdminInd("Y");
		userGroupId.setGroupName(groupName);
		userGroupId.setUserName(userName);
		userGroup.setId(userGroupId);
		chatStudioRepository.createGroup(userGroup);
	}

	// method to add user to a group
	public void addUserToGroup(String userName, String groupName, String adminUser, int groupId, String adminInd) {
		UserGroup userGroup = new UserGroup();
		UserGroupId userGroupId = new UserGroupId();
		userGroup.setAdminInd(adminInd);
		userGroupId.setGroupName(groupName);
		userGroupId.setUserName(userName);
		userGroupId.setGroupId(groupId);
		userGroup.setId(userGroupId);
		chatStudioRepository.updateUserGroup(userGroup);
	}

	// method to move user from a group
	public void removeUserFromGroup(String userName, String groupName, String adminUser, int groupId, String adminInd) {
		UserGroupId userGroupId = new UserGroupId();
		userGroupId.setGroupId(groupId);
		userGroupId.setGroupName(groupName);
		userGroupId.setUserName(userName);
		chatStudioRepository.removeUserFromGroup(userGroupId);
	}

	// method to assign admin role to user
	public void assignAdminRoleToUser(String userName, String groupName, String adminUser, int groupId,
			String adminInd) {
		UserGroup userGroup = new UserGroup();
		UserGroupId userGroupId = new UserGroupId();
		userGroup.setAdminInd(adminInd);
		userGroupId.setGroupName(groupName);
		userGroupId.setUserName(userName);
		userGroupId.setGroupId(groupId);
		userGroup.setId(userGroupId);
		chatStudioRepository.updateUserGroup(userGroup);
	}

}
