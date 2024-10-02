package com.example.entrega1.controllers;
//---------------------------------[IMPORTS DE SERVICO]----------------------------------------//
import com.example.entrega1.entities.UsuarioEntity;
import com.example.entrega1.repositories.UsuarioRepository;
import com.example.entrega1.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//---------------------------------[IMPORTS DE SERVICO]----------------------------------------//

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
//---------------------------------[IMPORTS DE SERVICO]----------------------------------------//
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    //-----------------------------------------------------------------------------------------//
    //----------------------------------[GETERS]----------------------------------------------//
    // * OBTENER TODOS LOS CLIENTES
    @GetMapping("/all") // -> VER SI NECESITA CAMBIO, ->  "/"
    /*
    public ResponseEntity<List<UsuarioEntity>> listAllUsuario() {
        return ResponseEntity.ok(usuarioService.getUsuario());
    }
    */
    public ResponseEntity<List<UsuarioEntity>> listAllUsuario() {
        List<UsuarioEntity> usuarios = usuarioService.getUsuario();
        return ResponseEntity.ok(usuarios);
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR ID
    @GetMapping("/find/{id}")
    public ResponseEntity<UsuarioEntity> findUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioRepository.findById(id).get());
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR RUT
    @GetMapping("/find/rut/{rut}")
    public ResponseEntity<List<UsuarioEntity>> findUsuarioByRut(@PathVariable String rut) {
        return ResponseEntity.ok(usuarioRepository.findByRut(rut));
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR NOMBRE
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<UsuarioEntity>> findUsuarioByName(@PathVariable String name) {
        return ResponseEntity.ok(usuarioRepository.findByName(name));
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR EDAD
    @GetMapping("/find/age/{age}")
    public ResponseEntity<List<UsuarioEntity>> findUsuarioByAge(@PathVariable int age) {
        return ResponseEntity.ok(usuarioRepository.findByAge(age));
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR DOCUMENTO
    @GetMapping("/find/document/{document}")
    public ResponseEntity<List<UsuarioEntity>> findUsuarioByDocument(@PathVariable String document) {
        return ResponseEntity.ok(usuarioRepository.findByDocument(document));
    }
    //-----------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR NÚMERO DE CASAS
    @GetMapping("/find/houses/{houses}")
    public ResponseEntity<List<UsuarioEntity>> findUsuarioByHouses(@PathVariable int houses) {
        return ResponseEntity.ok(usuarioRepository.findByHouses(houses));
    }
    //-----------------------------------------------------------------------------------------//
}
