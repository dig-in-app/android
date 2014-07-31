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

        if (dish != null ? !dish.equals(chef.dish) : chef.dish != null) return false;
        if (ingredient != null ? !ingredient.equals(chef.ingredient) : chef.ingredient != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        return result;
    }

}
