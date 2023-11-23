package movies.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}, name="users")
public class User {
	
	private String first_name;
	private String last_name;
	private String email;
	private String pass;
	private boolean activated;
	
	@OneToOne
	private Cart cart;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	@Transient // con esto decimos a hibernate que no considere este campo
	private MultipartFile avatar;
	
	@Lob
	@Column(name = "image_in_bytes")
	private byte[] imageInBytes;
	
	public User() {
		super();
	}

	public User(String first_name, String last_name, String email, String pass, Cart cart, int id,
			MultipartFile avatar) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.pass = pass;
		this.cart = cart;
		this.id = id;
		this.avatar = avatar;
	}

	/**
	 * Constructor without id, cart and avatar
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param pass
	 * @param cart
	 */
	public User(String first_name, String last_name, String email, String pass, boolean activated) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.pass = pass;
		this.activated = activated;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public byte[] getImageInBytes() {
		return imageInBytes;
	}

	public void setImageInBytes(byte[] imageInBytes) {
		this.imageInBytes = imageInBytes;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
