package civilianBank.repository.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import civilianBank.entity.bank.BankBranchEntity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Parshotam on 5/27/15.
 */
@Repository
public class BankBranchRepository {

	@Value("${cityBank.bankCode}")
	private String cityBankCode;
	Logger log = LoggerFactory.getLogger(BankBranchRepository.class);
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * @Purpose Get Bank Branch by Bank code.
	 * @param branchcode
	 * @return Bank Branch entity
	 */
	public BankBranchEntity getBankBranchByBranchCode(String branchCode) {
		// TODO Exception handling
		log.debug("BankBranchRepository.getBankBranchByBranchCode getting bank branch with branch code :" + branchCode);
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity br WHERE br.branchCode = :branchCode AND br.bank.bankCode = :bankCode");
		query.setParameter("branchCode", branchCode);
		query.setParameter("bankCode", cityBankCode);
		if ((BankBranchEntity) query.getSingleResult() == null) {
			log.warn("BankBranchRepository.getBankBranchByBranchCode bank branch not found with branch code :"
					+ branchCode);
		}
		log.info(
				"BankBranchRepository.getBankBranchByBranchCode retrieved bank branch with branch code :" + branchCode);
		return (BankBranchEntity) query.getSingleResult();
	}
	/**
	 * @Purpose Get Bank Branch by Bank code and District ID.
	 * @param branchcode districtid
	 * @return Bank Branch entity
	 */
	public BankBranchEntity getBankBranchByBranchCodeAndDistrictID(String branchCode, long districtId) {
		// TODO Exception handling
		log.debug(
				"BankBranchRepository.getBankBranchByBranchCodeAndDistrictID getting bank branch with branch code and district id :"
						+ branchCode + "  " + districtId);
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity br WHERE br.branchCode = :branchCode AND  br.district.id = :districtId AND br.bank.bankCode = :bankCode");
		query.setParameter("branchCode", branchCode);
		query.setParameter("districtId", districtId);
		query.setParameter("bankCode", cityBankCode);
		if ((BankBranchEntity) query.getSingleResult() == null) {
			log.warn(
					"BankBranchRepository.getBankBranchByBranchCodeAndDistrictID bank branch not found with branch code and district id :"
							+ branchCode + "  " + districtId);
		}
		log.info(
				"BankBranchRepository.getBankBranchByBranchCodeAndDistrictID bank branch retrieved with branch code and district id :"
						+ branchCode + "  " + districtId);
		return (BankBranchEntity) query.getSingleResult();
	}

	public List<BankBranchEntity> getCityBankBranch() {
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity br WHERE br.bank.bankCode = :bankCode AND ISREMOVE = :isRemove AND br.solId != 0 order by lower(br.branchName) asc");
		query.setParameter("bankCode", cityBankCode);
		query.setParameter("isRemove", 0);
		return (List<BankBranchEntity>) query.getResultList();
	}

	public List<BankBranchEntity> getBranchesById(int bankId) {
		Query query = entityManager
				.createQuery("FROM BankBranchEntity WHERE BANK_ID = :bankId AND ISREMOVE = :isRemove");
		query.setParameter("bankId", bankId);
		query.setParameter("isRemove", 0);
		return query.getResultList();
	}

	public BankBranchEntity getBankBranchByBankIdAndCode(int bankId, String branchCode) {
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity WHERE BANK_ID = :bankId AND CODE = :branchCode AND ISREMOVE= :isRemove");
		query.setParameter("branchCode", branchCode);
		query.setParameter("bankId", bankId);
		query.setParameter("isRemove", 0);

		return (BankBranchEntity) query.getSingleResult();
	}

	public List<BankBranchEntity> getBankBranchForPaging(int pageIndex, int itemAmount, String bankBranchName,
			long bankId) {
		int startPoint = (pageIndex - 1) * itemAmount;
		Query query = null;
		if (bankId > 0) {
			query = entityManager.createQuery(
					"FROM BankBranchEntity WHERE ISREMOVE = :isRemove AND  BANK_ID = :bankId AND lower(branchName) LIKE lower(:bankBranchName)  order by branchName");
			query.setParameter("bankBranchName", "%" + bankBranchName + "%");
			query.setParameter("isRemove", 0);
			query.setParameter("bankId", bankId);
		} else {
			query = entityManager.createQuery(
					"FROM BankBranchEntity WHERE ISREMOVE = :isRemove AND lower(branchName) LIKE lower(:bankBranchName)  order by branchName");
			query.setParameter("bankBranchName", "%" + bankBranchName + "%");
			query.setParameter("isRemove", 0);
		}
		query.setFirstResult(startPoint);
		query.setMaxResults(itemAmount);
		return query.getResultList();
	}

	public List<BankBranchEntity> getBankBranchByDistrictIdAndBankCode(String bankCode, long districtId) {
		Query query = null;
		if (districtId > 0) {
			query = entityManager.createQuery(
					"FROM BankBranchEntity br WHERE br.isRemove = :isRemove AND  br.district.id = :districtId AND br.bank.bankCode = :bankCode  order by br.branchName");
			query.setParameter("isRemove", 0);
			query.setParameter("districtId", districtId);
			query.setParameter("bankCode", bankCode);
		} else {
			query = entityManager.createQuery(
					"FROM BankBranchEntity br WHERE br.isRemove = :isRemove  AND br.bank.bankCode = :bankCode  order by br.branchName");
			query.setParameter("isRemove", 0);
			query.setParameter("bankCode", bankCode);
		}

		return query.getResultList();
	}

	public int getTotalBankBranchNumber(String bankBranchName, long bankId) {
		Query query = null;
		if (bankId > 0) {
			query = entityManager.createQuery(
					"select count(*) FROM BankBranchEntity WHERE ISREMOVE = :isRemove AND  BANK_ID = :bankId AND lower(branchName) LIKE lower(:bankBranchName)");
			query.setParameter("bankBranchName", "%" + bankBranchName + "%");
			query.setParameter("isRemove", 0);
			query.setParameter("bankId", bankId);
		} else {
			query = entityManager.createQuery(
					"select count(*) FROM BankBranchEntity WHERE ISREMOVE = :isRemove AND lower(branchName) LIKE lower(:bankBranchName)");
			query.setParameter("bankBranchName", "%" + bankBranchName + "%");
			query.setParameter("isRemove", 0);
		}
		int totalItem = ((Long) query.getSingleResult()).intValue();
		return totalItem;
	}

	public BankBranchEntity getBankBranchEntityBySolId(int solId, String branchCode, long districtId) {
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity br WHERE br.solId = :solId AND br.bank.bankCode = :bankCode AND br.branchCode != :branchCode AND  br.district.id != :districtId");
		query.setParameter("solId", solId);
		query.setParameter("branchCode", branchCode);
		query.setParameter("districtId", districtId);
		query.setParameter("bankCode", cityBankCode);
		return (BankBranchEntity) query.getSingleResult();
	}

	public BankBranchEntity getBankBranchByBranchSolId(int solId) {
		Query query = entityManager.createQuery(
				"FROM BankBranchEntity br WHERE br.solId = :solId AND br.bank.bankCode = :bankCode AND ISREMOVE = :isRemove");
		query.setParameter("solId", solId);
		query.setParameter("isRemove", 0);
		query.setParameter("bankCode", cityBankCode);
		if (query.getSingleResult() == null) {
			return new BankBranchEntity();
		}
		return (BankBranchEntity) query.getSingleResult();
	}
}
