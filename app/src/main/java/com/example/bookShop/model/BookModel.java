package com.example.bookShop.model;

import java.io.Serializable;

public class BookModel implements Serializable {
    int id;
    String  tensach, sotrang, tacgia, phathanh, loaisach, doituong, ghichu, gia, hangmuc;
    private byte[] imageBook;



    public BookModel(int id, byte[] imageBook, String tensach, String sotrang, String tacgia, String phathanh, String loaisach, String doituong, String ghichu, String gia, String hangmuc) {
        this.imageBook = imageBook;
        this.tensach = tensach;
        this.sotrang = sotrang;
        this.tacgia = tacgia;
        this.phathanh = phathanh;
        this.loaisach = loaisach;
        this.doituong = doituong;
        this.ghichu = ghichu;
        this.gia = gia;
        this.id = id;
        this.hangmuc = hangmuc;
    }

    public String getHangmuc() {
        return hangmuc;
    }

    public void setHangmuc(String hangmuc) {
        this.hangmuc = hangmuc;
    }

    public byte[] getImageBook() {
        return imageBook;
    }

    public void setImageBook(byte[] imageBook) {
        this.imageBook = imageBook;
    }

    public BookModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getPhathanh() {
        return phathanh;
    }

    public void setPhathanh(String phathanh) {
        this.phathanh = phathanh;
    }

    public String getLoaisach() {
        return loaisach;
    }

    public void setLoaisach(String loaisach) {
        this.loaisach = loaisach;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
