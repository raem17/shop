package movies.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "order_products")
public class OrderProduct {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Movie movie;
	
	private int quantity;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	public OrderProduct() {
		// TODO Auto-generated constructor stub
	}

	public OrderProduct(Order order, Movie movie, int quantity, int id) {
		super();
		this.order = order;
		this.movie = movie;
		this.quantity = quantity;
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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
