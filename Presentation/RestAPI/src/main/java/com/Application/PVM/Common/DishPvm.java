package com.Application.PVM.Common;

public class DishPvm {

    private int id;
    private  Type type;
    private String name;
    private int price;

    public DishPvm(int id, Type type, String name, int price){
        this.id=id;
        this.type = type;
        this.name=name;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public enum Type { MEAT, FISH, OTHER }
}
