package com.example.hwmaven_budgetapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Getter
@EqualsAndHashCode
@ToString(includeFieldNames = true)
public class Recipe {
    private String name;
    private int cookingTime;
    private List<String> products;
    private List<String> instruction;

    public Recipe(String name, int cookingTime, List<String> products, List<String> instruction) {
        setName(name);
        setCookingTime(cookingTime);
        setProducts(products);
        setInstruction(instruction);
    }

    public void setName(String name) {
        if (name!=null && StringUtils.isBlank(name) && StringUtils.isEmpty(name)){
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
        if (products!=null && CollectionUtils.isEmpty(products)){
        this.products = products;}
        else throw new IllegalArgumentException("<Некорректно заданы продукты>");
    }
    public void setInstruction(List<String> instruction) {
        if (instruction!=null && CollectionUtils.isEmpty(instruction)){
            this.instruction = instruction;;}
        else throw new IllegalArgumentException("<Некорректно задана инструкция>");
    }

}
