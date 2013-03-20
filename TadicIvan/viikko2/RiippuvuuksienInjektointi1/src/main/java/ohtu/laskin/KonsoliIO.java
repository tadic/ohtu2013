
package ohtu.laskin;

import java.util.Scanner;

public class KonsoliIO implements IO {
    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }    
    

    @Override
    public int nextInt() {
        return lukija.nextInt();
    }

    @Override
    public void print(String m) {
        System.out.print(m);
    }
    
}
