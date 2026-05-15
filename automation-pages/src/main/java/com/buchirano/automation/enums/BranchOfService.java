package com.buchirano.automation.enums;

/**
 * Enum representing Branch of Service options used in Military Service Details.
 */
public enum BranchOfService {
    AA_US_ARMY_AIR_FORCES("AA"),
    V2_AA_US_ARMY_AIR_FORCES("AF - US Air Force"),
    AC_US_ARMY_AIR_CORPS("AC"),
    V2_AC_US_ARMY_AIR_CORPS("AC-US Army Air Corps"),
    AD_US_ARMY_SIGNAL_CORPS_AERO_DIVISION("AD"),
    V2_AR_US_ARMY("AR - US Army"),
    V2_AS_ARMY_AIR_SERVICE("AS - US Army Air Service"),
    NG_US_ARMY_NATIONAL_GUARD("NG - US ARMY NATIONAL GUARD"),
    V2_MC_US_MARINE_CORPS("MC - US Marine Corps"),
    V2_AG_US_AIR_NATIONAL_GUARD("AG - US AIR NATIONAL GUARD"),
    V2_NG_US_ARMY_NATIONAL_GUARD("NG - US ARMY NATIONAL GUARD"),
    V2_IR_IRREGULAR_FORCES_LAOS("IR - IRREGULAR FORCES LAOS"),
    V2_OW_CONTINENTAL_MARINE("OW - CONTINENTAL MARINE"),
    V2_SP_SPECIAL_GUERILLA_UNIT_LAOS("SP - SPECIAL GUERILLA UNIT LAOS");

    private final String branchOfService;

    BranchOfService(String branchOfService) {
        this.branchOfService = branchOfService;
    }

    public String getText() {
        return branchOfService;
    }
}
