package com.synload.nucleoTest.requests;


public class SessionRequest {
    private String session=null;
    private String key = null;
    private String value = null;
    private boolean overwrite=false;
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public SessionRequest() {
    }
    public SessionRequest(String session) {
        this.session = session;
    }

    public SessionRequest(String session, String key) {
        this.session = session;
        this.key = key;
    }
}
