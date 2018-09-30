package com.sampana.login.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base Entity for sub entities.
 * 
 * @author Sudhanshu
 *
 */
@MappedSuperclass
public abstract class Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5979211121019117229L;

	/**
	 * Handles created current date
	 */
	private Date createdAt;

	/**
	 * Handles Modified time stamp
	 */
	/*private Date modifiedDate;*/
	
	private Date deletedAt;
	
	private Enum deleted;
	
	
	
	private Date updatedAt;

	/**
	 * created By null able true.
	 */
	//private User createdBy;

	/**
	 * Modified By null able true.
	 */
	private User modifiedBy;

	/**
	 * Is deleted true or false. By default is false.
	 */
	//private boolean isArchived;
	
	
	
	

	/**
	 * @return the createdDate
	 *//*
	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdAt;
	}
*/
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 *//*
	public void setCreatedDate(Date createdDate) {
		this.createdAt = createdDate;
	}*/

	/**
	 * @return the modifiedDate
	 */
	/*@Column(name = "MODIFIED_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	*//**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 *//*
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
*/
	/**
	 * @return the createdBy
	 *//*
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "CREATED_BY", nullable = true)
	public User getCreatedBy() {
		return createdBy;
	}

	*//**
	 * @param createdBy
	 *            the createdBy to set
	 *//*
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}*/

	/**
	 * @return the modifiedBy
	 */
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "MODIFIED_BY", nullable = true)
	public User getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the isArchived
	 */
	/*@Column(name = "IS_ARCHIVED", columnDefinition = "tinyint default false")
	public boolean isArchived() {
		return isArchived;
	}

	*//**
	 * @param isArchived
	 *            the isArchived to set
	 *//*
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}*/

	@Column(name = "DELETED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Enum getDeleted() {
		return deleted;
	}

	public void setDeleted(Enum deleted) {
		this.deleted = deleted;
	}
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	

}
