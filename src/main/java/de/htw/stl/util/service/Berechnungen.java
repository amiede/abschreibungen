/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.stl.util.service;

/**
 *
 * @author Oliver Avarello
 */
public class Berechnungen {

    public boolean isAfaBetrag(int input) {
        int maxAufzahler = 100;
        boolean result = false;
        int arrayIndex = input;
        if (arrayIndex == 2 || arrayIndex == 6) {
            result = true;
        } else {
            for (int i = 10; i <= maxAufzahler; i = i + 4) {
                if (arrayIndex == i) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isZeitwert(int input) {
        boolean result = false;
        int arrayIndex = input;
        if (arrayIndex == 3 || arrayIndex == 7) {
            result = true;
        } else {
            for (int i = 11; i <= 100; i = i + 4) {
                if (arrayIndex == i) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isAfaSatz(int w) {
        boolean result = false;
        if (w == 1 || w == 5) {
            result = true;
        } else {
            for (int i = 9; i <= 100; i = i + 4) {
                if (w == i) {
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean isJahr(int i) {

        boolean result = false;

        double rechnung = (double) i % 4;

        if (rechnung == 0) {
            result = true;
        }
        return result;
    }
}
