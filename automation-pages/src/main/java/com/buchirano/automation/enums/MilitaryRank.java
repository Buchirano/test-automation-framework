package com.buchirano.automation.enums;

/**
 * Enum representing Military Rank options used in Military Service Details.
 */
public enum MilitaryRank {
    CAPT_CAPTAIN("CAPT - CAPTAIN"),
    FIRST_LT("1ST LT - FIRST LIEUTENANT"),
    FIRST_SG("1SG - FIRST SERGEANT"),
    AG_FIRST_LT("1ST LT - FIRST LIEUTENANT"),
    COL_COLONEL("COL - COLONEL"),
    CPL_CORPORAL("CPL - CORPORAL"),
    A1C_AIRMAN_1ST_CLASS("A1C - AIRMAN 1ST CLASS"),
    ONE_BOY_BOY_ONE("1 BOY - BOY ONE"),
    OTHER_MILITARY_RANK("OTHER MILITARY RANK");

    private final String militaryRank;

    MilitaryRank(String militaryRank) {
        this.militaryRank = militaryRank;
    }

    public String getText() {
        return militaryRank;
    }
}
