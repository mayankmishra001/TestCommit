/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.rays.project_0.dto.CourseDTO;
import in.co.rays.project_0.exception.DuplicateRecordException;

/**
 * The Class CourseDAOHibImpl.
 */
@Repository("courseDAO")
public class CourseDAOHibImpl implements CourseDAOInt {

	/** The log. */
	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory=null;
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#add(in.co.rays.project_0.dto.CourseDTO)
	 */
	public long add(CourseDTO dto) throws DataAccessException {

		log.debug("Course Dao Add Started");
		System.out.println("CourseDAO add-----");
		long pk=0;
		pk=(Long)sessionFactory.getCurrentSession().save(dto);
		log.debug("User Dao Add End");
		System.out.println("UserDAO add End-----");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#update(in.co.rays.project_0.dto.CourseDTO)
	 */
	public void update(CourseDTO dto) {
		log.debug("course Dao Update Started");
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		session.clear();
		sessionFactory.getCurrentSession().update(dto);
		log.debug("course Dao Update End");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#delete(long)
	 */
	public void delete(long id) {
		log.debug("course Dao Delete Started");
		CourseDTO dto = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("course Dao Delete End");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#findByName(java.lang.String)
	 */
	public CourseDTO findByName(String name) {
		log.debug("coure Dao FindByname Started");
		CourseDTO dto=null; 
		List list=	sessionFactory.getCurrentSession().createCriteria(CourseDTO.class).add(Restrictions.like("name", name)).list();
		if(list.size()>0){
		dto=(CourseDTO)list.get(0);
		}
		log.debug("course Dao FindByname ended");
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#findByPK(long)
	 */
	public CourseDTO findByPK(long pk) {
		log.debug("course Dao FindByLogin Started");
		CourseDTO dto=null;
		dto=sessionFactory.getCurrentSession().get(CourseDTO.class, pk);
		
		log.debug("course Dao FindByLogin ended");
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#search(in.co.rays.project_0.dto.CourseDTO)
	 */
	public List search(CourseDTO dto) {
		
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.CourseDAOInt#search(in.co.rays.project_0.dto.CourseDTO, int, int)
	 */
	public List search(CourseDTO dto, int pageNo, int pageSize) {
		log.debug("User Dao search Started");
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(CourseDTO.class);
		if(dto!=null){
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getDuration()!=null&& dto.getDuration().length()>0){
				criteria.add(Restrictions.like("duration", dto.getDuration()));
			}
		}
		if(pageSize>0){
			criteria.setFirstResult((pageNo-1)*pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list=criteria.list();
		log.debug("course Dao search ended");
		return list;
	}

}
