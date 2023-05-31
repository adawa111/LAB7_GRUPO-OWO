package com.example.lab7.controller;


import com.example.lab7.Entity.Pagos;
import com.example.lab7.repository.PagosRepository;
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
@RequestMapping("/pagos")
public class PagosController {

    @Autowired
    PagosRepository pagosRepository;

    @ResponseBody
    @GetMapping("/lista")
    public List<Pagos> listaPagos(){

        return pagosRepository.findAll();
    }

        @PostMapping("/registrarPago")
    public ResponseEntity<HashMap<String, Object>> savePago(
            @RequestBody Pagos pagos,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();
        if(fetchId){
            responseJson.put("id",pagos.getId());
        }
        pagosRepository.save(pagos);
        responseJson.put("estado","creado");
        responseJson.put("id creado", pagos.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionException(HttpServletRequest request){
        HashMap<String,String> responseMap = new HashMap<>();
        if(request.getMethod().equals("POST")){
            responseMap.put("estado","error");
            responseMap.put("msg","Debe enviar un producto");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
