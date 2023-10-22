package org.example.daos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IDAO<T> extends Remote {
    List<T> findAll() throws RemoteException;
    T findById(Long id) throws RemoteException;
    boolean store(T t) throws RemoteException;
    boolean update(T t) throws RemoteException;
    boolean delete(T t) throws RemoteException;
}
