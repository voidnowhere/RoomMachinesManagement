package org.example.services;

import org.example.daos.IDAO;
import org.example.dtos.RoomDTO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RoomService {
    private static IDAO<RoomDTO> instance;

    private RoomService() {
    }

    public static IDAO<RoomDTO> getInstance() throws MalformedURLException, NotBoundException, RemoteException {
        if (instance == null) {
            instance = (IDAO<RoomDTO>) Naming.lookup("rmi://localhost:5000/roomDao");
        }
        return instance;
    }
}
