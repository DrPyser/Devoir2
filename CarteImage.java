public class CarteImage extends Carte{
    private String fichier;

    public CarteImage(String fichier,boolean recto){
	super(recto);
	this.fichier = fichier;
    }

    public CarteImage(CarteImage carte){
	this(carte.fichier,carte.recto);
    }

    public CarteImage duplique(){
	return (new CarteImage(this));
    }

    public String toString(){
	return ("Nom du fichier: "+this.fichier
		+"\n coté: "+this.recto);
    }

    public boolean rectoidentique(Carte carte){
	return (carte instanceof CarteImage && carte.fichier.equal(this.fichier));
    }
}
