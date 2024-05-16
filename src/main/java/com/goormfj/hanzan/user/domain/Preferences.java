package com.goormfj.hanzan.user.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Preferences {

    private boolean preference1;
    private boolean preference2;
    private boolean preference3;

    // Constructors
    public Preferences() {}

    public Preferences(boolean preference1, boolean preference2, boolean preference3) {
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
    }

    // Getters and Setters
    public boolean isPreference1() {
        return preference1;
    }

    public void setPreference1(boolean preference1) {
        this.preference1 = preference1;
    }

    public boolean isPreference2() {
        return preference2;
    }

    public void setPreference2(boolean preference2) {
        this.preference2 = preference2;
    }

    public boolean isPreference3() {
        return preference3;
    }

    public void setPreference3(boolean preference3) {
        this.preference3 = preference3;
    }

}
