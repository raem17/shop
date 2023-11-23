package movies.shop.webServices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import movies.shop.services.OrdersService;

@Controller(value = "webServiceAdminOrders")
@RestController
@RequestMapping("admin/webServiceOrders/")
public class WebServiceOrders {
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("updateOrderStatus")
	private String updateOrderStatus (@RequestParam("id") Integer id, String newStatus) {
		ordersService.updateOrderStatus(id, newStatus);
		
		return "ok";
	}
	
}
