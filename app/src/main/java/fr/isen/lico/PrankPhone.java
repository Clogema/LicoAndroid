package fr.isen.lico;

/**
 * Created by lanthonioz on 21/03/2018.
 */

public class PrankPhone {

    private String[] Mots;

    public String[] getMots() {
        return Mots;
    }

    public void setMots(String[] mots) {
        this.Mots = mots;
    }

    public String getMot ()
    {
        return Mots[(int)(Math.random() * Mots.length)];
    }
}
