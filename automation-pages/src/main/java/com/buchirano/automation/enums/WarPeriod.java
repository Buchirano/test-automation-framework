package com.buchirano.automation.enums;

/**
 * Enum representing War Period options used in Military Service Details.
 */
public enum WarPeriod {
    KOREA("KOREA"),
    VIETNAM("VIETNAM"),
    LEBANON("LEBANON"),
    KOSOVO("KOSOVO"),
    IRAQ("IRAQ"),
    PANAMA("PANAMA"),
    PERSIAN_GULF("PERSIAN GULF"),
    SOMALIA("SOMALIA"),
    WWI("WORLD WAR I"),
    WWII("WORLD WAR II"),
    NULL("");

    private final String warPeriod;

    WarPeriod(String warPeriod) {
        this.warPeriod = warPeriod;
    }

    public String getText() {
        return warPeriod;
    }
}
