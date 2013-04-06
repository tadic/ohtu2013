/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author ivantadic
 */
public class FileUserDAO implements UserDao{
    private File tiedosto;                

    public  FileUserDAO(File file){
        this.tiedosto =file;
        if (!file.exists()){
            System.out.print("ne postoji" + file.getName());
            try {
                this.tiedosto.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public List<User> listAll() {
        List<User> list = new ArrayList<User>();
        Scanner lukija;
        try {
            lukija = new Scanner(tiedosto, "UTF-8");
            while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            list.add(getUserFromLine(rivi));
            //System.out.println(rivi);
        }
        lukija.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private User getUserFromLine(String line){
        int i=0;
        StringBuilder username = new StringBuilder();
        StringBuilder password = new StringBuilder();
        while (line.length()!=i && line.charAt(i)!=' '){
            username.append(line.charAt(i));
            i++;
        }
        i++;
        while (line.length()!=i && line.charAt(i)!=' '){
            password.append(line.charAt(i));
            i++;
        }
        return new User(username.toString(), password.toString());
    }

    @Override
    public User findByName(String name) {
        List<User> list = listAll();
        for (User us: list){
            if (us.getUsername().equals(name)){
                return us;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter fw = new FileWriter(tiedosto, true);
            fw.write(user.getUsername() + " " + user.getPassword() + "\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
