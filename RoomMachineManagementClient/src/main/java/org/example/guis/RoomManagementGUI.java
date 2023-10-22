package org.example.guis;

import org.example.daos.IDAO;
import org.example.dtos.RoomDTO;
import org.example.models.RoomsTableModel;
import org.example.services.RoomService;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class RoomManagementGUI extends JFrame {
    private HomeGUI homeGUI;
    private IDAO<RoomDTO> roomService;
    private JPanel panelMain;
    private JTextField textFieldCode;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JTable tableRooms;
    private RoomsTableModel roomsTableModel;
    private RoomDTO selectedRoom;

    public RoomManagementGUI(HomeGUI homeGUI) throws MalformedURLException, NotBoundException, RemoteException {
        this.homeGUI = homeGUI;
        roomService = RoomService.getInstance();
        roomsTableModel = new RoomsTableModel(new ArrayList<>());
        tableRooms.setModel(roomsTableModel);
        tableRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillRoomsJTable();
        addActionListeners();
        //
        setContentPane(panelMain);
        setTitle("Rooms Management");
        setLocationRelativeTo(null);
        setSize(500, 500);
        setVisible(true);
    }

    private void fillRoomsJTable() throws RemoteException {
        roomsTableModel.setRooms(roomService.findAll());
        roomsTableModel.fireTableDataChanged();
    }

    private void addActionListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                homeGUI.setVisible(true);
            }
        });
        buttonAdd.addActionListener(e -> {
            try {
                roomService.store(new RoomDTO(textFieldCode.getText()));
                clearSelectedRoom();
                fillRoomsJTable();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonUpdate.addActionListener(e -> {
            try {
                selectedRoom.setCode(textFieldCode.getText());
                roomService.update(selectedRoom);
                clearSelectedRoom();
                fillRoomsJTable();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonDelete.addActionListener(e -> {
            try {
                roomService.delete(selectedRoom);
                clearSelectedRoom();
                fillRoomsJTable();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        tableRooms.getSelectionModel().addListSelectionListener(e -> {
            int index = tableRooms.getSelectedRow();
            if (index > -1) {
                selectedRoom = roomsTableModel.getRoomAt(tableRooms.getSelectedRow());
                textFieldCode.setText(selectedRoom.getCode());
            }
        });
    }

    private void clearSelectedRoom() {
        textFieldCode.setText("");
        selectedRoom = null;
    }
}
