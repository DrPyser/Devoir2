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
