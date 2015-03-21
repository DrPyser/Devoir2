import java.awt.*;

public class GenerateurDeCartesCouleur extends GenerateurDeCartes{
    private Strig nom = "Couleurs";
    private Color[] couleurs = {Color.GREEN,Color.RED,Color.BLUE,Color.CYAN,Color.MAGENTA,Color.YELLOW,Color.PINK,Color.ORANGE};

    public Carte genereUneCarte(){
	int index = (Math.floor(Math.random()*couleurs.length));
	return (new CarteCouleur(couleurs[index],false));
    }

    public int nombreDeCartesDifferentes(){
	return this.couleurs.length;
    }
    
    
}
