package com.cg.pos.dto;
/**
 * bean class for Customer
 * @author Prakhar
 *
 */
public class CustomerDetails {
	private String customerId;
	private String customerName;
	private	String contactNo;
	private	String gender;
	private	String email;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String custId) {
		this.customerId = custId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "CustomerDetails [customerId=" + customerId + ", customerName=" + customerName + ", contactNo="
				+ contactNo + ", gender=" + gender + ", email=" + email + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
