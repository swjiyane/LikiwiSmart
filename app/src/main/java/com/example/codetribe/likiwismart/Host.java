package com.example.codetribe.likiwismart;

/**
 * Created by CodeTribe on 7/27/2017.
 */

public class Host {
    private String hostName;
    private String emailHost;
    private String passwordHost;
    private String nameOfEvent;


    public Host() {
    }

    public Host(String hostName, String emailHost, String passwordHost, String nameOfEvent) {
        this.hostName = hostName;
        this.emailHost = emailHost;
        this.passwordHost = passwordHost;
        this.nameOfEvent = nameOfEvent;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getPasswordHost() {
        return passwordHost;
    }

    public void setPasswordHost(String passwordHost) {
        this.passwordHost = passwordHost;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public void setNameOfEvent(String nameOfEvent) {
        this.nameOfEvent = nameOfEvent;
    }
}
