package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetin täytyy olla positiivinen luku");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon täytyy olla positiivinen luku");
        }
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                ljono = kopioiTaulukko(ljono, new int[alkioidenLkm + kasvatuskoko]);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < mahtavuus(); i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                break;
            }
        }
        int[] uusiLjono = new int[ljono.length - 1];
        
        System.arraycopy(ljono, 0, uusiLjono, 0, kohta);
        System.arraycopy(ljono, kohta + 1, uusiLjono, kohta, ljono.length - kohta - 1);
    
        ljono = uusiLjono;
        alkioidenLkm--;
        return true;
    }

    private int[] kopioiTaulukko(int[] kopioitava, int[] uusi) {
        for (int i = 0; i < kopioitava.length; i++) {
            uusi[i] = kopioitava[i];
        }
        return uusi;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < mahtavuus(); i++) {
            sb.append(ljono[i]);
            if (i != mahtavuus() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[mahtavuus()];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        for (int i = 0; i < b.mahtavuus(); i++) {
            if (!a.kuuluu(b.ljono[i])) {
                a.lisaa(b.ljono[i]);
            }
        }
        return a;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < b.mahtavuus(); i++) {
            if (a.kuuluu(b.ljono[i])) {
                y.lisaa(b.ljono[i]);
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        for (int i = 0; i < b.mahtavuus(); i++) {
            a.poista(b.ljono[i]);
        }
        return a;
    }
}
