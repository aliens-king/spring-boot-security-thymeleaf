package com.sampana.login.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Sudhanshu
 *
 */
@Entity
@Table(name = "USERS")
public class User extends Base {

	private static final long serialVersionUID = -4140553868146341955L;

	private Long userId;

	private String fullName;

	private String email;

	private String password;

	private String mobileNo;

	private String rememberToken;

	private String userImage;

	private String userStatusId;

	private String lastLogin;

	private String userName;

	private UserRole userRoles;

	 private long active;

	/**
	 * @param userId
	 */
	public User() {
		super();
	}

	/**
	 * @param userId
	 */
	public User(final Long userId) {
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", updatable = false, nullable = false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*
	 * @Column(name = "FIRST_NAME", nullable = false) public String getFirstName() {
	 * return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * @Column(name = "LAST_NAME", nullable = false) public String getLastName() {
	 * return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 */
	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * @Column(name = "PHONE_NUMBER", nullable = false) public String
	 * getPhoneNumber() { return phoneNumber; }
	 * 
	 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
	 * phoneNumber; }
	 */
	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	public UserRole getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRole userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	  @Column(name = "ACTIVE", columnDefinition = "tinyint default false") public
	  long getActive() {
		  return active;
		  }
	  
	  public void setActive(long active) {
		  this.active = active;
		  }
	 

	@Column(name = "FULL_NAME", nullable = false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "REMEMBER_TOKEN")
	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	@Column(name = "USER_IMAGE")
	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	@Column(name = "USER_STATUS_ID")
	public String getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Column(name = "LAST_LOGIN")
	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

}
