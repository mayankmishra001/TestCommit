/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import in.co.rays.project_0.dto.SubjectDTO;

/**
 * The Interface SubjectDAOInt.
 */
public interface SubjectDAOInt {
	
	/**
	 * Adds the.
	 *
	 * @param dto the dto
	 * @return the long
	 */
	public long add(SubjectDTO dto);

	/**
	 * Update.
	 *
	 * @param dto the dto
	 */
	public void update(SubjectDTO dto);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(long id);

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the subject DTO
	 */
	public SubjectDTO findByName(String name);
	
	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the subject DTO
	 */
	public SubjectDTO findByPK(long pk);
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 */
	public List search(SubjectDTO dto);
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 */
	public List search(SubjectDTO dto, int pageNo, int pageSize);

}
