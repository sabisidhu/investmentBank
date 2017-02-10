package civilianBank.repository.bank;

import org.springframework.stereotype.Repository;

import civilianBank.entity.bank.DistrictEntity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by BS23 on 5/27/14.
 */
@Repository
public class DistrictRepository {
	@PersistenceContext
	EntityManager entityManager;

	public DistrictEntity getDistrictEntityById(long id) {
		Query query = entityManager.createQuery("From DistrictEntity WHERE  id = :id And isActive = :isActive");
		query.setParameter("isActive", 1);
		query.setParameter("id", id);
		return (DistrictEntity) query.getSingleResult();
	}

	public DistrictEntity getDistrictEntityByName(String name) {
		Query query = entityManager
				.createQuery("From DistrictEntity WHERE  categoryName = :name And isActive = :isActive");
		query.setParameter("isActive", 1);
		query.setParameter("name", name);
		return (DistrictEntity) query.getSingleResult();
	}

	public List<DistrictEntity> getAllDistrictList() {
		Query query = entityManager.createQuery("From DistrictEntity WHERE isActive = :isActive order by name");
		query.setParameter("isActive", 1);
		return query.getResultList();
	}

	// public void saveDistrict(List<Router> xml) {
	// XStream xstream = new XStream();
	// xstream.alias("router", Router.class);
	//
	// this.createProcedure();
	//
	// Query query = entityManager.createSQLQuery("CALL
	// SAVE_DIS_BANK_BRANCH(:xmlFile)")
	// .setParameter("xmlFile", xstream.toXML(xml));
	// query.executeUpdate();
	//
	// }

	public void createProcedure() {
		Query query1 = entityManager
				.createNativeQuery("SELECT COUNT(*) FROM USER_PROCEDURES WHERE object_name = 'SAVE_DIS_BANK_BRANCH'");
		int isProcedureExist = ((BigDecimal) query1.getResultList()).intValue();
		if (isProcedureExist == 0) {
			String q1 = "CREATE OR REPLACE PROCEDURE SAVE_DIS_BANK_BRANCH(xmlFile IN CLOB) AS \n"
					+ "x XMLType \\:= XMLType(xmlFile);\n" + "temp INTEGER;\n" + "dis_id INTEGER;\n"
					+ "bank_id INTEGER;\n" + "BEGIN\n" + "FOR r IN (\n" + "SELECT"
					+ " ExtractValue(Value(p),'/router/districtCode/text()') as districtCode\n"
					+ ",ExtractValue(Value(p),'/router/districtName/text()') as districtName\n"
					+ ",ExtractValue(Value(p),'/router/branchCode/text()') as branchCode\n"
					+ ",ExtractValue(Value(p),'/router/branchName/text()') as branchName\n"
					+ ",ExtractValue(Value(p),'/router/bankCode/text()') as bankCode\n"
					+ ",ExtractValue(Value(p),'/router/bankName/text()') as bankName\n"
					+ ",ExtractValue(Value(p),'/router/routingNo/text()') as routingNo\n"
					+ ",ExtractValue(Value(p),'/router/solId/text()') as solId\n"
					+ "FROM TABLE(XMLSequence(Extract(x,'/list/router'))) p\n" + ")LOOP\n"
					+ "SELECT COUNT(CODE) INTO temp FROM DISTRICT WHERE CODE = r.districtCode;\n" + "IF temp = 0 THEN\n"
					+ "INSERT INTO DISTRICT (ID,CODE,NAME,IS_ACTIVE) VALUES(DISTRICT_ID.NEXTVAL,r.districtCode,r.districtName,1);\n"
					+ "END IF;\n" + "SELECT COUNT(CODE) INTO temp FROM BANK WHERE CODE = r.bankCode;\n"
					+ "IF temp = 0 THEN\n"
					+ "INSERT INTO BANK (ID,CODE,NAME,IS_ACTIVE) VALUES(BANK__ID.NEXTVAL,r.bankCode,r.bankName,1);\n"
					+ "END IF;\n"
					+ "SELECT COUNT(CODE) INTO temp FROM BANK_BRANCH WHERE ROUTING_NUMBER = r.routingNo;\n"
					+ "IF temp = 0 THEN\n" + "SELECT ID INTO dis_id FROM DISTRICT WHERE CODE = r.districtCode;\n"
					+ "SELECT ID INTO bank_id FROM BANK WHERE CODE = r.bankCode;\n"
					+ "INSERT INTO BANK_BRANCH (ID,DISTRICT_ID,BANK_ID, CODE,NAME,ROUTING_NUMBER,SOL_ID,ISREMOVE) VALUES(BANK_BRANCH__ID.NEXTVAL,dis_id,bank_id,r.branchCode,r.branchName,r.routingNo,r.solId,0);\n"
					+ "ELSE\n" + "SELECT ID INTO dis_id FROM DISTRICT WHERE CODE = r.districtCode;\n"
					+ "SELECT ID INTO bank_id FROM BANK WHERE CODE = r.bankCode;\n"
					+ "UPDATE BANK_BRANCH SET  CODE=r.branchCode,NAME = r.branchName,SOL_ID = r.solId WHERE ROUTING_NUMBER = r.routingNo;\n"
					+ "END IF;\n" + "END LOOP;\n" + "END;\n";

			Query query = entityManager.createNativeQuery(q1);
			query.executeUpdate();
		}

	}

	// public void saveDistrictHiber(List<Router> routers)
	// {
	// this.createProcedure();
	//
	// }

	public List<DistrictEntity> getDistrictForPaging(int pageIndex, int itemAmount, String districtName) {
		int startPoint = (pageIndex - 1) * itemAmount;
		Query query = entityManager.createQuery(
				"FROM DistrictEntity WHERE isActive = :isActive AND lower(name) LIKE lower(:districtName) order by name");
		query.setParameter("districtName", "%" + districtName + "%");
		query.setParameter("isActive", 1);
		query.setFirstResult(startPoint);
		query.setMaxResults(itemAmount);
		return query.getResultList();
	}

	public int getTotalDistrictNumber(String districtName) {
		Query query = entityManager.createQuery(
				"select count(*) FROM DistrictEntity WHERE isActive = :isActive AND lower(name) LIKE lower(:districtName)");
		query.setParameter("districtName", "%" + districtName + "%");
		query.setParameter("isActive", 1);
		int result1 = ((Long) query.getSingleResult()).intValue();
		return result1;
	}
}
