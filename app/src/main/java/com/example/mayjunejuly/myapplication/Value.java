package com.example.mayjunejuly.myapplication;

/**
 * Created by MAYJUNEJULY on 1/30/2018.
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value{

    @SerializedName("result")
    private List<Product> listProduct;
    @SerializedName("value")
    private int value;

    public Value(List<Product> listProduct, int status) {
        this.listProduct = listProduct;
        this.value = status;
    }

    public Value() {
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListFoodM(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int status) {
        this.value = value;
    }


}
