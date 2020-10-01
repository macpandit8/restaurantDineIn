package com.example.restaurantdine_in.printerLib;

public class PrinterInfo {

    String modelName;
    String portName;
    String macAddress;
    boolean checked;

    public PrinterInfo(String modelName, String portName, String macAddress, boolean checked) {
        this.modelName = modelName;
        this.portName = portName;
        this.macAddress = macAddress;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

}
