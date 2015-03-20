public class GenerateurDeCartesMot extends GenerateurDeCartes{
    private String nom;
    private String[] mots;

    //constructeur du generateur
    public GenerateurDeCartesMot(String theme,String[] mots){
	this.nom = theme;
	this.mots = mots;
    }
    
    //generateur de carte
    public Carte genereUneCarte(){
	int index = (Math.floor(Math.random()*this.mots.length));
	return (new CarteMot(this.mots[index],false));
    }
    
    public nombreDeCartesDifferentes(){
	return (this.mots.length);
    }
}
