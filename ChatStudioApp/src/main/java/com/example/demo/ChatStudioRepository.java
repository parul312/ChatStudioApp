package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ChatStudioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<MessageDetails> getSentMessages(String username) {

		List<MessageDetails> messageDetails = new ArrayList<MessageDetails>();

		Query query = entityManager
				.createNativeQuery("select * from message_details where sender_username=:sender_username");

		query.setParameter("sender_username", username);

		messageDetails = (List<MessageDetails>) query.getResultList();

		return messageDetails;
	}

	public List<MessageDetails> getReceivedMessages(String username) {

		List<MessageDetails> messageDetails = new ArrayList<MessageDetails>();

		Query query = entityManager
				.createNativeQuery("select * from message_details where receiver_username=:receiver_username");

		query.setParameter("receiver_username", username);

		messageDetails = (List<MessageDetails>) query.getResultList();

		return messageDetails;
	}

	public List<UserGroup> getUserGroupDetails(String groupName, int groupId) {

		List<UserGroup> userGroupList = new ArrayList<UserGroup>();
		UserGroupId userGroupId = new UserGroupId();
		UserGroup userGroup = null;
		Query query = entityManager
				.createNativeQuery("select * from user_group where group_id=:group_id and group_name=:group_name");

		query.setParameter("group_id", groupId);
		query.setParameter("group_name", groupName);

		List<Object[]> objectList = query.getResultList();

		for (Object[] obj : objectList) {
			userGroup = new UserGroup();
			userGroupId = new UserGroupId();
			userGroupId.setGroupId((int) obj[0]);
			userGroupId.setGroupName((String) obj[1]);
			userGroupId.setUserName((String) obj[2]);
			userGroup.setId(userGroupId);
			userGroup.setAdminInd((String) obj[3]);
			userGroupList.add(userGroup);
		}

		return userGroupList;

	}

	public void save(MessageDetails messageDetails) {

		entityManager.persist(messageDetails);
	}

	public void delete(int messageId) {

		MessageDetails messageDetails = entityManager.find(MessageDetails.class, messageId);
		entityManager.remove(messageDetails);
	}

	public void deleteConversation(String sender, String receiver) {

		Query query = entityManager.createNativeQuery(
				"delete from message_details where receiver_username=:receiver_username and sender_username=:sender_username");

		query.setParameter("receiver_username", receiver);
		query.setParameter("sender_username", sender);
		query.executeUpdate();
	}

	public void createGroup(UserGroup userGroup) {

		entityManager.persist(userGroup);
	}

	public void updateUserGroup(UserGroup userGroup) {

		entityManager.merge(userGroup);
	}

	public void removeUserFromGroup(UserGroupId userGroupId) {

		UserGroup userGroup = entityManager.find(UserGroup.class, userGroupId);

		entityManager.remove(userGroup);
	}

}
