package com.sampana.login.vo;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * This class is uses for handling user related activity. And VO object will
 * travel b/w different layers.
 * 
 *
 */
public class UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1100616032345579119L;

	/**
	 * Primary key
	 * 
	 * @return
	 */
	private Long userId;

	private String fullName;
	/**
	 * enter email.
	 */
	private String email;

	/**
	 * user password
	 */
	private String password;

	/*
	 * confirm password for user, it should match the password
	 */
	private String confirmPassword;

	/*
	 * phone number of user
	 */
	private String phoneNumber;
	

	private String userName;

	private Date createdDate;
	
	private Date deletedAt;
	
	private Date createdAt;
	
    private Date lastLogin;
    
    private String mobileNumber;
    
    private Long rememberToken;
    
    private String userImage;
    
    private Integer useStatusId;
    
    private Date modifiedBy;
    
    
    
    
	
	
	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(Long rememberToken) {
		this.rememberToken = rememberToken;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Integer getUseStatusId() {
		return useStatusId;
	}

	public void setUseStatusId(Integer useStatusId) {
		this.useStatusId = useStatusId;
	}

	public Date getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	private Long isActive;
	
	private Long roleId;


	

	public UserVO() {
		super();
	}
	
	public UserVO(String email, String password ) {
		super();
		this.email = email;
		this.password = password;

	}
	
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 */
	public UserVO(Long userId, String email, String phoneNumber) {
		this.userId=userId;
		
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * Phone number of user
	 */
//	@NotBlank(message = "{phonenumber.not.empty}")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*
	 * confirm password should match the password
	 */
	//@NotBlank(message = "{confirmpassword.not.empty}")
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

	public Long isActive() {
		return isActive;
	}

	public void setActive(Long isActive) {
		this.isActive = isActive;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	

}
