package com.shliggy.ars_temeritas;

public class Chanting {
    private static boolean active;

    private Chanting(){ active = false; };

    public static boolean isActive() {
        return active;
    }

    public static void Chant(String ChantType, String key) {
        System.out.println("Chant");

        switch (ChantType) {
            case "CHANT_START" -> {
                active = true;
            }
            case "CHANT_STOP" -> {
                active = false;
            }
        }
    }

    public static void addGlyph(String key) {
    }
}