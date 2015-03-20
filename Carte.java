import javax.swing.*;
import java.awt.*;
public abstract class Carte extends JComponent {
    private boolean recto;

    protected Carte(boolean recto){
	this.recto = recto;
    }

    protected Carte(Carte carte){
	this(carte.recto);
    }

    public boolean estMontree(){
	return recto;
    }

    public boolean estCachee(){
	return !recto;
    }

    public void montre(){
	this.recto = true;
	this.repaint();//repaint le recto
    }
    
    public void cache(){
	this.recto = false;
	this.repaint();//repaint le verso
    }
    
    public void retourne(){
	this.recto = !this.recto;
    }

    public void paintVerso(Graphics2D g){
	//devrait "peinturer" un rectangle noire d'une certaine dimension
	//si l'objet 'g' représente l'espace dédier à la carte, alors on peut utiliser g.fillRect(0,0,width,height);
    }
	
    //méthode qui affichera le mot,l'image ou un rectangle d'une certaine couleur , selon le type de la carte
    public abstract void paintRecto();
    
    
    //cette méthode est appelé par repaint()
    public void paintComponent(Graphics2D g){
	if (this.recto) {
	    paintRecto(g);
	} else{
	    paintVerso(g);
	}
    }

    public abstract boolean rectoIdentique(Carte carte);
    

    public abstract Object duplique();
    
    public static void melangeCartes(Carte[] cartes){
	int index;
	Carte temp;
	for(int i=0;i<cartes.length;i++){
	    index = randomInt(0,cartes.length);
	    temp = cartes[index];
	    cartes[index] = cartes[i];
	    cartes[i] = temp;
	}
    }

    protected static int randomInt(int from,int to){
	return (Math.floor(Math.random()*to)+from);
    }
}
