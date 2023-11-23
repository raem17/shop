package movies.shop.servicesJPAImpl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import movies.shop.model.User;
import movies.shop.services.UsersService;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createUser(User u) {
		u.setActivated(true);
		
		if (u.getAvatar().getSize() == 0) {
			u.setImageInBytes(null);
			
		} else {
			try {
				u.setImageInBytes(u.getAvatar().getBytes());
				
			} catch (IOException e) {
				System.out.println("Error en registerUser: " + e.getMessage());
			}
		}
		
		entityManager.persist(u);
	}

    @Override
    public User getUserByEmailAndPass(String email, String pass) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.pass = :pass";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        query.setParameter("pass", pass);

        List<User> resultList = query.getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
            
        } else {
        	return null;
        }
    }

	@Override
	public List<User> getUsers() {
		List<User> users = entityManager.createQuery("SELECT u FROM User u where u.activated = true", User.class).getResultList();
		
		return users;
	}

	@Override
	public void deleteUserByID(int id) {
        User u = entityManager.find(User.class, id);
        u.setActivated(false);
        
        entityManager.merge(u);
	}

	@Override
	public User getUserByID(int id) {
        User user = entityManager.find(User.class, id);
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		user.setActivated(true);
		
		if (user.getAvatar().getSize() == 0) {
			User previousUser = entityManager.find(User.class, user.getId());
			user.setImageInBytes(previousUser.getImageInBytes());
			
		} else {
			try {
				user.setImageInBytes(user.getAvatar().getBytes());
				
			} catch (IOException e) {
				System.out.println("Error en updateUser: " + e.getMessage());

			}
		}
		
		entityManager.merge(user);
	}

}
