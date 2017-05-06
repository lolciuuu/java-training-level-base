package com.sda.collection;

public class PlanetClass {
    public final static PlanetClass EARTH = new PlanetClass("EARTH");
    public final static PlanetClass MARS = new PlanetClass("MARS");

    String name;

    private PlanetClass(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public static void valueOf(String value) {
        //TODO: na poniedzialek
    }

    public static void values() {
        //TODO: na poniedzialek
    }
}
