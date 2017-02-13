package com.civilianBank.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.civilianBank.notification.model.ConversationEntity;
import com.civilianBank.notification.model.NotificationEntity;
import com.civilianBank.notification.model.NotificationViewModel;
import com.civilianBank.notification.model.SystemUserEntity;
import com.civilianBank.notification.repository.ConversationRepository;
import com.civilianBank.notification.repository.NotificationRepository;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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

	public NotificationViewModel getNotificationViewModelFromNotificationEntity(NotificationEntity notificationEntity)
    {
    	RestTemplate restTemplate = new RestTemplate();
        NotificationViewModel notificationViewModel=new NotificationViewModel();

        notificationViewModel.setReferenceTableId(notificationEntity.getRefTableId());
        notificationViewModel.setNotificationId(notificationEntity.getNotificationTypeId());
        List<ConversationEntity> conversationEntities = notificationEntity.getConversationEntities();
//        Object object = CollectionUtils.find(conversationEntities, new Predicate() {
//            @Override
//            public boolean evaluate(Object ob) {
//                ConversationEntity conversationEntity =(ConversationEntity) ob;
//                return conversationEntity.getStatus()== ConversationStatusEnum.Active.getType(); //'A'=conversation Active
//            }
//        });
        //ConversationEntity conversationEntity =(ConversationEntity) object;
        ConversationEntity conversationEntityObject = new ConversationEntity();
        for(ConversationEntity object : conversationEntities){
        	if(object.getStatus()=='Y'){
        	conversationEntityObject = object;
        	break;
        }
        
        
        ConversationEntity conversationEntity =conversationEntityObject;
        notificationViewModel.setConversationId(conversationEntity.getId());
        notificationViewModel.setMessageBody(conversationEntity.getMessage());
        notificationViewModel.setUrl(notificationEntity.getUrl());
        notificationViewModel.setConversationDate(conversationEntity.getConversationDate());
        int fromUserId=conversationEntity.getFromUserId();
        SystemUserEntity systemUserEntity = restTemplate.getForObject("fromUserId", SystemUserEntity.class);
        notificationViewModel.setSenderName(systemUserEntity.getUserShortName());
        notificationViewModel.setSubtypeId(notificationEntity.getNotificationSubType());
        //SimpleDateFormat fmt = new SimpleDateFormat("dd MM yyyy");
        notificationViewModel.setConversationDateAsString(conversationEntity.getConversationDate()== null ? "No Date" : DateFormat.getDateTimeInstance().format(conversationEntity.getConversationDate()).toString());
        return notificationViewModel;
    }

	public List<NotificationViewModel> getNotificationListFromNotificationEntityList(
			List<NotificationEntity> notificationEntities) {
		List<NotificationViewModel> notificationViewModels = new ArrayList<NotificationViewModel>();
		for (NotificationEntity notificationEntity : notificationEntities) {
			NotificationViewModel notificationViewModel = getNotificationViewModelFromNotificationEntity(
					notificationEntity);
			notificationViewModels.add(notificationViewModel);
		}
		return notificationViewModels;
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

	public void edit(int refTableId, int checkerId, int notificationTypeId, String controllerName, String message) {
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

		String notificationTypeName = NotificationTypeEnum.getNameByValue(notificationTypeId);
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

	public void removeReject(int id) {
		ConversationEntity cTable = this.getConversationById(id);
		cTable.setStatus(ConversationStatusEnum.Close.getType());
		this.updateConversationEntity(cTable);
		NotificationEntity notificationEntity = this.getNotificationEntityById(cTable.getNotificationId());
		notificationEntity.setStatus(NotificationStatusEnum.Close.getType());// C=
																				// open
		this.updateNotificationEntity(notificationEntity);
	}

	public PreviousNextNotification getPreviousAndNextNotificationNumber(NotificationEntity n,
			ConversationEntity conversationEntity) {
		PreviousNextNotification previousNextNotification = new PreviousNextNotification();
		List<ConversationEntity> conversationEntities = this.getConversationEntityForAUser(
				currentUserInfoService.getCurrentUserId(), n.getNotificationTypeId(), n.getStatus(),
				n.getNotificationSubType());
		int totalLength = conversationEntities.size();
		int index = this.getListIndex(conversationEntities, conversationEntity);

		if (index > 1) {
			ConversationEntity conversationEntity2 = conversationEntities.get(index - 2);
			NotificationEntity notificationEntity = this
					.getNotificationEntityById(conversationEntity2.getNotificationId());
			previousNextNotification.setPreviousId(conversationEntity2.getId());
			previousNextNotification.setPreviousUrl(notificationEntity.getUrl());

		} else {
			previousNextNotification.setPreviousId(-1);
		}
		if (index < totalLength) {
			ConversationEntity conversationEntity3 = conversationEntities.get(index);
			NotificationEntity notificationEntity = this
					.getNotificationEntityById(conversationEntity3.getNotificationId());
			previousNextNotification.setNextId(conversationEntity3.getId());
			previousNextNotification.setNextUrl(notificationEntity.getUrl());

		} else {
			previousNextNotification.setNextId(-1);
		}

		return previousNextNotification;
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

	public List<NotificationModel> getAllNotificationForAUser(int currentSystemUser) {
		return notificationRepository.getAllNotificationForAUser(currentSystemUser);
	}

	public int getNumberOfNotificationForAUserOfASubType(int systemUserId, int notificationTypeId,
			int notificationSubTypeId, char status) {
		return notificationRepository.getNumberOfNotificationForAUserOfASubType(systemUserId, notificationTypeId,
				notificationSubTypeId, status);
	}

	// public <T extends Object> T checkIsDeleteReqAlreadySent(String
	// columnName, T table) {
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
