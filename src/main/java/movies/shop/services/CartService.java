package movies.shop.services;

import java.util.List;
import java.util.Map;


public interface CartService {

	void addProduct(int userID, int movieID, int quantity);
	
	void updateCartProduct(int userID, int movieID, int quantity);
	
	void deleteCartProduct(int userID, int movieID);
	
	// operaciones para ajax
	
	List<Map<String, Object>> getCartProducts(int userID);
	
}
