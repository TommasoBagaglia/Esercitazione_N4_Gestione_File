package gestionefile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Tommaso Bagaglia
 * @version 25/01/24
 */
public class GestioneFile {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
         //1) LETTURA
        Lettore lettore = new Lettore("user.json");
        lettore.start();

        //2) ELABORAZIONE
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci nome:");
        String username = scan.nextLine();
        System.out.println("Inserisci password:");
        String password = scan.nextLine();
        String encryptedPassword = CPassword(password);

        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore =  new Thread(() -> scrittore.run(username, encryptedPassword)); // ho utilizzato la lamdba expression per non creare un altro metodo
        threadScrittore.start();

        //4) COPIA DEL FILE 
        scrittore.copia();

        //5) GESTIONE FILE CON SERIALIZZAZIONE
        // Utilizzo della classe DataInputStream per leggere oggetti User
        try (DataInputStream dis = new DataInputStream(new FileInputStream("user.dat"))) {
            User user = User.readFromStream(dis);
            System.out.println("User letto: " + user.getName() + ", " + user.getPassword());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Utilizzo della classe DataOutputStream per scrivere oggetti User
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("user.dat"))) {
            User newUser = new User(username, encryptedPassword);
            newUser.writeToStream(dos);
            System.out.println("User scritto: " + newUser.getName() + ", " + newUser.getPassword());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
    

