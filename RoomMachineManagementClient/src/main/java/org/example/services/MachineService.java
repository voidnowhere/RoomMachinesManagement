package org.example.services;

import org.example.daos.IDAO;
import org.example.dtos.MachineDTO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MachineService {
    private static IDAO<MachineDTO> instance;

    private MachineService() {
    }

    public static IDAO<MachineDTO> getInstance() throws MalformedURLException, NotBoundException, RemoteException {
        if (instance == null) {
            instance = (IDAO<MachineDTO>) Naming.lookup("rmi://localhost:5000/machineDao");
        }
        return instance;
    }
}
