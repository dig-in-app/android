package com.github.digin.android.models;

/**
 *  We define a chef to mean any person or organization which is making food at this
 *  event. Every chef has a dish they are making and an ingredient contained in
 *  that dish.
 *
 *  Created by mike on 7/11/14.
 */
public class Chef extends Participant {

    private String ingredient, dish;
    private String cook, tent, farm;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getCook() {
        return cook;
    }

    public void setCook(String cook) {
        this.cook = cook;
    }

    public String getTent() {
        return tent;
    }

    public void setTent(String tent) {
        this.tent = tent;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    @Override
    public String toString() {
        return getName() + " (" + getId() + ") Dish: " + getDish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chef chef = (Chef) o;

        if (cook != null ? !cook.equals(chef.cook) : chef.cook != null) return false;
        if (dish != null ? !dish.equals(chef.dish) : chef.dish != null) return false;
        if (farm != null ? !farm.equals(chef.farm) : chef.farm != null) return false;
        if (ingredient != null ? !ingredient.equals(chef.ingredient) : chef.ingredient != null)
            return false;
        if (tent != null ? !tent.equals(chef.tent) : chef.tent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + (cook != null ? cook.hashCode() : 0);
        result = 31 * result + (tent != null ? tent.hashCode() : 0);
        result = 31 * result + (farm != null ? farm.hashCode() : 0);
        return result;
    }

}
