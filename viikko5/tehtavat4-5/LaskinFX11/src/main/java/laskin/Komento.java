
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected Sovelluslogiikka sovellus;   
    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    String edellinenTulos;
    
    public Komento(TextField tuloskentta, TextField syotekentta,  Button nollaa, 
                 Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.edellinenTulos = tuloskentta.getText();
    }
    
    public abstract void suorita();

    public abstract void peru();
}
