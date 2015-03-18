public class CarteImage extends Carte{
    private String pathtoimage;
    public CarteImage(String pathtoimage,boolean recto){
	super(recto);
	this.pathtoimage = pathtoimage;
    }
    public CarteImage(CarteImage carte){
	this(carte.pathtoimage,carte.recto);
    }
    public CarteImage duplique(){
	return (new CarteImage(this));
    }
    public String toString(){
	return ("path to image: "+this.pathtoimage
		+"\n coté: "+this.recto);
    }
}
