package movies.shop.services;

import java.util.List;

import movies.shop.model.User;


public interface UsersService {
	
	void createUser(User u);
	
	User getUserByEmailAndPass(String email, String pass);
	
	List<User> getUsers();
	
	void deleteUserByID(int id);
	
	User getUserByID(int id);
	
	void updateUser(User user);
	
}
