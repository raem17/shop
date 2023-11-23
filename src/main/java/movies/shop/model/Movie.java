package movies.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Size(min = 3, max = 10, message = "El título debe tener entre 3 y 10 caracteres.")
	@NotEmpty(message = "El título no puede estar vacío.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ]", message = "El título solo puede contener letras y números.")
	private String title;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "El precio mínimo es 1 euro.")
	@Max(value = 999, message = "El precio máximo es 999 euros.")
	private double price;
	
	@OneToOne
	private CartProduct cartProduct;
	
	@Column(columnDefinition = "TEXT")
	private String synopsis;
	private int release_year;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = Genre.class, optional = false, fetch = FetchType.EAGER)
	private Genre genre;
	
	@Transient
	private int idGenre;
	
	@Transient // con esto decimos a hibernate que no considere este campo
	private MultipartFile imageUploaded;
	
	@Lob
	@Column(name = "image_in_bytes")
	private byte[] imageInBytes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int length_min;
	
	private boolean activated;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with all data.
	 * @param title
	 * @param price
	 * @param synopsis
	 * @param release_year
	 * @param id
	 * @param length_min
	 */
	public Movie(String title, double price, String synopsis, int release_year, int id, int length_min, Genre genre) {
		super();
		this.title = title;
		this.price = price;
		this.synopsis = synopsis;
		this.release_year = release_year;
		this.id = id;
		this.length_min = length_min;
		this.genre = genre;
	}
	
	/**
	 * Constructor without id.
	 * @param title
	 * @param price
	 * @param synopsis
	 * @param release_year
	 * @param length_min
	 */
	public Movie(String title, double price, String synopsis, int release_year, int length_min, Genre genre, boolean activated) {
		super();
		this.title = title;
		this.price = price;
		this.synopsis = synopsis;
		this.release_year = release_year;
		this.length_min = length_min;
		this.genre = genre;
		this.activated = activated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CartProduct getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(CartProduct cartProduct) {
		this.cartProduct = cartProduct;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(int idGenre) {
		this.idGenre = idGenre;
	}

	public MultipartFile getImageUploaded() {
		return imageUploaded;
	}

	public void setImageUploaded(MultipartFile imageUploaded) {
		this.imageUploaded = imageUploaded;
	}
	
	public byte[] getImageInBytes() {
		return imageInBytes;
	}

	public void setImageInBytes(byte[] imageInBytes) {
		this.imageInBytes = imageInBytes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength_min() {
		return length_min;
	}

	public void setLength_min(int length_min) {
		this.length_min = length_min;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", price=" + price + ", cartProduct=" + cartProduct + ", synopsis=" + synopsis
				+ ", release_year=" + release_year + ", genre=" + genre + ", idGenre=" + idGenre + ", imageUploaded="
				+ imageUploaded + ", id=" + id + ", length_min=" + length_min + "]";
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
