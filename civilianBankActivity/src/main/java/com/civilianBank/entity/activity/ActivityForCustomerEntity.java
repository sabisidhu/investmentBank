package com.civilianBank.entity.activity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * created by simran
 *
 */
@Entity
@Table(name="ADMIN_ACTIVITY_CUSTOMER")
public class ActivityForCustomerEntity {
    @Id
    @Column
    @SequenceGenerator(name="ActivityForCustomerEntityIdGenerator", sequenceName="ADMIN_ACTIVITY_CUSTOMER_ID",initialValue=1, allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="ActivityForCustomerEntityIdGenerator")
    
    private long id;
    
    @Column(name="CUSTOMER_ID")
    private int customerId;
    
    @Column(name="CHECKER_ID")
    private int chekerId;
    
    @Column(name="MARKER_ID")
    private int marker_id;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPROVAL_DATE",columnDefinition="date")
	private Date approval_date;
	
	@Column(name="PREVIOUS_DATA")
	private String previous_data;
	
	@Column(name="CHANGE_FIELD_NAME")
	private String change_field_name;
	
	@Column(name="EXECUTED_DATA")
	private String executed_data;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getChekerId() {
		return chekerId;
	}

	public void setChekerId(int chekerId) {
		this.chekerId = chekerId;
	}

	public int getMarker_id() {
		return marker_id;
	}

	public void setMarker_id(int marker_id) {
		this.marker_id = marker_id;
	}

	public Date getApproval_date() {
		return approval_date;
	}

	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}

	public String getPrevious_data() {
		return previous_data;
	}

	public void setPrevious_data(String previous_data) {
		this.previous_data = previous_data;
	}

	public String getChange_field_name() {
		return change_field_name;
	}

	public void setChange_field_name(String change_field_name) {
		this.change_field_name = change_field_name;
	}

	public String getExecuted_data() {
		return executed_data;
	}

	public void setExecuted_data(String executed_data) {
		this.executed_data = executed_data;
	}
	

	
	

}
