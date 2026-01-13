package com.generation.nsmpi.model.entities;

public enum Specialty {
    INTERNAL_MEDICINE("Internal Medicine"),
    CARDIOLOGY("Cardiology"),
    GENERAL_SURGERY("General Surgery"),
    PEDIATRICS("Pediatrics"),
    GYNECOLOGY_OBSTETRICS("Gynecology and Obstetrics"),
    ORTHOPEDICS_TRAUMA("Orthopedics and Traumatology"),
    EMERGENCY_MEDICINE("Emergency Medicine"),
    ANESTHESIOLOGY("Anesthesiology and Intensive Care"),
    NEUROLOGY("Neurology"),
    PSYCHIATRY("Psychiatry");

    private final String displayName;

    Specialty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
