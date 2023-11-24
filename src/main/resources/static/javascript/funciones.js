function gestionarSesionUsuario() {
	let emailCookie = Cookies.get("email");
	
	if (emailCookie !== undefined) {
		let passCookie = Cookies.get("pass");
		
		// Si el usuario tiene cookies: cada vez que refresque la página o cierre la pestaña
		// y vuelva a la página, se meterá su usuario en la sesión del navegador.
		// La línea de abajo es necesaria porque al cerrar la pestaña se cierra su sesión tenga cookies o no.
		$.post("webServiceUsers/userLogin", { email: emailCookie, pass: passCookie } )
		
		nombre_login = Cookies.get("username")
	  	$("#mensaje_login").html("Identificado como: " + nombre_login)
	  	
	} else {
		// Si el usuario no tiene cookies porque no ha marcado la opción de recordar datos,
		// se cerrará cualquier sesión anterior cada vez que se refresque la página
		$.ajax("webServiceUsers/logout")
		
		nombre_login = ""
		$("#mensaje_login").html("Usuario no registrado.")
	}
}

// Método para saber si un usuario existe en la sesión
async function existeUsuarioEnSesion() {
    try {
        let res = $.ajax("webServiceUsers/userExistsInSession");
        return res;
        
    } catch (error) {
        throw error;
    }
}

function mostrar_movies() {	
	$.ajax("webServiceMovies/getMovies").done(function(res) {
		let texto_html = "";
	
		for(let i in res) {
			res[i].price = res[i].price.toString().replace(".", ",");
		}
		
		texto_html = Mustache.render(plantillaMovies, res);
		$("#contenedor").html(texto_html);		
		
	}); // end ajax y done servicioWebMovies/obtenerMovies
}

function actualizarSubtotalYTotalEnCarrito() {
    let total = 0;

    $('.producto').each(function() {
        let cantidad = parseFloat($(this).find('.cantidad').text());
        let precio = parseFloat($(this).find('.precio').data('precio'));

        let subtotal = cantidad * precio;
        $(this).find('.subtotal').text(subtotal.toFixed(2));

        total += subtotal;
    });

    $('#total').text(total.toFixed(2));
}

function actualizarCantidadesCarritoEnBD() {
	let spans = $(".cantidad");
	
	// Dejar el argumento index, sino da error
	// Se actualiza la cantidad de cada producto
	spans.each(function(index, span) {
		let idMovie = $(span).attr("idMovie");
	    let cantidad = $(span).text();
	    
	    $.post("webServiceCart/updateProductQuantity", { movieID: idMovie, quantity: cantidad })
	});
}