package movies.shop.servicesJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.shop.constantsSQL.SQLConstantsMovies;
import movies.shop.model.Cart;
import movies.shop.model.CartProduct;
import movies.shop.model.Movie;
import movies.shop.model.User;
import movies.shop.services.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addProduct(int userID, int movieID, int quantity) {
		User uDB = entityManager.find(User.class, userID);
		
		Cart c = uDB.getCart();
		if (c == null) {
			// Si el usuario no tiene un carrito asociado, lo creo y lo guardo en bd
			c = new Cart();
			c.setUser(uDB);
			uDB.setCart(c);
			
			entityManager.persist(c);
		}
		
		boolean productInCart = false;
		// compruebo si el producto ya esta en el carrito, y en tal caso incremento su cantidad
		for (CartProduct cp: c.getCartProducts()) {
			if (cp.getMovie().getId() == movieID) {
				productInCart = true;
				
				cp.setQuantity(cp.getQuantity() + quantity);
				
				entityManager.merge(cp);
				
				break;
			}
		}
		
		// si el producto no esta en el carrito, creamos un productoCarrito nuevo
		if(!productInCart) {
			CartProduct cp = new CartProduct();
			
			cp.setCart(c);
			cp.setMovie(entityManager.find(Movie.class, movieID));
			cp.setQuantity(quantity);
			
			entityManager.persist(cp);
		}
	}

	@Override
	public void updateCartProduct(int userID, int movieID, int quantity) {
		User uDB = entityManager.find(User.class, userID);
		Cart c = uDB.getCart();
		
		for (CartProduct cp: c.getCartProducts()) {
			if (cp.getMovie().getId() == movieID) {
				cp.setQuantity(quantity);
				entityManager.merge(cp);
				
				break;
			}
		}
	}

	@Override
	public void deleteCartProduct(int userID, int movieID) {
		User uDB = entityManager.find(User.class, userID);
		Cart c = uDB.getCart();
		
		Query q = entityManager.createNativeQuery(SQLConstantsMovies.DELETE_CART_PRODUCT);
		q.setParameter("cart_id", c.getId());
		q.setParameter("movie_id", movieID);
		
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCartProducts(int userID) {
		User uDB = entityManager.find(User.class, userID);
		Cart c = uDB.getCart();
		List<Map<String, Object>> res = null;
		
		if (c != null && c.getCartProducts().size() > 0) {
			Query q = entityManager.createNativeQuery(SQLConstantsMovies.GET_CART_PRODUCTS);
			q.setParameter("cart_id", c.getId());
			
			NativeQueryImpl nativeQuery = (NativeQueryImpl) q;
			nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			res = nativeQuery.getResultList();
		}
		
		return res;
	}

}
