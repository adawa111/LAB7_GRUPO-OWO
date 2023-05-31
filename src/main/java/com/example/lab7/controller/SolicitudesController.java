package com.example.lab7.controller;


import com.example.lab7.Entity.Solicitudes;
import com.example.lab7.repository.SolicitudesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
    SolicitudesRepository solicitudesRepository;

    @ResponseBody
    @GetMapping("/lista")
    public List<Solicitudes> listaSolicitudes(){

        return solicitudesRepository.findAll();
    }

    @PostMapping("/registro")
    public ResponseEntity<HashMap<String, Object>> registrarSolicitud(
            @RequestBody Solicitudes solicitudes,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();
        if(fetchId){
            responseJson.put("id",solicitudes.getId());
        }

        solicitudes.setSolicitudEstado("pendiente");
        solicitudesRepository.save(solicitudes);
        responseJson.put("id creado", solicitudes.getId());
        responseJson.put("Monto solicitado",solicitudes.getSolicitudMonto());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar una solicitud");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }


    @PutMapping(value = "/aprobarSolicitud")
    public ResponseEntity<HashMap<String,Object>> aprobarSolicitud(@RequestParam(value = "idSolicitud",required = false)  Integer idSolicitud){

        HashMap<String, Object> responseMap = new HashMap<>();

        if (idSolicitud != null && idSolicitud > 0) {
            Optional<Solicitudes> opt = solicitudesRepository.findById(idSolicitud);
            if (opt.isPresent()) {
                Solicitudes solicitudes = opt.get();
                if(solicitudes.getSolicitudEstado().equals("pendiente")){
                    solicitudes.setSolicitudEstado("aprobado");
                    solicitudesRepository.save(solicitudes);
                    responseMap.put("id solicitud aprobada", solicitudes.getId());
                    return ResponseEntity.ok(responseMap);
                }else {
                    responseMap.put("solicitud ya atendida", solicitudes.getId());
                    return ResponseEntity.ok(responseMap);
                }

            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "La solicitud a aprobar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }

    }

    @PutMapping(value = "/denegarSolicitud")
    public ResponseEntity<HashMap<String,Object>> denegarSolicitud(@RequestParam(value = "idSolicitud",required = false)  Integer idSolicitud){

        HashMap<String, Object> responseMap = new HashMap<>();

        if (idSolicitud != null && idSolicitud > 0) {
            Optional<Solicitudes> opt = solicitudesRepository.findById(idSolicitud);
            if (opt.isPresent()) {
                Solicitudes solicitudes = opt.get();
                if(solicitudes.getSolicitudEstado().equals("pendiente")){
                    solicitudes.setSolicitudEstado("denegado");
                    solicitudesRepository.save(solicitudes);
                    responseMap.put("id solicitud denegada", solicitudes.getId());
                    return ResponseEntity.ok(responseMap);
                }else {
                    responseMap.put("solicitud ya atendida", solicitudes.getId());
                    return ResponseEntity.ok(responseMap);
                }

            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "La solicitud a denegar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }

    }

    @DeleteMapping(value = "/borrarSolicitud")
    public ResponseEntity<HashMap<String, Object>> borrarSolicitud(@RequestParam(value = "idSolicitud",required = false)  Integer idSolicitud) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (idSolicitud != null && idSolicitud > 0) {
            Optional<Solicitudes> opt = solicitudesRepository.findById(idSolicitud);
            if (opt.isPresent()) {
                Solicitudes solicitudes = opt.get();
                if(solicitudes.getSolicitudEstado().equals("denegado")){
                    solicitudesRepository.deleteById(idSolicitud);
                    responseMap.put("id solicitud borrada", idSolicitud);
                    return ResponseEntity.ok(responseMap);
                }else {
                    responseMap.put("solicitud ya atendida", solicitudes.getId());
                    return ResponseEntity.ok(responseMap);
                }
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "no se encontró el producto con id: " +idSolicitud);
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }

}
