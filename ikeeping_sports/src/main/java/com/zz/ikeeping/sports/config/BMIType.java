package com.zz.ikeeping.sports.config;

public enum  BMIType {
    slim("偏瘦"),normal("正常"),obesity("肥胖"),overweight("偏重"),health("健康"),hubHeath("亚健康");
    private String value;
    private BMIType(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}
