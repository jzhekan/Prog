package com.company;

import java.util.Date;

/**
 * Created by IVoitsekhovskyi on 02.04.2015.
 */
public class DateFile {

    private byte[] data;
    private Date dateCreate;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
