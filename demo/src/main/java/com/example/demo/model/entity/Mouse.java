package com.example.demo.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mouse extends Product{

    private int keyCount;
    private boolean illumination;
    private boolean gaming;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mouse_id")
    public Set<MouseDpi> dpi;



    public Mouse() {
        super();
    }

    public Mouse(String name, int price, int keyCount, boolean illumination, boolean gaming, Set<MouseDpi> dpi) {
        super(name, price);
        this.keyCount = keyCount;
        this.illumination = illumination;
        this.gaming = gaming;
        this.dpi = dpi;
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

    public Set<MouseDpi> getDpi() {
        return dpi;
    }

    public void setDpi(Set<MouseDpi> dpi) {
        this.dpi = dpi;
    }


}
