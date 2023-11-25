package movies.shop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import movies.shop.model.Genre;
import movies.shop.services.GenresService;

@Controller
@RequestMapping("admin/")
public class GenresController {
	
	@Autowired
	private GenresService genresService;
	
	@RequestMapping("getGenres")
	public String getGenres(Model model) {
		model.addAttribute("genres", genresService.getGenres());
		
		return "admin/genres";
	}
	
	@RequestMapping("deleteGenre")
	public String deleteGenre(String id, Model model) {
		genresService.deleteGenreByID(Integer.parseInt(id));
		
		return getGenres(model);
	}
	
	
	@RequestMapping("editGenre")
	public String editGenre (String id, Model model) {
		Genre g = genresService.getGenreByID(Integer.parseInt(id));
		model.addAttribute("genre", g);
		
		return "admin/editGenre";
	}
	
	@RequestMapping("saveChangesGenre")
	public String saveChangesGenre(@ModelAttribute("genre") @Valid Genre genre, BindingResult validationsResults, Model model) {
		if (validationsResults.hasErrors()) {
			return "admin/editGenre";
		}
		
		genresService.updateGenre(genre);
		return getGenres(model);
	}
	
	@RequestMapping("registerGenre")
	public String registerGenre(Model model) {
		Genre g = new Genre();
		model.addAttribute("newGenre", g);
		
		return "admin/registerGenre";
	}
	
	@RequestMapping("saveGenre")
	public String saveGenre(@ModelAttribute("newGenre") @Valid Genre newGenre, BindingResult validationsResults, Model model) {
		if (validationsResults.hasErrors()) {
			return "admin/registerGenre";
		}
		
		genresService.createGenre(newGenre);
		return "admin/registerGenreOk";
	}

}
