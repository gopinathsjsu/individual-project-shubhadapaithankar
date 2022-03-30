package com.cmpe202;

public class Factory {

    public static Component create(String itemName, String category, String quantity, String costPerUnit) {


        CategoryType categoryType = CategoryType.valueOf(category.toUpperCase());

        int quantityInt = Integer.parseInt(quantity);
        double costPerUnitDouble = Double.parseDouble(costPerUnit);

        switch (categoryType) {

            case ESSENTIALS -> {
                return new Essentials(itemName, quantityInt, costPerUnitDouble);
            }
            case MISC -> {
                return new Misc(itemName, quantityInt, costPerUnitDouble);
            }
            case LUXURY -> {
                return new Luxury(itemName, quantityInt, costPerUnitDouble);
            }
            default -> throw new IllegalStateException("Unexpected value: " + categoryType);
        }

    }

}
