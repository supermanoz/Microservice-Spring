package com.manoj.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/fetchAll")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity getUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUser(id));
    }
}
