package com.example.bookShop.model;

import java.util.List;

public class MainRootBookModel {

    String hangmuc;
    private List<ViewBookModel> bookModels;

    public MainRootBookModel(String hangmuc, List<ViewBookModel> bookModels) {
        this.hangmuc = hangmuc;
        this.bookModels = bookModels;
    }

    public String getHangmuc() {
        return hangmuc;
    }

    public void setHangmuc(String hangmuc) {
        this.hangmuc = hangmuc;
    }

    public List<ViewBookModel> getBookModels() {
        return bookModels;
    }

    public void setBookModels(List<ViewBookModel> bookModels) {
        this.bookModels = bookModels;
    }
}
