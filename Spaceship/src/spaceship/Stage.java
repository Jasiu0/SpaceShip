/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import java.awt.image.ImageObserver;
/**
 *
 * @author Jasiu
 */
/**
* Interfejs gry zawierające informacje o wymiarach sceny
*/
public interface Stage extends ImageObserver {
 /**
* Zmienna mówiąca o szerokości okna
*/
public static final int szerokosc = 1024;
 /**
* Zmienna mówiąca o wysokosci okna
*/
public static final int wysokosc = 768;
 /**
* Zmienna mówiąca o szybkości gracza
*/
public static final int szybkosc = 10;
 /**
* Zmienna mówiąca o wysokości właściwej gry po odliczeniu miejsca na status gracza
*/
public static final int wysokosc_gry = 700;
 /**
* Metoda odpowiedzialna za operacje związane z grafiką
*/
public SpriteCache getSpriteCache();
 /**
* Metoda dodająca aktora
*/
public void addActor(Actor a);
 /**
* Metoda pobierajaca informacje o graczu
*/
public Player getPlayer();
 /**
* Metoda odpowiedzialna za konczenie gry
*/
public void gameOver();
}
