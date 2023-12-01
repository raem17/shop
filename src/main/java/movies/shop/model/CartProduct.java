package movies.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "cart_products")
public class CartProduct {
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "La cantidad debe estar entre 1 y 20.")
	@Max(value = 20 , message = "La cantidad debe estar entre 1 y 20.")
	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public CartProduct() {
		// TODO Auto-generated constructor stub
	}

	public CartProduct(Movie movie, Cart cart, int quantity, int id) {
		super();
		this.movie = movie;
		this.cart = cart;
		this.quantity = quantity;
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
