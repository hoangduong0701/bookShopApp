package com.example.bookShop.model;

public class OrderCartModel {

    int id, soluong;
    private byte[] imageBook;
    String  tensach, tacgia, gia, tongtien, card, thoigian, diachi, sdt, id_user, note;


    public OrderCartModel(int id, int soluong, byte[] imageBook, String tensach, String tacgia, String gia, String tongtien, String card, String thoigian, String diachi, String sdt, String id_user, String note) {
        this.id = id;
        this.soluong = soluong;
        this.imageBook = imageBook;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.gia = gia;
        this.tongtien = tongtien;
        this.card = card;
        this.thoigian = thoigian;
        this.diachi = diachi;
        this.sdt = sdt;

        this.id_user = id_user;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderCartModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public byte[] getImageBook() {
        return imageBook;
    }

    public void setImageBook(byte[] imageBook) {
        this.imageBook = imageBook;
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

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
