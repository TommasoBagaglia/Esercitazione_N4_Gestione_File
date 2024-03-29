package gestionefile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Tommaso Bagaglia
 * @ 25/01/24
 */

public class Lettore extends Thread{
    private String nomeFile;
    
    public Lettore(String nomeFile){
        this.nomeFile = nomeFile;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public void leggi(){
        FileReader fr;
        int i; 
        try { 
            //1) apro il file
            fr = new FileReader(nomeFile);
            //2) leggo carattere per carattere e lo stampo 
            while ((i=fr.read()) != -1)
                System.out.print((char) i);
            
            System.out.print("\n\r");
            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
    }
    

    public void run(){
        leggi();
    }
}
