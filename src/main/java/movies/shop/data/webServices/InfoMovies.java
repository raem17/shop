package movies.shop.data.webServices;

import java.util.List;
import java.util.Map;

public class InfoMovies {
	
	private List<Map<String, Object>> movies;
	private int total;
	
	public List<Map<String, Object>> getMovies() {
		return movies;
	}
	
	public void setMovies(List<Map<String, Object>> movies) {
		this.movies = movies;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

}
