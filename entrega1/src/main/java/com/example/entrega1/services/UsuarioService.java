package com.example.entrega1.services;
//---------------------------------[IMPORTS DE SERVICO]----------------------------------------//
import com.example.entrega1.entities.UsuarioEntity;
import com.example.entrega1.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
@Service
//--------------------------------[FUNCIONES DE SERVICO]---------------------------------------//
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    //-------------------------------------[CRUD]----------------------------------------------//
    // * OBTENER TODOS LOS CLIENTE -> ENTREGA UNA LISTA DE CLIENTES
    public ArrayList<UsuarioEntity> getUsuario() {
        return (ArrayList<UsuarioEntity>) usuarioRepository.findAll();
    }
    //------------------------------------------------------------------//
    // * GUARDAR CLIENTE -> RECIVE UN CLIENTE Y LO GUARDA
    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }
    //------------------------------------------------------------------//
    // * ACTUALIZAR CLIENTE
    public UsuarioEntity update(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }
    //------------------------------------------------------------------//
    // * ELIMINAR CLIENTE -> SI HAY ALGÚN FALLO, ENTREGA EXCEPCIÓN
    public void deleteUsuario(Long id) throws Exception {
        try {
            usuarioRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new Exception("NO SE PUDO ELIMINAR EL CLIENTE CON id: " + id);
        }
    }
    //------------------------------[OPERACIONES CLIENTE]-------------------------------------//
    //--------------------------------------[FIND-BY]--------------------------------------------//
    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }
    //-----------------------------------[ALL-FINDERS]-----------------------------------------//
    // + OBTENER CLIENTE POR ID -> REGRESA NULL SI NO EXISTE
    /*
     public UsuarioEntity findUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
     */
    // + OBTENER CLIENTE POR ID -> REGRESA EXCEPCIÓN SI NO EXISTE
    public UsuarioEntity findUsuarioById(Long id) {
        return usuarioRepository.findById(id).get();
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByAge(int age) {
        return usuarioRepository.findByAge(age);
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByDocument(String document) {
        return usuarioRepository.findByDocument(document);
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByHouses(int houses) {
        return usuarioRepository.findByHouses(houses);
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByIngresos(int ingresos) {
        return usuarioRepository.findByIngresos(ingresos);
    }
    //------------------------------------------------------------------//
    public List<UsuarioEntity> findByObjective(String objective) {
        return usuarioRepository.findByObjective(objective);
    }
    //------------------------------------------------------------------//
    // + OBTENER TODOS LOS RUT´S
    public List<String> findAllRuts() {
        return usuarioRepository.findAllRuts();
    }
    //------------------------------------------------------------------//
    // + OBTENER TODOS LOS NOMBRES -> |||||||| ESTE PUEDE TENER PROBLEMAS ||||||||
    public List<String> findAllNames() {
        return usuarioRepository.findAllNames();
    }
    //------------------------------------------------------------------//
    // + OBTENER TODOS LAS EDADES
    public List<Integer> findAllAges() {
        return usuarioRepository.findAllAges();
    }
    //------------------------------------------------------------------//
}
