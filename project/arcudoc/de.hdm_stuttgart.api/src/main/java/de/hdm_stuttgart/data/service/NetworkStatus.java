package de.hdm_stuttgart.data.service;

/**
 * Indicates the status of a network coll
 * A custom message can be set (e.g. to inform the user about certain events)
 */
public enum NetworkStatus {

    SUCCESS("-"),
    FAIL("-"),
    LOADING("-"),
    DEFAULT("-");

    private String networkMessage;

    NetworkStatus(String networkMessage){
        this.networkMessage = networkMessage;
    }

    public String getNetworkMessage() {
        return networkMessage;
    }

    public void setNetworkMessage(String networkMessage) {
        this.networkMessage = networkMessage;
    }
}
