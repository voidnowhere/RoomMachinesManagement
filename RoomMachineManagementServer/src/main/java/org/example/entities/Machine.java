package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NamedQuery(name = "findAllMachines", query = "select m from Machine m")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Machine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String ref;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @ManyToOne
    private Room room;
}
