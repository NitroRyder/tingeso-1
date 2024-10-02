package com.example.entrega1.repositories;
//-------------------------------[IMPORTS DE REPOSITORIO]--------------------------------------//
import com.example.entrega1.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
//------------------------------[INTERFACE DE REPOSITORIO]-------------------------------------//
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> { // VALOR DE REPOSITORIO DE CLIENTES
    //----------------------------------[BUSQUEDA POR VALOR]-----------------------------------//
    // -> ENTREGA LA TABLA COMPLETA DE CLIENTE SOLICITADO
    // * ENCONTRAR POR RUT
    List<UsuarioEntity> findByRut(String rut);
    // * ENCONTRAR POR NOMBRE
    List<UsuarioEntity> findByName(String name);
    // * ENCONTRAR POR EDAD
    List<UsuarioEntity> findByAge(int age);
    // * ENCONTRAR POR DOCUMENTO
    List<UsuarioEntity> findByDocument(String document);
    // * ENCONTRAR POR NÚMERO DE CASAS
    List<UsuarioEntity> findByHouses(int houses);
    // * ENCONTRAR POR INGRESOS
    List<UsuarioEntity> findByIngresos(int ingresos);
    // * ENCONTRAR POR OBJETIVO
    List<UsuarioEntity> findByObjective(String objective);
    //--------------------------------[BUSQUEDA POR CONDICIÓN]---------------------------------//
    // -> RECORTA LA TABLA SEGÚN EL DATO QUE ESTOY BUSCANDO, ME DÁ MAS DE UN CLIENTE
    // + OBTENER TODOS LOS RUT´S
    @Query("SELECT c.rut FROM UsuarioEntity c WHERE c.rut IS NOT NULL")
    List<String> findAllRuts();
    // + OBTENER TODOS LOS NOMBRES
    @Query("SELECT c.name FROM UsuarioEntity c WHERE c.name IS NOT NULL")
    List<String> findAllNames();
    // + OBTENER TODOS LAS EDADES
    @Query("SELECT c.age FROM UsuarioEntity c WHERE c.age IS NOT NULL")
    List<Integer> findAllAges();
    // + OBTENER TODOS LOS DOCUMENTOS
    @Query("SELECT c.document FROM UsuarioEntity c WHERE c.document IS NOT NULL")
    List<String> findAllDocuments();
    // + OBTENER TODOS LOS NÚMEROS DE CASAS
    @Query("SELECT c.houses FROM UsuarioEntity c WHERE c.houses IS NOT NULL")
    List<Integer> findAllHouses();
    // + OBTENER TODOS LOS INGRESOS
    @Query("SELECT c.ingresos FROM UsuarioEntity c WHERE c.ingresos IS NOT NULL")
    List<Integer> findAllIngresos();
    // + OBTENER TODOS LOS OBJETIVOS
    @Query("SELECT c.objective FROM UsuarioEntity c WHERE c.objective IS NOT NULL")
    List<String> findAllObjectives();
    //-----------------------------------------------------------------------------------------//
}
