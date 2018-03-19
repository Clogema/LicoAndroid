package fr.isen.lico;

/**
 * Created by lanthonioz on 19/03/2018.
 */

public class Jeu {
    private String[] Theme;
    private String[] Dilemme;
    private String[] Divers;
    private String[] Caliente;

    public String[] getTheme ()
    {
        return Theme;
    }

    public void setTheme (String[] Theme)
    {
        this.Theme = Theme;
    }

    public String[] getDilemme ()
    {
        return Dilemme;
    }

    public void setDilemme (String[] Dilemme)
    {
        this.Dilemme = Dilemme;
    }

    public String[] getDivers ()
    {
        return Divers;
    }

    public void setDivers (String[] Divers)
    {
        this.Divers = Divers;
    }

    public String[] getCaliente ()
    {
        return Caliente;
    }

    public void setCaliente (String[] Caliente)
    {
        this.Caliente = Caliente;
    }

    @Override
    public String toString()
    {
        return "Theme = "+Theme+", Dilemme = "+Dilemme+", Divers = "+Divers+", Caliente = "+Caliente;
    }
}
