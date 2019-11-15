package com.example.restaurantdine_in.dashboard;

import android.widget.ImageView;

public class Table {
    int seatingCapacity;
    String shape;
    ImageView associatedTable;
    boolean occupied;
    int tableNumber;

    public Table(ImageView associatedTable, int seatingCapacity, String shape, int tableNumber) {
        this.seatingCapacity = seatingCapacity;
        this.shape = shape;
        this.associatedTable = associatedTable;
        this.occupied = false;
        this.tableNumber = tableNumber;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getShape() {
        return shape;
    }

    public ImageView getAssociatedTable() {
        return this.associatedTable;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.occupied = isOccupied;
    }

}
