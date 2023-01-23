package com.example.hwmaven_budgetapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@EqualsAndHashCode
@ToString(includeFieldNames = true)
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
        if (name!=null && StringUtils.isBlank(name) && StringUtils.isEmpty(name)){
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
        if (measure!=null && StringUtils.isBlank(measure) && StringUtils.isEmpty(measure)){
            this.measure = measure;}
        else {this.measure = "<Некорректная грамовка>";}
    }

}
