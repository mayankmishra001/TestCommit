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

import in.co.rays.project_0.dto.StudentDTO;
import in.co.rays.project_0.dto.SubjectDTO;

/**
 * The Class StudentDAOHibImpl.
 */
@Repository("studentDao")
public class StudentDAOHibImpl implements StudentDAOInt{

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory=null;
	
	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#add(in.co.rays.project_0.dto.StudentDTO)
	 */
	public long add(StudentDTO dto) {
		// TODO Auto-generated method stub
		long pk=0;
		
		pk=(Long) sessionFactory.getCurrentSession().save(dto);
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#update(in.co.rays.project_0.dto.StudentDTO)
	 */
	public void update(StudentDTO dto) {
		// TODO Auto-generated method stub
		System.out.println("update method");
		sessionFactory.getCurrentSession().update(dto);
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#delete(long)
	 */
	public void delete(long id) {
		// TODO Auto-generated method stub
		StudentDTO dto1=findByPK(id);
		sessionFactory.getCurrentSession().delete(dto1);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#findByEmail(java.lang.String)
	 */
	public StudentDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		StudentDTO dto=null;
		List list= (List) sessionFactory.getCurrentSession().createCriteria(StudentDTO.class).add(Restrictions.like("email", email)).list();
		if(list.size()>0){
			dto=(StudentDTO) list.get(0);
		}
		System.out.println("kkkkkkkkkkkkkk"+dto);
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#findByPK(long)
	 */
	public StudentDTO findByPK(long pk) {
		// TODO Auto-generated method stub
		System.out.println("in find by pk"+pk);
		StudentDTO dto=sessionFactory.getCurrentSession().get(StudentDTO.class, pk);
		
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#search(in.co.rays.project_0.dto.StudentDTO, int, int)
	 */
	public List search(StudentDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(StudentDTO.class);
		if(dto!=null){
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id",dto.getId()));
			}
			if(dto.getFirstName()!=null&&dto.getFirstName().length()>0){
				criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
				
			}
			if(dto.getLastName()!=null&&dto.getLastName().length()>0){
				criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
				
			}
			if(dto.getEmail()!=null&&dto.getEmail().length()>0){
				criteria.add(Restrictions.like("email", dto.getEmail()+"%"));
			}
			
		}
		
		 if(pageSize>0){
			 criteria.setFirstResult((pageNo-1)*pageSize);
			 criteria.setMaxResults(pageSize);
		 }
		 List list=criteria.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.StudentDAOInt#search(in.co.rays.project_0.dto.StudentDTO)
	 */
	public List search(StudentDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

}
