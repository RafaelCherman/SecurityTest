package com.example.security.controller;

import com.example.security.entity.SecurityEntity;
import com.example.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private SecurityService service;

    @GetMapping("/teste")
    //@PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1>Teste</h1>";
    }

    @GetMapping("/teste2")
    public String teste2()
    {
        return "<h1>Teste2</h1>";
    }

    @PostMapping("/new")
    public ResponseEntity<SecurityEntity> create(@RequestBody SecurityEntity user)
    {
        return ResponseEntity.ok(service.create(user));
    }

    @PutMapping("/edit")
    public ResponseEntity<SecurityEntity> edit(@RequestParam Long id, @RequestBody SecurityEntity user)
    {
        return ResponseEntity.ok(service.edit(user, id));
    }

    @GetMapping
    public String findById(@RequestParam Long id){
        SecurityEntity user = service.findById(id);
        return "<p><strong>ID: </strong>" + user.getId() + "</p>" +
                "<p><strong>Username: </strong>" + user.getUsername() + "</p>" +
                "<p><strong>Password: </strong>" + user.getPassword() + "</p>" +
                "<p><strong>Role: </strong>" + user.getRole() + "</p>";
    }
}
