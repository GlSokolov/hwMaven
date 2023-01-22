package com.example.hwmaven_budgetapp.model;

public class Ingredient {
    private String name;
    private int totalIngredients;
    private String measure;

    public Ingredient(String name, int totalIngredients, String measure) {
        setName(name);
        setTotalIngredients(totalIngredients);
        setMeasure(measure);
    }

    public void setName(String name) {
        if (name!=null && !name.isBlank() && !name.isEmpty()){
        this.name = name;}
        else {this.name = "<Некорректное имя>";}
    }
    public void setTotalIngredients(int totalIngredients) {
        if (totalIngredients <=0) {
            throw new IllegalArgumentException("<Некорректно задано количество ингридиентов>");
        } else {
        this.totalIngredients = totalIngredients;}
    }
    public void setMeasure(String measure) {
        if (measure!=null && !measure.isBlank() && !measure.isEmpty()){
            this.measure = measure;}
        else {this.measure = "<Некорректная грамовка>";}
    }

    public String getName() {
        return name;
    }
    public int getTotalIngredients() {
        return totalIngredients;
    }
    public String getMeasure() {
        return measure;
    }
}
