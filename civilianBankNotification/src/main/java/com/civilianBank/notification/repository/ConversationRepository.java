package com.civilianBank.notification.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.civilianBank.notification.model.ConversationEntity;

/**
 * Created by BS23 on Parshotam.
 */
@Repository
public class ConversationRepository {
	@PersistenceContext
	EntityManager entityManager;
    public ConversationEntity getConversationById(int conId, int currentUserId) {
        Query query = entityManager.createQuery("FROM ConversationEntity WHERE ID = :conId AND TO_USER_ID =:currentUserId AND STATUS=:openStatus");
        query.setParameter("conId", conId);
        query.setParameter("currentUserId", currentUserId);
        query.setParameter("openStatus", "Y");
        return (ConversationEntity)query.getSingleResult();
    }
}
