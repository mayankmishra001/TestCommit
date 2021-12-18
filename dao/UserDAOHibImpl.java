/*
 * @author Mayank
 */
package in.co.rays.project_0.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import in.co.rays.project_0.dto.UserDTO;

/**
 * The Class UserDAOHibImpl.
 */
@Repository(value="userDao")
public class UserDAOHibImpl implements UserDAOInt {
	
	/** The log. */
	private static Logger log = Logger.getLogger(UserDAOHibImpl.class);

	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory=null;
	
	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#add(in.co.rays.project_0.dto.UserDTO)
	 */
	public long add(UserDTO dto) throws DataAccessException {
		log.debug("User Dao Add Started");
		System.out.println("UserDAO add-----"+dto.getDob());
		long pk = 0;
		pk = (Long) sessionFactory.getCurrentSession().save(dto);
		log.debug("User Dao Add End");
		System.out.println("UserDAO add End-----");
		return pk;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#update(in.co.rays.project_0.dto.UserDTO)
	 */
	public void update(UserDTO dto) {
		log.debug("User Dao Update Started");
		sessionFactory.getCurrentSession().update(dto);
		log.debug("User Dao Update End");
	}


	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#delete(long)
	 */
	public void delete(long id) {
		log.debug("User Dao Delete Started");
		UserDTO dto1 = findByPK(id);
		sessionFactory.getCurrentSession().delete(dto1);
		log.debug("User Dao Delete End");
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#findByLogin(java.lang.String)
	 */
	public UserDTO findByLogin(String login) {
		log.debug("User Dao FindByLogin Started");
		System.out.println("UserDAOHibImpl......."+login);
        UserDTO dto=null;
        List list = (List) sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.eq("login",login)).list();
		if(list.size()==1){
		dto=(UserDTO)list.get(0);
		}
        return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#findByPK(long)
	 */
	public UserDTO findByPK(long pk) {
		UserDTO dto=new UserDTO();
		dto=sessionFactory.getCurrentSession().get(UserDTO.class, pk);
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project_0.dao.UserDAOInt#search(in.co.rays.project_0.dto.UserDTO, int, int)
	 */
	public List search(UserDTO dto, int pageNo, int pageSize) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserDTO.class);
		if(dto!=null){
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if(dto.getFirstName()!=null&&dto.getFirstName().length()>0){
				criteria.add(Restrictions.like("firstName", dto.getFirstName()+"%"));
			}
			if(dto.getLastName()!=null&&dto.getLastName().length()>0){
				criteria.add(Restrictions.like("lastName", dto.getLastName()+"%"));
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
	 * @see in.co.rays.project_0.dao.UserDAOInt#search(in.co.rays.project_0.dto.UserDTO)
	 */
	public List search(UserDTO dto) {
		return search(dto, 0, 0);
	}
	
}
