//
// Clicks dentro de inicio.html (nav):
//

$("#logoTienda").click(function(){
	mostrar_movies()
});

$("#movies").click(mostrar_movies);

$("#registrarme").click(function(){
	let modal = $("#myModal")
	modal.removeClass("active")
	
	$("#contenedor").html(plantillaRegistro);
	
	$("#form_registro_usuario").submit(function (e) {
	    let errores = $(this).closest("form.formBox").find("span.error").length
	    let inputs = $(this).closest("form.formBox").find('div.field').find("input")
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
			let formulario = document.forms[0];
			let formData = new FormData(formulario);
			
			// Crear un nuevo objeto FormData para almacenar los datos actualizados
			let formDataActualizado = new FormData();
			
			// Recorre todos los elementos del formulario para enviar los datos al servidor con un .trim()
			for (let pair of formData) {
			    // pair[0] es el nombre del campo, pair[1] es el valor
			    let fieldName = pair[0];
			    let originalValue = pair[1];
			
			    // Verifica si el campo no es de tipo 'file'
			    if (formulario.elements[fieldName].type !== 'file') {
			        // Aplica trim al valor solo si no es un campo de tipo 'file'
			        let trimmedValue = originalValue.trim();
			
			        // Agrega el par clave/valor actualizado al nuevo FormData
			        formDataActualizado.append(fieldName, trimmedValue);
			    } else {
			        // Si es un campo de tipo 'file', simplemente copia el par clave/valor al nuevo FormData
			        formDataActualizado.append(fieldName, originalValue);
			    }
			}			
			
			$.ajax("webServiceUsers/userRegister",{
				type: "POST",
				data: formDataActualizado,
				cache: false,
				contentType: false,
				processData: false,
	            success: function() {
	                // Manejar la respuesta del servidor
	                alert("Te has registrado correctamente");
	            },
	            error: function() {
	                // Manejar errores
	                alert("No has podido registrarte, revisa tus datos.");
	            }
			})	        
	        
	    } else {
	        spanMsg.text("Rellene correctamente todos los campos.")
	    }		
		
		// evitar envio de form, todo el cliente se gestiona con javascript
		e.preventDefault()
		}
	) // end registro
});

$("#login").click(function() {
	let modal = $("#myModal")
	modal.removeClass("active")	
	
	$("#contenedor").html(plantillaLogin);
	
	$("#form_login").submit(function(e) {
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
	        
			$.post("webServiceUsers/userLogin", { email: $("#email").val().trim(), pass: $("#pass").val().trim() } ).done(function(res) {
				if (res.split(";")[0] == "ok") {
					userID = res.split(";")[1];
					nombre_login = res.split(";")[2];
					$("#mensaje_login").html("Identificado como: " + nombre_login)
					
					if ( $("#recordar_datos").prop('checked') ) {
						// si esto se cumple es que se dejo activado el checkbox de recordar datos
						Cookies.set("email", $("#email").val(), {expires: 100} )
						Cookies.set("pass", $("#pass").val(), {expires: 100} )
						Cookies.set("username", nombre_login, {expires: 100} )
						Cookies.set("id", userID, {expires: 100} )
					}
					
					location.reload()				
					
				} else {
					// El usuario no ha introducido datos válidos
					alert(res)
				}
				
			}) // end post y .done	        
	        
	    } else {
	        spanMsg.text("Rellene correctamente todos los campos.")
	    }		
		
		// evitar envio de form, todo el cliente se gestiona con javascript
		e.preventDefault()
	}); // end submit form_login
	
}) // end click login
	
$("#cart").click(async function() {
	let modal = $("#myModal");		
	let existeUsuario = await existeUsuarioEnSesion()
	
	if (existeUsuario == "true") {
		$.ajax("webServiceCart/getCartProducts").done(function(res) {
			if (res == "") {
				$("#contenedor").html("No hay productos en el carrito.")
				
			} else {
				let html = Mustache.render(plantillaCarrito, res);
				$("#contenedor").html(html)		
				
				actualizarSubtotalYTotalEnCarrito();
			}
		
		}) // end ajax y done
		
	} else {
		modal.addClass("active");
	}
	
}) // end carrito click

$("#logout").click(function name() {
	$.ajax("webServiceUsers/logout", {
		success: function (res) {
			if(res == "ok") {
				$("#contenedor").html("Hasta pronto " + nombre_login)
				$("#mensaje_login").html("Usuario no registrado.")
				
				nombre_login = ""
				Cookies.remove('email')
				Cookies.remove('pass')
				Cookies.remove('username')
				Cookies.remove('id')
				
				location.reload()
				
			} // fin de if
		} // fin de success
	}) // fin de ajax
})

$("#account").click(async function() {
  	let modal = $("#myModal");	
	let existeUsuario = await existeUsuarioEnSesion()
	
	if (existeUsuario == "true") {
    	$("#user-menu").addClass("toggle")
		
	} else {
	    modal.addClass("active");
	}	
})

$("#close-user-menu").click(function() {
    $("#user-menu").removeClass("toggle")    
}) 












