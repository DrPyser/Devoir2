import javax.swing.*;
import java.awt.*;
import java.util.Scanner;//import le scanner
/**
 * Created by Alexandra on 19-03-15.
 */
public class Memory {

    public static void main (String[] args){
        //il est necessaire d'avoir cinq arguments

        if (args.length < 5 ) {


            System.out.println("Nombre de paramètres incorrects.");
            System.out.println("Utilisation: java Memory <nRangees> <nColonnes>" +
                    " <delaiAffichageInitial(ms)> <delaiAffichageMauvaisePaire(ms)> <numeroDeTheme>.");
            System.out.println("Ex: java Memory 5 6 5000 1000 3");
            System.out.println("Voici la liste des themes disponibles: \n " +
                                    "0: Cartes couleurs \n 1: Lettres A ... Z \n 2: Noms d'émotions" +
                                    "3: Images d'animaux \n 4: Images de galaxies \n 5: Melange des themes 0 à 4) ");

            System.exit(42);

        }

        //prend toutes les cinq valeurs donne par l'utilisateur
         int nbRangees = Integer.parseInt(args[0]);
         int nbColonnes = Integer.parseInt(args[1]);
        final int delaiInitial = (int)(Double.parseDouble(args[2]))*1000;
        final int delaiErreur = (int)(Double.parseDouble(args[3]))*1000;
        int themes = Integer.parseInt(args[4]);
        Carte cartes[];//contient toutes les cartes generes

        //demande le theme tant qu'il n'est pas valide
        while (themes > 5 || themes < 0) {

            System.out.println("Theme non valide. Choisir parmis la liste de themes disponible: \n" +
                                     "0: Cartes couleurs \n 1: Langage Informatique \n 2: Galaxies" +
                                     "3:  \n 4:  \n 6:Image Informatique \n 7: Image Lettres Grecs \n " +
                                     "8: Melange des themes 0 à 7) ");

            Scanner scan = new Scanner(System.in);
            themes = scan.nextInt();

        }
        //on ne peut pas avoir plus de 30 cartes en jeu
        while (nbColonnes*nbRangees > 30){

            System.out.println("Vous ne pouvez pas avoir plus de 30 cartes en jeu. (colonne * range < 30) ");
            System.out.println("Nombre de colonnes :");
            Scanner scanC = new Scanner(System.in);
            nbColonnes = scanC.nextInt();
            System.out.println("Nombre de range :");
            Scanner scanR = new Scanner(System.in);
            nbRangees = scanR.nextInt();

        }

        // TODO lis le themes et cree un tableau des mots / images
        switch(themes) {
            case 0:
                GenerateurDeCartesCouleur genereC = new GenerateurDeCartesCouleur();
                cartes = genereC.generePairesDeCartesMelangees(nbColonnes * nbRangees);//cree les cartes
                break;
            default:
                GenerateurDeCartesCouleur genereA = new GenerateurDeCartesCouleur();
                cartes = genereA.generePairesDeCartesMelangees(nbColonnes * nbRangees);//cree les cartes
                break;
        }

        PanneauDeCartes panel = new PanneauDeCartes(nbRangees,nbColonnes,cartes,delaiInitial,delaiErreur);
        panel.setLayout(new GridLayout(nbRangees,nbColonnes));
        for (int i = 0; i<cartes.length;i++){
            panel.cartes[i].montre();
            panel.cartes[i].setVisible(true);
            panel.cartes[i].addMouseListener(panel.click);
        }

        panel.delaiAffichageInitial(delaiInitial);

        JFrame window = new JFrame("Memory Game");//title of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what it does when we push x

        window.getContentPane().add(panel, BorderLayout.CENTER);//set le panel au centre

        window.setSize(850, 500);//size in pixel par defaut
        window.setLocationRelativeTo(null);//set to the center of the desktop
        window.setVisible(true);//rend la fenetre visible

    }

}
