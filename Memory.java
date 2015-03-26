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
        final int nbRangees = Integer.parseInt(args[0]);
        final int nbColonnes = Integer.parseInt(args[1]);
        final int delaiInitial = (int)(Double.parseDouble(args[2]))*1000;
        final int delaiErreur = (int)(Double.parseDouble(args[3]))*1000;
        int themes = Integer.parseInt(args[4]);

        //demande le theme tant qu'il n'est pas valide
        while (themes > 5 || themes < 0) {

            System.out.println("Theme non valide. Choisir parmis la liste de themes disponible: \n" +
                                     "0: Cartes couleurs \n 1: Lettres A ... Z \n 2: Noms d'émotions" +
                                     "3: Images d'animaux \n 4: Images de galaxies \n 5: Melange des themes 0 à 4) ");

            Scanner scan = new Scanner(System.in);
            themes = scan.nextInt();

        }

        JButton [] tableauCartes = new JButton[nbRangees * nbColonnes];//on cree les Cartes

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(nbRangees,nbColonnes));
        for (int i = 0; i<tableauCartes.length;i++){
            tableauCartes[i] = new JButton("nb: " + i);
            panel.add(tableauCartes[i]);
        }

        jeuMemoire(panel);

    }


    public static void  jeuMemoire(JPanel panneau){

        JFrame window = new JFrame("Memory Game");//title of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what it does when we push x

        window.getContentPane().add(panneau, BorderLayout.CENTER);//set le panel au centre

        window.setSize(850, 500);//size in pixel par defaut
        window.setLocationRelativeTo(null);//set to the center of the desktop
        window.setVisible(true);//rend la fenetre visible

    }
}
