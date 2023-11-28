package movies.shop.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import movies.shop.constants.OrderStatuses;
import movies.shop.model.Order;
import movies.shop.services.OrdersService;

@Controller
@RequestMapping("admin/")
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping("getOrders")
	public String getOrders(Model model) {
		model.addAttribute("orders", ordersService.getOrders());
		
		return "admin/orders";
	}
	
	@RequestMapping("viewOrderDetails")
	public String viewOrderDetails(Model model, String id) {
		Order o = ordersService.getOrderById(Integer.parseInt(id));
		
		String cardNumberTruncate = "****" + o.getCardNumber().substring(o.getCardNumber().length() - 4);
		o.setCardNumber(cardNumberTruncate);
		
		model.addAttribute("order", o);
		
		Map<String, String> statuses = new HashMap<String, String>();
		statuses.put(OrderStatuses.IN_PROCESS, "En proceso");
		statuses.put(OrderStatuses.FINISHED, "Terminado");
		statuses.put(OrderStatuses.READY_TO_SHIP, "Listo para enviar");
		statuses.put(OrderStatuses.RECEIVED_BY_CUSTOMER, "Recibido por el cliente");
		
		model.addAttribute("statuses", statuses);
		
		return "admin/orderDetails";
	}
	
}
