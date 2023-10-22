package org.example.guis;

import org.example.daos.IDAO;
import org.example.dtos.MachineDTO;
import org.example.dtos.RoomDTO;
import org.example.models.MachinesTableModel;
import org.example.models.RoomsComboBoxModel;
import org.example.services.MachineService;
import org.example.services.RoomService;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class MachineManagementGUI extends JFrame {
    private HomeGUI homeGUI;
    private IDAO<MachineDTO> machineService;
    private IDAO<RoomDTO> roomService;
    private JComboBox comboBoxRooms;
    private RoomsComboBoxModel roomsComboBoxModel;
    private JTextField textFieldRef;
    private JTextField textFieldBrand;
    private JTextField textFieldPrice;
    private JButton buttonAdd;
    private JButton buttonDelete;
    private JButton buttonUpdate;
    private JTable tableMachines;
    private MachinesTableModel machinesTableModel;
    private JPanel mainPanel;
    private MachineDTO selectedMachine;

    public MachineManagementGUI(HomeGUI homeGUI) throws MalformedURLException, NotBoundException, RemoteException {
        this.homeGUI = homeGUI;
        roomService = RoomService.getInstance();
        machineService = MachineService.getInstance();
        roomsComboBoxModel = new RoomsComboBoxModel(roomService.findAll());
        comboBoxRooms.setModel(roomsComboBoxModel);
        comboBoxRooms.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel();
            if (value != null) {
                label.setText(((RoomDTO) value).getCode());
            }
            return label;
        });
        comboBoxRooms.setSelectedIndex(1);
        machinesTableModel = new MachinesTableModel(new ArrayList<>());
        tableMachines.setModel(machinesTableModel);
        tableMachines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fillTableMachines();
        addActionListeners();
        //
        setContentPane(mainPanel);
        setTitle("Machines Management");
        setLocationRelativeTo(null);
        setSize(500, 500);
        setVisible(true);
    }

    private void fillTableMachines() throws RemoteException {
        machinesTableModel.setMachines(machineService.findAll());
        machinesTableModel.fireTableDataChanged();
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
                machineService.store(new MachineDTO(
                        textFieldRef.getText(),
                        textFieldBrand.getText(),
                        new BigDecimal(textFieldPrice.getText()),
                        new RoomDTO(roomsComboBoxModel.getElementAt(comboBoxRooms.getSelectedIndex()).getId())
                ));
                clearSelectedMachine();
                fillTableMachines();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonUpdate.addActionListener(e -> {
            try {
                selectedMachine.setRef(textFieldRef.getText());
                selectedMachine.setBrand(textFieldBrand.getText());
                selectedMachine.setPrice(new BigDecimal(textFieldPrice.getText()));
                machineService.update(selectedMachine);
                clearSelectedMachine();
                fillTableMachines();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonDelete.addActionListener(e -> {
            try {
                machineService.delete(selectedMachine);
                clearSelectedMachine();
                fillTableMachines();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });
        tableMachines.getSelectionModel().addListSelectionListener(e -> {
            int index = tableMachines.getSelectedRow();
            if (index > -1) {
                selectedMachine = machinesTableModel.getMachineAt(index);
                textFieldRef.setText(selectedMachine.getRef());
                textFieldBrand.setText(selectedMachine.getBrand());
                textFieldPrice.setText(selectedMachine.getPrice().toString());
            }
        });
    }

    private void clearSelectedMachine() {
        selectedMachine = null;
        textFieldRef.setText("");
        textFieldBrand.setText("");
        textFieldPrice.setText("");
    }
}
