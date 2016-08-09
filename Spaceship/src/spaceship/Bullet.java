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
* Klasa poświecona zachowaniu się pocisków
*/
public class Bullet extends Actor {
protected static final int BULLET_SPEED=10;

public Bullet(Stage stage) {
super(stage);
setSpriteName( "laser5.png");
}
/**
* Metoda odpowiedzialna za odswiezanie pozycje pociskow i ich usunieciu
*/
public void act() {
super.act();
y-=BULLET_SPEED;
if (y < 0)
remove();
}

/**
* Metoda odpowiedzialna za wykrywanie kolizji z asteroidami
*/
public void collision(Actor a) {
if (a instanceof Asteroid){
remove();
}
}



}