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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "movies")
public class Movie {
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?ç\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ¡!&()¿?ç-:")
	private String title;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "El precio mínimo es 1 euro.")
	@Max(value = 999, message = "El precio máximo es 999 euros.")
	private double price;
	
	@OneToOne
	private CartProduct cartProduct;
	
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?ç.,\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ¡!&()¿?ç.,-:")
	@Column(columnDefinition = "TEXT")
	private String synopsis;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1895, message = "El año debe estar entre 1895 y 2500.")
	@Max(value = 2500, message = "El año debe estar entre 1895 y 2500.")
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
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "La duración debe estar entre 1 y 60000 minutos.")
	@Max(value = 60000 , message = "La duración debe estar entre 1 y 60000 minutos.")
	private int length_min;
	
	private boolean activated;
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ç-")
	private String director;
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ç-")
	private String country;
	
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "^[0-9]{13}$", message = "El campo EAN debe contener exactamente 13 dígitos numéricos.")
	private String ean;
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ç-")
	private String editor;
	
	// Constructors
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor for Setup
	 * @param title
	 * @param price
	 * @param synopsis
	 * @param release_year
	 * @param genre
	 * @param length_min
	 * @param activated
	 * @param director
	 * @param country
	 * @param ean
	 * @param editor
	 * @param actors
	 */
	public Movie(String title, double price, String synopsis, int release_year, Genre genre, int length_min, boolean activated, 
			String director, String country, String ean, String editor) {
		super();
		this.title = title;
		this.price = price;
		this.synopsis = synopsis;
		this.release_year = release_year;
		this.genre = genre;
		this.length_min = length_min;
		this.activated = activated;
		this.director = director;
		this.country = country;
		this.ean = ean;
		this.editor = editor;
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

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", price=" + price + ", synopsis=" + synopsis + ", release_year="
				+ release_year + ", genre=" + genre.getName() + ", id=" + id + ", length_min=" + length_min + ", activated="
				+ activated + ", director=" + director + ", country=" + country + ", ean=" + ean + ", editor=" + editor + "]";
	}

}
