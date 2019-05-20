package com.example.wlsxo.checkmate;
//test
public class RoomData {
    int r_num;
    String r_title;
    String coment;
    int join_num;
    String reader_id;
    int state;
    String sex;

    public RoomData(int r_num, String r_title, int join_num, String sex) {
        this.r_num = r_num;
        this.r_title = r_title;
        this.join_num = join_num;
        this.sex = sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setR_num(int r_num) {
        this.r_num = r_num;
    }

    public void setR_title(String r_title) {
        this.r_title = r_title;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public void setJoin_num(int join_num) {
        this.join_num = join_num;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getR_num() {
        return r_num;
    }

    public String getR_title() {
        return r_title;
    }

    public String getComent() {
        return coment;
    }

    public int getJoin_num() {
        return join_num;
    }

    public String getReader_id() {
        return reader_id;
    }

    public int getState() {
        return state;
    }

    public String getSex() {
        return sex;
    }
}
