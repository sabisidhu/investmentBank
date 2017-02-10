package civilianBank.repository.bank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import civilianBank.entity.bank.BankEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by BS23 on 5/16/14.
 */
@Repository
public class BankRepository {

	@Value("${cityBank.bankCode}")
	private String cityBankCode;
	EntityManager entityManager;

	// public BankEntity getAllCityBranch()
	// {
	// Session session =getSession();
	// Query query = session.createQuery("FROM BankEntity bb WHERE bb.bankCode =
	// :cityBankCode order by bankName");
	// query.setParameter("cityBankCode",cityBankCode);
	// return (BankEntity)query.uniqueResult();
	// }

	public List<BankEntity> getAllBankList() {
		Query query = entityManager.createQuery("From BankEntity WHERE isActive = :isActive order by bankName");
		query.setParameter("isActive", 1);
		return query.getResultList();

	}

	public BankEntity getBankEntityById(int id) {
		Query query = entityManager.createQuery("From BankEntity WHERE  id = :id And isActive = :isActive");
		query.setParameter("isActive", 1);
		query.setParameter("id", id);
		return (BankEntity) query.getSingleResult();

	}

	public List<BankEntity> getBankForPaging(int pageIndex, int itemAmount, String bankName) {
		int startPoint = (pageIndex - 1) * itemAmount;
		Query query = entityManager.createQuery(
				"FROM BankEntity WHERE isActive = :isActive AND lower(bankName) LIKE lower(:bankName) order by bankName");
		query.setParameter("bankName", "%" + bankName + "%");
		query.setParameter("isActive", 1);
		query.setFirstResult(startPoint);
		query.setMaxResults(itemAmount);
		return query.getResultList();

	}

	public int getTotalBankNumber(String bankName) {
		Query query = entityManager.createQuery(
				"select count(*) FROM BankEntity WHERE isActive = :isActive AND lower(bankName) LIKE lower(:bankName)");
		query.setParameter("bankName", "%" + bankName + "%");
		query.setParameter("isActive", 1);
		int result1 = ((Long) query.getSingleResult()).intValue();
		return result1;
	}
}
