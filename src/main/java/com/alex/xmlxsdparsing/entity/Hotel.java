package com.alex.xmlxsdparsing.entity;

import com.alex.xmlxsdparsing.entity.enumerationvalue.FoodType;

public class Hotel {

    private String name;
    private short stars;
    private boolean isFoodIncluded;
    private FoodType foodType;
    private short amountOfRooms;
    private boolean isTVIncluded;
    private boolean isConditionerIncluded;

    public Hotel(){

    }

    public Hotel(String name, short stars, boolean isFoodIncluded, short amountOfRooms, boolean isTVIncluded, boolean isConditionerIncluded) {
        this.name = name;
        this.stars = stars;
        this.isFoodIncluded = isFoodIncluded;
        this.amountOfRooms = amountOfRooms;
        this.isTVIncluded = isTVIncluded;
        this.isConditionerIncluded = isConditionerIncluded;
    }

    public Hotel(String name, short stars, boolean isFoodIncluded, FoodType foodType, short amountOfRooms, boolean isTVIncluded, boolean isConditionerIncluded) {
        this.name = name;
        this.stars = stars;
        this.isFoodIncluded = isFoodIncluded;
        this.foodType = foodType;
        this.amountOfRooms = amountOfRooms;
        this.isTVIncluded = isTVIncluded;
        this.isConditionerIncluded = isConditionerIncluded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getStars() {
        return stars;
    }

    public void setStars(short stars) {
        this.stars = stars;
    }

    public boolean isFoodIncluded() {
        return isFoodIncluded;
    }

    public void setFoodIncluded(boolean foodIncluded) {
        isFoodIncluded = foodIncluded;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public short getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(short amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public boolean isTVIncluded() {
        return isTVIncluded;
    }

    public void setTVIncluded(boolean TVIncluded) {
        isTVIncluded = TVIncluded;
    }

    public boolean isConditionerIncluded() {
        return isConditionerIncluded;
    }

    public void setConditionerIncluded(boolean conditionerIncluded) {
        isConditionerIncluded = conditionerIncluded;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hotel{");
        sb.append("name='").append(name).append('\'');
        sb.append(", stars=").append(stars);
        sb.append(", isFoodIncluded=").append(isFoodIncluded);
        sb.append(", foodType=").append(foodType);
        sb.append(", amountOfRooms=").append(amountOfRooms);
        sb.append(", isTVIncluded=").append(isTVIncluded);
        sb.append(", isConditionerIncluded=").append(isConditionerIncluded);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (stars != hotel.stars) return false;
        if (isFoodIncluded != hotel.isFoodIncluded) return false;
        if (amountOfRooms != hotel.amountOfRooms) return false;
        if (isTVIncluded != hotel.isTVIncluded) return false;
        if (isConditionerIncluded != hotel.isConditionerIncluded) return false;
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        return foodType == hotel.foodType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) stars;
        result = 31 * result + (isFoodIncluded ? 1 : 0);
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + (int) amountOfRooms;
        result = 31 * result + (isTVIncluded ? 1 : 0);
        result = 31 * result + (isConditionerIncluded ? 1 : 0);
        return result;
    }
}
