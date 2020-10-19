package com.Application.PVM.Common;

public class DishCalories {
    private int id;
    private int dishId;
    private int calories;
    private boolean isVegetarian;

    public DishCalories(int id,int dishId,int calories,boolean isVegetarian){
        this.id=id;
        this.dishId=dishId;
        this.calories=calories;
        this.isVegetarian=isVegetarian;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }
}
