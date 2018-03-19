package fr.isen.lico;

import fr.isen.lico.Jeu;

/**
 * Created by lanthonioz on 19/03/2018.
 */

public class Picolo {
    private Jeu Jeu;
    private String[] Divers;

    public fr.isen.lico.Jeu getJeu ()
    {
        return Jeu;
    }

    public void setJeu (Jeu Jeu)
    {
        this.Jeu = Jeu;
    }

    public String[] getDivers ()
    {
        return Divers;
    }

    public String getRandomDivers ()
    {
        return Divers[(int)(Math.random() * Divers.length)];
    }

    public void setDivers (String[] Divers)
    {
        this.Divers = Divers;
    }

    @Override
    public String toString()
    {
        return "Divers = "+this.getRandomDivers();
    }
}
