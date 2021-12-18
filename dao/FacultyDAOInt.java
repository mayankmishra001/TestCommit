/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import in.co.rays.project_0.dto.FacultyDTO;


/**
 * The Interface FacultyDAOInt.
 */
public interface FacultyDAOInt {
	
	/**
	 * Adds the.
	 *
	 * @param dto the dto
	 * @return the long
	 */
	public long add(FacultyDTO dto);

/**
 * Update.
 *
 * @param dto the dto
 */
public void update(FacultyDTO dto);

/**
 * Delete.
 *
 * @param id the id
 */
public void delete(long id);

/**
 * Find by email.
 *
 * @param email the email
 * @return the faculty DTO
 */
public FacultyDTO findByEmail(String email);

/**
 * Find by PK.
 *
 * @param pk the pk
 * @return the faculty DTO
 */
public FacultyDTO findByPK(long pk);

/**
 * Search.
 *
 * @param dto the dto
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 */
public List search(FacultyDTO dto, int pageNo, int pageSize);

/**
 * Search.
 *
 * @param dto the dto
 * @return the list
 */
public  List search(FacultyDTO dto);
}
