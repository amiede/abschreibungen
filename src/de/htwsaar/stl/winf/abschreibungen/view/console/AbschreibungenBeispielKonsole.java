/**
 * 
 */
package de.htwsaar.stl.winf.abschreibungen.view.console;

import de.htwsaar.stl.winf.abschreibungen.core.*;

import java.util.Scanner;

/**
 * @author miede
 *
 */
public class AbschreibungenBeispielKonsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); ; 
		double anschaffungswert;
		int nutzungsdauer;
		Vermoegensgegenstand.Abschreibungsverfahren abschreibungsverfahren;
		Vermoegensgegenstand vermoegensgegenstand;
		
		while(true) {
			
		    System.out.print("Geben Sie den Anschaffungswert ein (Euro, double): ");
		    if (scanner.hasNextDouble()) {
		    	anschaffungswert = scanner.nextDouble();
		    } else {
		    	scanner.nextLine();
		    	continue;
		    }
		    
		    System.out.print("Geben Sie die Nutzungsdauer ein (Jahre, int): ");
		    if (scanner.hasNextInt()) {
		    	nutzungsdauer = scanner.nextInt();
		    } else {
		    	scanner.nextLine();
		    	continue;
		    }
		    
		    System.out.print("Wählen Sie das Abschreibungsverfahren aus: \n " + 
		    		Vermoegensgegenstand.Abschreibungsverfahren.LINEAR.ordinal() + " für linear \n " +
		    		Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV.ordinal() + " für geometrisch-degressiv \n " +
		    		Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR.ordinal() + " für geometrisch-degressiv --> linear ");
		    if (scanner.hasNextInt()) {
		    	switch(scanner.nextInt()) {
		    		case 0:
		    			abschreibungsverfahren = Vermoegensgegenstand.Abschreibungsverfahren.LINEAR;
		    			break;
		    		case 1:
		    			abschreibungsverfahren = Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV;
		    			break;
		    		case 2:
		    			abschreibungsverfahren = Vermoegensgegenstand.Abschreibungsverfahren.GEOMETRISCH_DEGRESSIV_LINEAR;
		    			break;
		    		default:
		    			abschreibungsverfahren = Vermoegensgegenstand.Abschreibungsverfahren.LINEAR;
		    	}
		    } else {
		    	scanner.nextLine();
		    	continue;
		    }
			
		    //		    
		    vermoegensgegenstand = new VermoegensgegenstandImpl(anschaffungswert, nutzungsdauer);
			vermoegensgegenstand.setAbschreibungsverfahren(abschreibungsverfahren);
			vermoegensgegenstand.abschreiben();
			System.out.println(vermoegensgegenstand.toString());
			System.out.println("\n");
		}
		
	}
}
