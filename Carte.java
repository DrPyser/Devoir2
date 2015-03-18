import javax.swing.*;

public abstract class Carte extends JComponent {
    private boolean recto;
    private Graphics2D imageRecto;

    protected Carte(boolean recto){
	this.recto = recto;
    }

    protected Carte(Carte carte){
	return this(carte.recto);
    }

    public boolean estMontree(){
	return recto;
    }

    public boolean estCachee(){
	return !recto;
    }

    public void montre(){
	this.recto = true;
    }
    
    public void cache(){
	this.recto = false;
    }
    
    public void retourne(){
	this.recto = !this.recto;
    }

    public void paintVerso(Graphics2D g){
	
    }

    public abstract void paintRecto();

    public void paintComponent(Graphics2D g){
	if (this.recto) {
	    paintRecto(g);
	} else{
	    paintVerso(g);
	}
    }

    public abstract boolean rectoIdentique(Carte carte){
	return (this.imageRecto.equal(carte.imageRecto));
    }

    public abstract Object duplique();
    
    public static void melageCartes(Carte[] cartes){
	
    }
}
