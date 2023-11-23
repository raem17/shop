package movies.shop.controllersImage;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import movies.shop.services.MoviesService;
import movies.shop.services.UsersService;


@Controller
public class ShowImage {
	
	@Autowired
	private MoviesService moviesService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("showMovieImage")
	// Tiene que ser Integer no int
	public void showMovieImage (@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
		byte[] info = moviesService.getMovieByID(id).getImageInBytes();
		
		if (info == null) {
			return;
		}
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/webp");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
	
	@RequestMapping("showUserImage")
	// Tiene que ser Integer no int
	public void showUserImage (@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
		byte[] info = usersService.getUserByID(id).getImageInBytes();
		
		if (info == null) {
			return;
		}
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/webp");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
}
