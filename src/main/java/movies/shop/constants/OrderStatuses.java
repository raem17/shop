package movies.shop.constants;

public class OrderStatuses {
	
	//el usuario ha iniciado un proceso de formar pedido
	public static final String IN_PROCESS = "in process";
	
	//el usuario ha finalizado un pedido y un administrador podra gestionarlo
	public static final String FINISHED = "finished";
	
	//un administrador ha preparado "fisicamente" el envio para ser recogido
	//por la empresa de mensajeria
	public static final String READY_TO_SHIP = "ready to ship";
	
	//la empresa de mensajaria ha confirmado la recepcion del pedido
	public static final String RECEIVED_BY_CUSTOMER = "received by customer";

}