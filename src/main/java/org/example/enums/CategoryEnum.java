package org.example.enums;

import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {

    MALE("MS", "masculino"),
    FEMALE("FM", "feminino"),
    BABY("BB", "bebe");

    private final String categoriaStr;
    private final String description;

    private static Map<String, CategoryEnum> categoryMap = new HashMap<>();

    static {
        for (CategoryEnum category : CategoryEnum.values()) {
            categoryMap.put(category.getCategoriaStr(), category);
        }
    }

    public static CategoryEnum getCategoryEnum(String category) {
        return categoryMap.get(category);
    }

    CategoryEnum(String categoriaStr, String description) {
        this.categoriaStr = categoriaStr;
        this.description = description;
    }

    public String getCategoriaStr() {
        return categoriaStr;
    }

    public String getDescription() {
        return description;
    }


}
