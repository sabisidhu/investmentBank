package com.civilianBank.notification.repository;

import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.civilianBank.notification.model.ConversationEntity;
import com.civilianBank.notification.model.NotificationEntity;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 */
@Repository
public class NotificationRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	public NotificationEntity getNotificationById(int id){
		return entityManager.find(NotificationEntity.class, id);
		
	}

	public int create(NotificationEntity entity) {
		entityManager.persist(entity);
		return entity.getId();
	}

	public NotificationEntity update(NotificationEntity notificationEntity) {
		NotificationEntity entity = new NotificationEntity();
		entity = entityManager.find(NotificationEntity.class, notificationEntity.getId());
		entity = notificationEntity;
		return entity;
	}

	public int getTotalMessageNumber(int systemUserId) {
		int totalMessage = 0;
		Query query = entityManager.createQuery(
				"select count(*) FROM NotificationEntity WHERE (FROM_USER_ID =:systemUserId AND STATUS=:rejectStatus)OR(TO_USER_ID =:systemUserId AND STATUS=:openStatus)");
		query.setParameter("systemUserId", systemUserId);
		query.setParameter("rejectStatus", 'N');
		query.setParameter("openStatus", 'N');
		totalMessage = ((Long) query.getSingleResult()).intValue();
		return totalMessage;
	}

	public List<NotificationEntity> getAllNotificationsForAUser(int systemUserId) {

		Query query = entityManager.createQuery(
				"FROM NotificationEntity WHERE (FROM_USER_ID =:systemUserId AND STATUS=:rejectStatus)OR(TO_USER_ID =:systemUserId AND STATUS=:openStatus)");
		query.setParameter("systemUserId", systemUserId);
		query.setParameter("rejectStatus", 'N');
		query.setParameter("openStatus", 'N');
		return query.getResultList();
	}

	public List<NotificationEntity> getTypedNotificationEntitiesForAUser(int systemUserId, int notificationType,
			char status, int notificationSubType) {

		Query query = null;
		if (status == 'N') {
			query = entityManager.createQuery(
					"FROM NotificationEntity WHERE TO_USER_ID = :systemUserId AND STATUS= :openStatus AND NOTIFICATION_TYPE_ID = :notificationType AND NOTIFICATION_SUB_TYPE = :notificationSubType order by ID DESC");
			query.setParameter("systemUserId", systemUserId);
			query.setParameter("openStatus", 'N');
			query.setParameter("notificationType", notificationType);
			query.setParameter("notificationSubType", notificationSubType);
		} else if (status == 'N') {
			query = entityManager.createQuery(
					"FROM NotificationEntity WHERE FROM_USER_ID = :systemUserId AND STATUS= :rejectStatus AND NOTIFICATION_TYPE_ID = :notificationType AND NOTIFICATION_SUB_TYPE = :notificationSubType order by ID DESC");
			query.setParameter("systemUserId", systemUserId);
			query.setParameter("rejectStatus", 'N');
			query.setParameter("notificationType", notificationType);
			query.setParameter("notificationSubType", notificationSubType);
		}

		return query.getResultList();
	}

	public List<ConversationEntity> getTypedConversationEntitiesForAUser(int currentUserId, int notificationType,
			char status, int notificationSubType) {
		Query query = null;
		if (status == 'N') {
			query = entityManager.createQuery(
					"FROM ConversationEntity WHERE STATUS= :conversationStatus AND NOTIFICATION_ID in (select n.id from NotificationEntity n WHERE TO_USER_ID = :systemUserId AND STATUS= :openStatus AND NOTIFICATION_TYPE_ID = :notificationType And NOTIFICATION_SUB_TYPE = :notificationSubType) order by ID DESC");
			query.setParameter("systemUserId", currentUserId);
			query.setParameter("openStatus", 'N');
			query.setParameter("conversationStatus", 'Y');
			query.setParameter("notificationType", notificationType);
			query.setParameter("notificationSubType", notificationSubType);
		} else if (status == 'N') {
			query = entityManager.createQuery(
					"FROM ConversationEntity WHERE STATUS= :conversationStatus AND NOTIFICATION_ID in (select n.id FROM NotificationEntity n WHERE FROM_USER_ID = :systemUserId AND STATUS= :rejectStatus AND NOTIFICATION_TYPE_ID = :notificationType And NOTIFICATION_SUB_TYPE = :notificationSubType) order by ID DESC");
			query.setParameter("systemUserId", currentUserId);
			query.setParameter("rejectStatus", 'N');
			query.setParameter("conversationStatus", 'Y');
			query.setParameter("notificationType", notificationType);
			query.setParameter("notificationSubType", notificationSubType);
		}

		return query.getResultList();
	}

	// public List<NotificationEntity> getAllNotificationForAUser(int
	// currentSystemUser) {
	// List<NotificationEntity> resultList = entityManager.createQuery("select
	// NOTIFICATION_TYPE_ID as notificationStatusId,STATUS as status,
	// count(NOTIFICATION_TYPE_ID) as numberOfNotification From NOTIFICATION
	// WHERE (FROM_USER_ID ="+currentSystemUser+" AND STATUS='R')OR(TO_USER_ID
	// ="+currentSystemUser+" AND STATUS='O') group By
	// NOTIFICATION_TYPE_ID,STATUS ORDER By numberOfNotification DESC ")
	// .addScalar("notificationStatusId", StandardBasicTypes.INTEGER)
	// .addScalar("status", StandardBasicTypes.CHARACTER)
	// .addScalar("numberOfNotification", StandardBasicTypes.INTEGER)
	// .setResultTransformer(new
	// AliasToBeanResultTransformer(NotificationModel.class))
	// .list();
	// return resultList;
	// }

	public int getNumberOfNotificationForAUserOfASubType(int systemUserId, int notificationTypeId,
			int notificationSubType, char status) {

		int totalNotifications = 0;
		Query query = null;
		if (status == 'N') {
			query = entityManager.createQuery(
					"select count(*) FROM NotificationEntity WHERE TO_USER_ID = :systemUserId AND STATUS= :openStatus AND NOTIFICATION_TYPE_ID = :notificationTypeId AND NOTIFICATION_SUB_TYPE = :notificationSubType");
			query.setParameter("systemUserId", systemUserId);
			query.setParameter("openStatus", 'N');
			query.setParameter("notificationTypeId", notificationTypeId);
			query.setParameter("notificationSubType", notificationSubType);
			totalNotifications = ((Long) query.getSingleResult()).intValue();

			return totalNotifications;
		}

		else if (status == 'N') {
			query = entityManager.createQuery(
					"select count(*) FROM NotificationEntity WHERE FROM_USER_ID = :systemUserId AND STATUS= :rejectStatus AND NOTIFICATION_TYPE_ID = :notificationTypeId AND NOTIFICATION_SUB_TYPE = :notificationSubType");
			query.setParameter("systemUserId", systemUserId);
			query.setParameter("rejectStatus", 'N');
			query.setParameter("notificationTypeId", notificationTypeId);
			query.setParameter("notificationSubType", notificationSubType);
			totalNotifications = ((Long) query.getSingleResult()).intValue();

			return totalNotifications;
		}

		return 0;

	}

	// public <T extends Object> T checkIsDeleteReqAlreadySent(String columnName
	// ,int columnValue ,T table) {
	// Session session = getSession();
	// String queryString="FROM "+table.getClass().getName()+" WHERE
	// "+columnName+" = :columnValue AND createOrUpdateStatus =
	// :createOrUpdateStatus AND approvalDate is null ";
	// Query query = session.createQuery(queryString);
	// query.setParameter("columnValue", columnValue);
	// query.setParameter("createOrUpdateStatus",
	// CreateOrUpdateFlagEnum.Update.getCode());
	// query.setMaxResults(1);
	// return type.cast(query.uniqueResult());
	// }
	public <T extends Object> T checkIsDeleteReqAlreadySent(String columnName, long columnValue, Class<T> type) {
		String queryString = "FROM " + type.getName() + " WHERE " + columnName + " = " + columnValue
				+ " AND createOrUpdateStatus = :createOrUpdateStatus  AND approvalDate is null ";
		Query query = entityManager.createQuery(queryString);
		// query.setParameter("columnValue", columnValue);
		query.setParameter("createOrUpdateStatus", 'N');
		query.setMaxResults(1);
		return type.cast(query.getSingleResult());
	}

	public String getNotificationStatus(int id) {
		Query query = entityManager.createQuery(
				"SELECT ne.status FROM NotificationEntity ne WHERE ne.refTableId = :id AND ne.status != :status");
		query.setParameter("id", id);
		query.setParameter("status", 'N');
		query.setMaxResults(1);
		Object result = query.getSingleResult();
		if (result != null) {
			return result.toString();
		}
		return "";
	}
}
