package org.example.enums;

import java.util.HashMap;
import java.util.Map;

public enum SizeEnum {

    PP("PP", 22),
    P("P", 33),
    M("M", 44),
    G("G", 55),
    GG("GG", 66);

    private final String size;
    private final int sizeCod;

    private static final Map<Integer, SizeEnum> sizeMap = new HashMap<>();

    static {
        for (SizeEnum size : SizeEnum.values()) {
            sizeMap.put(size.getSizeCod(), size);
        }
    }

    public static SizeEnum getSizeEnum(Integer size) {
        return sizeMap.get(size);
    }

    SizeEnum(String size, int sizeCod) {
        this.size = size;
        this.sizeCod = sizeCod;
    }

    public String getSize() {
        return size;
    }

    public int getSizeCod() {
        return sizeCod;
    }
}
