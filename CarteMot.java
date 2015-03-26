import java.awt.*;

public class CarteMot extends Carte{
    private String mot;//mot charactérisant cette carte

    public CarteMot(String mot,boolean recto){
	super(recto);
	this.mot = mot;
    }
    
    public CarteMot(CarteMot carte){
	this(carte.mot,carte.recto);	
    }

    public CarteMot duplique(){
	return (new CarteMot(this));
    }

    public String toString(){
	return ("Mot: "+this.mot+"\n Coté: "+(this.recto?"recto":"verso"));
    }
	    
    public boolean rectoIdentique(Carte carte){
	return (carte instanceof CarteMot && ((CarteMot) carte).mot.equals(this.mot));
    }

    public void paintRecto(Graphics2D g){
	g.drawString(this.mot,(int) Math.floor(this.getWidth()/2),(int) Math.floor(this.getHeight()/2));//affiche le mot au milieu de la carte
	this.repaint();
    }
	    
}
