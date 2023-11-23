package movies.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "carts")
public class Cart {
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private User user;
	
	@OneToMany (mappedBy = "cart")
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(User user, List<CartProduct> cartProducts, int id) {
		super();
		this.user = user;
		this.cartProducts = cartProducts;
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
