package org.example.enums;

import java.util.HashMap;
import java.util.Map;

public enum DepartmentEnum {

    CLOTHING("VST", "vestuário"),
    SHOE("CLD", "calçado");

    private final String departmentCod;
    private final String departmentDescription;

    private static final Map<String, DepartmentEnum> departmentMap = new HashMap<>();

    static {
        for (DepartmentEnum department : DepartmentEnum.values()) {
            departmentMap.put(department.getDepartmentCod(), department);
        }
    }

    public static DepartmentEnum getDepartmentEnum(String department) {
        return departmentMap.get(department);
    }

    DepartmentEnum(String departmentCod, String departmentDescription) {
        this.departmentCod = departmentCod;
        this.departmentDescription = departmentDescription;
    }

    public String getDepartmentCod() {
        return departmentCod;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }


}
