
package Logic;

import datapackage.DataMapper;


class Quit extends Komento {

    public Quit(DataMapper mapper) {
    }

    @Override
    public void suorita() {
        System.out.println("Bye bue...");
        System.exit(0);
    }
    
    
}
