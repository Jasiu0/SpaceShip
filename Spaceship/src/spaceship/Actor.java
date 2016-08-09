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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
* Klasa odpowiedzialna za wszystkie obiekty znajdujace sie w świecie
*/
public class Actor {
 /**
* Zmienne określające pozycje obiektu
*/
protected int x,y;
 /**
* Zmienna mówiąca o szerokości i wysokości obiektu
*/
protected int width, height;
 /**
* Zmienna związana z pozyskiwanie obrazów
*/
protected String spriteName;
 /**
* Zmienna odwołująca sie do intrefejsu Stage
*/
protected Stage stage;
 /**
* Zmienna oprecujaca obrazami
*/
protected SpriteCache spriteCache;


public Actor(Stage stage) {
this.stage = stage;
spriteCache = stage.getSpriteCache();
}

/**
* Metoda rysujaca obiekty w danym miejscu na scenie
*/
public void paint(Graphics2D g){
g.drawImage( spriteCache.getSprite(spriteName), x,y, stage );
}
 /**
* Metoda pobierajaca pozycje na płaszczyźnie x
*/
public int getX() { return x; }
 /**
* Metoda ustawiająca pozycje na płaszczyźnie x
*/
public void setX(int i) { x = i; }
 /**
* Metoda pobierajaca pozycje na płaszczyźnie y
*/
public int getY() { return y; }
 /**
* Metoda ustawiająca pozycje na płaszczyźnie x
*/
public void setY(int i) { y = i; }
public String getSpriteName() { return spriteName; }

 /**
* Metoda odpowiedzialna za ustalenie wysokosci i szerokosci pobranego obrazu
*/
public void setSpriteName(String string) {
spriteName = string;
BufferedImage image = spriteCache.getSprite(spriteName);
height = image.getHeight();
width = image.getWidth();
}

 /**
* Metoda pobierajaca wysokosc obrazu
*/
public int getHeight() { return height; }
 /**
* Metoda pobierajaca szerokosc obrazu
*/
public int getWidth() { return width; }
 /**
* Metoda ustalająca wysokośc obrazu
*/
public void setHeight(int i) {height = i; }
 /**
* Metoda ustalająca szerokość obrazu
*/
public void setWidth(int i) { width = i; }

 /**
* Metoda aktualizacji aktorów
*/
public void act() { }


protected boolean markedForRemoval;
 /**
* Metoda ustawiajaca obiekt do usunięcia
*/
public void remove() {
markedForRemoval = true;
}
 /**
* Metoda sprawdzajaca czy dany obiekt jest do usunięcia
*/
public boolean isMarkedForRemoval() {
return markedForRemoval;
}
 /**
* Metoda pobierajaca zakres do odczytania kolizji
*/
public Rectangle getBounds() {
return new Rectangle(x,y,width,height);
}
 /**
* Metoda odpowiadająca za kolizje obiektów
*/
public void collision(Actor a){}

}
