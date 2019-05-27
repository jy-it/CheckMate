package com.example.wlsxo.checkmate;

import java.util.ArrayList;

public class RoomVO {

    private int r_num;
    private String r_title;
    private String coment;
    private int join_num;
    private String reader_id;
    ArrayList<UserVO> member_info;

    public RoomVO(int r_num, String r_title, String coment, int join_num, String reader_id) {
        this.r_num = r_num;
        this.r_title = r_title;
        this.coment = coment;
        this.join_num = join_num;
        this.reader_id = reader_id;
        this.member_info = new ArrayList<UserVO>();
    }
    public RoomVO(){
        this.member_info = new ArrayList<UserVO>();
    }

    public void Clear(){
        this.r_num = 0;
        this.r_title = "";
        this.coment = "";
        this.join_num = 0;
        this.reader_id = "";
        this.member_info = null;
    }

    public int getR_num() {
        return r_num;
    }

    public void setR_num(int r_num) {
        this.r_num = r_num;
    }

    public String getR_title() {
        return r_title;
    }

    public void setR_title(String r_title) {
        this.r_title = r_title;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public int getJoin_num() {
        return join_num;
    }

    public void setJoin_num(int join_num) {
        this.join_num = join_num;
    }

    public String getReader_id() {
        return reader_id;
    }

    public void setReader_id(String reader_id) {
        this.reader_id = reader_id;
    }

    @Override
    public String toString() {
        return "RoomVO{" +
                "r_num=" + r_num +
                ", r_title='" + r_title + '\'' +
                ", coment='" + coment + '\'' +
                ", join_num=" + join_num +
                ", reader_id='" + reader_id + '\'' +
                '}';
    }
}
