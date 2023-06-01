package com.example.lab7.controller;


import com.example.lab7.Entity.Acciones;
import com.example.lab7.Entity.Usuarios;
import com.example.lab7.repository.AccionesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/acciones")
public class AccionesController {

    @Autowired
    AccionesRepository accionesRepository;


    @ResponseBody
    @GetMapping("/lista")
    public List<Acciones> listaAccciones(){

        return accionesRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> saveStonks(
            @RequestBody Acciones acciones,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();
        if(fetchId){
            responseJson.put("id",acciones.getId());
        }
        accionesRepository.save(acciones);
        responseJson.put("estado","creado");
        responseJson.put("id creado", acciones.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){
        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("estado","error");
            responseMap.put("msg","Debe enviar una accion");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }
}
