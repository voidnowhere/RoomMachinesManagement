package org.example.models;

import lombok.Setter;
import org.example.dtos.MachineDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MachinesTableModel extends AbstractTableModel {
    @Setter
    private List<MachineDTO> machines;
    private String[] columns;

    public MachinesTableModel(List<MachineDTO> rooms) {
        columns = new String[]{"Id", "Ref", "Brand", "Price", "Room"};
        this.machines = rooms;
    }

    @Override
    public int getRowCount() {
        return machines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MachineDTO room = machines.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> room.getId();
            case 1 -> room.getRef();
            case 2 -> room.getBrand();
            case 3 -> room.getPrice();
            case 4 -> room.getRoom().getCode();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return this.columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public MachineDTO getMachineAt(int index) {
        return machines.get(index);
    }
}
