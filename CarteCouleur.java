public class CarteCouleur extends Carte{
    private Color couleur;
    public CarteCouleur(Color couleur,boolean recto){
	super(recto);
	this.couleur = couleur;
    }
    public CarteCouleur(CarteCouleur carte){
	this(carte.couleur,carte.recto);
    }

    public CarteCouleur duplique(){
	return (new CarteCouleur(this));
    }
    public String toString(){
	return ("Couleur: "+this.couleur.toString
		+",\n coté: "+(this.rcto?"recto":"verso"));
    }

    
}
