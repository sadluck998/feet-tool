package org.example.feettool.enums;

import lombok.Getter;
import org.example.feettool.util.EnumNameValue;

@Getter
public enum CarbonCategory implements EnumNameValue {
    E("能源"), M("原材料");

    private final String value;

    CarbonCategory(String value) {
        this.value = value;
    }
}
