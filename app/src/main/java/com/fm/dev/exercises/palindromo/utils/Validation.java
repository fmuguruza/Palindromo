package com.fm.dev.exercises.palindromo.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;

/**
 * Created by Muguruza on 10/01/2017.
 */
public class Validation {

    /**
     * Validar si el texto es palìndromo.
     *
     * @param phrase
     * @return
     * @throws Exception
     */
    public static boolean isPalindrome(String phrase) throws DefaultCheckedException {
        if (StringUtils.isNotBlank(phrase)) {
            String phraseAux = phrase.replace(" ","").trim().toLowerCase();
            phraseAux = partiallyNormalize(phraseAux).replaceAll("[^a-zñ]+", "");

            if (phraseAux.length() >= 2) {
                String wordReverse = new StringBuilder(phraseAux).reverse().toString();
                return phraseAux.equals(wordReverse);
            } else {
                throw new DefaultCheckedException("La frase es menor a 2 caracteres v\u00e1lidos, solo letras");
            }
        } else {
            throw new DefaultCheckedException("La frase es requerida");
        }
    }

    /**
     * stripAccents except Ñ
     * @param string
     * @return
     */
    private static String partiallyNormalize(String string){
        string = string.replace('ñ', '\001');
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        string = string.replace('\001', 'ñ');
        return string;
    }

}
