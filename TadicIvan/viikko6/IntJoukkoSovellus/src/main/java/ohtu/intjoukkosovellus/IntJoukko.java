
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,        // aloitustalukon koko
                            OLETUSKASVATUS = 5;      // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkuTaulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        initAlkuTaulukko(KAPASITEETTI);
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        initAlkuTaulukko(kapasiteetti);
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    private void initAlkuTaulukko(int kapasiteetti){
        if (kapasiteetti >= 0) {
            alkuTaulukko = new int[kapasiteetti];
            for (int i = 0; i < alkuTaulukko.length; i++) {
                alkuTaulukko[i] = 0;
            }
            alkioidenLkm = 0;
        }
     }
    private void checkKapasiteetti(int kapasiteetti){
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        } 
    }
    
    private void checkKasvatuskoko(int kasvatuskoko){
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        checkKapasiteetti(kapasiteetti);
        checkKasvatuskoko(kasvatuskoko);
        initAlkuTaulukko(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;
    }

    private void lisaaLuku(int luku){
            alkuTaulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
    } 
    
    private void lisaaAlkuTaulukkoTarvitaessa(){
        if (alkioidenLkm % alkuTaulukko.length == 0) {
            int[] taulukkoOld = new int[alkioidenLkm];
            kopioiTaulukko(alkuTaulukko, taulukkoOld);
            alkuTaulukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(taulukkoOld, alkuTaulukko);
        }
    }
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lisaaLuku(luku);
            lisaaAlkuTaulukkoTarvitaessa();
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkuTaulukko[i]) {
                return true;
            }
        }
        return false;
    }

    private int indexOf(int luku){
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkuTaulukko[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private void poistaSe(int kohta){
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
                alkuTaulukko[j] = alkuTaulukko[j + 1];
            }
        alkioidenLkm--;
    }
    
    private boolean poistaAt(int kohta){
        if (kohta == -1) {
            return false;
        } 
        poistaSe(kohta);
        return true;
    }
    
    public boolean poista(int luku) {
        int kohta = indexOf(luku);
        return poistaAt(kohta);
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } 
        return defaultTulos();
        
        
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(alkuTaulukko, 0, taulu, 0, taulu.length);
        return taulu;
    }
   

    private static IntJoukko lisaaToJoukko(IntJoukko x, int[] taulukko){
        for (int i = 0; i < taulukko.length; i++) {
            x.lisaa(taulukko[i]);
        }
        return x;
    }
    
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        x = lisaaToJoukko(x, a.toIntArray());
        return lisaaToJoukko(x, b.toIntArray());
    }

 
    private static IntJoukko copyOf(IntJoukko x){
        IntJoukko z = new IntJoukko();
        for (int i=0; i<x.alkioidenLkm; i++){
            z.lisaa(x.alkuTaulukko[i]);
        }
        return z;
    }
    private static IntJoukko lisaaJosEsinttyy(IntJoukko to, IntJoukko in, int luku){
        IntJoukko z = copyOf(to);
        if (in.kuuluu(luku)){
            z.lisaa(luku);
        }
        return z;
    }
    
    public static IntJoukko leikkaus(IntJoukko x1, IntJoukko x2){
        IntJoukko z = new IntJoukko();
        for (int i=0; i<x1.alkioidenLkm; i++){
            z = lisaaJosEsinttyy(z, x2, x1.alkuTaulukko[i]);
        }
        return z;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = copyOf(a);
        IntJoukko w = leikkaus(a, b);
        for (int i=0; i<w.alkioidenLkm; i++){
            z.poista(w.alkuTaulukko[i]);
        }
        return z;
    }

    private String defaultTulos() {
        StringBuilder tuotos = new StringBuilder("{");
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos.append(alkuTaulukko[i]).append(", ");
        }
        tuotos.append(alkuTaulukko[alkioidenLkm - 1]).append("}");
        return tuotos.toString();
    }
        
}