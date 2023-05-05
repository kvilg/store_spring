package com.example.demo.model;

public class KeyboardFindModelIn extends ProductFindModelIn{

    private Integer keyCountMin;
    private Integer keyCountMax;
    private Boolean illumination;
    private Boolean gaming;


    public KeyboardFindModelIn() {
        super();
    }

    public KeyboardFindModelIn(String name, Integer minPrice, Integer maxPrice, Integer keyCountMin, Integer keyCountMax, boolean illumination, boolean gaming) {
        super(name, minPrice, maxPrice);
        this.keyCountMin = keyCountMin;
        this.keyCountMax = keyCountMax;
        this.illumination = illumination;
        this.gaming = gaming;
    }

    public Integer getKeyCountMin() {
        return keyCountMin;
    }

    public void setKeyCountMin(Integer keyCountMin) {
        this.keyCountMin = keyCountMin;
    }

    public Integer getKeyCountMax() {
        return keyCountMax;
    }

    public void setKeyCountMax(Integer keyCountMax) {
        this.keyCountMax = keyCountMax;
    }

    public Boolean getIllumination() {
        return illumination;
    }

    public void setIllumination(boolean illumination) {
        this.illumination = illumination;
    }

    public Boolean getGaming() {
        return gaming;
    }

    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }
}
