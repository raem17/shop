package movies.shop.data.webServices;

import java.util.List;
import java.util.Map;

public class OrderSummary {
	
	// productos que hay en el carrito
	private List<Map<String, Object>> cartProducts;
	
    // Data from step 1
    private String firstName;  // Nombre
    private String lastName;  // Apellidos
    private String email;  // Email
    private String phoneNumber;  // Teléfono
    private String address;  // Dirección
    private String addressDetails;  // Detalles de la dirección
    private int postalCode;  // Código postal
    private String town;  // Localidad
    private String province;  // Provincia
    
    // Data from step 2
    private String shippingNotes;  // Notas de envío
    private String contactName;  // Nombre de contacto
    private String contactPhone;  // Teléfono de contacto
    private String contactEmail;  // Email de contacto
    
    // Data from step 3
    private String cardType;  // Tipo de tarjeta
    private String cardHolder;  // Titular de la tarjeta
    private String cardNumber;  // Número de tarjeta
    private String cardExpiration;  // Caducidad de la tarjeta
    private int cardCvv;  // CVV de la tarjeta
	
	public OrderSummary() {
		
	}

	public OrderSummary(List<Map<String, Object>> cartProducts, String firstName, String lastName, String email,
			String phoneNumber, String address, String addressDetails, int postalCode, String town, String province,
			String shippingNotes, String contactName, String contactPhone, String contactEmail, String cardType,
			String cardHolder, String cardNumber, String cardExpiration, int cardCvv) {
		super();
		this.cartProducts = cartProducts;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.addressDetails = addressDetails;
		this.postalCode = postalCode;
		this.town = town;
		this.province = province;
		this.shippingNotes = shippingNotes;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
		this.cardType = cardType;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.cardExpiration = cardExpiration;
		this.cardCvv = cardCvv;
	}

	public List<Map<String, Object>> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<Map<String, Object>> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getShippingNotes() {
		return shippingNotes;
	}

	public void setShippingNotes(String shippingNotes) {
		this.shippingNotes = shippingNotes;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(String cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public int getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(int cardCvv) {
		this.cardCvv = cardCvv;
	}
	
}
