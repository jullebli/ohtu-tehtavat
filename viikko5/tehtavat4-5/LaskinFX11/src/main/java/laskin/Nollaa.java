package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenTulos = tuloskentta.getText();
        sovellus.nollaa();
        tuloskentta.setText(String.valueOf(sovellus.tulos()));
    }

    @Override
    public void peru() {
        sovellus.setTulos(Integer.valueOf(edellinenTulos));
        tuloskentta.setText(edellinenTulos);
    }
}
