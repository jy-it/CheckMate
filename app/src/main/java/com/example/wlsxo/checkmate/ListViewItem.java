package com.example.wlsxo.checkmate;
import android.graphics.drawable.Drawable;


public class ListViewItem {
    private Drawable img;
    private String Title;
    private String numofperson;

    //kjt-수정(매칭신청)
    private int r_num;

    //kjt-수정(매칭신청)
    public void setR_num(int r_num) {this.r_num = r_num;}
    public int getR_num() {return r_num;}

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

