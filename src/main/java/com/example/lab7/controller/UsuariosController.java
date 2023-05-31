package com.example.lab7.controller;


import com.example.lab7.Entity.Usuarios;
import com.example.lab7.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuariosController {

   @Autowired
   UsuariosRepository usuariosRepository;

    @ResponseBody
    @GetMapping("/lista")
    public List<Usuarios> listaUsuarios(){
        return usuariosRepository.findAll();
    }

}
