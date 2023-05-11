package com.example.demo.model.entity;

import javax.persistence.Entity;

@Entity
public class Keyboard extends Product{

    private int keyCount;
    private boolean illumination;
    private boolean gaming;

    public Keyboard(){
        super();
    }

    public Keyboard(String name, int price, int keyCount, boolean illumination, boolean gaming) {
        super(name, price);
        this.keyCount = keyCount;
        this.illumination = illumination;
        this.gaming = gaming;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public boolean isIllumination() {
        return illumination;
    }

    public void setIllumination(boolean illumination) {
        this.illumination = illumination;
    }

    public boolean isGaming() {
        return gaming;
    }

    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    @Override
    public String toString() {
        return this.name + "\n" +
                this.img + "\n" +
                this.price + "\n" +
                this.keyCount + "\n" +
                this.illumination + "\n" +
                this.gaming;
    }
}
