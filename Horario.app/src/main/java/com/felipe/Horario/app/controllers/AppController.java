package com.felipe.Horario.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(){
        Map<String, Object> data = new HashMap<>();
        data.put("title","Bienvenidos al sistema de atencion");
        data.put("time",new Date());
        return ResponseEntity.ok(data);
    }
}
