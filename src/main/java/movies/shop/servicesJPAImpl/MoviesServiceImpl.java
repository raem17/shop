package movies.shop.servicesJPAImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.shop.constantsSQL.SQLConstantsMovies;
import movies.shop.model.Genre;
import movies.shop.model.Movie;
import movies.shop.services.MoviesService;

@Service
@Transactional
public class MoviesServiceImpl implements MoviesService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createMovie(Movie newMovie) {
		Genre g = entityManager.find(Genre.class, newMovie.getIdGenre());
		newMovie.setGenre(g);
		newMovie.setActivated(true);
		
		try {
			newMovie.setImageInBytes(newMovie.getImageUploaded().getBytes());
			newMovie.setExtra1InBytes(newMovie.getExtra1Uploaded().getBytes());
			newMovie.setExtra2InBytes(newMovie.getExtra2Uploaded().getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		entityManager.persist(newMovie);
	}

	@Override
	public List<Movie> getMoviesByTitleAndStartAndEnd(String title, int start, int resultsPerPage) {
		List<Movie> movies = entityManager.createQuery("select m from Movie m where m.activated = "
				+ "true and lower(m.title) like lower(:title) order by m.id desc", Movie.class)
				.setParameter("title", "%" + title + "%")
				.setFirstResult(start)
				.setMaxResults(resultsPerPage)
				.getResultList();
		
		return movies;
	}

	@Override
	public int getTotalMovies(String title) {
		Query q = entityManager.createNativeQuery(SQLConstantsMovies.GET_TOTAL_MOVIES);
		q.setParameter("title", "%" + title + "%");
		
		return Integer.parseInt(q.getSingleResult().toString());
	}

	@Override
	public Movie getMovieByID(int id) {
		Movie m = entityManager.find(Movie.class, id);
		m.setIdGenre(m.getGenre().getId());
		
		return m;
	}

	@Override
	public void deleteMovieByID(int id) {
		Movie m = entityManager.find(Movie.class, id);
		m.setActivated(false);
		
		entityManager.merge(m);
	}

	@Override
	public void updateMovie(Movie movieUpdate) {
		Genre g = entityManager.find(Genre.class, movieUpdate.getIdGenre());
		movieUpdate.setGenre(g);
		movieUpdate.setActivated(true);
		
		// image
		if (movieUpdate.getImageUploaded().getSize() == 0) {
			Movie previousMovie = entityManager.find(Movie.class, movieUpdate.getId());
			movieUpdate.setImageInBytes(previousMovie.getImageInBytes());
			
		} else {
			try {
				movieUpdate.setImageInBytes(movieUpdate.getImageUploaded().getBytes());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// extra 1
		if (movieUpdate.getExtra1Uploaded().getSize() == 0) {
			Movie previousMovie = entityManager.find(Movie.class, movieUpdate.getId());
			movieUpdate.setExtra1InBytes(previousMovie.getExtra1InBytes());
			
		} else {
			try {
				movieUpdate.setExtra1InBytes(movieUpdate.getExtra1Uploaded().getBytes());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// extra 2
		if (movieUpdate.getExtra2Uploaded().getSize() == 0) {
			Movie previousMovie = entityManager.find(Movie.class, movieUpdate.getId());
			movieUpdate.setExtra2InBytes(previousMovie.getExtra2InBytes());
			
		} else {
			try {
				movieUpdate.setExtra2InBytes(movieUpdate.getExtra2Uploaded().getBytes());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		entityManager.merge(movieUpdate);
	}

	// Métodos para AJAX
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMoviesByTitleAndStartAndEndForJSONFormation(String title, int start) {
		Query q = entityManager.createNativeQuery(SQLConstantsMovies.GET_MOVIES_BY_TITLE_AND_START_AND_END_FOR_JSON);
		q.setParameter("title", "%" + title + "%");
		q.setParameter("start", start);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) q;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return nativeQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getMovieDetails(int idMovie) {
		Query q = entityManager.createNativeQuery(SQLConstantsMovies.GET_MOVIE_DETAILS);
		NativeQueryImpl nativeQuery = (NativeQueryImpl) q;
		
		nativeQuery.setParameter("id", idMovie);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		return (Map<String, Object>) nativeQuery.getResultList().get(0);
	}

}