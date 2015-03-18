public class CarteMot extends Carte{
    String mot;
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
	return ("Mot: "+this.mot+"\n Coté: "+(this.recto?"recto":"verso");
    }
}
