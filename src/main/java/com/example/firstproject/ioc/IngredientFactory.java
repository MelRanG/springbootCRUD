package com.example.firstproject.ioc;

public class IngredientFactory {

    public Ingredient get(String menu) {
        switch (menu){
            case "돈가스":
                return new Pork("한돈 등심");
            case "스테이크":
                return new Beef("스테이크");
            default:
                return null;
        }
    }
}
