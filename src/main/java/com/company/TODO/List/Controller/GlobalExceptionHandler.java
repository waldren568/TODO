package com.company.TODO.List.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public Object handle(ResponseStatusException ex,
                         HttpServletRequest request,
                         RedirectAttributes ra) {

        // Se la richiesta è AJAX o chiede JSON → JSON
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ||
                MediaType.APPLICATION_JSON.isCompatibleWith(
                        MediaType.parseMediaTypes(request.getHeader("Accept")).stream()
                                .findFirst()
                                .orElse(MediaType.ALL))) {

            return ResponseEntity.status(ex.getStatusCode())
                    .body(new ErrorMessage(ex.getReason()));
        }

        // Altrimenti → redirect
        ra.addFlashAttribute("error", ex.getReason());
        return "redirect:/index";
    }

    public record ErrorMessage(String message) {}
}