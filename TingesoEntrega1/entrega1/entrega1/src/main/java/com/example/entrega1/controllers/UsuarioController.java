package com.example.entrega1.controllers;
//------------------------------[IMPORTS DE SERVICO]-------------------------------------//
import com.example.entrega1.entities.UsuarioEntity;
import com.example.entrega1.repositories.UsuarioRepository;
import com.example.entrega1.services.UsuarioService;

import com.example.entrega1.services.SistemaService;

import com.example.entrega1.entities.CreditoEntity;
import com.example.entrega1.repositories.CreditoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//---------------------------------[IMPORTS DE SERVICO]----------------------------------------//

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")

//------------------------------[IMPORTS DE SERVICO]-------------------------------------//
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CreditoRepository creditoRepository;
    @Autowired
    private SistemaService sistemaService;
    //-------------------------------------------------------------------------------------------//
    //-----------------------------------[GETERS]----------------------------------------------//
    // * OBTENER TODOS LOS CLIENTES
    @GetMapping("/all") // -> VER SI NECESITA CAMBIO, ->  "/"
    public ResponseEntity<List<UsuarioEntity>> listAllUsuario() {
        List<UsuarioEntity> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    //-------------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR ID
    @GetMapping("/find/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Long id) {
        UsuarioEntity valorid = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(valorid);
    }
    //-------------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR RUT
    @GetMapping("/find/rut/{rut}")
    public ResponseEntity<List<UsuarioEntity>> getUsuarioByRut(@PathVariable String rut) {
        List<UsuarioEntity> ruts = usuarioService.getUsuarioByRut(rut);
        return ResponseEntity.ok(ruts);
    }
    //-------------------------------------------------------------------------------------------//
    // * OBTENER CLIENTE POR OBJETIVO
    @GetMapping("/find/objective/{objective}")
    public ResponseEntity<List<UsuarioEntity>> getUsuarioByObjective(@PathVariable String objective) {
        List<UsuarioEntity> objectives = usuarioService.getUsuarioByObjective(objective);
        return ResponseEntity.ok(objectives);
    }
    //-------------------------------------------------------------------------------------------//
    //------------------------------------[POST]-----------------------------------------------//
    // * GUARDADO DE USUARIOS
    @PostMapping("/save")
    public ResponseEntity<?> saveUsuario(@RequestBody UsuarioEntity usuario) {
        if (usuario.getHouses() < 0) {
            return ResponseEntity.badRequest().body("EL VALOR DE CANTIDAD DE CASAS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        if (usuario.getIngresos() < 0) {
            return ResponseEntity.badRequest().body("EL VALOR DE INGRESOS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }
    //------------------------------------------------------------------------------------------//
    //------------------------------------[PUT]------------------------------------------------//
    // * ACTUALIZACIÓN DE USUARIOS
    @PutMapping("/update")
    public ResponseEntity<UsuarioEntity> updateUsuario(@RequestBody UsuarioEntity usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }
    //-------------------------------------------------------------------------------------------//
    //------------------------------------[DELETE]---------------------------------------------//
    // * ELIMINACIÓN DE USUARIOS
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok("USUARIO ELIMINADO CON id: " + id);
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    // * ELIMINAR TODO CLIENTE
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllUsuario() {
        try {
            usuarioRepository.deleteAll();
            return ResponseEntity.ok("TODOS LOS USUARIOS HAN SIDO ELIMINADOS");
        }
        catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
    //------------------------------------------------------------------------------------------//
    //----------------[P1]- FUNCIONES DE CALCULO DE CRÉDITO HIPOTECARIO-----------------//
    @GetMapping("/calcularMontoMensual")
    public ResponseEntity<Double> calcularMontoMensual(@RequestBody Map<String, Object> body) {
        double P = ((Number) body.get("P")).doubleValue();   // MONTO PRESTAMO
        double r = ((Number) body.get("r")).doubleValue();     // TASA DE INTERES ANUAL
        double n = ((Number) body.get("n")).doubleValue();    // INGRESE PLAZO EN AÑOS ->
        if (P <= 0 || r <= 0 || n <= 0) {
            System.out.println("Error: All input values must be non-negative.");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(sistemaService.Credito_Hipotecario(P, r, n));
    }
    /* EL JSON ES;
    {
    "M": 1000,
    "P": 50000,
    "r": -0.05,
    "n": 12
    }
     */
    //-----------------------[P2]- FUNCIONES DE REGISTRO DE USUARIO-------------------------//
    @PostMapping("/register")
    public ResponseEntity<?> registerUsuario(@RequestBody UsuarioEntity usuario) {
        if(usuario.getRut() == null || usuario.getRut().isEmpty()) {
            return ResponseEntity.badRequest().body("POR FAVOR INGRESAR RUT");
        }
        if(usuarioRepository.existsByRut(usuario.getRut())) {
            return ResponseEntity.badRequest().body("EL RUT YA EXISTE");
        }
        if(usuario.getName() == null || usuario.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("POR FAVOR INGRESAR NOMBRE");
        }
        if (usuario.getAge() == 0) {
            return ResponseEntity.badRequest().body("POR FAVOR INGRESAR VALOR DE EDAD");
        }
        if (usuario.getAge() <= 18) {
            return ResponseEntity.badRequest().body("EL VALOR DE LA EDAD TIENE QUE SER MAYOR QUE 18");
        }
        if(usuario.getDocuments() == null || usuario.getDocuments().isEmpty()) {
            return ResponseEntity.badRequest().body("POR FAVOR INGRESAR DOCUMENTO");
        }
        for (String document : usuario.getDocuments()) {
            if (!document.toLowerCase().endsWith(".pdf")) {
                return ResponseEntity.badRequest().body("TODOS LOS DOCUMENTOS DEBEN SER ARCHIVOS PDF");
            }
        }
        if (usuario.getHouses() < 0) {
            return ResponseEntity.badRequest().body("EL VALOR DE CANTIDAD DE CASAS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        if (usuario.getIngresos() < 0) {
            return ResponseEntity.badRequest().body("EL VALOR DE INGRESOS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        try {
            UsuarioEntity registeredUsuario = sistemaService.registerUsuario(
                    usuario.getRut(),
                    usuario.getName(),
                    usuario.getAge(),
                    usuario.getDocuments(),
                    usuario.getHouses(),
                    usuario.getIngresos(),
                    usuario.getObjective(), // Puede ser nulo
                    usuario.getCreditos()
            );
            return ResponseEntity.ok(registeredUsuario);
        }
        catch (DataIntegrityViolationException e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.badRequest().body("ERROR DE INTEGRIDAD DE DATOS");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(500).body("ERROR INTERNO DEL SERVIDOR");
        }
    }
    //----------------[P3]- FUNCIONES DE SOLICITÚD DE CRÉDITO-----------------//
   // @PostMapping("/solicitarCredito")
}
