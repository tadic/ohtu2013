/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Ostoskori;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author ivantadic
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
      @Test
  public void ostoksenPaaytyttyapankinMetodiaTilisiirtoKutsutaan() {
      // luodaan ensin mock-oliot
      Pankki pankki = mock(Pankki.class);
      
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      
      Varasto varasto = mock(Varasto.class);
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      
      // tehdään ostokset
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      
      // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
      verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());        
  }
      
 @Test
  public void Kutsutaan1() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      
      // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
      verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(5));       
  }
 @Test
 public void Kutsutaan2() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.saldo(2)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
      when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "justo", 7));
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.lisaaKoriin(2);
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      
      verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(12));       
  }
  @Test
 public void Kutsutaan3() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
      when(varasto.saldo(1)).thenReturn(10); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.lisaaKoriin(1);
            k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      
      verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(10));       
  }
   @Test
 public void Kutsutaan5() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
      when(varasto.saldo(1)).thenReturn(0); 
      when(varasto.saldo(2)).thenReturn(2); 
      when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
      when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "justo", 7));
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.lisaaKoriin(2);
      k.lisaaKoriin(1);
      k.tilimaksu("pekka", "12345");
      
      verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(),eq(7));       
  }
   
 @Test
 public void Kutsutaan6() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.tilimaksu("", "");
      
      verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),eq(0));       
  }
 
 @Test
 public void Kutsutaan7() {
      Pankki pankki = mock(Pankki.class);
      Viitegeneraattori viite = mock(Viitegeneraattori.class);
      when(viite.uusi()).thenReturn(1);
      Varasto varasto = mock(Varasto.class);
     
      // sitten testattava kauppa 
      Kauppa k = new Kauppa(varasto, pankki, viite);              
      // tehdään ostokset
      k.aloitaAsiointi();
      k.tilimaksu("", "");
      
      verify(viite).uusi();       
  }
}
