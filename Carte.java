import javax.swing.*;
import java.awt.*;
public abstract class Carte extends JComponent {

    protected boolean recto;//recto = état "découvert". !recto = état "caché"

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
	g.setColor(Color.BLACK);//fixe la couleur du contexte graphique de la carte à 'noir'
	g.fillRect(0,0,this.getWidth()-1,this.getHeight()-1);//peinture le contexte graphique de la carte avec la couleur 'noir'
    }
	
    //méthode qui affichera le mot,l'image ou un rectangle d'une certaine couleur , selon le type de la carte
    public abstract void paintRecto(Graphics2D g);
    
    
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
	    index = (int) (Math.floor(Math.random()*cartes.length));
	    temp = cartes[index];
	    cartes[index] = cartes[i];
	    cartes[i] = temp;
	}
    }
}
