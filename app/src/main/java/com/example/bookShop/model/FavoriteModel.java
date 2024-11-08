package com.example.bookShop.model;

public class FavoriteModel {

    int id;
    String  tensach, tacgia,  gia;
    private byte[] imageBook;

    public FavoriteModel(int id, String tensach, String tacgia, String gia, byte[] imageBook) {
        this.id = id;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.gia = gia;
        this.imageBook = imageBook;
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

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public byte[] getImageBook() {
        return imageBook;
    }

    public void setImageBook(byte[] imageBook) {
        this.imageBook = imageBook;
    }
}
