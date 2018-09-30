package com.sampana.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Modules Entity.
 * 
 * @author Sudhanshu
 *
 */

@Entity
@Table(name="MODULES")
public class Modules extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -865879574319631262L;
	
	private Integer moduleId;
	private String module;
	private String moduleKey;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MODULE_ID")
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	@Column(name="MODULE")
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	@Column(name="MODULE_KEY")
	public String getModuleKey() {
		return moduleKey;
	}
	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}
	
	

}
