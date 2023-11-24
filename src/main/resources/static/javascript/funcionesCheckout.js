//funciones de los pasos para el check out
function checkout_paso_0(){
	//mostrar al usuario un formulario donde insertar la informacion de envio
	$("#contenedor").html(plantillaCheckout_1);
	$("#checkout1_aceptar").click(checkout_paso_1_aceptar); 	
}//end checkout_paso_0

function checkout_paso_1_aceptar(){
	//recoger los valores introducidos y mandarlos al servidor
	let nombre = $("#nombre").val();
	let apellidos = $("#apellidos").val();
	let email = $("#email").val();
	let telefono = $("#telefono").val();
	let direccion = $("#direccion").val();
	let detallesDireccion = $("#detallesDireccion").val();
	let codigoPostal = $("#codigoPostal").val();
	let localidad = $("#localidad").val();
	let provincia = $("#provincia").val();
	
	//ahora lo suyo seria validar los valores recogidos
	//...
	
	//mandar los valores al servicio web de pedidos
	$.post("webServiceOrders/step1", {
        firstName: nombre,
        lastName: apellidos,
        email: email,
        phoneNumber: telefono,
        address: direccion,
        addressDetails: detallesDireccion,
        postalCode: codigoPostal,
        town: localidad,
        province: provincia
        
	}).done(function(res){
		if (res == "ok"){
			$("#contenedor").html(plantillaCheckout_2)
			$("#checkout2_aceptar").click(checkout_paso_2_aceptar)
			
		} else {
			alert(res);
		}
	});//end done	
	
}//end checkout_paso_1_aceptar

function checkout_paso_2_aceptar() {
	let notasEnvio = $("#notasEnvio").val();
	let nombreContacto = $("#nombreContacto").val();
	let telefonoContacto = $("#telefonoContacto").val();
	let emailContacto = $("#emailContacto").val();
	
	$.post("webServiceOrders/step2", {
		shippingNotes: notasEnvio,
		contactName: nombreContacto,
		contactPhone: telefonoContacto,
		contactEmail: emailContacto
		
	}).done(function(res) {
		if (res == "ok"){
			$("#contenedor").html(plantillaCheckout_3)
			$("#checkout3_aceptar").click(checkout_paso_3_aceptar)
			
		} else {
			alert(res);
		}
		
	}) // end done post
	
} // end checkout_paso_2_aceptar

function checkout_paso_3_aceptar() {
	let tipoTarjeta = $("#tipoTarjeta").val();
	let titularTarjeta = $("#titularTarjeta").val();
	let numeroTarjeta = $("#numeroTarjeta").val();
	let caducidadTarjeta = $("#caducidadTarjeta").val();
	let cvvTarjeta = $("#cvvTarjeta").val();
	
	$.post("webServiceOrders/step3", {
		cardType: tipoTarjeta,
		cardHolder: titularTarjeta,
		cardNumber: numeroTarjeta,
		cardExpiration: caducidadTarjeta,
		cardCvv: cvvTarjeta
		
	}).done(function(res) {
		let resumenPedido = res
		let html = Mustache.render(plantillaCheckout_4, resumenPedido)
		
		$("#contenedor").html(html)
		
		mostrarSubtotalYTotalEnResumenPedido()
		
		$("#boton_confirmar_pedido").click(function() {
			$.ajax("webServiceOrders/step4", {
				success: function(res) {
					alert("Respuesta del servicio web: " + res)
					mostrar_movies()
				}
			})
		})
		
	}) // end done post
	
} // end checkout_paso_3_aceptar





