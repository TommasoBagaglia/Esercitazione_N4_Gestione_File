package gestionefile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author TB
 * @version 16/01/24
 */
public class GestioneFile {
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        private String nome;
        private String parolachiave;
        System.out.println("inserire la password");
        System.out.println("inserire il nome");
        System.out.println("--------------------");
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
    }
    
}
