import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;//import le scanner
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
            System.out.println("Voici la liste des themes disponibles: \n " +
                                    "0: Cartes couleurs \n 1: Lettres A ... Z \n 2: Noms d'émotions" +
                                    "3: Images d'animaux \n 4: Images de galaxies \n 5: Melange des themes 0 à 4) ");

            System.exit(42);

        }

        //prend toutes les cinq valeurs donne par l'utilisateur
	int nbRangees = Integer.parseInt(args[0]);
	int nbColonnes = Integer.parseInt(args[1]);
        final int delaisInitial = (Integer.parseInt(args[2]));//délai initial en nombre de millisecondes
        final int delaisErreur = (Integer.parseInt(args[3]));//délai pour une erreur en nombre de millisecondes
        int themes = Integer.parseInt(args[4]);//numéro du thème
        Carte cartes[];//contient toutes les cartes générées

        //demande le thème tant qu'il n'est pas valide
	Scanner scan = new Scanner(System.in);
        while (themes > 5 || themes < 0) {

            System.out.println("Theme non valide. Choisir parmis la liste de themes disponible: \n" +
			       "0: Cartes couleurs \n 1: Langage Informatique \n 2: Systeme Solaire" +
			       "3: Notion Informatique  \n 4:  \n 6:Image Informatique \n 7: Image Lettres Grecs \n " +
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

        String theme = "";//contient le theme des cartes en mot
        boolean changement = true;//dit si on peut changer le theme

        switch(themes) {
            case 0:
                GenerateurDeCartesCouleur genereC = new GenerateurDeCartesCouleur();
                cartes = genereC.generePairesDeCartesMelangees((int) Math.floor(nbColonnes * nbRangees/2));//cree les cartes
                theme = "Couleurs";
                changement = false;
                break;
            case 1:
                theme = "Langue Informatique";
                changement = false;
            case 2:
                if (changement){
                    theme = "Systeme Solaire";
                    changement = false;
                }
            case 3:
                if (changement){
                    theme = "Notion Informatique";
                    changement = false;
                }
                //TODO find how to make the text file path for everything
                String textPath = "C:\\Users\\Alexandra\\workspace\\Memory\\src\\themesMots.txt";//lien vers les themes
                BufferedReader flux = null;
                String ligne;
                String elementsDeLigne[] = new String[17];

                //try to find the file if not close and tell it havent found it
                try {

                    flux = new BufferedReader(new FileReader(textPath ));

                }catch (FileNotFoundException exc) {

                    System.out.println("You don't have the themes files in the right place, or it is inexistant");
                    System.exit(3);

                }

                int rendu = 1;//nous prend le bonne elements dans les lignes
                //takes all the text and remembers it
                while ((ligne = flux.readLine()) != null) {

                    if (themes == rendu) {//prend la ligne dans elementsDeLigne lorsque c'est le bon element

                        elementsDeLigne = ligne.split(",");//split at , to put in the tableau

                    }
                    rendu++;
                }

                flux.close();//fermer le flux a la fin

                //genere les cartes
                GenerateurDeCartesMot genereM = new GenerateurDeCartesMot(theme,elementsDeLigne);
                cartes = genereM.generePairesDeCartesMelangees(nbColonnes * nbRangees);//cree les cartes
                break;
            case 5:
                theme = "ImageBD";
                changement = false;
            case 6:
                if (changement){
                    theme = "ImageGrecs";
                    changement = false;
                }

                //TODO find how to make the text file path for everything
                String imagePath = "C:\\Users\\Alexandra\\workspace\\Memory\\src\\"+theme+" .txt";//lien vers les themes
                BufferedReader entree = null;
                String rangee;
                String imageLien[] = new String[15];

                //try to find the file if not close and tell it havent found it
                try {

                    entree = new BufferedReader(new FileReader(imagePath));

                }catch (FileNotFoundException exc) {

                    System.out.println("You don't have the image theme files in the right place, or it is inexistant.");
                    System.exit(3);

                }

                int i = 0;
                //takes all the text and remembers it
                while ((rangee = entree.readLine()) != null) {

                    //prend les 15 premier automatiquement puis apres le prend aleatoirement (0.4%) et le place aleatorement
                    if (i >= imageLien.length){
                        if(Math.random() < 0.4 ){
                            imageLien[(int)(Math.floor(Math.random()*15 +1))] = rangee;
                        }
                    }else {
                        imageLien[i] = rangee;
                        i++;
                    }

                }

                entree.close();//fermer le flux a la fin

                //genere les cartes
                GenerateurDeCartesMot genereI = new GenerateurDeCartesMot(theme,imageLien);
                cartes = genereI.generePairesDeCartesMelangees(nbColonnes * nbRangees);//cree les cartes
                break;

            default:
                GenerateurDeCartesCouleur genereA = new GenerateurDeCartesCouleur();
                cartes = genereA.generePairesDeCartesMelangees(nbColonnes * nbRangees);//cree les cartes
                break;
        }


	//création de la fenêtre                                               
        JFrame window = new JFrame("Memory Game");//title of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what it does when we push x

        window.setSize(850, 500);//size in pixel par defaut

        window.setLocationRelativeTo(null);//set to the center of the desktop
	
        window.setVisible(true);//rend la fenetre visible

	//attend un temps défini, puis retourne toutes les cartes sur leur face cachée
	/*
	for (int i = 0; i<cartes.length;i++){
	    panel.cartes[i].addMouseListener(panel.click);
            panel.cartes[i].montre();
            panel.cartes[i].setVisible(true);
        }
	*/
	//création du panneau de carte
        PanneauDeCartes panel = new PanneauDeCartes(nbRangees,nbColonnes,cartes,delaisInitial,delaisErreur);
	window.getContentPane().add(panel, BorderLayout.CENTER);//set le panel au centre

	//le constructeur du panneau initialise les cartes au coté recto, et ensuite les retourne après un délai

	//à partir de ce point, les intéractions par click de souris et le fonctionnement du Panneau déterminent le reste de la partie

    }

}
