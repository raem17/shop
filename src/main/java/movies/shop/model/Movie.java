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
	
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?ç\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ¡!&()¿?ç-:")
	private String title;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "El precio mínimo es 1 euro.")
	@Max(value = 999, message = "El precio máximo es 999 euros.")
	private double price;
	
	@OneToOne
	@Transient
	private CartProduct cartProduct;
	
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?ç.,\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ¡!&()¿?ç.,-:")
	@Column(columnDefinition = "TEXT")
	private String synopsis;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1895, message = "El año debe estar entre 1895 y 2500.")
	@Max(value = 2500, message = "El año debe estar entre 1895 y 2500.")
	private int release_year;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = Genre.class, optional = false, fetch = FetchType.EAGER)
	private Genre genre;
	
	@Transient // con esto decimos a hibernate que no considere este campo
	private int idGenre;
	
	@Transient
	private MultipartFile imageUploaded;
	
	@Lob
	@Column(name = "image_in_bytes")
	private byte[] imageInBytes;
	
	@Transient
	private MultipartFile extra1Uploaded;
	
	@Lob
	@Column(name = "extra1_in_bytes")
	private byte[] extra1InBytes;
	
	@Transient
	private MultipartFile extra2Uploaded;
	
	@Lob
	@Column(name = "extra2_in_bytes")
	private byte[] extra2InBytes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Debes introducir un número.")
	@Min(value = 1, message = "La duración debe estar entre 1 y 60.000 minutos.")
	@Max(value = 60000 , message = "La duración debe estar entre 1 y 60.000 minutos.")
	private int length_min;
	
	private boolean activated;
	
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ç-")
	private String director;
	
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ç-")
	private String country;
	
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "^[0-9]{13}$", message = "El campo EAN debe contener exactamente 13 dígitos numéricos.")
	private String ean;
	
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ç-")
	private String editor;
	
	private String format;
	
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
			String director, String country, String ean, String editor, String format) {
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
		this.format = format;
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
	
	public MultipartFile getExtra1Uploaded() {
		return extra1Uploaded;
	}

	public void setExtra1Uploaded(MultipartFile extra1Uploaded) {
		this.extra1Uploaded = extra1Uploaded;
	}

	public byte[] getExtra1InBytes() {
		return extra1InBytes;
	}

	public void setExtra1InBytes(byte[] extra1InBytes) {
		this.extra1InBytes = extra1InBytes;
	}

	public MultipartFile getExtra2Uploaded() {
		return extra2Uploaded;
	}

	public void setExtra2Uploaded(MultipartFile extra2Uploaded) {
		this.extra2Uploaded = extra2Uploaded;
	}

	public byte[] getExtra2InBytes() {
		return extra2InBytes;
	}

	public void setExtra2InBytes(byte[] extra2InBytes) {
		this.extra2InBytes = extra2InBytes;
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", price=" + price + ", synopsis=" + synopsis + ", release_year="
				+ release_year + ", genre=" + genre.getName() + ", id=" + id + ", length_min=" + length_min + ", activated="
				+ activated + ", director=" + director + ", country=" + country + ", ean=" + ean + ", editor=" + editor
				+ ", format=" + format + "]";
	}

}
