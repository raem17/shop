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

// Click en comprar
$("#contenedor").on("click", ".enlaceComprarProducto", async function() {
	let modal = $("#myModal");	
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
		modal.addClass("active");
	}
});

// Click en ver detalles
$("#contenedor").on("click", ".verDetallesProducto", function() {
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
    let cantidadElement = $(this).closest('.product').find('.quantity');
    let cantidad = parseFloat(cantidadElement.data('quantity'));
    let nuevaCantidad = cantidad + 1
    
    cantidadElement.data("quantity", nuevaCantidad);
    cantidadElement.text(nuevaCantidad.toString());
    
    actualizarSubtotalYTotalEnCarrito();
    actualizarCantidadesCarritoEnBD()
});

$("#contenedor").on("click", ".restar", function() {
    let cantidadElement = $(this).closest('.product').find('.quantity');
    let cantidad = parseFloat(cantidadElement.data('quantity'));

    if (cantidad > 1) {
		let nuevaCantidad = cantidad - 1
		
	    cantidadElement.data("quantity", nuevaCantidad);
	    cantidadElement.text(nuevaCantidad.toString());
        
        actualizarSubtotalYTotalEnCarrito();
        actualizarCantidadesCarritoEnBD()
    }
});

// Click en realizar pedido
$("#contenedor").on("click", "#realizar_pedido", function() {
	checkout_paso_0()
});