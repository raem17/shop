package movies.shop.services;

import java.util.List;
import java.util.Map;

import movies.shop.model.Movie;


public interface MoviesService {
	
	void createMovie(Movie newMovie);
	
	List<Movie> getMovies();
	
	List<Movie> getMoviesByStartAndEnd(int start, int resultsPerPage);
	
	List<Movie> getMoviesByTitle(String title);
	
	List<Movie> getMoviesByTitleAndStartAndEnd(String title, int start, int resultsPerPage);
	
	int getTotalMovies(String title);
	
	Movie getMovieByID(int id);
	
	void deleteMovieByID(int id);
	
	void updateMovie(Movie movieUpdate);
	
	// Metodos para la comunicacion por ajax
	
	List<Map<String, Object>> getMoviesForJSONFormation();
	
	List<Map<String, Object>> getMoviesByTitleForJSONFormation(String title);

	Map<String, Object> getMovieDetails(int idMovie);
}
