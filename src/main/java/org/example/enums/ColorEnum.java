package org.example.enums;

import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {

    YELLOW("AM", "amarelo"),
    RED("VM", "vermelho"),
    GREEN("VD", "verde"),
    BLUE("AZ", "azul"),
    BLACK("PT", "preto"),
    WHITE("BC", "branco"),
    GREY("CZ", "cinza");

    private final String colorCod;
    private final String colorDescription;

    public static final Map<String, ColorEnum> colorMap = new HashMap<>();

    static {
        for (ColorEnum color : ColorEnum.values()) {
            colorMap.put(color.getColorCod(), color);
        }
    }

    public static ColorEnum getColorEnum(String color) {
        return colorMap.get(color);
    }

    ColorEnum(String colorCod, String colorDescription) {
        this.colorCod = colorCod;
        this.colorDescription = colorDescription;
    }

    public String getColorCod() {
        return colorCod;
    }

    public String getColorDescription() {
        return colorDescription;
    }


}
