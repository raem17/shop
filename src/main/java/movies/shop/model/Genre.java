package movies.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "genres")
public class Genre {
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@NotBlank(message = "El campo no puede estar formado únicamente por espacios.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑç\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ç-")
	private String name;
	
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?ç.,\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres ¡!&()¿?ç.,-:")
	private String description;
	
	// Cascade indica que se puede propagar una operacion desde el dato actual
	// con cascade a tipo ALL estamos diciendo que una operacion aplicada a una categoria
	// pueda ser propagada a los libros asociados
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "genre", fetch = FetchType.LAZY)
	private List<Movie> movies = new ArrayList<Movie>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean activated;
	
	public Genre() {
		// TODO Auto-generated constructor stub
	}
	
	public Genre(String name, String description, int id) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
	}
	
	/**
	 * Constructor without id
	 * @param name
	 * @param description
	 * @param id
	 */
	public Genre(String name, String description, boolean activated) {
		super();
		this.name = name;
		this.description = description;
		this.activated = activated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
