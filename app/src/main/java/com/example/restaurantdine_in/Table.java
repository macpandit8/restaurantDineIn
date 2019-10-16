package com.example.restaurantdine_in;

import android.widget.ImageView;

public class Table {
    int seatingCapacity;
    String shape;
    ImageView associatedTable;
    boolean isOccupied;

    public Table(ImageView associatedTable, int seatingCapacity, String shape) {
        this.seatingCapacity = seatingCapacity;
        this.shape = shape;
        this.associatedTable = associatedTable;
        this.isOccupied = false;
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

    public boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

}
