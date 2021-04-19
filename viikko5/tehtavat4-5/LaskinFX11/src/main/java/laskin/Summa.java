package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa,
            Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        if (syotekentta.getText().equals("")) {
            return;
        } else {
            edellinenTulos = tuloskentta.getText();
            sovellus.plus(Integer.valueOf(syotekentta.getText()));
            tuloskentta.setText(String.valueOf(sovellus.tulos()));
            syotekentta.setText("");
        }
    }

    @Override
    public void peru() {
        sovellus.setTulos(Integer.valueOf(edellinenTulos));
        tuloskentta.setText(edellinenTulos);
    }
}
