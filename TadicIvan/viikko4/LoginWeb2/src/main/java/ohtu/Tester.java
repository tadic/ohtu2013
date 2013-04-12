
package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        kirjautuminen(driver, "pekka", "akkep");
        kirjautuminen(driver, "pekka", "akkkk");
        kirjautuminen(driver, "akkkk", "akkep");
        uusiKayttaja(driver, "ppppp", "pppp9999");
        kirjautuminen(driver, "ppppp", "pppp9999");
        
        
    }
    private static void kirjautuminen(WebDriver driver, String username, String password){
        driver.get("http://localhost:8080");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }
    private static void uusiKayttaja(WebDriver driver, String username, String password){
        driver.get("http://localhost:8080");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("add"));
        element.click();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }
    
}
