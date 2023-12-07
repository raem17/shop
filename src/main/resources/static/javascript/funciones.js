function gestionarSesionUsuario() {
	let emailCookie = Cookies.get("email");
	
	if (emailCookie !== undefined) {
		let passCookie = Cookies.get("pass");
		
		// Si el usuario tiene cookies: cada vez que refresque la página o cierre la pestaña
		// y vuelva a la página, se meterá su usuario en la sesión del navegador.
		// La línea de abajo es necesaria porque al cerrar la pestaña se cierra su sesión tenga cookies o no.
		$.post("webServiceUsers/userLogin", { email: emailCookie, pass: passCookie } )
		
		nombre_login = Cookies.get("username")
		userID = Cookies.get("id")
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

let movieTitle = "";
let resultsStart = 0;
function mostrar_movies(){
	$.getJSON("webServiceMovies/getMovies",{ title : movieTitle, start : resultsStart }).done(function(res) {
		let texto_html = "";
		let movies = res.movies;

		texto_html = Mustache.render(plantillaMovies, movies);
		$("#contenedor").html(texto_html);
		
		// Bloque de código relativo al buscador
		
		$("#searchTitle").val(movieTitle);
		$("#searchTitle").focus();
	
		// Boton buscar
		$("#buttonSearchMovie").click(function() {
			movieTitle = $("#searchTitle").val();
			resultsStart = 0; //resetea paginacion
			mostrar_movies();
		});	
		
		if (resultsStart <= 0 ){
			$("#previousMovies").hide();
			
		} else{
			$("#previousMovies").show();
		}
		
		// Mostrar total de resultados
		let totalMovies = res.total;
		$("#movieTotalResults").html(totalMovies);
		
		if ((resultsStart + 10 ) < totalMovies){
			$("#nextMovies").show();
			
		} else {
			$("#nextMovies").hide();
		}
		
		// Clicks en anterior y siguiente	
		$("#previousMovies").click(function(){
			resultsStart -= 10;
			mostrar_movies();
		});
		
		$("#nextMovies").click(function(){
			resultsStart += 10;
			mostrar_movies();
		});					
		
	});//end getJSON
	
}//end mostrar_movies

function actualizarSubtotalYTotalEnCarrito() {
    let total = 0;

    $('.producto').each(function() {
        let cantidad = parseFloat($(this).find('.cantidad').data('cantidad'));
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
		let idMovie = $(span).data('id');
	    let cantidad = $(span).data('cantidad');
	    
	    $.post("webServiceCart/updateProductQuantity", { movieID: idMovie, quantity: cantidad })
	});
}

function mostrarSubtotalYTotalEnResumenPedido() {
    let total = 0;

    $('.productoPedido').each(function() {
        let cantidad = parseFloat($(this).find('.cantidad').data('cantidad'));
        let precio = parseFloat($(this).find('.precio').data('precio'));

        let subtotal = cantidad * precio;
        $(this).find('.subtotal').text(subtotal.toFixed(2));

        total += subtotal;
    });

    $('#totalPedido').text(total.toFixed(2));
}

function mostrarNombreUsuario() {
	$("#userName").text(nombre_login)
}

function mostrarAvatarUsurio() {
	$("#userAvatar").attr("src","showUserImage?id=" + userID);
}

function gestionarModal() {
  let closeModalBtn = $("#closeModalBtn");
  let modal = $("#myModal");

  closeModalBtn.click(function () {
    modal.removeClass("active")
  });

  $(window).click(function (event) {
    if (event.target === modal[0]) {
    	modal.removeClass("active")
    }
  });	
}








