package com.buchirano.automation.enums;

/**
 * Enum representing Military Honors field options on the Interment screen.
 */
public enum MilitaryHonors {
    NONE("--None--"),
    AIR_FORCE("Air Force"),
    ARMY("Army"),
    NAVY("Navy"),
    MARINE_CORPS("Marine Corps");

    private final String militaryHonors;

    MilitaryHonors(String militaryHonors) {
        this.militaryHonors = militaryHonors;
    }

    public String getText() {
        return militaryHonors;
    }
}
