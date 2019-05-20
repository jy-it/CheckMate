package com.example.wlsxo.checkmate;

import android.graphics.drawable.Drawable;

public class WaitingStateVO {
    private Drawable img;
    private String Title;
    private String numofperson;

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getnumofperson() {
        return numofperson;
    }

    public void setnumofperson(String numofperson) {
        this.numofperson = numofperson;
    }

}
