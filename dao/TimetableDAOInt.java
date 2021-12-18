/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import in.co.rays.project_0.dto.TimetableDTO;

/**
 * The Interface TimetableDAOInt.
 */
public interface TimetableDAOInt {
	
	/**
	 * Adds the.
	 *
	 * @param dto the dto
	 * @return the long
	 */
	public long add(TimetableDTO dto);
	
	/**
	 * Check by semester.
	 *
	 * @param dto the dto
	 * @return the timetable DTO
	 */
	public TimetableDTO checkBySemester(TimetableDTO dto);
	
	/**
	 * Check by course.
	 *
	 * @param dto the dto
	 * @return the timetable DTO
	 */
	public TimetableDTO checkByCourse(TimetableDTO dto);
	
	/**
	 * Check by subject.
	 *
	 * @param dto the dto
	 * @return the timetable DTO
	 */
	public TimetableDTO checkBySubject(TimetableDTO dto);
	
	/**
	 * Update.
	 *
	 * @param dto the dto
	 */
	public void update(TimetableDTO dto);
	
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
	 * @return the timetable DTO
	 */
	public TimetableDTO findByName(String name);
	
	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the timetable DTO
	 */
	public TimetableDTO findByPK(long pk);
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @return the list
	 */
	public List search(TimetableDTO dto);
	
	/**
	 * Search.
	 *
	 * @param dto the dto
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 */
	public List search(TimetableDTO dto, int pageNo, int pageSize);

}
