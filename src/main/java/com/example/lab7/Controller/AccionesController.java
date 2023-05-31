package com.example.lab7.Controller;
import com.example.lab7.Entity.*;
import  com.example.lab7.Repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value="/acciones", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class AccionesController {

    private final AccionesRepository accionesRepository;

    public AccionesController(AccionesRepository accionesRepository) {
        this.accionesRepository = accionesRepository;
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<HashMap<String, Object>> guardarAcciones(
            @RequestBody Acciones acciones){

    HashMap<String,Object> responseMap = new HashMap<>();
    accionesRepository.save(acciones);
    responseMap.put("id creado", acciones.getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }
    //Exceptionhandlerpost
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){
        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("Estado", "Error");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
