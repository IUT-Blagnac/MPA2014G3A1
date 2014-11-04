import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Vue.OPTI;

public class MakeOPTIweb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			java.io.File fichier = new java.io.File("OPTIweb.html");

			fichier.createNewFile(); // Cette fonction doit être appelée au sein
										// d'un bloc TRY
		} catch (IOException e) {
			System.out.println("Impossible de créer le fichier");
		}
		
		String text = PageAccueil(donnesAccueil(),donnesCredits(),donnesProjets(),donnesSujets(),donnesEtudiants(),donnesIntervenant()) ;
		ecrire("OPTIweb.html", text);
		

	}

	public static String PageAccueil(String donnees, String donnees2, String donnees3, String donnees4, String donnees5, String donnees6) {
		String pageAccueil = "<!DOCTYPE html> \n <html> \n <head> \n <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n <meta name=\"generator\" content=\"OPTIweb VOPTIweb\" /> \n <title>OPTIweb - V0.1</title> \n <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" />   \n <link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" />   \n <script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script> \n <script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script> \n <style type='text/css'> \n @media all and (orientation:portrait) { .landscape {display: none;} } \n @media all and (orientation:landscape) { .landscape {display: inline;} } \n </style> \n </head><body>";
		pageAccueil = pageAccueil.concat(donnees) ;
		pageAccueil = pageAccueil.concat(donnees2) ;
		pageAccueil = pageAccueil.concat(donnees3) ;
		pageAccueil = pageAccueil.concat(donnees4) ;
		pageAccueil = pageAccueil.concat(donnees5) ;
		pageAccueil = pageAccueil.concat(donnees6) ;
		pageAccueil = pageAccueil.concat("\n <script> \n $( 'li[data-find]' ).on( 'click',function(event){ \n $(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change'); \n  }); \n </script> \n </body> \n </html> \n ") ;
		
		return pageAccueil;
		
	}
	
	public static String donnesAccueil() {
		String donnes = "<div data-role=\"page\" id=\"accueil\" data-title = \"OPTIweb - V0.1\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">orés</span> 2014-2015<br/>Département INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1> \n <a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Crédits</a> \n </div> \n <div data-role=\"content\"> \n <ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\"> \n  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li> \n  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li> \n <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li> \n <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li> \n </ul> \n </div> \n <div data-role=\"footer\">  <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4> \n </div> \n </div> " ;
		return donnes;
		
	}
	
	public static String donnesCredits() {
		String credits = "<div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Crédits\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>Crédits</h1> \n </div> \n <div data-role=\"content\"> \n <p>Cette application a été réalisée dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p> \n <ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\"> \n <li data-role=\"list-divider\">Product Owner</li> \n <li>André PÉNINOU</li> \n <li>Université Toulouse 2 - IUT de Blagnac \n     <br/>Département INFORMATIQUE</li> \n </ul> \n <ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\"> \n <li data-role=\"list-divider\">Membres de l'équipe enseignante</li> \n <li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>André PÉNINOU</li><li>Olivier ROQUES</li> \n </ul> \n <ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\"> \n <li data-role=\"list-divider\">Propulsé par</li> \n <li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li> \n <li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li> \n </ul> \n </div> \n <div data-role=\"footer\"> \n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4> \n </div> \n </div>";
		return credits ;
	}
	
	public static String donnesIntervenant() {
		ArrayList<String[]> listeInter = lire("./OPTIweb/test/intervenants2014_2015.csv") ;
		ArrayList<String[]> listeProjets = lire("./OPTIweb/test/projets2014_2015.csv") ;
		String intervenant = "" ;
		int client = 0 ;
		int superviseur = 0 ;
		for (int i=0;i<listeInter.size();i++) {
			client = 0 ;
			superviseur = 0 ;
			for (int j=0;j<listeProjets.size();j++) {
				if(listeInter.get(i)[0].equals(listeProjets.get(j)[3])) {
					
						client = client + 1 ; }
					
				if (listeInter.get(i)[0].equals(listeProjets.get(j)[4])) {
						superviseur = superviseur + 1 ;
					}
				
			}
			intervenant = intervenant + "<li data-find=\"" + listeInter.get(i)[2] + " " + listeInter.get(i)[1] + "\"> \n <a href=\"#projets\"> \n" + listeInter.get(i)[2] + " " + listeInter.get(i)[1] + "\n <span class=\"ui-li-count\" style=\"right: 120px !important;\" title=\"Client\">" + client + "</span> \n <span class=\"ui-li-count\" title=\"Superviseur\">" + superviseur + "</span> \n  </a> \n </li>" ;
		}
		
		String intervenants = "<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>Intervenants 2014-2015</h1> \n </div> \n <div data-role=\"content\"> \n <div data-role=\"content\"> \n <input id=\"autocomplete-input-intervenant\" name=\"intervenant\" data-type=\"search\" placeholder=\"Intervenant\">  \n </form> \n <ul id=\"listeintervenants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-intervenant\" data-divider-theme=\"b\"> \n <li data-role=\"list-divider\"> \n Intervenant<span class=\"ui-li-count\" style=\"right: 110px !important;\" title=\"Client\">Client</span><span class=\"ui-li-count\" title=\"Superviseur\">Superviseur</span> \n </li>" + intervenant +  "\n <!-- 5 --> \n </ul> \n </div> \n <div data-role=\"footer\"> \n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4> \n </div> \n </div> ";
		return intervenants;
	}
	
	public static String donnesProjets() {
		ArrayList<String[]> listeProjets = lire("./OPTIweb/test/projets2014_2015.csv") ;
		ArrayList<String[]> listeEtus = lire("./OPTIweb/test/etudiants2014_2015.csv") ;
		ArrayList<String[]> listeSujets = lire("./OPTIweb/test/sujets2014_2015.csv") ;
		ArrayList<String[]> listeInter = lire("./OPTIweb/test/intervenants2014_2015.csv");
		String projet = "" ;
		String sujet = "" ;
		String sujetTitre = "" ;
		String etudiants = "" ;
		String client = "" ;
		String superviseur = "" ;
		String idSujet = "";
		for (int i=0;i<listeProjets.size();i++) {
			etudiants = "";
			idSujet = listeProjets.get(i)[2] ;
			for (int j=0;j<listeSujets.size();j++) {
				if(idSujet.equals(listeSujets.get(j)[0])) {
					sujetTitre = listeSujets.get(j)[1] ;
					sujet = listeSujets.get(j)[2] ;
				}
			}
			for (int p=0;p<listeEtus.size();p++) {
				
				if (listeProjets.get(i)[1].equals(listeEtus.get(p)[0])) {
					etudiants = etudiants + listeEtus.get(p)[2] + " " + listeEtus.get(p)[3] + " - ";
				}
			}
			for (int d = 0;d<listeInter.size();d++) {
				if (listeProjets.get(i)[4].equals(listeInter.get(d)[0])) {
					superviseur = listeInter.get(d)[2] + " " + listeInter.get(d)[1] ; }
				
				if (listeProjets.get(i)[3].equals(listeInter.get(d)[0])) {
						client = listeInter.get(d)[2] + " " + listeInter.get(d)[1] ;
					}
				
			}
			
			projet = projet +  "<li> \n <p> \n <b>["+ sujetTitre  + "] </b>" + sujet + "\n </p> \n <p> \n <b>Client : </b>" + client + "\n </p> \n <p> \n <b>Superviseur : </b>" + superviseur + "\n </p> \n <p> \n <b>Groupe " + listeProjets.get(i)[1] + " : </b>" + etudiants + "\n </p> \n </li>" ;
		}
		
		String projets = "<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>Projets 2014-2015</h1> \n </div> \n <div data-role=\"content\"> \n <form class=\"ui-filterable\"> \n <input id=\"autocomplete-input-projet\" name=\"projet\" data-type=\"search\" placeholder=\"Vous cherchez ?...\"> \n </form> \n <ol id=\"listeprojets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-projet\"> \n " + projet +  "\n </ol> \n </div> \n <div data-role=\"footer\"> \n <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4> \n </div> \n </div> " ;
		
		return projets ;
	}
	
	public static String donnesSujets() {
		ArrayList<String[]> listeSujets = lire("./OPTIweb/test/sujets2014_2015.csv") ;
		ArrayList<String[]> listeProjets = lire("./OPTIweb/test/projets2014_2015.csv") ;
		String sujet = "" ;
		String groupe = "" ;
		for (int i = 0;i<listeSujets.size();i++) {
			for (int j = 0;j<listeProjets.size();j++) {
			if (listeSujets.get(i)[0].equals(listeProjets.get(j)[2])) {
				groupe = listeProjets.get(j)[1] ;
			}
			}
			sujet = sujet + "<li data-find=\"[" + listeSujets.get(i)[1] + "]\"> \n <a href=\"#projets\">" + listeSujets.get(i)[1] + "<br/> \n <div style=\"white-space:normal;\"> \n <span><b>" + listeSujets.get(i)[2] + "</b> \n </span><span class=\"ui-li-count\">" + groupe + "</span> \n </div> \n </a> \n </li> " ;
			
		}
		
		
		
		String sujets = "<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>Sujets 2014-2015</h1> \n </div> \n <div data-role=\"content\"> \n <form class=\"ui-filterable\"> \n <input id=\"autocomplete-input-sujet\" name=\"sujet\" data-type=\"search\" placeholder=\"Vous cherchez ?\"> \n </form> \n <ol id=\"listesujets\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-sujet\" data-divider-theme=\"b\" data-count-theme=\"a\"> \n <li data-role=\"list-divider\"> \n Sujet<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span> \n </li> \n" + sujet + "\n </ol> \n </div> <div data-role=\"footer\"> \n  <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4> \n </div> \n </div> " ;		
		return sujets ;
	}
	
	public static String donnesEtudiants() {
		ArrayList<String[]> listeEtus = lire("./OPTIweb/test/etudiants2014_2015.csv") ;
		String etudiant = "" ;
		for (int i = 0;i<listeEtus.size();i++) {
			etudiant = etudiant + "<li data-find=\""+ listeEtus.get(i)[2] + " " + listeEtus.get(i)[3] + "\"> \n <a href=\"#projets\">" + listeEtus.get(i)[2] + " " + listeEtus.get(i)[3] +"<span class=\"ui-li-count\" title=\"Groupe\">" + listeEtus.get(i)[0] + "</span> \n </a> \n </li>" ;
		}
		
		String etudiants = "<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\"> \n <div data-role=\"header\" data-add-back-btn=\"true\"> \n <h1>Etudiants 2014-2015</h1> \n </div> \n <div data-role=\"content\"> \n <form class=\"ui-filterable\"> \n <input id=\"autocomplete-input-etudiant\" name=\"etudiant\" data-type=\"search\" placeholder=\"Etudiant ou Groupe X\"> \n </form> \n <ol id=\"listeetudiants\" data-role=\"listview\" data-inset=\"true\" data-filter=\"true\" data-filter-reveal=\"false\" data-input=\"#autocomplete-input-etudiant\" data-divider-theme=\"b\"> \n <li data-role=\"list-divider\"> \n Etudiant<span class=\"ui-li-count\" title=\"Groupe\" style=\"right: 40px !important;\">Groupe</span> \n </li> \n" + etudiant + "</ol> \n </div> \n <div data-role=\"footer\"> \n  <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4> \n </div> \n </div> ";
		return etudiants;
	}

	public static void ecrire(String nomFic, String codeHTML) {
		// on va chercher le chemin et le nom du fichier et on me tout ca dans
		// un String
		String adressedufichier = System.getProperty("user.dir") + "/" + nomFic;

		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 */
			FileWriter fw = new FileWriter(adressedufichier, true);

			// le BufferedWriter output auquel on donne comme argument le
			// FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);

			// on marque dans le fichier ou plutot dans le BufferedWriter qui
			// sert comme un tampon(stream)
			output.write(codeHTML);
			// on peut utiliser plusieurs fois methode write

			output.flush();
			// ensuite flush envoie dans le fichier, ne pas oublier cette
			// methode pour le BufferedWriter

			output.close();

		} catch (IOException ioe) {
			System.out.print("Erreur : ");
			ioe.printStackTrace();
		}

	}
	
	public static ArrayList<String[]> lire(String path){
		ArrayList<String[]> array = new ArrayList<String[]>() ;
		BufferedReader ficTexte;
		String ligne;
		File file = new File(path) ;
		
		try {
			array = new ArrayList<String[]>();
			ficTexte = new BufferedReader(new FileReader(file));
			int i = 0;
			while ((ligne = ficTexte.readLine()) != null) {
				if (i > 0) {
					array.add(ligne.split(";"));
				}
				i++;
			}
			ficTexte.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return array ;
	}

}
