import java.util.Arrays;

public abstract class GenerateurDeCartes{
    private String nom;
    
    public String getNom(){
	return this.nom;
    }
    
    //genere une carte
    public abstract Carte genereUneCarte();
    
    //retourne le nombre de cartes uniques de l'ensemble des cartes du generateur
    public abstract int nombreDeCartesDifferentes();
    
    //genere un array de cartes en utilisant la methode genereUneCarte
    //note: la méthode ne peut actuellement pas gerer le cas ou n>nombre cartes possible, et entrera dans une boucle infini
    public Carte[] genereCartes(int n){
	Carte[] cartes = new Carte[n];
	Carte carte;
	for(int i = 0;i<cartes.length;i++){
	    carte = genereUneCarte();
	    //si la carte est déjà présente dans la liste
	    if(Arrays.asList(cartes).indexOf(carte) != -1){
		i = i-1;//on recommence l'itération actuelle
	    } else {
		cartes[i] = carte;
	    }
	}
	return cartes;
    }

    public Carte[] generePairesDeCartesMelangees(int n){
	Carte[] cartes = genereCartes(n);
	Carte[] paires = new Carte[2*n];
	for(int i = 0;i<cartes.length;i++){
	    paires[i] = cartes[i];
	    paires[i+n] = (Carte) cartes[i].duplique();//les duplicats sont d'abord placé à l'index i+n
	}

	Carte.melangeCartes(paires);//melange les cartes
	return paires;//retourne la liste des paires
    }
}
