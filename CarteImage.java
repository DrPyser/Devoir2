public class CarteImage extends Carte{
    private String path;//chemin vers l'image
    private ImageIcon image;//image

    public CarteImage(String path,boolean recto){
	super(recto);
	this.path = path;
	this.image = new ImageIcon(path);
    }

    //constructeur de copie
    public CarteImage(CarteImage carte){
	this(carte.path,carte.recto);
    }

    public CarteImage duplique(){
	return (new CarteImage(this));
    }

    public String toString(){
	return ("Nom du fichier: "+this.fichier
		+"\n coté: "+this.recto);
    }

    public boolean rectoidentique(Carte carte){
	return (carte instanceof CarteImage && carte.path.equal(this.path));//test l'égalité des chemins vers l'image
    }

    public paintRecto(Graphics2D g){
	g.drawImage(this.image.getImage(),
		    0,
		    0,//coordonnés de référence = coin supérieur gauche
		    this.getWidth(),//largeur de la carte
		    this.getHeight(),//hauteur de la carte
		    this);//l'observateur est la carte elle même
	
    }
}
