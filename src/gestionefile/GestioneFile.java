package gestionefile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
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
       
        //1)LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();
        //2)ELABORAZIONE
        Scanner scan = new Scanner(System.in);// creazione dell oggetto scan
        System.out.println("inserisci nome"); // rischesta in input del nome all'utente
        String username = scan.nextLine();// lettura del nome dal input
        
        System.out.println("inserisci password");// rischesta in input della password all'utente
        String password = scan.nextLine();// lettura del password dal input 
        
        
        String CPassword = CPassword(password);// cifratura della passaword con il cifrario di vigénere
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore =  new Thread(() -> scrittore.run(username, CPassword));
        threadScrittore.start();
        
        //4) COPIA DEL FILE 
        scrittore.copia(); 
        
    }
    //codice del cifrario di Vigénere
    private static String CPassword(String password){
        // Chiave del cifrario
        String key = "KEY"; // Chiave del cifrario
        
        StringCostruttore CPassword = new  StringCostruttore();
        // Scorrimento dei caratteri della password originale
        for (int i = 0, j = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Verifica se il carattere è una lettera
            if (Character.isLetter(c)) {
                // Applicazione del cifrario di Vigénere
                CPassword.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length(); // Incremento dell'indice della chiave, con ciclo se necessario
            } else {
                // Se il carattere non è una lettera, lo aggiungiamo direttamente senza cifrarlo
                CPassword.append(c);
            }
        }
        // Restituisce la nuova password cifrata come stringa
        return CPassword.toString();
    }
  }
    

