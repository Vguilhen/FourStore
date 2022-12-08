package org.example.enums;

import java.util.HashMap;
import java.util.Map;

public enum TypeEnum {

    PANTS("101", "calça"),
    TSHIRT("102", "camiseta"),
    JACKET("103", "jaqueta"),
    SHORTS("104", "shortes"),
    DRESS("105", "vestido"),
    SHOE("106", "sapato");

    private final String typecod;
    private final String description;

    private static final Map<String, TypeEnum> typeMap = new HashMap<>();

    static {
        for (TypeEnum type : TypeEnum.values()) {
            typeMap.put(type.getTypecod(), type);
        }
    }

    public static TypeEnum getTypeEnum(String type) {
        return typeMap.get(type);
    }

    TypeEnum(String typecod, String description) {
        this.typecod = typecod;
        this.description = description;
    }

    public String getTypecod() {
        return typecod;
    }

    public String getDescription() {
        return description;
    }
}
