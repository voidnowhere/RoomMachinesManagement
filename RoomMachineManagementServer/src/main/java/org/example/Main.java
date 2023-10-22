package org.example;


import org.example.daos.IDAO;
import org.example.dtos.MachineDTO;
import org.example.dtos.RoomDTO;
import org.example.services.MachineService;
import org.example.services.RoomService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        IDAO<MachineDTO> machineDao = new MachineService();
        IDAO<RoomDTO> roomDao = new RoomService();

        LocateRegistry.createRegistry(5000);
        Naming.bind("rmi://localhost:5000/machineDao", machineDao);
        Naming.bind("rmi://localhost:5000/roomDao", roomDao);

        System.out.println("Server is running...");
    }
}