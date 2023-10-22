package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO implements Serializable {
    private Long id;
    private String code;

    public RoomDTO(Long id) {
        this.id = id;
    }

    public RoomDTO(String code) {
        this.code = code;
    }
}
