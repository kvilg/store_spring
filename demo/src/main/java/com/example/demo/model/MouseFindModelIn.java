package com.example.demo.model;

public class MouseFindModelIn extends ProductFindModelIn{

    private Integer keyCountMin;
    private Integer keyCountMax;
    private Boolean illumination;
    private Boolean gaming;

    private Integer[] dpi;

    public MouseFindModelIn() {
        super();
    }

    public MouseFindModelIn(String name, Integer minPrice, Integer maxPrice, Integer keyCountMin, Integer keyCountMax, Boolean illumination, Boolean gaming,Integer[] dpi) {
        super(name, minPrice, maxPrice);
        this.keyCountMin = keyCountMin;
        this.keyCountMax = keyCountMax;
        this.illumination = illumination;
        this.gaming = gaming;
        this.dpi = dpi;
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

    public void setIllumination(Boolean illumination) {
        this.illumination = illumination;
    }

    public Boolean getGaming() {
        return gaming;
    }

    public void setGaming(Boolean gaming) {
        this.gaming = gaming;
    }

    public Integer[] getDpi() {
        return dpi;
    }

    public void setDpi(Integer[] dpi) {
        this.dpi = dpi;
    }
}
