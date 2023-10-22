package org.example.models;

import lombok.Setter;
import org.example.dtos.RoomDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RoomsTableModel extends AbstractTableModel {
    @Setter
    private List<RoomDTO> rooms;
    private String[] columns;

    public RoomsTableModel(List<RoomDTO> rooms) {
        columns = new String[]{"Id", "Code"};
        this.rooms = rooms;
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RoomDTO room = rooms.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> room.getId();
            case 1 -> room.getCode();
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

    public RoomDTO getRoomAt(int index) {
        return rooms.get(index);
    }
}
