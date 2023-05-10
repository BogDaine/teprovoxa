package com.example.teprovoxa;

import java.util.ArrayList;

public class Animal {
    private String mName;
    private String mContinent;

    public Animal(String name, String continent) {
        mName = name;
        mContinent = continent;
    }

    public String getName() {
        return mName;
    }
    public String getmContinent(){return mContinent;}

    public boolean isOnline() {
        return true;
    }

    public static ArrayList<Animal> createAnimalsList() {
        ArrayList<Animal> animals = new ArrayList<Animal>();

        animals.add(new Animal("tigru tasmanian", "Australia"));
        animals.add(new Animal("porc", "Europa"));
        animals.add(new Animal("echidna", "Australia"));
        animals.add(new Animal("iepure de camp", "Europa"));
        animals.add(new Animal("ornitorinc", "Australia"));
        animals.add(new Animal("dragon de komodo", "Asia"));
        animals.add(new Animal("panda urias", "Asia"));
        animals.add(new Animal("bizon", "America"));
        animals.add(new Animal("castor", "America"));
        animals.add(new Animal("urs negru", "America"));
        animals.add(new Animal("leopard", "Australia"));
        animals.add(new Animal("sconx", "America"));
        animals.add(new Animal("iguana", "America"));
        animals.add(new Animal("zebra", "Africa"));
        animals.add(new Animal("capra alpina", "Europa"));
        animals.add(new Animal("leu", "Africa"));
        animals.add(new Animal("hipopotam", "Africa"));
        animals.add(new Animal("bonobo", "Africa"));
        animals.add(new Animal("impala", "Africa"));
        animals.add(new Animal("termita", "Africa"));
        animals.add(new Animal("pangolin", "Asia"));
        animals.add(new Animal("rinocer", "Africa"));
        animals.add(new Animal("monjon ", "Australia"));
        animals.add(new Animal("yak", "Asia"));
        animals.add(new Animal("pisica", "America"));
        animals.add(new Animal("capra neagra", "Europa"));
        animals.add(new Animal("caracal", "Asia"));
        animals.add(new Animal("coiot", "America"));
        animals.add(new Animal("mistret", "Europa"));
        animals.add(new Animal("oaia", "Europa"));
        animals.add(new Animal("cangur", "Australia"));
        animals.add(new Animal("emu", "Australia"));
        animals.add(new Animal("ras", "Europa"));
        animals.add(new Animal("oposum", "Australia"));
        animals.add(new Animal("tigru bengalez", "Asia"));
        animals.add(new Animal("soarece de casa", "Europa"));
        animals.add(new Animal("papagal gri", "Africa"));
        animals.add(new Animal("vulpe rosie", "Europa"));
        animals.add(new Animal("piton", "Asia"));
        animals.add(new Animal("boa", "America"));
        animals.add(new Animal("sarpele tigru", "Australia"));
        animals.add(new Animal("veverita gri", "America"));
        animals.add(new Animal("caprioara", "Europa"));
        animals.add(new Animal("cerb lopatar", "Europa"));
        animals.add(new Animal("colibri", "America"));
        animals.add(new Animal("testoasa", "America"));
        animals.add(new Animal("diavol tasmanian", "Australia"));
        animals.add(new Animal("dhole", "Asia"));
        animals.add(new Animal("urs polar", "America"));
        animals.add(new Animal("macac", "Asia"));
        animals.add(new Animal("cobra", "Asia"));
        return animals;
    }
}