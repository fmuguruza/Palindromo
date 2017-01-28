package com.fm.dev.exercises.palindromo.utils;

/**
 * Created by Muguruza on 10/01/2017.
 */
public class DefaultCheckedException extends Exception {
    private static final long serialVersionUID = 6449371344715568491L;
    public DefaultCheckedException(String message) {
        super(message);
    }
}
