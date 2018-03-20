package fr.isen.lico;

import fr.isen.lico.Jeu;

/**
 * Created by lanthonioz on 19/03/2018.
 */

public class Picolo {
    private Jeu Jeu;
    private String[] Divers;
    private String Mode;

    public fr.isen.lico.Jeu getJeu ()
    {
        return Jeu;
    }

    public void setJeu (Jeu Jeu)
    {
        this.Jeu = Jeu;
    }

    public String getRandomJeu ()
    {
        String jeu = Jeu.getRandom();
        this.Mode = Jeu.getMode();
        return jeu;
    }

    public String[] getDivers ()
    {
        return Divers;
    }

    public String getMode ()
    {
        return Mode;
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
