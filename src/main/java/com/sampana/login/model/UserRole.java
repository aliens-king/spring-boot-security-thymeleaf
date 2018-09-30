/**
 * 
 */
package com.sampana.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * 
 * @author Sudhanshu
 *
 */
@Entity
@Table(name = "USER_ROLE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "USER_ID", "ROLE_ID" }, name = "UNI_USER_ID_ROLE_ID"), })
public class UserRole extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5550244324149901126L;

	private Long userRoleId;

	private User user;

	private Role role;

	/**
	 * 
	 */
	public UserRole() {
	}

	/**
	 * @param userRoleId
	 */
	public UserRole(final Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the userRoleId
	 */
	@Id
	@GeneratedValue
	@Column(name = "USER_ROLE_ID", updatable = false, nullable = false)
	public Long getUserRoleId() {
		return userRoleId;
	}

	/**
	 * @param userRoleId
	 *            the userRoleId to set
	 */
	public void setUserRoleId(final Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	/**
	 * @return the user
	 */
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_ID_U"), nullable = false)
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	@ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLE_ID_R"), nullable = false)
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

}
