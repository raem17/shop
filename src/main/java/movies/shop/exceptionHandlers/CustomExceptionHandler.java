package movies.shop.exceptionHandlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model, HttpServletRequest request) {
        String viewName;
        
        if (request.getRequestURI().startsWith("/admin")) {
        	// Página de error para administracion
            viewName = "admin/error";
            
        } else {
        	// Página de error para cliente
            viewName = "error";
        }

        model.addAttribute("error", "Se ha producido un error.");
        return viewName;
    }
}
