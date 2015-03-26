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
    public Carte[] genereCartes(int n){
	Carte[] cartes = new Carte[n];
	Carte carte;
	int duplicats = 0;
	int treshold = n-this.nombreDeCartesDifferentes();//nombre minimal de duplicats, si n>nombre de cartes différentes
	for(int i = 0;i<cartes.length;i++){
	    carte = this.genereUneCarte();
	    //si la carte est déjà présente dans la liste
	    if(Arrays.asList(cartes).indexOf(carte) != -1){
		//si n<=nombre de cartes differentes, alors il est possible de produire un ensemble de n cartes différentes
		//si le nombre de duplicats a déjà atteint le treshold(nombre minimum de duplicats possible), 
		//alors il est possible de ne générer que des cartes différentes de celles déjà générées
		if(n<=this.nombreDeCartesDifferentes() || duplicats == treshold){
		    i = i-1;//on recommence l'itération actuelle
		} else{
		    cartes[i] = carte;
		    duplicats++;	
		}
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
