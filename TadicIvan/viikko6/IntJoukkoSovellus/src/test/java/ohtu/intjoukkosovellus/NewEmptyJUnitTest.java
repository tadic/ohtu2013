/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.intjoukkosovellus;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ivantadic
 */
public class NewEmptyJUnitTest {
    IntJoukko joukko;
    
    @Before
    public void setUp() {
        joukko = new IntJoukko(2,0);
        joukko.lisaa(10);
    }
    @Test
    public void toStringToimii(){
        assertEquals("{10}", joukko.toString());
    }
    @Test
    public void toStringToimii2(){
        joukko.poista(10);
        assertEquals("{}", joukko.toString());
    }
    @Test
    public void poistaaNOTexsisted(){
        assertFalse(joukko.poista(3));
    }
    @Test
    public void leikausJaErotus(){
        joukko.lisaa(3);
        IntJoukko joukko2 = new IntJoukko(3,2);
        joukko2.lisaa(3);
        joukko2.lisaa(2);
        assertEquals("{3}", IntJoukko.leikkaus(joukko, joukko2).toString());
        assertEquals("{10}", IntJoukko.erotus(joukko, joukko2).toString());
    }
    @Test
    public void testCOnstructorForExceptions(){
        try{
             IntJoukko x1 = new IntJoukko(-2, 5);
             assertFalse(true);
        } catch (Exception ex){
            assertEquals("Kapasitteetti väärin", ex.getMessage());
        }
        try{
             IntJoukko x2 = new IntJoukko(2,-5);
             assertFalse(true);
        } catch (Exception ex){
            assertEquals("kapasiteetti2", ex.getMessage());
        }
    }
}
