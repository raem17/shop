//
// Clicks dentro de inicio.html (nav):
//

$("#inicio").click(function(){
	$("#contenedor").html(plantillaInicio);
});

$("#movies").click(mostrar_movies);

$("#registrarme").click(function(){
	$("#contenedor").html(plantillaRegistro);
	
	$("#form_registro_usuario").submit(function (e) {
			let formulario = document.forms[0];
			let formData = new FormData(formulario);
			
			$.ajax("webServiceUsers/userRegister",{
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
	            success: function(e) {
	                // Manejar la respuesta del servidor
	                alert("Te has registrado correctamente: " + e);
	            },
	            error: function(error) {
	                // Manejar errores
	                alert("Error: " + error);
	            }
			})
			
			// evitar envio de form, todo el cliente se gestiona con javascript
			e.preventDefault()
		}
	) // end registro
});

$("#login").click(function() {
	$("#contenedor").html(plantillaLogin);
	
	$("#form_login").submit(function(e) {
		$.post("webServiceUsers/userLogin", { email: $("#email").val(), pass: $("#pass").val() } ).done(function(res) {
			if (res.split(", ")[0] == "ok") {
				nombre_login = res.split(", ")[1];
				$("#mensaje_login").html("Identificado como: " + nombre_login)
				
				if ( $("#recordar_datos").prop('checked') ) {
					// si esto se cumple es que se dejo activado el checkbox de recordar datos
					Cookies.set("email", $("#email").val(), {expires: 100} )
					Cookies.set("pass", $("#pass").val(), {expires: 100} )
					Cookies.set("username", nombre_login, {expires: 100} )
				}
				
			} else {
				// El usuario no ha introducido datos válidos
				alert(res)
			}
			
		}) // end post y .done
		
		// evitar envio de form, todo el cliente se gestiona con javascript
		e.preventDefault()
	}); // end submit form_login
	
}) // end click login
	
$("#carrito").click(async function() {
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
		alert("Identificate para acceder al carrito.")
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
				
			} // fin de if
		} // fin de success
	}) // fin de ajax
})