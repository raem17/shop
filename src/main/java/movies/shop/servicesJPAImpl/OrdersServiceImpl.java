package movies.shop.servicesJPAImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.shop.constants.OrderStatuses;
import movies.shop.constantsSQL.SQLConstantsMovies;
import movies.shop.data.webServices.OrderSummary;
import movies.shop.model.Cart;
import movies.shop.model.CartProduct;
import movies.shop.model.Order;
import movies.shop.model.OrderProduct;
import movies.shop.model.User;
import movies.shop.services.CartService;
import movies.shop.services.OrdersService;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CartService cartService;

	@Override
	public List<Order> getOrders() {
		TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM orders o", Order.class);
		
        return query.getResultList();
	}

	@Override
	public Order getOrderById(int orderID) {
		return entityManager.find(Order.class, orderID);
	}

	@Override
	public void updateOrderStatus(int orderID, String newStatus) {
		Order o = entityManager.find(Order.class, orderID);
		o.setStatus(newStatus);
		
		entityManager.merge(o);
	}

	@Override
	public void processStep1(int userID, String firstName, String lastName, String email, String phoneNumber,
			String address, String addressDetails, String postalCode, String town, String province) {
		// cada usuario podra tener como maximo un solo pedido en estado "en proceso"
		// ese es el pedido donde rellenaremos los datos de envio, los de pago y demas
		// si el usuario finaliza un pedido en estado "en proceso", el estado de dicho pedido pasara a ser "terminado"
		// puede haber tantos pedidos en estado "terminado" como se quiera
		Order o = getCurrentOrder(userID);
		
		o.setFirstName(firstName);
		o.setLastName(lastName);
		o.setEmail(email);
		o.setPhoneNumber(phoneNumber);
		o.setAddress(address);
		o.setAddressDetails(addressDetails);
		o.setPostalCode(postalCode);
		o.setTown(town);
		o.setProvince(province);
		
		o.setStatus(OrderStatuses.IN_PROCESS);
		
		entityManager.merge(o);
	}

	@Override
	public void processStep2(int userID, String shippingNotes, String contactName, String contactPhone,
			String contactEmail) {
		Order o = getCurrentOrder(userID);
		
		o.setShippingNotes(shippingNotes);
		o.setContactName(contactName);
		o.setContactPhone(contactPhone);
		o.setContactEmail(contactEmail);
		
		entityManager.merge(o);
	}

	@Override
	public void processStep3(int userID, String cardType, String cardHolder, String cardNumber, String cardExpiration,
			String cardCvv) {
		Order o = getCurrentOrder(userID);
		
		o.setCardType(cardType);
		o.setCardHolder(cardHolder);
		o.setCardNumber(cardNumber);
		o.setCardExpiration(cardExpiration);
		o.setCardCvv(cardCvv);
		
		entityManager.merge(o);		
	}

	@Override
	public OrderSummary getOrderSummary(int userID) {
		OrderSummary summary = new OrderSummary();
		Order o = getCurrentOrder(userID);
		
		// Data from step 1
		summary.setFirstName(o.getFirstName());
		summary.setLastName(o.getLastName());
		summary.setEmail(o.getEmail());
		summary.setPhoneNumber(o.getPhoneNumber());
		summary.setAddress(o.getAddress());
		summary.setAddressDetails(o.getAddressDetails());
		summary.setPostalCode(o.getPostalCode());
		summary.setTown(o.getTown());
		summary.setProvince(o.getProvince());
		
		// Data from step 2
		summary.setShippingNotes(o.getShippingNotes());
		summary.setContactName(o.getContactName());
		summary.setContactPhone(o.getContactPhone());
		summary.setContactEmail(o.getContactEmail());
		
		// Data from step 3
		summary.setCardType(o.getCardType());
		summary.setCardHolder(o.getCardHolder());
		summary.setCardExpiration(o.getCardExpiration());
		summary.setCardCvv(o.getCardCvv());
		
		String cardNumberTruncate = "****" + o.getCardNumber().substring(o.getCardNumber().length() - 4);
		summary.setCardNumber(cardNumberTruncate);
		
		// Se agregan las peliculas al resumen
		summary.setCartProducts(cartService.getCartProducts(userID));
		
		return summary;
	}

	@Override
	public void confirmOrder(int userID) {
		Order o = getCurrentOrder(userID);
		User uDB = (User)entityManager.find(User.class, userID);
		Cart c = uDB.getCart();
		
		// Pasar los productos del carrito al pedido
		if (c != null && c.getCartProducts().size() > 0) {
			for (CartProduct cp : c.getCartProducts()) {
				OrderProduct op = new OrderProduct();
				
				op.setQuantity(cp.getQuantity());
				op.setMovie(cp.getMovie());
				
				o.getOrderProducts().add(op);
				op.setOrder(o);
				
				entityManager.persist(op);
			}
		}
		
		// borrar los productos del carrito del usuario
		Query q = entityManager.createNativeQuery(SQLConstantsMovies.DELETE_CART_PRODUCTS);
		q.setParameter("cart_id", c.getId());
		q.executeUpdate();
		
		o.setStatus(OrderStatuses.FINISHED);
		
		entityManager.merge(o);
	}
	
    public Order getCurrentOrder(int userID) {
        User u = entityManager.find(User.class, userID);
		Object orderInProcess = null;

		@SuppressWarnings("unchecked")
		// Preguntar a Ares por qué es o.user.id y no o.user_id
		List<Order> queryResult = entityManager.createQuery(
				"select o from orders o where o.status = :status and o.user.id = :user_id")
				.setParameter("status", OrderStatuses.IN_PROCESS)
				.setParameter("user_id", u.getId()).getResultList();
		
		if (queryResult.size() == 1) {
			orderInProcess = queryResult.get(0);
			
		} else {
			orderInProcess = null;
		}
		
		Order o = null;
		if (orderInProcess != null ) {
			o = (Order)orderInProcess;
			
		} else {
			o = new Order();
			o.setUser(u);;
		}	
		
		return o;
    }

}