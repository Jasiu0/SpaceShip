/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

/**
 *
 * @author Jasiu
 */
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import javax.imageio.ImageIO;

/**
* Klasa służaca do obsługi grafiki i muzyki
*/
public class SpriteCache extends ResourceCache implements ImageObserver{
/**
* Metoda odpowiedzialna za pobierania plików/obrazów
*/    
protected Object loadResource(URL url) {
try {
return ImageIO.read(url);
} catch (Exception e) {
System.out.println("Przy otwieraniu " + url);
System.out.println("Wystapil blad : "+e.getClass().getName()+""+e.getMessage());
System.exit(0);
return null;
}
}

/**
* Metoda optymalizująca działanie programu
*/
public BufferedImage createCompatible(int width, int height, int transparency)
{
GraphicsConfiguration gc =
GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
BufferedImage compatible =
gc.createCompatibleImage(width,height,transparency);
return compatible;
}

/**
* Metoda odpowiedzialna za załadowanie i wyświetlenei obrazu
*/
public BufferedImage getSprite(String name) {
BufferedImage loaded = (BufferedImage)getResource("obrazki/"+name);
BufferedImage compatible =
createCompatible(loaded.getWidth(),loaded.getHeight(),Transparency.BITMASK);
Graphics g = compatible.getGraphics();
g.drawImage(loaded,0,0,this);
return compatible;
}

/**
* Metoda odpowiedzialna za odswieżanie obrazów
*/
public boolean imageUpdate(Image img, int infoflags,int x, int y, int w, int
h) {
return (infoflags & (ALLBITS|ABORT)) == 0;
}
}