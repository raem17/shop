// Validar nombres propios
$("div.form-group.properNoun").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^[a-zA-ZáéíóúüñçÇÁÉÍÓÚÜÑ\s\-]+$/u

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("El campo solo puede contener letras y los caracteres: çÇ-")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar emails
$("div.form-group.email").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese una dirección de correo electrónico válida.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar descripciones/detalles
$("div.form-group.description").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¡!&()¿?çÇº.,\\-\s:]+$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let textarea = field.find("textarea");
    let span = $(this).find('span')
    span.text("El campo solo puede contener letras, números y los caracteres: ¡!&()¿?çÇº.,-:")

    let textareaLength = 0;
    let textareaError = false;

    textarea.on("input", function() {
        let textareaValue = textarea.val().trim();
        textareaLength = textareaValue.length;

        if (textareaValue !== "" && regularExp.test(textareaValue)) {
            textareaError = false;

        } else {
            textareaError = true;
        }

        if (textareaError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            textarea.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            textarea.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    textarea.on("focus", function() {
        if (textareaError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    textarea.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (textareaLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar contraseñas
$("div.form-group.pw").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@#$%^&+=!¡?¿]{8,}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("La contraseña debe tener al menos 8 caracteres e incluir al menos una minúscula, una mayúscula y un dígito.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar teléfonos
$("div.form-group.phoneNumber").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^(\+34)?\d{9}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese un número de teléfono válido en España.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar direcciones
$("div.form-group.address").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^[A-Za-z0-9áéíóúÁÉÍÓÚñÑçÇº\s\\-]+$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("El campo solo puede contener letras, números y los caracteres: çÇº-")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar códigos postales
$("div.form-group.postalCode").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^\d{5}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese un código postal válido en España.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar titulares de tarjeta bancaria
$("div.form-group.cardHolder").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^[A-Za-z0-9áéíóúÁÉÍÓÚñÑçÇ\s\\-]+$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("El campo solo puede contener letras, números y los caracteres: çÇ-")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar números de tarjeta bancaria
$("div.form-group.cardNumber").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^\d{13,20}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese un número de tarjeta válido sin guiones.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar fecha de expiración de tarjeta bancaria
$("div.form-group.cardExpiration").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^(0[1-9]|1[0-2])\/(0[1-9]|[1-9][0-9])$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese una fecha de expiración de tarjeta válida (MM/YY).")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});

// Validar CVV de tarjeta bancaria
$("div.form-group.cardCvv").each(function() {
    // No sacar esta variable fuera del bloque
    var regularExp = /^\d{3}$/;

    let field = $(this).find('div.field')
    let label = field.find("label");
    let input = field.find("input");
    let span = $(this).find('span')
    span.text("Ingrese un código CVV de tarjeta válido.")

    let inputLength = 0;
    let inputError = false;

    input.on("input", function() {
        let inputValue = input.val().trim();
        inputLength = inputValue.length;

        if (inputValue !== "" && regularExp.test(inputValue)) {
            inputError = false;

        } else {
            inputError = true;
        }

        if (inputError) {
            field.removeClass("focus").addClass("error");
            label.addClass("error").removeClass("field-filled");
            input.addClass("error");
            span.addClass("error").removeClass("noError");

        } else {
            field.removeClass("error").addClass("focus");
            label.removeClass("error");
            input.removeClass("error");
            span.removeClass("error").addClass("noError");
        }
    });

    input.on("focus", function() {
        if (inputError) {
            field.removeClass("focus");
            
        } else {
            field.addClass("focus");
        }

        label.addClass("field-focus").removeClass("field-filled");
    });

    input.on("blur", function() {
        field.removeClass("focus");
        label.removeClass("field-focus")

        if (inputLength > 0) {
            label.addClass("field-filled")
        }
    });
});