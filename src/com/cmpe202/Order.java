package com.cmpe202;

import java.util.ArrayList;
import java.util.List;

public class Order implements Component {
    private int quantity;
    private double costPerUnit;

    public Order(int quantity, double costPerUnit) {
        this.quantity = quantity;
        this.costPerUnit = costPerUnit;
    }

    List<Component> component = new ArrayList<>();


    @Override
    public String itemName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public double costPerUnit() {
        return costPerUnit;
    }

    @Override
    public void add(Component component) {
        component.add(component);
    }


    @Override
    public void remove(Component component) {

    }

    @Override
    public Component getChild(String name) {
        return null;
    }
}
