/**
 * 
 */
package com.sampana.login.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import com.sampana.login.repository.BaseRepository;


/**
 * 
 * @author Sudhanshu
 *
 * @param <T>
 * @param <ID>
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> {

	public static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	private BaseRepository<T, ID> baseRepository;

	public void setBaseRepository(BaseRepository<T, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	/**
	 * Find one.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */

	public T findOne(ID id) {
		return baseRepository.findById(id).orElse(null);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List<T> findAll() {
		return baseRepository.findAll();
	}

	/**
	 * Save.
	 * 
	 * @param entity
	 *            the entity
	 */
	public T save(T entity) {
		return baseRepository.saveAndFlush(entity);
	}

	/**
	 * Save all: save a list
	 *
	 * @param aoEntities
	 *            the ao entities
	 */
	public List<T> saveAll(List<T> aoEntities) {
		return (List<T>) ((CrudRepository<T, ID>)baseRepository).saveAll(aoEntities);
	}
//return (List<T>) ((CrudRepository<T, ID>)baseRepository).save(aoEntities);
	/**
	 * Delete.
	 * 
	 * @param entity
	 *            the entity
	 */
	public void delete(T entity) {
		baseRepository.delete(entity);

	}

	/**
	 * Delete by id.
	 * 
	 * @param entityId
	 *            the entity id
	 */
	public void deleteById(ID entityId) {
		try {
			baseRepository.deleteById(entityId);
			logger.info("***Data deteted successfully from databse.***");
		} catch (Exception e) {
			logger.info("***No Record found with id " + entityId+"***");
		}

	}

}
