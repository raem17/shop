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

gestionarSesionUsuario()