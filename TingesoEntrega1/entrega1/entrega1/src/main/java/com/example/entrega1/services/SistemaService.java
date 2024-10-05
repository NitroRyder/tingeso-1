package com.example.entrega1.services;

// ELABORAR FUNCIONES A SOLICTADAS POR ACÁ -> CORE DE LA LOGICA DE NEGOCIOS
// -> PROCURAR DE QUE ESTÉ BIEN REALIZADO
// -> ESTE SERVICIO SOLO USA GETS PARA CALCULAR LAS COSAS
import com.example.entrega1.entities.UsuarioEntity;
import com.example.entrega1.repositories.UsuarioRepository;
import com.example.entrega1.entities.CreditoEntity;
import com.example.entrega1.repositories.CreditoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

//-------------------------------[IMPORTS DE REPOSITORIO]--------------------------------------//
@Service
//------------------------------[INTERFACE DE REPOSITORIO]-------------------------------------//
public class SistemaService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CreditoRepository creditoRepository;
    //----------------[P1]- FUNCIONES DE CALCULO DE CRÉDITO HIPOTECARIO-----------------// -------------> REVIZAR
    // P = MONTO DEL PRESTAMO
    // r = TASA DE INTERES ANUAL
    // n = PLAZO DEL PRESTAMO EN AÑOS
    public double Credito_Hipotecario(double P, double r, double n){
        if(n == 30 && 0.35<= r && r <=0.5){ // Primera Vivienda
            System.out.println("PRESTAMO REALIZADO: Primera Vivienda");
            r = r / 12 / 100; // PASA DE TASA ANUAL A MENSUAL
            n = n * 12;        // PASA DE AÑOS A CANTIDAD DE PAGOS
            double M = P * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            return M;
        } else if(n == 20 && 0.4<= r && r <=0.6){ //Segunda Vivienda
            System.out.println("PRESTAMO REALIZADO: Segunda Vivienda");
            r = r / 12 / 100;
            n = n * 12;
            double M = P * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            return M;
        }
        else if(n == 25 && 0.5<= r && r <=0.7){ //Propiedades Comerciales
            System.out.println("PRESTAMO REALIZADO: Propiedades Comerciales");
            r = r / 12 / 100;
            n = n * 12;
            double M = P * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            return M;
        }
        else if(n ==15 && 0.45<= r && r <=0.6){ //Remodelación
            System.out.println("PRESTAMO REALIZADO: Remodelación");
            r = r / 12 / 100;
            n = n * 12;
            double M = P * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            return M;
        }else{
            System.out.println("PRESTAMO NO REALIZADO");
            return 0;
        }
    }
    //-----------------------[P2]- FUNCIONES DE REGISTRO DE USUARIO-------------------------//
    public UsuarioEntity registerUsuario(String rut, String name, int age, List<String> documents, int houses, int ingresos, String objective, List<CreditoEntity> creditos) {
        if (rut == null || rut.isEmpty() || !rut.matches("^[0-9]{7,8}-[0-9Kk]$")) {
            throw new IllegalArgumentException("EL RUT DEBE SER VÁLIDO");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("POR FAVOR INGRESAR NOMBRE");
        }
        if (documents != null) {
            for (String document : documents) {
                if (document == null || ingresos != 0) {
                    throw new IllegalArgumentException("NO SE HA INGRESADO UN ARCHIVO POR FAVOR INGRESAR DOCUMENTO DE TIPO pdf");
                } else if (!document.toLowerCase().endsWith(".pdf") || ingresos != 0) {
                    throw new IllegalArgumentException("LOS ARCHIVOS NO SON DE TIPO pdf, POR FAVOR INGRESAR DOCUMENTO DE TIPO pdf");
                }
            }
        }
        //-------------------------------------------------------------------------------------------------//
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRut(rut);
        usuario.setName(name);
        usuario.setAge(age);
        usuario.setDocuments(documents);
        usuario.setHouses(houses);
        usuario.setIngresos(ingresos);
        usuario.setObjective(objective);
        //-------------------------------------------------------------------------------------------------//
        // Asocia los créditos al usuario
        if (creditos != null) {
            for (CreditoEntity credito : creditos) {
                credito.setUsuario(usuario);
            }
            usuario.setCreditos(creditos);
        }
        UsuarioEntity savedUsuario = usuarioRepository.save(usuario);
        //-------------------------------------------------------------------------------------------------//
        // Guarda los créditos en el repositorio
        if (creditos != null) {
            for (CreditoEntity credito : creditos) {
                creditoRepository.save(credito);
            }
        }
        return usuarioRepository.findById(savedUsuario.getId()).orElse(null);
        //-------------------------------------------------------------------------------------------------//
    }
}
