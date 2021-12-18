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

import in.co.rays.project_0.dto.CollegeDTO;
import in.co.rays.project_0.dto.SubjectDTO;

/**
 * The Class SubjectDAOHibImpl.
 */
@Repository("subjectDAO")
public class SubjectDAOHibImpl implements SubjectDAOInt{
	
	/** The log. */
	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#add(in.co.rays.project_0.dto.SubjectDTO)
	 */
	public long add(SubjectDTO dto) throws DataAccessException{
		log.debug("subject Dao Add Started");
		long pk=0;
		pk=(Long)sessionFactory.getCurrentSession().save(dto);
		log.debug("subject Dao Add Started");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#update(in.co.rays.project_0.dto.SubjectDTO)
	 */
	public void update(SubjectDTO dto) {
		log.debug("subject Dao update Started");
		org.hibernate.Session session=sessionFactory.getCurrentSession();
		session.clear();
		sessionFactory.getCurrentSession().update(dto);
		
		log.debug("subject Dao update ended");		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#delete(long)
	 */
	public void delete(long id) {
		log.debug("subject Dao delete Started");
		 SubjectDTO dto = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("subject Dao delete ended");		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#findByName(java.lang.String)
	 */
	public SubjectDTO findByName(String name) {
		log.debug("subject Dao findByName Started");
		SubjectDTO dto=null;
		List list=sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class).add(Restrictions.like("name", name)).list();
		System.out.println("in dao name method"+list);
		if(list.size()>0){
			dto=(SubjectDTO)list.get(0);
		}
		log.debug("subject Dao findByName ended");
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#findByPK(long)
	 */
	public SubjectDTO findByPK(long pk) {
		log.debug("subject Dao findBypk Started");
		SubjectDTO dto=sessionFactory.getCurrentSession().get(SubjectDTO.class,pk);
		log.debug("subject Dao findBypk ended");
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#search(in.co.rays.project_0.dto.SubjectDTO)
	 */
	public List search(SubjectDTO dto) {
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.SubjectDAOInt#search(in.co.rays.project_0.dto.SubjectDTO, int, int)
	 */
	public List search(SubjectDTO dto, int pageNo, int pageSize) {
		log.debug("subject Dao search Started");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SubjectDTO.class);
		if(dto!=null){
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getCourseId()>0){
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if(dto.getName()!=null&&dto.getName().length()>0){
				criteria.add(Restrictions.like("name", dto.getName()+"%"));
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
