package movies.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movies.shop.model.Genre;
import movies.shop.model.Movie;
import movies.shop.services.GenresService;
import movies.shop.services.MoviesService;

@Controller
@RequestMapping("admin/")
public class MoviesController {
	
	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private GenresService genresService;
	
	@RequestMapping("getMovies")
	public String getMovies(Model model) {
		List<Movie> movies = moviesService.getMovies();
		model.addAttribute("movies", movies);
		
		return "admin/movies";
	}
	
	@RequestMapping("getMoviesByTitle")
	public String getMoviesByTitle(@RequestParam(name = "title", defaultValue = "") String title, Model model) {
		List<Movie> movies = moviesService.getMoviesByTitle(title);
		model.addAttribute("movies", movies);
		model.addAttribute("title", title);
		
		return "admin/movies";
	}
	
	@RequestMapping("registerMovie")
	public String registerMovie (Model model) {
		Movie newMovie = new Movie();

		model.addAttribute("newMovie", newMovie);
		model.addAttribute("genres", genresService.getGenres());
		
		return "admin/registerMovie";
	}
	
	@RequestMapping("saveMovie")
	public String saveMovie (@Valid Movie newMovie, BindingResult validationsResults) {
		if (validationsResults.hasErrors()) {
			return "admin/registerMovie";
		}
		
		moviesService.createMovie(newMovie);
		return "admin/registerMovieOk";
	}
	
	@RequestMapping("deleteMovie")
	public String deleteMovie (@RequestParam("id") Integer id, Model model) {
		moviesService.deleteMovieByID(id);
		
		return getMovies(model);
	}
	
	@RequestMapping("editMovie")
	public String editMovie (@RequestParam("id") Integer id, Model model) {
		Movie movie = moviesService.getMovieByID(id);
		
		List<Genre> genres = genresService.getGenres();
		
		model.addAttribute("movieEdit", movie);
		model.addAttribute("genres", genres);
		
		return "admin/editMovie";
	}
	
	@RequestMapping("saveChangesMovie")
	public String saveChangesMovie (@Valid Movie movieEdit, Model model) {
		moviesService.updateMovie(movieEdit);
		
		return getMovies(model);
	}
	
}
