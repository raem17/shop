//funciones de los pasos para el check out
function checkout_paso_0(){
	//mostrar al usuario un formulario donde insertar la informacion de envio
	$("#contenedor").html(plantillaCheckout_1);
	
	$("#checkout1_aceptar").click(checkout_paso_1_aceptar);
}//end checkout_paso_0

function checkout_paso_1_aceptar(e) {
    let errores = $(this).closest("form.formBox").find("span.error").length
    let inputs = $(this).closest("form.formBox").find("input")
    let textareas = $(this).closest("form.formBox").find("textarea")
    let campoVacio = false
    let spanMsg = $(".submitErrorMsg")

    inputs.each(function(index, input) {
        if ($(input).val().trim() == "") {
            campoVacio = true
            $(input).closest('div.form-group').find('span').addClass("error")
        }
    })

    textareas.each(function(index, textarea) {
        if ($(textarea).val().trim() == "") {
            campoVacio = true
            $(textarea).closest('div.form-group').find('span').addClass("error")
        }
    })

    if (errores == 0 && campoVacio == false) {
        // Aquí estaría la acción del botón teniendo todos los campos correctos
        spanMsg.text("")
		//recoger los valores introducidos y mandarlos al servidor
		let nombre = $("#nombre").val().trim();
		let apellidos = $("#apellidos").val().trim();
		let email = $("#email").val().trim();
		let telefono = $("#telefono").val().trim();
		let direccion = $("#direccion").val().trim();
		let detallesDireccion = $("#detallesDireccion").val().trim();
		let codigoPostal = $("#codigoPostal").val().trim();
		let localidad = $("#localidad").val().trim();
		let provincia = $("#provincia").val().trim();
		
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
        
    } else {
        spanMsg.text("Rellene correctamente todos los campos.")
    }	
	
	e.preventDefault();
	
}//end checkout_paso_1_aceptar

function checkout_paso_2_aceptar(e) {
    let errores = $(this).closest("form.formBox").find("span.error").length
    let inputs = $(this).closest("form.formBox").find("input")
    let textareas = $(this).closest("form.formBox").find("textarea")
    let campoVacio = false
    let spanMsg = $(".submitErrorMsg")

    inputs.each(function(index, input) {
        if ($(input).val().trim() == "") {
            campoVacio = true
            $(input).closest('div.form-group').find('span').addClass("error")
        }
    })

    textareas.each(function(index, textarea) {
        if ($(textarea).val().trim() == "") {
            campoVacio = true
            $(textarea).closest('div.form-group').find('span').addClass("error")
        }
    })

    if (errores == 0 && campoVacio == false) {
        // Aquí estaría la acción del botón teniendo todos los campos correctos
        spanMsg.text("")
        
		let notasEnvio = $("#notasEnvio").val().trim();
		let nombreContacto = $("#nombreContacto").val().trim();
		let telefonoContacto = $("#telefonoContacto").val().trim();
		let emailContacto = $("#emailContacto").val().trim();
		
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
        
    } else {
        spanMsg.text("Rellene correctamente todos los campos.")
    }	
	
	e.preventDefault();
	
} // end checkout_paso_2_aceptar

function checkout_paso_3_aceptar(e) {
    let errores = $(this).closest("form.formBox").find("span.error").length
    let inputs = $(this).closest("form.formBox").find("input")
    let textareas = $(this).closest("form.formBox").find("textarea")
    let campoVacio = false
    let spanMsg = $(".submitErrorMsg")

    inputs.each(function(index, input) {
        if ($(input).val().trim() == "") {
            campoVacio = true
            $(input).closest('div.form-group').find('span').addClass("error")
        }
    })

    textareas.each(function(index, textarea) {
        if ($(textarea).val().trim() == "") {
            campoVacio = true
            $(textarea).closest('div.form-group').find('span').addClass("error")
        }
    })

    if (errores == 0 && campoVacio == false) {
        // Aquí estaría la acción del botón teniendo todos los campos correctos
        spanMsg.text("")
        
		let tipoTarjeta = $("#tipoTarjeta").val().trim();
		let titularTarjeta = $("#titularTarjeta").val().trim();
		let numeroTarjeta = $("#numeroTarjeta").val().trim();
		let caducidadTarjeta = $("#caducidadTarjeta").val().trim();
		let cvvTarjeta = $("#cvvTarjeta").val().trim();
		
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
						alert(res)
						mostrar_movies()
					}
				})
			})
			
		}) // end done post        
        
    } else {
        spanMsg.text("Rellene correctamente todos los campos.")
    }	
    
	e.preventDefault();
	
} // end checkout_paso_3_aceptar

