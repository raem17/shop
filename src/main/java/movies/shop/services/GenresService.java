package movies.shop.services;

import java.util.List;

import movies.shop.model.Genre;


public interface GenresService {
	
	void createGenre(Genre newGenre);

	List<Genre> getGenres();
	
	Genre getGenreByID(int genreID);
	
	void updateGenre(Genre genreUpdate);
	
	void deleteGenreByID(int genreID);
}
