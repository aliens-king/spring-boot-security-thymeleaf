package com.sampana.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Sudhanshu
 *
 */
@Entity
@Table(name = "ROLE")
public class Role extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5279229396337953732L;

	private Long roleId;

	private String roleName;
	
	private String description;

	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID", updatable = false, nullable = false)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", nullable = false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
