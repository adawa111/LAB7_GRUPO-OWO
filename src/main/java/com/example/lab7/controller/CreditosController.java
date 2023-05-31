package com.example.lab7.controller;

import com.example.lab7.repository.CreditosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/creditos")
public class CreditosController {

    @Autowired
    CreditosRepository creditosRepository;

}
