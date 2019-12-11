package com.pauloneto.apirest.resources.handler.dto;

import java.io.Serializable;

public class ErroMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    private String msgUser;
    private String msgDev;

    public ErroMessage(String msgUser, String msgDev) {
        this.msgUser = msgUser;
        this.msgDev = msgDev;
    }

    public ErroMessage() {
    }

    public String getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(String msgUser) {
        this.msgUser = msgUser;
    }

    public String getMsgDev() {
        return msgDev;
    }

    public void setMsgDev(String msgDev) {
        this.msgDev = msgDev;
    }
}
