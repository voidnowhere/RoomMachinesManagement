package org.example.guis;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HomeGUI extends JFrame {
    private JButton roomsButton;
    private JButton machinesButton;
    private JPanel panelMain;

    public HomeGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addActionListeners();
        setContentPane(panelMain);
        setTitle("Home");
        setLocationRelativeTo(null);
        setSize(400, 100);
        setVisible(true);
    }

    private void addActionListeners() {
        roomsButton.addActionListener(e -> {
            try {
                setVisible(false);
                new RoomManagementGUI(this);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        machinesButton.addActionListener(e -> {
            try {
                setVisible(false);
                new MachineManagementGUI(this);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
