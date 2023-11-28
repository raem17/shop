package movies.shop.setup;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.shop.constants.MovieFormats;
import movies.shop.model.Genre;
import movies.shop.model.Movie;
import movies.shop.model.User;
import movies.shop.model.setup.Setup;
import movies.shop.utilities.Utilities;

@Service
@Transactional
public class SetupServiceImpl implements SetupService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Utilities utilities;

	private void createMovies() {
		// Se crean los genres
		Genre accion = new Genre("Acción", "Películas llenas de emocionantes escenas de acción y aventuras", true);
		Genre aventura = new Genre("Aventura", "Películas que te llevarán a emocionantes viajes y descubrimientos", true);
		Genre superheroes = new Genre("Superhéroes", "Películas que presentan héroes con poderes extraordinarios", true);
		Genre comedia = new Genre("Comedia", "Películas divertidas que te harán reír", true);
		Genre drama = new Genre("Drama", "Películas con historias emotivas y personajes profundos", true);
		
		// Se registran los genres en la base de datos
		entityManager.persist(accion);
		entityManager.persist(aventura);
		entityManager.persist(superheroes);
		entityManager.persist(comedia);
		entityManager.persist(drama);

		// Se crean las movies
		Movie m1 = new Movie("Kill Bill", 5.99, "Mamba Negra es una asesina que, el día de su boda, es atacada por los miembros de"
				+ " la banda de su jefe, Bill. Sin embargo consigue sobrevivir, aunque queda en coma. Cinco años después despierta, con "
				+ "un deseo de venganza", 2003, accion, 247, true, "Quentin Tarantino", "Estados Unidos", "0659358465242", "Miramax Films", 
				MovieFormats.DVD);
		
		Movie m2 = new Movie("Django desencadenado", 8.99, "La D es muda. La venganza no lo será", 2012, aventura, 165, true, 
				"Quentin Tarantino", "Estados Unidos", "0659355930051", "The Weinstein Company", MovieFormats.DVD);
		
		Movie m3 = new Movie("Spider-Man 2", 1.99, "Como si Peter Parker no tuviera suficiente con sus propios problemas, "
				+ "estudios y su amor por Mary Jane, ahora tiene que salvar a la ciudad de un nuevo villano, el Doctor Octopus", 
				2004, superheroes, 127, true, "Sam Raimi", "Estados Unidos", "5035822480931", "Sony Pictures Entertainment", 
				MovieFormats.DVD);
		
		Movie m4 = new Movie("Spider-Man 3", 2.99, "Peter Parker sufre una terrible transformación cuando su traje se vuelve negro "
				+ "y libera su personalidad oscura y vengativa. Afrontará el mayor desafío de su vida al tener que redescubrir la "
				+ "humildad y compasión que lo hacen ser quien es: un héroe", 2007, comedia, 139, true, "Sam Raimi", "Estados Unidos",
				"5035822558034", "Sony Pictures Entertainment", MovieFormats.DVD);
		
		Movie m5 = new Movie("Pulp Fiction", 1, "Historias de dos matones, un boxeador y una pareja de atracadores de "
				+ "poca monta envueltos en una violencia espectacular e irónica", 1994, drama, 165, true, "Quentin Tarantino", 
				"Estados Unidos", "5017239191964", "Miramax Films", MovieFormats.DVD);
		
		// imagenes
		m1.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImages/kill_bill.jpg"));
		m2.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImages/django.jpg"));
		m3.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImages/spiderman2.jpg"));
		m4.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImages/spiderman3.jpg"));
		m5.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImages/pulp_fiction.jpg"));
		
		// Se registran las movies en la base de datos
		entityManager.persist(m1);
		entityManager.persist(m2);
		entityManager.persist(m3);
		entityManager.persist(m4);
		
		// borrar
		for (int i = 0; i < 30; i++) {
			Movie newM = new Movie("Pulp Fiction " + i, 1, "Historias de dos matones, un boxeador y una pareja de atracadores de "
					+ "poca monta envueltos en una violencia espectacular e irónica", 1994 + i, drama, 165 + i, true, "Quentin Tarantino", 
					"Estados Unidos", "5017239191964", "Miramax Films", MovieFormats.BR);
			entityManager.persist(newM);
		}
	}
	
	private void createUsers() {
		User u1 = new User("Manolo", "Pérez", "manolo@gmail.com", "Manoli1976", true);
		User u2 = new User("Rafael", "Alvarado", "rafaavl@gmail.com", "Pepe4564", true);
		User u3 = new User("Teodoro", "Robles", "teorobles@gmail.com", "Lalanda3756", true);
		
		// imagenes
		u1.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImagesUsers/u1.png"));
		u2.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImagesUsers/u2.png"));
		u3.setImageInBytes(utilities.copyBaseImage("http://localhost:8080/baseImagesUsers/u3.png"));
		
		entityManager.persist(u1);
		entityManager.persist(u2);
		entityManager.persist(u3);
	}
	
	@Override
	public void prepareSetup() {
		Setup setup = null;
		
		try {
			setup = entityManager.createQuery("select s from Setup s", Setup.class).getSingleResult();
			
		} catch (NoResultException e) {
			System.out.println("No se ha encontrado ningún registro de Setup.");
		}
		
		if (setup == null || !setup.isCompleted()) {
			// si no hay nada registrado en la tabla setup o el unico elemento 
			// registrado no tiene completado true
			createMovies();
			createUsers();
			
			// marcar setup como completado para que no se vuelva a hacer
			Setup setupRegister = new Setup();
			setupRegister.setCompleted(true);
			
			entityManager.persist(setupRegister);
			
		} else {
			System.out.println("El setup ya existe.");
		}
	}

}