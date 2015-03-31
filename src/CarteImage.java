import javax.swing.*;
import java.awt.*;

public class CarteImage extends Carte {
    private String path;//chemin vers l'image
    private ImageIcon image;//image

    public CarteImage(String path, boolean recto) {
        super(recto);
        this.path = path;
        this.image = new ImageIcon(path);
    }

    //constructeur de copie
    public CarteImage(CarteImage carte) {
        this(carte.path, carte.recto);
    }

    public String getPath() {
        return this.path;
    }

    public CarteImage duplique() {
        return (new CarteImage(this));
    }

    public String toString() {
        return ("Nom du fichier: " + this.path
                + "\n coté: " + ((this.recto) ? "recto" : "verso"));
    }

    //si 'carte' de type 'CarteImage', compare le path de l'image
    public boolean rectoIdentique(Carte carte) {
        return carte instanceof CarteImage && ((CarteImage) carte).getPath().equals(this.path);
    }

    public void paintRecto(Graphics2D g) {
        g.drawImage(this.image.getImage(),
                0,
                0,//coordonnés de référence = coin supérieur gauche
                this.getWidth(),//largeur de la carte
                this.getHeight(),//hauteur de la carte
                this);//l'observateur est la carte elle même
        this.repaint();
    }
}
