package movies.shop.webServices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import movies.shop.services.MoviesService;

@Controller
@RestController
@RequestMapping("webServiceMovies/")
public class WebServiceMovies {
	
	@Autowired
	private MoviesService moviesService;
	
	@RequestMapping("getMovies")
	public List<Map<String,Object>> getMovies() {
		List<Map<String,Object>> res = moviesService.getMoviesForJSONFormation();
		
		return res;
	}
	
	@RequestMapping("getMoviesByTitle")
	public List<Map<String,Object>> getMoviesByTitle(@RequestParam(name = "title", defaultValue = "") String title) {
		List<Map<String,Object>> res = moviesService.getMoviesByTitleForJSONFormation(title);
		
		return res;
	}
	
	@RequestMapping("getMovieDetails")
	public Map<String, Object> getMovieDetails(@RequestParam("id") String id) {
		Map<String, Object> res = moviesService.getMovieDetails(Integer.parseInt(id));
		
		return res;
	}
}

