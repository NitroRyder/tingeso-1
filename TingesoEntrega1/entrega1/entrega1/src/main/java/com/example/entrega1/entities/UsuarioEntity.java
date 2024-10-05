package com.example.entrega1.entities;
//---------------------------------[IMPORTS DE ENTIDAD]----------------------------------------//
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//----------------------------------[CLASE DE ENTIDAD]-----------------------------------------//
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;
    //-----------------------------------------------------------------------------------------//
    @Column(name = "rut")
    private String rut;     // VALOR DE RUT -> IRREPETIBLE
    //-----------------------------------------------------------------------------------------//
    @Column(name = "name")
    private String name;    // VALOR DE NOMBRE
    //-----------------------------------------------------------------------------------------//
    @Column(name = "age", nullable = false)
    private int age = 0;   // VALOR DE EDAD
    //-----------------------------------------------------------------------------------------//
    @ElementCollection
    @CollectionTable(name = "usuario_documents", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "documents")
    private List<String> documents;  // List of PDF file paths  -> // VALOR DE DOCUMENTOS ----> ES UNA LISTA DE PDF
    //-----------------------------------------------------------------------------------------//
    @Column(name = "houses", nullable = false)
    private int houses = 0;   // VALOR DE NÚMERO DE CASAS
    //-----------------------------------------------------------------------------------------//
    @Column(name = "ingresos", nullable = false)
    private int ingresos = 0;   // VALOR DE INGRESOS MENSUALES
    //-----------------------------------------------------------------------------------------//
    @Column(name = "objective", nullable = true)
    private String objective; // VALOR DE OBJETIVO -> ES TEXTO
    //-----------------------------------------------------------------------------------------//
    // UN CLIENTE TIENE UNA LISTA DE CREDITOS (HISTORIAL)
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private List<CreditoEntity> creditos = new ArrayList<>(); // LISTA DE CRÉDITOS -> HISTORIAL DE CREDITOS
    //-----------------------------------------------------------------------------------------//
    // -------------------------[CONDICIONES DE LIMITACIONES]-------------------------// -> OPCIÓN POR CREACIÓN POR POSTMAN
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; SOLO SE ACEPTAN RUTS VÁLIDOS
    public void setRut(String rut) {
        if (rut == null || rut.isEmpty() || !rut.matches("^[0-9]{7,8}-[0-9Kk]$")) {
            throw new IllegalArgumentException("EL RUT DEBE SER VÁLIDO");
        }
        this.rut = rut;
    }
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; SOLO SE ACEPTAN NOMBRES NO NULOS
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("EL NOMBRE NO PUEDE SER NULO");
        }
        this.name = name;
    }
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; SOLO SE ACEPTAN MAYOR O IGUAL A 18
    public void setAge(int age) {
        if (age <= 18) {
            throw new IllegalArgumentException("LA EDAD TIENE QUE SER MAYOR QUE 18");
        }
        this.age = age;
    }
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; LA LISTA DE DOCUMENTOS SOLO POSEE TIPO PFD
    public void setDocuments(List<String> documents) {
        if (documents != null) {
            for (String document : documents) {
                if (document == null || !document.toLowerCase().endsWith(".pdf")) {
                    throw new IllegalArgumentException("TODOS LOS DOCUMENTOS DEBEN SER ARCHIVOS PDF Y NO NULOS");
                }
            }
        }
        this.documents = documents;
    }
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; SOLO SE ACEPTAN MAYOR O IGUAL A 0
    public void setHouses(int nHouses) {
        if (nHouses < 0) {
            throw new IllegalArgumentException("EL VALOR DE CANTIDAD DE CASAS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        this.houses = nHouses;
    }
    //-----------------------------------------------------------------------------------------//
    // LIMITACIÓN DE VALOR; SOLO SE ACEPTAN MAYOR O IGUAL A 0
    public void setIngresos(int nIngresos) {
        if (nIngresos < 0) {
            throw new IllegalArgumentException("EL VALOR DE INGRESOS TIENE QUE SER MAYOR O IGUAL QUE: 0");
        }
        this.ingresos = nIngresos;
    }
    // -------------------------------[GETTERS-SELECTIVOS]------------------------------//
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE RUT
    public String getRut() {return rut;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE NOMBRE
    public String getName() {return name;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE EDAD
    public int getAge() {
        return age;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE DOCUMENTOS
    public List<String> getDocuments() {return documents;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE NÚMERO DE CASAS
    public int getHouses() {return houses;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE INGRESOS
    public int getIngresos() {return ingresos;
    }
    //-----------------------------------------------------------------------------------------//
    // OBTENER VALOR DE CRÉDITOS
    public List<CreditoEntity> getCreditos() {return creditos;
    }
    //-----------------------------------------------------------------------------------------//
}
