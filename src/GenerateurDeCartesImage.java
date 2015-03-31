public class GenerateurDeCartesImage extends GenerateurDeCartes {
    String nom;
    String[] fichiers;

    public GenerateurDeCartesImage(String theme, String[] fichiers) {
        this.nom = theme;
        this.fichiers = fichiers;
    }

    public Carte genereUneCarte() {
        int index = (int) Math.floor(Math.random() * this.fichiers.length);
        return (new CarteImage(this.fichiers[index], false));
    }

    public int nombreDeCartesDifferentes() {
        return this.fichiers.length;
    }
}
