import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alexandra on 19-03-15.
 */

public class PanneauDeCartes extends JPanel {

    private final int delaiInitial, delaiMauvaisePaire;//contient le temps d'attende
    private final Carte cartes[]; //contient les cartes
    private GridLayout carteLayout;//contient le grid
    private boolean premiereTournee = false, deuxiemeTournee = false;//contient les cartes si tourner ou non
    private Carte premiereCarte;//premiere carte
    private int nombreDePaires = 0;//garde le nombre de paire retourner
    private int nbCoup = 0;//garde le nombre de coup
    private ClickListener clicklistener;
    private int nbCartesShown = 0;

    //constructeur prend le nb ranger nb colonnes les cartes le delai affichage et delai erreur
    public PanneauDeCartes(int nRangees, int nColonnes, Carte[] cartes,
                           int delaiAffichageDebut, int delaiAffichageErreur) {

        this.cartes = cartes;//cartes du panneau
        this.carteLayout = new GridLayout(nRangees, nColonnes, 10, 10);
        this.setLayout(carteLayout);

        this.delaiInitial = delaiAffichageDebut;//délai pour l'affichage initial des cartes
        this.delaiMauvaisePaire = delaiAffichageErreur;//délai lors d'une erreur de la part du joueur

        //instancie le clicklistener
        this.clicklistener = new ClickListener(this);

        for (int i = 0; i < cartes.length; i++) {
            this.add(cartes[i]);//ajoute la carte au panneau
            this.cartes[i].addMouseListener(clicklistener);//ajoute le listener à la carte
            this.cartes[i].montre();//montre la carte
        }
        delaiAffichageInitial();//attend 'delaiInitial' secondes, et retourne les cartes
    }

    public int getNbCartesShown() {
        return this.nbCartesShown;
    }

    //permet de tourner une carte en gérant le délai imposé lors d'une erreur
    public void carteClicked(final Carte carteTourne) {
        Timer timer;//le timer utiliser lors d'une erreur
        //observe quelle carte est tournée
        if (!premiereTournee) {//premiere carte tourné
            if (carteTourne.estCachee()) {
                //si la carte n'est pas déjà tourné
                this.premiereTournee = true;
                carteTourne.montre();
                this.nbCartesShown++;
                this.premiereCarte = (Carte) carteTourne;
                this.nbCoup++;
            } else {
                System.out.println("Carte déjà retournée.");
            }

        } else if (!deuxiemeTournee) {
            //deuxieme carte tournée
            if (carteTourne.estCachee()) {
                //si carte pas déjà tournée
                this.deuxiemeTournee = true;
                carteTourne.montre();
                this.nbCartesShown++;
                this.nbCoup++;
                //regarde si identique si oui 1 paire de plus si non attente delai erreur
                if (carteTourne.rectoIdentique(premiereCarte)) {
                    //bravo une paire reussie
                    nombreDePaires++;
                    if (this.nbCartesShown == cartes.length) {
                        //si on a le nombre de paire
                        System.out.println("Bravo vous avez toutes les paires! ");
                        System.out.println("Vous avez gagne en " + nbCoup + " coups.");

                    }

                } else {
                    //si deux cartes différentes, délais d'attente
                    //on instancie directement le timer
                    //après 'delaiMauvaisePaire' millisecondes, retourne les cartes
                    timer = new Timer(delaiMauvaisePaire, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            premiereCarte.cache();//retourne la première carte
                            carteTourne.cache();//retourne la deuxième carte
                            nbCartesShown -= 2;
                        }
                    });
                    timer.setInitialDelay(delaiMauvaisePaire);//le délais initial est celui déterminé par la variable "delaiMauvaisePaire"
                    timer.setRepeats(false);//pas de répétition
                    timer.start();

                }


            } else {
                System.out.println("Carte déjà retournée.");
            }

            //on réinitialise le "cycle"
            this.premiereTournee = false;
            this.deuxiemeTournee = false;

        } else {
            //si un click est fait sur une troisième carte avant la fin des délais
            System.out.println("Vous ne pouvez pas encore tourner une autre carte.");
        }

    }

    //délais initial pour l'affichage des cartes
    public void delaiAffichageInitial() {

        //après 'délaiInitial' millisecondes, cache toutes les cartes du jeu
        Timer timer = new Timer(this.delaiInitial, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Carte carte : cartes) {
                    carte.cache();
                }
            }
        });
        timer.setInitialDelay(this.delaiInitial);//délai initial = 'délaiInitial'
        timer.setRepeats(false);//pas de répétition
        timer.start();
    }

}
