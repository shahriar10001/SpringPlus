package com.Application.PVM.Common;

import java.util.Arrays;
import java.util.List;

public class CreateDataSet {

    public static List<DishPvm>  getMenu(){
        List<DishPvm> menu = Arrays.asList(
                new DishPvm(1, DishPvm.Type.MEAT, "pork",750),
                new DishPvm(2, DishPvm.Type.MEAT, "beef", 700),
                new DishPvm(3, DishPvm.Type.MEAT, "chicken", 400),
                new DishPvm(4, DishPvm.Type.OTHER, "french fries", 530),
                new DishPvm(5, DishPvm.Type.MEAT, "rice", 350),
                new DishPvm(6, DishPvm.Type.OTHER, "season fruit", 120),
                new DishPvm(7, DishPvm.Type.MEAT, "pizza", 550),
                new DishPvm(8, DishPvm.Type.FISH, "prawns", 300),
                new DishPvm(9, DishPvm.Type.FISH, "salmon", 450));

        return  menu;
    }

    public static List<DishCalories>  getCalories(){
        List<DishCalories> dishCalories = Arrays.asList(
                new DishCalories(1,1,8700,false),
                new DishCalories(2,2,5000,false),
                new DishCalories(3,3,1000,false),
                new DishCalories(4,4,2500,false),
                new DishCalories(5,5,3000,false),
                new DishCalories(6,6,2600,false),
                new DishCalories(7,7,7000,false),
                new DishCalories(8,8,6500,false),
                new DishCalories(9,9,4500,false));

        return  dishCalories;
    }
}
