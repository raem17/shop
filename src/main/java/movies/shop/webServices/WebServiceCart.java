package movies.shop.webServices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import movies.shop.model.User;
import movies.shop.services.CartService;

@Controller
@RestController
@RequestMapping("webServiceCart/")
public class WebServiceCart {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("addMovie")
	private String addMovie (String movieID, String quantity, HttpServletRequest request) {
		String res = "";
		
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			// esto es que el usuario se identifico correctamente
			User u = (User)request.getSession().getAttribute("usuario_identificado");
			
			// u es el usuario que fue metido en sesion cuando el usuario se identifico
			res = u.getFirst_name() + ", se ha agregado la película con id: " + movieID + " al carrito. Cantidad: " + quantity;
			
			cartService.addProduct(u.getId(), Integer.parseInt(movieID), Integer.parseInt(quantity));
			
		} else {
			res = "Usuario no identificado, identificate para poder comprar productos.";
		}
		
		return res;
	}
	
	@RequestMapping("deleteMovie")
	private String deleteMovie (String movieID, HttpServletRequest request) {
		String res = "";
		
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			User u = (User)request.getSession().getAttribute("usuario_identificado");
			cartService.deleteCartProduct(u.getId(), Integer.parseInt(movieID));
			
			res = "Se ha eliminado correctamente el producto.";
			
		} else {
			res = "Usuario no identificado, identificate para poder comprar productos.";
		}
		
		return res;
	}
	
	@RequestMapping("updateProductQuantity")
	private String updateProductQuantity (String movieID, String quantity, HttpServletRequest request) {
		String res = "";
		
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			User u = (User)request.getSession().getAttribute("usuario_identificado");
			cartService.updateCartProduct(u.getId(), Integer.parseInt(movieID), Integer.parseInt(quantity));
			
			res = "Cantidad actualizada";
			
		} else {
			res = "Usuario no identificado, identificate para poder comprar productos.";
		}
		
		return res;
	}
	
	@RequestMapping("getCartProducts")
	private List<Map<String, Object>> getCartProducts (HttpServletRequest request) throws Exception {
		// la comprobacion de si el usuario esta identificado la haremos un poco mas adelante
		// usando un interceptor a parte, para no estar poniendo este if else todo el rato
		if (request.getSession().getAttribute("usuario_identificado") != null) {
			return cartService.getCartProducts( ((User)request.getSession().getAttribute("usuario_identificado")).getId()); 
			
		} else {
			throw new Exception("*** USUARIO NO IDENTIFICADO ***");
		}
	}
	
}

