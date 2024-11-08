package com.example.bookShop.model;

public class NotificationModel {
    int id;
    String loaithongbao;
    String noidungthongbao;
    String thoigian;

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public NotificationModel(int id, String loaithongbao, String noidungthongbao, String thoigian) {
        this.id = id;
        this.loaithongbao = loaithongbao;
        this.noidungthongbao = noidungthongbao;
        this.thoigian = thoigian;
    }

    public NotificationModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaithongbao() {
        return loaithongbao;
    }

    public void setLoaithongbao(String loaithongbao) {
        this.loaithongbao = loaithongbao;
    }

    public String getNoidungthongbao() {
        return noidungthongbao;
    }

    public void setNoidungthongbao(String noidungthongbao) {
        this.noidungthongbao = noidungthongbao;
    }
}
