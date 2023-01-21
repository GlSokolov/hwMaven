package com.example.hwmaven_budgetapp.model;

import java.util.List;

public class Recipe {
    private String name;
    private int cookingTime;
    private List<String> products;
    private List<String> instruction;

    public Recipe(String name, int cookingTime, List<String> products, List<String> instruction) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.products = products;
        this.instruction = instruction;
    }

    public void setName(String name) {
        if (name!=null && !name.isBlank() && !name.isEmpty()){
            this.name = name;}
        else {this.name = "<Некорректное имя>";}
    }
    public void setCookingTime(int cookingTime) {
        if (cookingTime <=0) {
            throw new IllegalArgumentException("<Некорректно задано время готовки>");
        } else {
            this.cookingTime = cookingTime;}
    }
    public void setProducts(List<String> products) {
        if (products!=null && !products.isEmpty()){
        this.products = products;}
        else throw new IllegalArgumentException("<Некорректно заданы продукты>");
    }
    public void setInstruction(List<String> instruction) {
        if (instruction!=null && !instruction.isEmpty()){
            this.instruction = instruction;;}
        else throw new IllegalArgumentException("<Некорректно задана инструкция>");
    }

    public String getName() {
        return name;
    }
    public int getCookingTime() {
        return cookingTime;
    }
    public List<String> getProducts() {
        return products;
    }
    public List<String> getInstruction() {
        return instruction;
    }
}
