
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static ArrayList<Integer> list = new ArrayList<>();
    public static void readJpg() {
        try {
            FileInputStream fis = new FileInputStream("Pic.jpg");
            int read;
            while ((read = fis.read()) != -1) {
                list.add(read);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public static void copyJpg(String picName) {
        try {
            FileOutputStream fos = new FileOutputStream(picName);
            for (int temp: list) {
                fos.write(temp);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) {
        readJpg();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                copyJpg("Pic1.jpg");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                copyJpg("Pic2.jpg");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                copyJpg("Pic3.jpg");
            }
        });
        
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
