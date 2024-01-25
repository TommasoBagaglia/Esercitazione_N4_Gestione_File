
package gestionefile;
/**
 *
 * @author TB
 * @version 25/01/24
 */
import java.io.*;

class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Metodo per scrivere l'oggetto su uno stream di output
    public void writeToStream(DataOutputStream out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(password);
    }

    // Metodo per leggere l'oggetto da uno stream di input
    public static User readFromStream(DataInputStream in) throws IOException {
        String name = in.readUTF();
        String password = in.readUTF();
        return new User(name, password);
    }
}

