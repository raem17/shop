package movies.shop.services;

import java.util.List;

import movies.shop.data.webServices.OrderSummary;
import movies.shop.model.Order;

public interface OrdersService {

    // Administration management
    List<Order> getOrders();
    
    Order getOrderById(int orderID);
    
    void updateOrderStatus(int orderID, String newStatus);
    
    // Ajax functions
    void processStep1(int userID, String firstName, String lastName, String email, String phoneNumber, String address, 
        String addressDetails, int postalCode, String town, String province);
    
    void processStep2(int userID, String shippingNotes, String contactName, String contactPhone, String contactEmail);
    
    void processStep3(int userID, String cardType, String cardHolder, String cardNumber, String cardExpiration, 
        int cardCvv);
    
    OrderSummary getOrderSummary(int userID);
    
    void confirmOrder(int userID);
}
