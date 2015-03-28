import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


/**
 * Created by Alexandra on 19-03-15.
 */
public class Memory {

    public static void main (String[] args) throws IOException {
        //il est necessaire d'avoir cinq arguments

        if (args.length < 5 ) {

            System.out.println("Nombre de paramètres incorrects.");
            System.out.println("Utilisation: java Memory <nRangees> <nColonnes>" +
                    " <delaisAffichageInitial(ms)> <delaisAffichageMauvaisePaire(ms)> <numeroDeTheme>.");
            System.out.println("Ex: java Memory 5 6 5000 1000 3");
            System.out.println("Choisir parmis la liste de themes disponible: \n" +
                    " 0: Cartes couleurs \n 1: Langage Informatique \n 2: Systeme Solaire \n" +
                    " 3: Notion Informatique  \n \n 6: Image Lettres Grecs \n " +
                    "8: Melange des themes 0 à 7) ");

            System.exit(42);

        }

        //prend toutes les cinq valeurs donne par l'utilisateur
	int nbRangees = Integer.parseInt(args[0]);
	int nbColonnes = Integer.parseInt(args[1]);
        final int delaiInitial = (Integer.parseInt(args[2]));//délai initial en nombre de millisecondes
        final int delaiErreur = (Integer.parseInt(args[3]));//délai pour une erreur en nombre de millisecondes
        int themes = Integer.parseInt(args[4]);//numéro du thème
        Carte[] cartes = {};//contient toutes les cartes générées

        //demande le thème tant qu'il n'est pas valide
	Scanner scan = new Scanner(System.in);
        while (themes > 8 || themes < 0) {

            System.out.println("Theme non valide. Choisir parmis la liste de themes disponible: " +
                    " 0: Cartes couleurs \n 1: Langage Informatique \n 2: Systeme Solaire \n" +
                            " 3: Notion Informatique  \n 4:  \n 6: Image Lettres Grecs \n  " +
                            "8: Melange des themes 0 à 7) ");


            themes = scan.nextInt();

        }
        //on ne peut pas avoir plus de 30 cartes en jeu
        while (nbColonnes*nbRangees > 30){

            System.out.println("Vous ne pouvez pas avoir plus de 30 cartes en jeu. (colonne * range < 30) ");
            System.out.println("Nombre de colonnes :");
            //Scanner scan = new Scanner(System.in);
            nbColonnes = scan.nextInt();
            System.out.println("Nombre de range :");
            //Scanner scan = new Scanner(System.in);
            nbRangees = scan.nextInt();

        }
	scan.close();

        String nomTheme;//contient le theme des cartes en mot
        boolean changement = true;//dit si on peut changer le theme
	
	//sélection du thème
        switch(themes) {
	    
	      case 1:
		  //languages informatiques
		  nomTheme = "Languages Informatique";
		  changement = false;
		  //essai de lire le fichier contenant les languages
		  try {
		      //idiome répété pour les autres thèmes:
		      //on utilise un scanner pour lire le fichier au complet, puis on split le string résultant aux delimiteurs appropriés
		      Scanner file = new Scanner(new File("languages.txt"));
		      String[] words = file.nextLine().split(",");//ici, on sépare les mots aux virgules
		      GenerateurDeCartesMot genereNotions = new GenerateurDeCartesMot(nomTheme,words);
		      cartes = genereNotions.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
		      
		  } catch (FileNotFoundException exc) {
		      
		      System.out.println("You don't have the themes files in the right place, or it is inexistant");
		      System.exit(3);
		  
		  }

	      case 2:
		  //astres du système solaire
                    nomTheme = "Systeme Solaire";
		    //changement = false;
		    //voir thème précédent
		    try {
			Scanner file = new Scanner(new File("systèmesolaire.txt"));
			String[] words = file.nextLine().split(",");
			GenerateurDeCartesMot genereNotions = new GenerateurDeCartesMot(nomTheme,words);
			cartes = genereNotions.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
		    
		    } catch (FileNotFoundException exc) {
			
			System.out.println("You don't have the themes files in the right place, or it is inexistant");
			System.exit(3);
		    
		    }


            case 3:
		//Concepts d'informatique
		nomTheme = "Notion Informatique";
		//changement = false;
		
		//voir thèmes précédent
                try {
		    Scanner file = new Scanner(new File("concepts.txt"));
		    String[] words = file.nextLine().split(",");
		    GenerateurDeCartesMot genereNotions = new GenerateurDeCartesMot(nomTheme,words);
		    cartes = genereNotions.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes

                } catch (FileNotFoundException exc) {

                    System.out.println("You don't have the themes files in the right place, or it is inexistant");
                    System.exit(3);

                }
		
		break;

		/*
		  case 5:
                nomTheme = "ImageBD";
                changement = false;
		*/

            case 6:
		//image de lettres grecques
		nomTheme = "ImageGrecs";
		changement = false;

		//essaie de trouver le fichier. S'il existe, le sépare en une array de noms/chemins de fichiers images.
		//Sinon, signale l'erreur et exit
                try {
		    Scanner file = new Scanner(new File("cheminsimagesgrec.txt")).useDelimiter("\\A");//considère tout le fichier comme un "token"
		    String[] paths = file.next().split("\n");//prend tout le texte du fichier, et sépare chaque lignes
		    file.close();
		    GenerateurDeCartesImage genereImage = new GenerateurDeCartesImage(nomTheme,paths);
		    cartes = genereImage.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));
                }catch (FileNotFoundException exc) {

                    System.out.println("You don't have the image theme files in the right place, or it is inexistant.");
                    System.exit(3);

                }
                break;
		
            default:
		//par défaut, cartes couleur
		GenerateurDeCartesCouleur genereC = new GenerateurDeCartesCouleur();
                cartes = genereC.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
                nomTheme = "Couleurs";
                changement = false;
                break;

        }


	//création de la fenêtre                                               
        JFrame window = new JFrame("Memory Game");//title of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what it does when we push x

        window.setSize(850, 500);//size in pixel par defaut

        window.setLocationRelativeTo(null);//set to the center of the desktop
	
        window.setVisible(true);//rend la fenetre visible

	//création du panneau de carte
        PanneauDeCartes panel = new PanneauDeCartes(nbRangees,nbColonnes,cartes,delaiInitial,delaiErreur);
	window.getContentPane().add(panel, BorderLayout.CENTER);//set le panel au centre

	//le constructeur du panneau initialise les cartes au coté recto, et ensuite les retourne après un délai

	//à partir de ce point, les intéractions par click de souris et le fonctionnement du Panneau déterminent le reste de la partie

    }

}
