package com.example.firstproject.ioc;

public class Chef {
    //셰프가 재료를 넣지 않고 중간에 재료공장을 만들어서 거기서 분류를 해서 의존성을 낮춤
    //셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;
    //셰프가 식재료 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        Ingredient ingredient = ingredientFactory.get(menu);

         return ingredient.getName() + "으로 만든 " + menu;
    }
}
