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

/**
* Klasa poświecona zachowaniu się ataku specjalnego
*/
public class Bomb extends Actor {
    /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int UP_LEFT = 0;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int UP = 1;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int UP_RIGHT = 2;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int LEFT = 3;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int RIGHT = 4;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int DOWN_LEFT = 5;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int DOWN = 6;
   /**
* Zmienna odpowiedzialna za kierunek ruchu poszczególnej części ataku specjalnego
*/
public static final int DOWN_RIGHT = 7;
   /**
* Zmienna wskazująca prędkośc ataku specjalnego
*/
protected static final int BOMB_SPEED = 5;
   /**
* Zmienna odpowiedzialna za kierunek ruchu ataku specjalnego w plaszczyźnie x
*/
protected int vx;
   /**
* Zmienna odpowiedzialna za kierunek ruchu ataku specjalnego w plaszczyźnie y
*/
protected int vy;

   /**
* Konstruktor pobierająca obrazy i kierunek ruchu poszczególnych częsci ataku specjalnego 
*/
public Bomb(Stage stage, int heading, int x, int y) {
super(stage);
this.x = x+20;
this.y = y+30;
String sprite ="";
switch (heading) {
case UP_LEFT : vx = -BOMB_SPEED; vy = -BOMB_SPEED;
sprite="ogien2.png";break;
case UP : vx = 0; vy = -BOMB_SPEED; sprite="ogien2.png";break;
case UP_RIGHT: vx = BOMB_SPEED; vy = -BOMB_SPEED;
sprite="ogien2.png";break;
case LEFT : vx = -BOMB_SPEED; vy = 0; sprite = "ogien2.png";break;
case RIGHT : vx = BOMB_SPEED; vy = 0; sprite = "ogien2.png";break;
case DOWN_LEFT : vx = -BOMB_SPEED; vy = BOMB_SPEED;
sprite="ogien2.png";break;
case DOWN : vx = 0; vy = BOMB_SPEED; sprite = "ogien2.png";break;
case DOWN_RIGHT : vx = BOMB_SPEED; vy = BOMB_SPEED; sprite =
"ogien2.png";break;
}
setSpriteName( sprite);
}

   /**
* Metoda odpowiedzialna za ruch ataku specjalnegooraz jego usuwanie
*/
public void act() {
super.act();
y+=vy;
x+=vx;
if (y < 0 || y > Stage.wysokosc || x < 0 || x > Stage.szerokosc)
remove();
}

   /**
* Metoda odpowiedzialna za wykrycie kolizji ataku z asteroidą
*/
public void collision(Actor a) {
if (a instanceof Asteroid){
remove();
}
}


}