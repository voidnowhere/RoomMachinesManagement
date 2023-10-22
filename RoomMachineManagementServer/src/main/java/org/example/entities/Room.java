package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name = "findAllRooms", query = "select r from Room r")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String code;
    @OneToMany(mappedBy = "room")
    private List<Machine> machines;

    public Room(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Room(String code) {
        this.code = code;
    }
}
