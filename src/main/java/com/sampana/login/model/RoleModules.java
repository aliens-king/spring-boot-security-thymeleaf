package com.sampana.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;



/**
 * 
 * using Lombook
 * @author Sudhanshu
 *
 */
@Entity
@Table(name="ROLE_MODULES")
@Getter
@Setter
public class RoleModules extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 992914620484863790L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_MODULE_ID")
	private Integer roleModuleId;
	
	@Column(name="MODULE")
	private Modules module;
	
	@Column(name="ROLE")
	private Role role;
	
	
	

}
