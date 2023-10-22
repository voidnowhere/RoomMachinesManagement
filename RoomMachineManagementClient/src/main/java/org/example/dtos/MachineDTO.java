package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MachineDTO implements Serializable {
    private Long id;
    private String ref;
    private String brand;
    private BigDecimal price;
    private RoomDTO room;

    public MachineDTO(String ref, String brand, BigDecimal price, RoomDTO room) {
        this.ref = ref;
        this.brand = brand;
        this.price = price;
        this.room = room;
    }
}
