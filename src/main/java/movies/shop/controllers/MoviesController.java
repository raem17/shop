package movies.shop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String getMovies(Model model, @RequestParam(name = "title", defaultValue = "") String title, 
			@RequestParam(name= "start", defaultValue = "0") Integer start) {
		
		model.addAttribute("movies", moviesService.getMoviesByTitleAndStartAndEnd(title, start, 10));
		
		model.addAttribute("title", title);
		model.addAttribute("next", start + 10);
		model.addAttribute("previous", start - 10);
		model.addAttribute("total", moviesService.getTotalMovies(title));
		
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
	public String saveMovie (@ModelAttribute("newMovie") @Valid Movie newMovie, BindingResult validationsResults, Model model) {
		if (validationsResults.hasErrors()) {
			model.addAttribute("genres", genresService.getGenres());
			return "admin/registerMovie";
		}
		
		moviesService.createMovie(newMovie);
		return "admin/registerMovieOk";
	}
	
	@RequestMapping("deleteMovie")
	public String deleteMovie (@RequestParam("id") Integer id, Model model) {
		moviesService.deleteMovieByID(id);
		
		return getMovies(model, "", 0);
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
	public String saveChangesMovie (Movie movieEdit, Model model) {
		moviesService.updateMovie(movieEdit);
		
		return getMovies(model, "", 0);
	}
	
}
