/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.rays.project_0.dto.TimetableDTO;

/**
 * The Class TimetableDAOHibImpl.
 */
@Repository("timetableDAO")
public class TimetableDAOHibImpl implements TimetableDAOInt {

	/** The log. */
	private static Logger log = Logger.getLogger(TimetableDAOHibImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;

	/** The course DAO. */
	@Autowired
	private CourseDAOInt courseDAO;

	/** The subject DAO. */
	@Autowired
	private SubjectDAOInt subjectDAO;

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#add(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public long add(TimetableDTO dto) {
		log.debug("DAO add Started");
/*		dto.setCourseName(courseDAO.findByPK(dto.getCourseId()).getName());*/
		/*dto.setSubjectName(subjectDAO.findByPK(dto.getSubjectId()).getName());*/
		long pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("DAO add ended");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#checkBySemester(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public TimetableDTO checkBySemester(TimetableDTO dto) {

		log.debug("Check Duplicates");
		TimetableDTO getDTO = null;
		Session session = sessionFactory.getCurrentSession();
		List list= session.createCriteria(TimetableDTO.class)
				.add(Restrictions.eq("courseId", dto.getCourseId()))
				.add(Restrictions.eq("subjectId", dto.getSubjectId()))
				.add(Restrictions.like("semester", dto.getSemester()))
				.add(Restrictions.eq("examDate", dto.getExamDate())).list();
		
		if (list.size() > 0) {
			getDTO = (TimetableDTO) list.get(0);
		}
		log.debug("Check Duplicates");
		return getDTO;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#checkByCourse(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public TimetableDTO checkByCourse(TimetableDTO dto) {
		log.debug("Check Duplicates");
		TimetableDTO dto1=null;
		Session session = sessionFactory.getCurrentSession();
		List list = session.createCriteria(TimetableDTO.class)
		.add(Restrictions.eq("courseId", dto.getCourseId()))
		.add(Restrictions.eq("examDate", dto.getExamDate())).list();
		if (list.size() > 0) {
			dto1 = (TimetableDTO) list.get(0);
		}
		log.debug("Check Duplicates");
		return dto1;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#checkBySubject(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public TimetableDTO checkBySubject(TimetableDTO dto) {
		log.debug("Check Duplicates");
		TimetableDTO dto1=null;
		Session session = sessionFactory.getCurrentSession();
		List list = session.createCriteria(TimetableDTO.class)
		.add(Restrictions.eq("courseId", dto.getCourseId()))
		.add(Restrictions.eq("subjectId", dto.getSubjectId()))
		.add(Restrictions.eq("examDate", dto.getExamDate())).list();
		 
		if (list.size() > 0) {
			dto1 = (TimetableDTO) list.get(0);
		}
		log.debug("Check Duplicates");
		return dto1;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#update(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public void update(TimetableDTO dto) {
		log.debug("DAO Update Started");
		dto.setCourseName(courseDAO.findByPK(dto.getCourseId()).getName());
		dto.setSubjectName(subjectDAO.findByPK(dto.getSubjectId()).getName());
		sessionFactory.getCurrentSession().update(dto);
		log.debug("DAO Update ended");

	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#delete(long)
	 */
	public void delete(long id) {
		log.debug("DAO delete Started");
		TimetableDTO dto = new TimetableDTO();
		dto = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto);
		log.debug("DAO delete ended");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#findByName(java.lang.String)
	 */
	public TimetableDTO findByName(String name) {
		// TODO Auto-generated method stub
		TimetableDTO dto = null;
		List list = (List) sessionFactory.getCurrentSession().createCriteria(TimetableDTO.class)
				.add(Restrictions.eq("name", name));
		if (list.size() > 1) {
			dto = (TimetableDTO) list.get(0);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#findByPK(long)
	 */
	public TimetableDTO findByPK(long pk) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TimetableDTO dto = session.get(TimetableDTO.class, pk);
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#search(in.co.rays.project_0.dto.TimetableDTO)
	 */
	public List search(TimetableDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.TimetableDAOInt#search(in.co.rays.project_0.dto.TimetableDTO, int, int)
	 */
	public List search(TimetableDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TimetableDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
			if (dto.getSubjectId() > 0) {
				criteria.add(Restrictions.eq("subjectId", dto.getSubjectId()));
			}
			if (dto.getExamDate() != null && dto.getExamDate().getTime() > 0) {
				criteria.add(Restrictions.like("examDate", dto.getExamDate()));
			}
		}
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		return list;
	}

}
