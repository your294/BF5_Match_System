package com.yupi.springbootinit.model.enums;

public enum TeamStatusEnum {
    PUBLIC("公开", 0),
    PRIVATE("私密", 1),
    PASSWORD("加密", 2);

    private final String text;

    private final Integer val;

    TeamStatusEnum(String text, int val) {
        this.text = text;
        this.val = val;
    }

    public String getText() {
        return text;
    }

    public Integer getVal() {
        return val;
    }

    public static TeamStatusEnum getEnumByValue(Integer val) {
        for (TeamStatusEnum teamEnum : TeamStatusEnum.values()) {
            if (teamEnum.getVal().equals(val)) {
                return teamEnum;
            }
        }
        return null;
    }
}
