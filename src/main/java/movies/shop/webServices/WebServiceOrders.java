package movies.shop.webServices;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.shop.data.webServices.OrderSummary;
import movies.shop.model.User;
import movies.shop.services.OrdersService;


@Controller
@RestController
@RequestMapping("webServiceOrders/")
public class WebServiceOrders {
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("step1")
	public String step1 (HttpServletRequest request, String firstName, String lastName, String email, String phoneNumber, String address, 
	String addressDetails, String postalCode, String town, String province) {
		
		String res = "";
		User u = (User) request.getSession().getAttribute("usuario_identificado");
		
		ordersService.processStep1(u.getId(), firstName, lastName, email, phoneNumber, address, addressDetails, Integer.parseInt(postalCode), 
		town, province);
		
		res = "ok";
		return res;
	}
	
	@RequestMapping("step2")
	public String step2 (HttpServletRequest request, String shippingNotes, String contactName, String contactPhone, 
	String contactEmail) {
		
		String res = "";
		
		User u = (User) request.getSession().getAttribute("usuario_identificado");
		ordersService.processStep2(u.getId(), shippingNotes, contactName, contactPhone, contactEmail);
		
		res = "ok";
		return res;
	}
	
	@RequestMapping("step3")
	public OrderSummary step3 (HttpServletRequest request, String cardType, String cardHolder, String cardNumber, String cardExpiration, 
	String cardCvv) {
		
		User u = (User) request.getSession().getAttribute("usuario_identificado");
		ordersService.processStep3(u.getId(), cardType, cardHolder, cardNumber, cardExpiration, Integer.parseInt(cardCvv));
		
		OrderSummary summary = ordersService.getOrderSummary(u.getId());
		
		return summary;
	}
	
	@RequestMapping("step4")
	public String step4 (HttpServletRequest request) {
		String res = "";
		
		User u = (User) request.getSession().getAttribute("usuario_identificado");
		ordersService.confirmOrder(u.getId());
		
		res = "Pedido completado";
		return res;
	}
}

