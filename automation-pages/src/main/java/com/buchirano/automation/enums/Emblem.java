package com.buchirano.automation.enums;

/**
 * Enum representing Emblem field options on the Interment screen.
 */
public enum Emblem {
    NONE("--None--"),
    CHRISTIAN_CROSS("01 - CHRISTIAN CROSS"),
    BUDDHIST("02 - BUDDHIST (Wheel of Righteousness)"),
    HEBREW_STAR_OF_DAVID("03 - HEBREW (Star of David)"),
    PRESBYTERIAN_CROSS("04 - PRESBYTERIAN CROSS"),
    RUSSIAN_ORTHODOX_CROSS("05 - RUSSIAN ORTHODOX CROSS"),
    LUTHERAN_CROSS("06 - LUTHERAN CROSS"),
    EPISCOPAL_CROSS("07 - EPISCOPAL CROSS"),
    UNITARIAN_CHURCH_FLAMING_CHALICE("08 - UNITARIAN CHURCH (Flaming Chalice)"),
    UNITED_METHODIST_CHURCH("09 - UNITED METHODIST CHURCH"),
    AARONIC_ORDER_CHURCH("10 - AARONIC ORDER CHURCH"),
    DRUZE("98 - DRUZE");

    private final String emblem;

    Emblem(String emblem) {
        this.emblem = emblem;
    }

    public String getText() {
        return emblem;
    }
}
