package com.civilianBank.notification.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civilianBank.notification.model.ConversationEntity;
import com.civilianBank.notification.model.NotificationEntity;
import com.civilianBank.notification.model.NotificationViewModel;
import com.civilianBank.notification.repository.ConversationRepository;
import com.civilianBank.notification.repository.NotificationRepository;

/**
 * Created by BS23 on 4/30/14.
 */
@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public void createNotificationEntity(NotificationEntity notificationEntity) {
		notificationRepository.create(notificationEntity);
	}

	public void updateNotificationEntity(NotificationEntity notificationEntity) {
		notificationRepository.update(notificationEntity);
	}

	public void createConversationEntity(ConversationEntity conversationEntity) {
		conversationEntity.setConversationDate(new Date());
		conversationRepository.create(conversationEntity);
	}

	public void updateConversationEntity(ConversationEntity conversationEntity) {

		conversationRepository.update(conversationEntity);
	}

	public int getTotalMessageNumber(int systemUserId) {
		return notificationRepository.getTotalMessageNumber(systemUserId);
	}

	public List<NotificationEntity> getAllNotificationEntitiesForAUser(int systemUserId) {
		List<NotificationEntity> notificationEntities = new ArrayList<NotificationEntity>();
		notificationEntities = notificationRepository.getAllNotificationsForAUser(systemUserId);
		return notificationEntities;
	}

	public List<NotificationEntity> getTypedNotificationEntitiesForAUser(int systemUserId, int notificationType,
			char status, int notificationSubType) {
		return notificationRepository.getTypedNotificationEntitiesForAUser(systemUserId, notificationType, status,
				notificationSubType);
	}



	public NotificationEntity getNotificationEntityById(int id) {
		return notificationRepository.getNotificationById(id);
	}

	// public ConversationEntity getConversationById(int id) {
	// return conversationRepository.get(id);
	// }

	public ConversationEntity getConversationById(int id, int currentUserId) {
		return conversationRepository.getConversationById(id, currentUserId);
	}

	public void createNewConversation(int notificationType, int refTableId, int fromUserId, int toUserId,
			String message, String url) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setNotificationTypeId(notificationType);
		notificationEntity.setRefTableId(refTableId);
		notificationEntity.setStatus('N');// O=
											// open
		notificationEntity.setFromUserId(fromUserId);
		notificationEntity.setToUserId(toUserId);
		notificationEntity.setUrl(url);
		notificationEntity.setNotificationSubType('Y');
		this.createNotificationEntity(notificationEntity);

		ConversationEntity conversationEntity = new ConversationEntity();
		conversationEntity.setNotificationId(notificationEntity.getId());
		conversationEntity.setFromUserId(fromUserId);
		conversationEntity.setToUserId(toUserId);
		conversationEntity.setStatus('Y');
		conversationEntity.setMessage(message);
		this.createConversationEntity(conversationEntity);
	}

	public void rejectByChecker(int id, int currentUserId, String message) {
		ConversationEntity conversationEntity = this.getConversationById(id, currentUserId);
		conversationEntity.setStatus('Y'); // C=Close
											// Old
											// Conversation
		NotificationEntity notificationEntity = this.getNotificationEntityById(conversationEntity.getNotificationId());
		notificationEntity.setStatus('Y'); // R=Rejected
		if (notificationEntity.getNotificationSubType() == 'Y') {
			notificationEntity.setNotificationSubType('Y');
		} else if (notificationEntity.getNotificationSubType() == 'Y') {
			notificationEntity.setNotificationSubType('Y');
		}

		this.updateConversationEntity(conversationEntity);
		this.updateNotificationEntity(notificationEntity);

		ConversationEntity conversationEntity1 = new ConversationEntity();
		conversationEntity1.setNotificationId(notificationEntity.getId());
		conversationEntity1.setFromUserId(currentUserId);
		conversationEntity1.setToUserId(currentUserId);
		conversationEntity1.setStatus('Y');
		conversationEntity1.setMessage(message);
		this.createConversationEntity(conversationEntity1);
	}

	public int updateRejected(int id, int checkerSystemUserId, String message, int currentUserId) {
		ConversationEntity cTable = this.getConversationById(id,currentUserId);
		cTable.setStatus('Y');
		this.updateConversationEntity(cTable);
		NotificationEntity notificationEntity = this.getNotificationEntityById(cTable.getNotificationId());

		notificationEntity.setStatus('Y');
		notificationEntity.setFromUserId(currentUserId);
		notificationEntity.setToUserId(checkerSystemUserId);

		if (notificationEntity.getNotificationSubType() == 'Y') {
			notificationEntity.setNotificationSubType('Y');
		} else if (notificationEntity.getNotificationSubType() == 'Y') {
			notificationEntity.setNotificationSubType('Y');
		}

		this.updateNotificationEntity(notificationEntity);

		ConversationEntity conversationEntity = new ConversationEntity();
		conversationEntity.setNotificationId(notificationEntity.getId());
		conversationEntity.setFromUserId(currentUserId);
		conversationEntity.setToUserId(checkerSystemUserId);
		conversationEntity.setStatus('Y');
		conversationEntity.setMessage(message);
		this.createConversationEntity(conversationEntity);
		return notificationEntity.getRefTableId();
	}

	public void edit(int refTableId, int checkerId, int notificationTypeId, String controllerName, String message, int currentUserId) {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setNotificationTypeId(notificationTypeId); // 5 =
																		// CREATE
																		// Lookup
																		// NOTIFICATION
																		// TYPE

		notificationEntity.setRefTableId(refTableId);
		notificationEntity.setStatus('Y');
		notificationEntity.setFromUserId(currentUserId);
		notificationEntity.setToUserId(checkerId);
		notificationEntity.setUrl(controllerName);

		String notificationTypeName = "Y";
		String notificationType[] = notificationTypeName.split("_");
		if (notificationType[0].equals("Create")) {
			notificationEntity.setNotificationSubType('Y');
		} else if (notificationType[0].equals("Delete")) {
			notificationEntity.setNotificationSubType('Y');
		}
		this.createNotificationEntity(notificationEntity);

		ConversationEntity conversationEntity = new ConversationEntity();
		conversationEntity.setNotificationId(notificationEntity.getId());
		conversationEntity.setFromUserId('Y');
		conversationEntity.setToUserId(checkerId);
		conversationEntity.setStatus('Y');
		conversationEntity.setMessage(message);
		this.createConversationEntity(conversationEntity);
	}



	public int getListIndex(List<ConversationEntity> conversationEntities, ConversationEntity conversationEntity) {

		int index = 0;
		for (ConversationEntity conversationEntity1 : conversationEntities) {
			index += 1;
			if (conversationEntity1.getId() == conversationEntity.getId()) {
				return index;
			}
		}
		return 0;
	}

	public List<ConversationEntity> getConversationEntityForAUser(int currentUserId, int notificationType, char status,
			int notificationSubType) {
		return notificationRepository.getTypedConversationEntitiesForAUser(currentUserId, notificationType, status,
				notificationSubType);
	}

	public List<NotificationViewModel> getNotificationListFromConversationEntityList(
			List<ConversationEntity> conversationEntities) {
		List<NotificationViewModel> notificationViewModels = new ArrayList<NotificationViewModel>();
		for (ConversationEntity conversationEntity : conversationEntities) {
			NotificationViewModel notificationViewModel = this
					.getNotificationViewModelFromConversationEntity(conversationEntity);
			notificationViewModels.add(notificationViewModel);
		}
		return notificationViewModels;
	}

	public NotificationViewModel getNotificationViewModelFromConversationEntity(ConversationEntity conversationEntity) {
		return null;
	}


	public int getNumberOfNotificationForAUserOfASubType(int systemUserId, int notificationTypeId,
			int notificationSubTypeId, char status) {
		return notificationRepository.getNumberOfNotificationForAUserOfASubType(systemUserId, notificationTypeId,
				notificationSubTypeId, status);
	}

	// public <T extends Object> T checkIsDeleteReqAlreadySent(String
	// columnName, T table) {  + 
	// return
	// type.cast(notificationRepository.checkIsDeleteReqAlreadySent(columnName,table));
	// }

	public <T extends Object> T checkIsDeleteReqAlreadySent(String columnName, long columnValue, Class<T> type) {
		return type.cast(notificationRepository.checkIsDeleteReqAlreadySent(columnName, columnValue, type));
	}

	public String getNotificationStatusFromId(int id) {
		return notificationRepository.getNotificationStatus(id);
	}
}
