package com.example.entrega1.entities;
//---------------------------------[IMPORTS DE ENTIDAD]----------------------------------------//
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "creditos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//----------------------------------[CLASE DE ENTIDAD]-----------------------------------------//

public class CreditoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //-----------------------------------------------------------------------------------------//
    @Column(unique = true, nullable = false)
    private Long id;        // VALOR DE id
    //-----------------------------------------------------------------------------------------//
    @Column(name = "montop")
    private double montop;    // VALOR DE MONTO DE PRÉSTAMO
    //-----------------------------------------------------------------------------------------//
    @Column(name = "plazo")
    private int plazo;     // VALOR DE PLAZO (años)
    //-----------------------------------------------------------------------------------------//
    @Column(name = "intanu")
    private double intanu;    // VALOR DE INTERÉS ANUAL
    //-----------------------------------------------------------------------------------------//
    @Column(name = "intmen")
    private double intmen;    // VALOR DE INTERÉS MENSUAL
    //-----------------------------------------------------------------------------------------//
    @Column(name = "segudesg")
    private double segudesg;    // VALOR DE SEGURO DE DESGRAVAMEN
    //-----------------------------------------------------------------------------------------//
    @Column(name = "seguince")
    private double seguince;    // VALOR DE SEGURO DE INCENDIO
    //-----------------------------------------------------------------------------------------//
    @Column(name = "comiad")
    private double comiad;    // VALOR DE COMICIÓN ADMINISTRATIVA
}
