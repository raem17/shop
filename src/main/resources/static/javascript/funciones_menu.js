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

function mostrar_movies() {
	$.ajax("webServiceMovies/getMovies").done(function(res) {
		let texto_html = "";
	
		for(let i in res) {
			res[i].fecha_hora_actual = new Date();
			res[i].price = res[i].price.toString().replace(".", ",");
		}
		
		texto_html = Mustache.render(plantillaMovies, res);
		$("#contenedor").html(texto_html);		
		
	}); // end ajax y done servicioWebMovies/obtenerMovies
}

//
// Clicks dentro de inicio.html (nav):
//

$("#inicio").click(function(){
	$("#contenedor").html(plantillaInicio);
});

$("#movies").click(mostrar_movies);

$("#registrarme").click(function(){
	$("#contenedor").html(plantillaRegistro);
	
	$("#form_registro_usuario").submit(
		function (e) {
			let formulario = document.forms[0];
			let formData = new FormData(formulario);
			$.ajax("webServiceUsers/userRegister",{
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(res) {
					alert("Se ha registrado correctamente.");
				} // end success
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
				
				actualizarSubtotalYTotal();
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

//
// Clicks dentro de plantillas mustache:
//

/* Ejemplo de cómo usar la función .on() de jQuery

Ejemplo 1:

	$("div#contenedor").on("click", ".enlace_comprar_listado_principal", function() {
		alert("hola");
	});

En el primer caso, se está utilizando el selector "div#contenedor". Esto selecciona el elemento con el ID "contenedor". 
Luego, el método on asocia un controlador de eventos a los elementos dentro de "contenedor" que coinciden con la clase ".enlace_comprar_listado_principal". 
Esto funciona correctamente y activará el controlador de eventos cuando se haga clic en un elemento con esa clase dentro de "contenedor".

///

Ejemplo 2:

	$("div.card").on("click", ".enlace_comprar_listado_principal", function() {
		alert("hola");
	});

En el segundo caso, se está utilizand el selector "div.card". Esto selecciona directamente los elementos con la clase ".card". 
Luego, se está intentando asociar un controlador de eventos a los elementos con la clase ".enlace_comprar_listado_principal" dentro de 
estos elementos ".card". Sin embargo, el problema aquí es que estos elementos ".card" no existen inicialmente en el DOM cuando la página se carga.
En su lugar, se generan dinámicamente a partir de la plantilla Mustache y se agregan a "contenedor". Dado que estos elementos ".card" no existen en el DOM 
en el momento de la vinculación del evento, el evento no se asociará correctamente.
*/

//// Clicks dentro de plantilla movies:

// Click en comprar
$("#contenedor").on("click", ".enlace_comprar_listado_principal", async function() {
	let id_producto = $(this).attr("id-producto");
	let existeUsuario = await existeUsuarioEnSesion()
	
	if (existeUsuario == "true") {
		$.post("webServiceCart/addMovie", { movieID: id_producto, quantity: 1 })
		.done(function() {
			alert("Se ha añadido el producto a la cesta.")
		})
		
		.fail(function (res) {
			alert("Ha fallado la operación: " + res)
		})
	
	} else {
		alert("Tienes que identificarte para poder comprar productos.");
	}
});

// Click en ver detalles
$("#contenedor").on("click", ".enlace_ver_detalles_movie", function() {
	let id_producto = $(this).attr("id-producto");
	
	$.post("webServiceMovies/getMovieDetails", {id: id_producto}).done(function (res) {
		let html = Mustache.render(plantillaDetallesMovie, res)
		$("#contenedor").html(html)
	}) // end post y done
});

//// Clicks dentro de plantilla carrito:

// Click en eliminar producto
$("#contenedor").on("click", "#eliminarProducto", function() {
    let idMovie = $(this).attr("idMovie");
	
	$.post("webServiceCart/deleteMovie", {movieID: idMovie} ).done(function() {
		$.ajax("webServiceCart/getCartProducts").done(function(res) {
			if (res == "") {
				$("#contenedor").html("No hay productos en el carrito.")
				
			} else {
				let html = Mustache.render(plantillaCarrito, res);
				$("#contenedor").html(html)
				
				actualizarSubtotalYTotal();
			}
		}) // end ajax y done
		
	}) // end post y done
});

// Click en realizar pedido
$("#contenedor").on("click", "#realizar_pedido", function() {
	checkout_paso_0()
});

// Buscador
$("#contenedor").on("click", "#buttonSearchMovie", function() {
	let title = $("#searchTitle").val();
	
	$.ajax({
	    url: "webServiceMovies/getMoviesByTitle",
	    data: { title: title },
	    method: "GET",
	}).done(function(res) {
	    let texto_html = "";
	
	    for (let i in res) {
	        res[i].price = res[i].price.toString().replace(".", ",");
	    }
	
	    texto_html = Mustache.render(plantillaMovies, res);
	    $("#contenedor").html(texto_html);
	    
		$("#searchTitle").val(title);
	}); // end ajax
	
});


/////////////////////

function actualizarCantidadesEnBD() {
	let spans = $(".cantidad");
	
	// Dejar el argumento index, sino da error
	// Se actualiza la cantidad de cada producto
	spans.each(function(index, span) {
		let idMovie = $(span).attr("idMovie");
	    let cantidad = $(span).text();
	    
	    $.post("webServiceCart/updateProductQuantity", { movieID: idMovie, quantity: cantidad })
	});
}


$(document).ready(function() {
    actualizarSubtotalYTotal();
    setearListenersBotones();
});

function actualizarSubtotalYTotal() {
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

function setearListenersBotones() {
    $("#contenedor").on("click", ".sumar", function() {
        let cantidadElement = $(this).closest('.producto').find('.cantidad');
        let cantidad = parseFloat(cantidadElement.text());
        cantidadElement.text((cantidad + 1).toString());
        
        actualizarSubtotalYTotal();
        actualizarCantidadesEnBD()
    });

    $("#contenedor").on("click", ".restar", function() {
        let cantidadElement = $(this).closest('.producto').find('.cantidad');
        let cantidad = parseFloat(cantidadElement.text());

        if (cantidad > 1) {
            cantidadElement.text((cantidad - 1).toString());
            
            actualizarSubtotalYTotal();
            actualizarCantidadesEnBD()
        }
    });
}


