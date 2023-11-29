package movies.shop.webServices;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import movies.shop.data.webServices.InfoMovies;
import movies.shop.services.MoviesService;

@Controller
@RestController
@RequestMapping("webServiceMovies/")
public class WebServiceMovies {
	
	@Autowired
	private MoviesService moviesService;
	
	@RequestMapping("getMovies")
	public InfoMovies getMovies(@RequestParam(name = "title",defaultValue = "") String title, 
	@RequestParam(name="start", defaultValue = "0") Integer start) {
		InfoMovies info = new InfoMovies();
		info.setMovies(moviesService.getMoviesByTitleAndStartAndEndForJSONFormation(title, start));
		info.setTotal(moviesService.getTotalMovies(title));
		
		return info;
	}
	
	@RequestMapping("getMovieDetails")
	public Map<String, Object> getMovieDetails(@RequestParam("id") String id) {
		Map<String, Object> res = moviesService.getMovieDetails(Integer.parseInt(id));
		
		return res;
	}
}

