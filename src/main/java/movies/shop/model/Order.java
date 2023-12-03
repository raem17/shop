package movies.shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name="orders")
public class Order {
	
	/* READ ME
	Problema:
	El código presentaba un problema al procesar el paso 1 del formulario del check-out debido a las validaciones de campos que aún 
	no se habían completado en pasos posteriores. En particular, las anotaciones @NotBlank estaban causando errores, ya que se 
	aplicaban a campos que no estaban destinados a ser completados en el paso actual.
	
	Solución:
	Se resolvió el problema eliminando las anotaciones @NotBlank
	
	Nota:
	Cuando se trabaje con formularios divididos en varios pasos y campos que se completan progresivamente, es crucial adaptar las validaciones 
	para cada paso. Evitar la aplicación de validaciones innecesarias en campos no relevantes para el paso actual puede mejorar la usabilidad y 
	evitar errores prematuros. Considerar el uso de grupos de validación o cambiar el enfoque de validación según el flujo del formulario puede 
	ser una estrategia eficaz para abordar este tipo de situaciones.
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
    private String status;  // Estado

    // Data from step 1
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras y los caracteres: çÇ-")
    private String firstName;  // Nombre
	
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras y los caracteres: çÇ-")
    private String lastName;  // Apellidos
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Ingrese una dirección de correo electrónico válida.")
    private String email;  // Email
	
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@Pattern(regexp = "^(\\+34)?\\d{9}$", message = "Ingrese un número de teléfono válido en España.")
	private String phoneNumber;  // Teléfono en España
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑçÇº\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: çÇº-")
    private String address;  // Dirección
    
	@Size(min = 1, max = 250, message = "El campo debe tener entre 1 y 250 caracteres.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?çÇº.,\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ¡!&()¿?çÇº.,-:")
    private String addressDetails;  // Detalles de la dirección
    
	@Size(min = 5, max = 5, message = "El campo debe tener exactamente 5 dígitos.")
	@Pattern(regexp = "^\\d{5}$", message = "Ingrese un código postal válido en España.")
	private String postalCode;  // Código postal en España

	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras y los caracteres: çÇ-")
    private String town;  // Localidad
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras y los caracteres: çÇ-")
    private String province;  // Provincia
    
    
    // Data from step 2
	@Size(min = 1, max = 250, message = "El campo debe tener entre 1 y 250 caracteres.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ¡!&()¿?çÇº.,\\-:]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: ¡!&()¿?çÇº.,-:")
    private String shippingNotes;  // Notas de envío
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras y los caracteres: çÇ-")
    private String contactName;  // Nombre de contacto
    
	// ¡Aviso! Este campo no se valida con la anotación @Pattern, sino en su setter
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
    private String contactPhone;  // Teléfono de contacto
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Ingrese una dirección de correo electrónico válida.")
    private String contactEmail;  // Email de contacto
    
    
    // Data from step 3
	@Size(min = 1, max = 50, message = "El campo debe tener entre 1 y 50 caracteres.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑ\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: -")
    private String cardType;  // Tipo de tarjeta
    
	@Size(min = 1, max = 150, message = "El campo debe tener entre 1 y 150 caracteres.")
	@Pattern(regexp = "[A-Za-z0-9 áéíóú´ÁÉÍÓÚñÑçÇ\\-]+", message = "El campo solo puede contener letras, "
			+ "números y los caracteres: çÇ-")
    private String cardHolder;  // Titular de la tarjeta
    
	@Size(min = 13, max = 20, message = "El campo debe tener entre 13 y 20 caracteres.")
	@Pattern(regexp = "^\\d{13,20}$", message = "Ingrese un número de tarjeta válido sin guiones.")    
	private String cardNumber;  // Número de tarjeta
    
	@Size(min = 5, max = 5, message = "El campo debe tener este formato: 01/25.")
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[1-9][0-9])$", message = "Ingrese una fecha de expiración de tarjeta válida (MM/YY).")
    private String cardExpiration;  // Caducidad de la tarjeta
    
	@Size(min = 3, max = 3, message = "El campo debe tener exactamente 3 dígitos.")
	@Pattern(regexp = "^\\d{3}$", message = "Ingrese un código CVV de tarjeta válido.")
	private String cardCvv;  // CVV de la tarjeta


	// se pide en paso 3
	@ManyToOne(targetEntity = User.class, optional = false)
	private User user;
	
	public Order() {
		
	}

	/**
	 * Data from step 1
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param address
	 * @param addressDetails
	 * @param postalCode
	 * @param town
	 * @param province
	 */
	public Order(String firstName, String lastName, String email, String phoneNumber, String address,
			String addressDetails, String postalCode, String town, String province) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.addressDetails = addressDetails;
		this.postalCode = postalCode;
		this.town = town;
		this.province = province;
	}

	/**
	 * Data from step 2
	 * @param shippingNotes
	 * @param contactName
	 * @param contactPhone
	 * @param contactEmail
	 */
	public Order(String shippingNotes, String contactName, String contactPhone, String contactEmail) {
		super();
		this.shippingNotes = shippingNotes;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.contactEmail = contactEmail;
	}

	/**
	 * Data from step 3
	 * @param cardType
	 * @param cardHolder
	 * @param cardNumber
	 * @param cardExpiration
	 * @param cardCvv
	 */
	public Order(String cardType, String cardHolder, String cardNumber, String cardExpiration, String cardCvv) {
		super();
		this.cardType = cardType;
		this.cardHolder = cardHolder;
		this.cardNumber = cardNumber;
		this.cardExpiration = cardExpiration;
		this.cardCvv = cardCvv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
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

	public void setContactPhone(String contactPhone) throws IllegalArgumentException {
		if (contactPhone.matches("^(\\+34)?\\d{9}$")) {
			this.contactPhone = contactPhone;
		} else {
			throw new IllegalArgumentException("Invalid contactPhone argument.");
		}
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

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
