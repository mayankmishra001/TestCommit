/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.rays.project_0.dto.FacultyDTO;

/**
 * The Class FacultyDAOHibImpl.
 */
@Repository(value = "facultyDao")
public class FacultyDAOHibImpl implements FacultyDAOInt {

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory = null;

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#add(in.co.rays.project_0.dto.FacultyDTO)
	 */
	public long add(FacultyDTO dto) {
		// TODO Auto-generated method stub
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#update(in.co.rays.project_0.dto.FacultyDTO)
	 */
	public void update(FacultyDTO dto) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(dto);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#delete(long)
	 */
	public void delete(long id) {
		// TODO Auto-generated method stub
		FacultyDTO dto1 = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto1);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#findByEmail(java.lang.String)
	 */
	public FacultyDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		FacultyDTO dto = null;
		List list = (List) sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class)
				.add(Restrictions.like("email", email)).list();
		if (list.size() > 0) {
			dto = (FacultyDTO) list.get(0);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#findByPK(long)
	 */
	public FacultyDTO findByPK(long pk) {
		// TODO Auto-generated method stub
		FacultyDTO dto = sessionFactory.getCurrentSession().get(FacultyDTO.class, pk);
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#search(in.co.rays.project_0.dto.FacultyDTO, int, int)
	 */
	public List search(FacultyDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FacultyDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}

			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));

			}
			if (dto.getLastName()!= null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));

			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			if (dto.getCourseId() > 0) {
				criteria.add(Restrictions.eq("courseId", dto.getCourseId()));
			}
		}
		if (pageSize > 0) {
			criteria.setFirstResult((pageNo - 1) * pageSize);
			criteria.setMaxResults(pageSize);
		}
		List list = criteria.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.FacultyDAOInt#search(in.co.rays.project_0.dto.FacultyDTO)
	 */
	public List search(FacultyDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

}
