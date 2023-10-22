package org.example.services;

import jakarta.persistence.EntityManager;
import org.example.daos.IDAO;
import org.example.db.DBEntityManager;
import org.example.dtos.RoomDTO;
import org.example.entities.Room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RoomService extends UnicastRemoteObject implements IDAO<RoomDTO> {
    private final EntityManager entityManager;

    public RoomService() throws RemoteException {
        super();
        entityManager = DBEntityManager.getInstance();
    }

    @Override
    public List<RoomDTO> findAll() throws RemoteException {
        List<RoomDTO> rooms = entityManager
                .createNamedQuery("findAllRooms", Room.class)
                .getResultList()
                .stream()
                .map(room -> new RoomDTO(room.getId(), room.getCode()))
                .toList();
        entityManager.clear();
        return rooms;
    }

    @Override
    public RoomDTO findById(Long id) throws RemoteException {
        Room room = entityManager.find(Room.class, id);
        entityManager.clear();
        return new RoomDTO(room.getId(), room.getCode());
    }

    @Override
    public boolean store(RoomDTO roomDTO) throws RemoteException {
        entityManager.getTransaction().begin();
        entityManager.persist(new Room(roomDTO.getCode()));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }

    @Override
    public boolean update(RoomDTO roomDTO) throws RemoteException {
        entityManager.getTransaction().begin();
        entityManager.merge(new Room(roomDTO.getId(), roomDTO.getCode()));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }

    @Override
    public boolean delete(RoomDTO roomDTO) throws RemoteException {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(new Room(roomDTO.getId(), roomDTO.getCode())));
        entityManager.getTransaction().commit();
        entityManager.clear();
        return true;
    }
}
