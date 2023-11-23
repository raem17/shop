package movies.shop.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.shop.model.Genre;
import movies.shop.services.GenresService;

@Service
@Transactional
public class GenresServiceImpl implements GenresService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createGenre(Genre newGenre) {
		newGenre.setActivated(true);
		
		entityManager.persist(newGenre);
	}
	
	@Override
	public List<Genre> getGenres() {
		List<Genre> genres = entityManager.createQuery("select g from Genre g where g.activated = true", Genre.class).getResultList();
		
		return genres;
	}

	@Override
	public void updateGenre(Genre genreUpdate) {
		genreUpdate.setActivated(true);
		
		entityManager.merge(genreUpdate);
	}

	@Override
	public void deleteGenreByID(int genreID) {
		Genre g = entityManager.find(Genre.class, genreID);
		g.setActivated(false);
		
		entityManager.merge(g);
	}

	@Override
	public Genre getGenreByID(int genreID) {
		return entityManager.find(Genre.class, genreID);
	}

}
