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

//
// Clicks dentro de plantillas mustache:
//

//// Clicks dentro de plantilla movies:

// Buscador de movies
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
				
				actualizarSubtotalYTotalEnCarrito();
			}
		}) // end ajax y done
		
	}) // end post y done
});

// Clicks botones sumar o restar cantidades en carrito
$("#contenedor").on("click", ".sumar", function() {
    let cantidadElement = $(this).closest('.producto').find('.cantidad');
    let cantidad = parseFloat(cantidadElement.text());
    cantidadElement.text((cantidad + 1).toString());
    
    actualizarSubtotalYTotalEnCarrito();
    actualizarCantidadesCarritoEnBD()
});

$("#contenedor").on("click", ".restar", function() {
    let cantidadElement = $(this).closest('.producto').find('.cantidad');
    let cantidad = parseFloat(cantidadElement.text());

    if (cantidad > 1) {
        cantidadElement.text((cantidad - 1).toString());
        
        actualizarSubtotalYTotalEnCarrito();
        actualizarCantidadesCarritoEnBD()
    }
});

// Click en realizar pedido
$("#contenedor").on("click", "#realizar_pedido", function() {
	checkout_paso_0()
});