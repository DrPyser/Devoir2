import javax.swing.*;
import java.util.Arrays;
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

//vérification des arguments	
        if (args.length < 5 ) {

            System.out.println("Nombre de paramètres incorrects.");
            System.out.println("Utilisation: java Memory <nRangees> <nColonnes>" +
                    " <delaisAffichageInitial(s)> <delaisAffichageMauvaisePaire(s)> <numeroDeTheme>.");
            System.out.println("Ex: java Memory 5 6 5 1 3");
            System.out.println("Choisir parmis la liste de themes disponible: \n" +
                    " 0: Cartes couleurs \n 1: Langage Informatique \n 2: Systeme Solaire \n" +
                    " 3: Notion Informatique \n 4: Image Lettres Grecs \n 5: " +
                    "6: Melange des themes 0 à 5 ");

            System.exit(42);

        }

        //parsing des arguments donnés par l'utilisateur
	int nbRangees = Integer.parseInt(args[0]);
	int nbColonnes = Integer.parseInt(args[1]);
        final int delaiInitial = (Integer.parseInt(args[2])*1000);//délai initial en nombre de millisecondes
        final int delaiErreur = (Integer.parseInt(args[3])*1000);//délai pour une erreur en nombre de millisecondes
        int themes = Integer.parseInt(args[4]);//numéro du thème

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
	generateGame(nbRangees,nbColonnes,delaiInitial,delaiErreur,themes);
    }
    public static void generateGame(int nbRangees,int nbColonnes,int delaiInitial,int delaiErreur,int theme){    
	Carte[] cartes = {};//contient toutes les cartes générées
	String nomTheme;//contient le theme des cartes en mot

	//sélection du thème
        switch(theme) {
	    
	      case 1:
		  //languages informatiques
		  nomTheme = "Languages Informatique";
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
		  break;

	      case 2:
		  //astres du système solaire
                    nomTheme = "Systeme Solaire";
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
		    break;

            case 3:
		//Concepts d'informatique
		nomTheme = "Notion Informatique";
		
		//voir thèmes précédent
                try {
		    Scanner file = new Scanner(new File("concepts.txt"));
		    String[] words = file.nextLine().split(",");
		    System.out.println("Il y a "+words.length+" cartes différentes dans ce thème");
		    GenerateurDeCartesMot genereNotions = new GenerateurDeCartesMot(nomTheme,words);
		    System.out.println(genereNotions.nombreDeCartesDifferentes());
		    cartes = genereNotions.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
		    
                } catch (FileNotFoundException exc) {

                    System.out.println("You don't have the themes files in the right place, or they are inexistant.");
                    System.exit(3);

                }
		
		break;

            case 4:
		//image de lettres grecques
		nomTheme = "ImageGrecs";

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
		
	    case 5:
		nomTheme = "bande-dessinées";

		//essaie de trouver le fichier. S'il existe, le sépare en une array de noms/chemins de fichiers images.
		//Sinon, signale l'erreur et exit
                try {
		    Scanner file = new Scanner(new File("ImageBD.txt")).useDelimiter("\\A");//considère tout le fichier comme un "token"
		    String[] paths = file.next().split("\n");//prend tout le texte du fichier, et sépare chaque lignes
		    file.close();
		    GenerateurDeCartesImage genereImage = new GenerateurDeCartesImage(nomTheme,paths);
		    cartes = genereImage.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));
                } catch (FileNotFoundException exc) {
                    System.out.println("You don't have the image theme files in the right place, or it is inexistant.");
                    System.exit(3);
                }

                break;

	    case 6:
		
		
            default:
		//par défaut, cartes couleur
		GenerateurDeCartesCouleur genereC = new GenerateurDeCartesCouleur();
                cartes = genereC.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
                nomTheme = "Couleurs";
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
	while(panel.getNbCartesShown() < cartes.length){
	}
	nouvellePartie(nbRangees,nbColonnes,delaiInitial,delaiErreur,theme);
    }

    public static void nouvellePartie(int nbrangés,int nbcolonnes,int delaiInitial,int delaiErreur,int theme){
	System.out.println("Voulez-vous faire une nouvelle partie? [oui/o|non/n]");
	Scanner scan = new Scanner(System.in);
	String answer = scan.next();
	while(answer != "oui" || answer != "o" || answer != "non" || answer != "n"){
	    System.out.println("Vous n'avez pas entré une réponse valide. Veuillez réessayer.");
	    answer = scan.next();
	}
	if (answer.equalsIgnoreCase("oui") || answer.equalsIgnoreCase("o")){
	    generateGame(nbrangés,nbcolonnes,delaiInitial,delaiErreur,theme);
	} else {
	    System.out.println("Au revoir, Dave.");
	    System.exit(3);
	}
	
    }

}
