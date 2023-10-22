package org.example.services;

import jakarta.persistence.EntityManager;
import org.example.daos.IDAO;
import org.example.db.DBEntityManager;
import org.example.dtos.MachineDTO;
import org.example.dtos.RoomDTO;
import org.example.entities.Machine;
import org.example.entities.Room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MachineService extends UnicastRemoteObject implements IDAO<MachineDTO> {
    private final EntityManager entityManager;

    public MachineService() throws RemoteException {
        super();
        entityManager = DBEntityManager.getInstance();
    }

    @Override
    public List<MachineDTO> findAll() throws RemoteException {
        List<MachineDTO> machines = entityManager
                .createNamedQuery("findAllMachines", Machine.class)
                .getResultList()
                .stream()
                .map(machine -> {
                    Room room = machine.getRoom();
                    return new MachineDTO(
                            machine.getId(),
                            machine.getRef(),
                            machine.getBrand(),
                            machine.getPrice(),
                            new RoomDTO(room.getId(), room.getCode())
                    );
                }).toList();
        entityManager.clear();
        return machines;
    }

    @Override
    public MachineDTO findById(Long id) throws RemoteException {
        Machine machine = entityManager.find(Machine.class, id);
        entityManager.clear();
        Room room = machine.getRoom();
        return new MachineDTO(
                machine.getId(),
                machine.getRef(),
                machine.getBrand(),
                machine.getPrice(),
                new RoomDTO(room.getId(), room.getCode())
        );
    }

    @Override
    public boolean store(MachineDTO machineDTO) throws RemoteException {
        RoomDTO roomDTO = machineDTO.getRoom();
        entityManager.getTransaction().begin();
        entityManager.persist(new Machine(
                machineDTO.getId(),
                machineDTO.getRef(),
                machineDTO.getBrand(),
                machineDTO.getPrice(),
                new Room(roomDTO.getId(), roomDTO.getCode())
        ));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }

    @Override
    public boolean update(MachineDTO machineDTO) throws RemoteException {
        RoomDTO roomDTO = machineDTO.getRoom();
        entityManager.getTransaction().begin();
        entityManager.merge(new Machine(
                machineDTO.getId(),
                machineDTO.getRef(),
                machineDTO.getBrand(),
                machineDTO.getPrice(),
                new Room(roomDTO.getId(), roomDTO.getCode())
        ));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }

    @Override
    public boolean delete(MachineDTO machineDTO) throws RemoteException {
        RoomDTO roomDTO = machineDTO.getRoom();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(new Machine(
                machineDTO.getId(),
                machineDTO.getRef(),
                machineDTO.getBrand(),
                machineDTO.getPrice(),
                new Room(roomDTO.getId(), roomDTO.getCode())
        )));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }
}
