import javax.swing.*;
import java.awt.*;

/**
 * Created by Alexandra on 19-03-15.
 * sub-class of carte
 */
public class PanneauDeCartes extends JPanel{

    private int delaiInitial, delaisMauvaisePaire;//contient le temps d'attende
    GridLayout carteLayout;//contient le grid
    JButton cartes[]; // contient les cartes
    boolean premiereTournee = false, deuxiemeTournee = false;//contient les cartes si tourner ou non

    //constructeur prend le nb ranger nb colonnes les cartes le delai affichage et delai erreur
    public PanneauDeCartes(int nRangees, int nColonnes,JButton[] carte,
                           int delaiAffichageDebut, int delaiAffichageErreur){

        carteLayout = new GridLayout( nRangees, nColonnes,10,10);
        setLayout(carteLayout);
        delaiInitial = delaiAffichageDebut;
        delaisMauvaisePaire = delaiAffichageErreur;
        cartes = carte;
    }

    //instancie le clicklistener
    ClickListener click = new ClickListener(this);

    //contient la carte tourne
    public void runGame (Carte carteTourne){

        //observe quelle carte est tourner
        //TODO tourne les cartes et joue le jeu !
        if (!premiereTournee){
            premiereTournee = true;
        } else if (!deuxiemeTournee){
            deuxiemeTournee = true;
        }

    }

    public void delaiAffichageInitial(int delaiInitial){

        sleep(delaiInitial);

    }

    public void delaiAffichageMauvaisePaire(int delaisMauvaisePaire){

        sleep(delaisMauvaisePaire);

    }

    public static void sleep(int secondes){

        long temps = secondes*1000;

        try {

            Thread.sleep(temps);

        } catch(InterruptedException e){

            System.out.println("Sleep interrompu");

        }

    }

}
