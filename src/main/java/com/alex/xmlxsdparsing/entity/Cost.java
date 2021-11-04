package com.alex.xmlxsdparsing.entity;

import java.util.ArrayList;
import java.util.List;

public class Cost {

    private double cost;
    private List<String> includes;

    public Cost(){
        this.includes = new ArrayList<>();
    }

    public Cost(double cost, List<String> includes){
        this.includes = new ArrayList<>();
        this.cost = cost;
        this.includes = includes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    public void addIncludes(String item){
        this.includes.add(item);
    }

    public void removeIncludes(String item){
        this.includes.remove(item);
    }

    public void clearIncludes(){
        this.includes.clear();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cost{");
        sb.append("cost=").append(cost);
        sb.append(", includes=").append(includes);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost1 = (Cost) o;

        if (Double.compare(cost1.cost, cost) != 0) return false;
        return includes != null ? includes.equals(cost1.includes) : cost1.includes == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cost);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (includes != null ? includes.hashCode() : 0);
        return result;
    }
}
