public class GenerateurDeCartesMultiple extends GenerateurDeCartes{
    private GenerateurDeCartes[] generateurs;//liste des generateurs de cartes

    public GenerateurDeCartesMultiple(String theme,GenerateurDeCartes[] generateurs){
	this.generateurs = generateurs;
    }

    public int nombreDeCartesDifferentes(){
	int nombredecartesdifferentes = 0;
	for(int i = 0;i<this.generateurs.length;i++){
	    nombredecartesdifferentes+=generateurs[i].nombreDeCartesDifferentes();//ajoute le nombre de cartes differentes de chaque generateur
	}
	return nombredecartesdifferentes;
    }
    
}
