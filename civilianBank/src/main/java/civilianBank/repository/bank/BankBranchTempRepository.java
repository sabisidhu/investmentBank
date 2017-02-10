package civilianBank.repository.bank;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import civilianBank.entity.bank.BankBranchTempEntity;

/**
 * Created by BS23 on 5/28/14.
 */
@Repository
public class BankBranchTempRepository {
	@PersistenceContext
	EntityManager entityManager;

    public BankBranchTempEntity getBankBranchTempByBankIdAndCode(int bankId, String branchCode) {
        Query query=entityManager.createQuery("FROM BankBranchTempEntity WHERE BANK_ID = :bankId AND CODE = :branchCode AND CREATE_OR_UPDATE_STATUS < :createOrUpdateStatus  AND APPROVAL_DATE is null");
        query.setParameter("branchCode",branchCode);
        query.setParameter("bankId",bankId);
//        query.setParameter("createOrUpdateStatus", CreateOrUpdateFlagEnum.Close_Create_Conversation);

        return (BankBranchTempEntity)query.getSingleResult();
    }
}
