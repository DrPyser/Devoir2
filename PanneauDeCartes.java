import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexandra on 19-03-15.
 * sub-class of carte
 */
public class PanneauDeCartes extends JPanel{

    private int delaiInitial, delaisMauvaisePaire;//contient le temps d'attende
    GridLayout carteLayout;//contient le grid
    Carte cartes[] ; // contient les cartes
    boolean premiereTournee = false, deuxiemeTournee = false;//contient les cartes si tourner ou non
    Carte premiereCarte;//premiere carte
    int nombreDePaire = 0;//garde le nombre de paire retourner
    int nbCoup = 0;//garde le nombre de coup
    Timer timer;//conserve le timer


    //constructeur prend le nb ranger nb colonnes les cartes le delai affichage et delai erreur
    public PanneauDeCartes(int nRangees, int nColonnes,Carte[] carte,
                           int delaiAffichageDebut, int delaiAffichageErreur){

        cartes = new Carte[carte.length];
        carteLayout = new GridLayout( nRangees, nColonnes,10,10);
        setLayout(carteLayout);
        delaiInitial = delaiAffichageDebut;
        delaisMauvaisePaire = delaiAffichageErreur;
        for (int i = 0; i < carte.length;i++) {
            cartes[i] = carte[i];
            this.add(carte[i]);
        }
    }

    //instancie le clicklistener
    ClickListener click = new ClickListener(this);

    //contient la carte tourne
    public void runGame (Carte carteTourne){

        //observe quelle carte est tourner
        if (!premiereTournee){//premiere carte tourner
            if (carteTourne.estCachee()) {//si la carte n'est pas tourner on la tourne
                premiereTournee = true;
                carteTourne.montre();
                premiereCarte = (Carte) carteTourne;
                nbCoup ++;
            }else{

                System.out.println("Carte deja retourne.");
            }
        } else if (!deuxiemeTournee){//deuxieme carte tourner
            if(carteTourne.estCachee()) {
                deuxiemeTournee = true;
                carteTourne.montre();
                nbCoup++;
                //regarde si identique si oui 1 paire de plus si non attente delais erreur
                if (carteTourne.rectoIdentique(premiereCarte)) {//bravo une pare reussie
                    nombreDePaire++;
                    if (nombreDePaire == Math.floor(cartes.length / 2)) {//si on a le nombre de paire
                        System.out.println("Bravo vous avez toutes les paires! ");
                        System.out.println("Vous avez gagne en "+nbCoup+" coups.");
                    }


                } else {//cas d'attente et retourne les cartes apres l'attente

                    this.delaiAffichageMauvaisePaire(delaisMauvaisePaire);
                    premiereCarte.cache();
                    carteTourne.cache();

                }
            }
        }else {//tentative de troisieme tournage de carte
            System.out.println("Vous ne pouvez pas encore tourner une autre carte.");
        }

    }

    //diverse delais soit affichage initial erreur et le sleep
    public void delaiAffichageInitial(final int delaiInitial){

        timer = new Timer(delaiInitial,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleep(delaiInitial);
                timer.stop();
            }
        });

        for(int i = 0; i<cartes.length; i++){

            cartes[i].cache();

        }

    }

    public void delaiAffichageMauvaisePaire(final int delaisMauvaisePaire){

        timer = new Timer(delaiInitial,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleep(delaisMauvaisePaire);
                timer.stop();
            }
        });

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
