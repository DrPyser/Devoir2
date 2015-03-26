/**
 * Created by Alexandra on 26-03-15.
 */
import java.util.Scanner;

public class NouvellePartie {

    //fait les nouvelle partie
    public boolean nouvellePartie (int nbCartes){

        //efface le tableau pour une nouvelle partie

        System.out.println("Voulez-vous jouer une nouvelle partie? oui/non ");

        boolean reponse = false;//garde dans la boucle while
        boolean rep = false;//donne le return qui est la reponse de l'utilisateur


        //tand qu'il n'y a pas de reponse valide il demande une reponse
        while(!reponse) {


            Scanner scan = new Scanner(System.in);//prend la reponse de l'utilisateur
            String answer = scan.next();// qui sera le prochain mot (soit oui ou non

            if (answer.equalsIgnoreCase("oui")) {

                //autre truc pour restart

                rep = true;
                reponse = true;

            } else if (answer.equalsIgnoreCase("non")) {

                rep = false;
                System.out.print("Vous avez quitte le jeu.");
                System.exit(2);

            } else {

                System.out.println("Vous avez entre une reponse non valide. Reponse : oui / non");

            }
        }

        return rep;

    }


}
