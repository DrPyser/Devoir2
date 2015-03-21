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
		+",\n coté: "+(this.recto?"recto":"verso"));
    }

    public boolean rectoIdentique(Carte carte){
	return (carte instanceof CarteCouleur && carte.couleur.equals(this.couleur));
    }

    public void paintRecto(Graphics2D g){
	g.setColor(this.couleur);//fixe la couleur de la carte à la couleur déterminée par la propriété 'couleur'
	g.fillRect(0,0,this.getWidth()-1,this.getHeight()-1);//peinture la carte avec la couleur précédemment établi
    }
    
}
