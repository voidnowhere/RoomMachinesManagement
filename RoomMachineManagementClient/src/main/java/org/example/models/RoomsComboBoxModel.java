package org.example.models;

import org.example.dtos.RoomDTO;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class RoomsComboBoxModel implements ComboBoxModel<RoomDTO> {
    private List<RoomDTO> rooms;
    private RoomDTO selectedRoom;

    public RoomsComboBoxModel(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof RoomDTO) {
            selectedRoom = (RoomDTO) anItem;
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedRoom;
    }

    @Override
    public int getSize() {
        return rooms.size();
    }

    @Override
    public RoomDTO getElementAt(int index) {
        return rooms.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
