package com.chat.core.persistence;

public class UserInfo {

    public String userID;
    public String uniqueID;
    public String phoneNumber;
    public String encryptedPassword;


    public UserInfo(String userID, String uniqueID, String password, String phoneNumber) {
        this.uniqueID = uniqueID;
        this.userID = userID;
        this.phoneNumber = phoneNumber;
        this.encryptedPassword = password;
    }

    public synchronized String getUserID() {
        return userID;
    }

    public synchronized void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}
