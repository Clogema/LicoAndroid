package fr.isen.lico;

/**
 * Created by lanthonioz on 19/03/2018.
 */

public class Jeu {
    private String[] Theme;
    private String[] Dilemme;
    private String[] Divers;
    private String[] Caliente;
    private String Mode;

    public String[] getTheme ()
    {
        return Theme;
    }

    public void setTheme (String[] Theme)
    {
        this.Theme = Theme;
    }

    public String getRandomTheme ()
    {
        return Theme[(int)(Math.random() * Theme.length)];
    }

    public String[] getDilemme ()
    {
        return Dilemme;
    }

    public String getRandomDilemme ()
    {
        return Dilemme[(int)(Math.random() * Dilemme.length)];
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

    public String getMode () {
        return Mode;
    }

    public String getRandomDivers ()
    {
        String rand = Divers[(int)(Math.random() * Divers.length)];

        if (rand.contains("THEME")) {
            this.Mode = "Thème";
            rand = rand.replace("THEME", getRandomTheme());
        }
        else if (rand.contains("DILEMME")) {
            this.Mode = "Dilemme";
            rand = rand.replace("DILEMME.", getRandomDilemme());
        }
        else {
            this.Mode = "Jeu";
        }
        return rand;
    }

    public String[] getCaliente ()
    {
        return Caliente;
    }

    public String getRandomCaliente ()
    {
        return Caliente[(int)(Math.random() * Caliente.length)];
    }

    public String getRandom ()
    {
        String rand = "";
        int i = (int)(Math.random() * 100);

        if(i < 10)
        {
            this.Mode = "Caliente";
            rand = getRandomCaliente() + "";
        }
        else {
            rand = getRandomDivers() + "";
        }

        return rand;
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
