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
* Klasa odpowiedzialna za tworzenie i ruch Asteroid
*/
public class Asteroid extends Actor {
 /**
* Zmienna opisujaca prędkośc asteroidy w płaszczyźnie x
*/
protected int vx;
 /**
* Zmienna opisujaca prędkośc asteroidy w płaszczyźnie y
*/
protected int vy;
 /**
* Przyspieszenie nowo powstających asteroid
*/
protected int przysp=1;

public Asteroid(Stage stage) {
super(stage);
setSpriteName("asteroida3.png");
} 

 /**
* Metoda tworząca nową asteroide o ustalonej prędkości i kierunku
*/
public void spawn() {
    przysp+=0.1;
Asteroid m = new Asteroid(stage);
m.setX( (int)(Math.random()*Stage.szerokosc) );
m.setY( (int)(-100) );
//m.setY( (int)(Math.random()*Stage.wysokosc_gry/2) );
m.setVx( (int)(Math.random()*3)+przysp);
m.setVy( (int)(Math.random()*3)+przysp);
stage.addActor(m);
}

 /**
* Metoda wykrywająca kolizje z pociskiem, bomba lub graczem i usuwajaca obiekty
*/
public void collision(Actor a) {
if (a instanceof Bullet || a instanceof Bomb || a instanceof Player){
remove();
spawn();
stage.getPlayer().addScore(10);}
}

 /**
* Metoda uaktualniajaca pozycje asteroid
*/
public void act() {
x+=vx;
y+=vy;
if (x < -140 || x > Stage.szerokosc)
vx = -vx;
if (y < -100 || y > Stage.wysokosc)
vy=-vy;    

}

 /**
* Metoda pobierajaca pozycje asteroidy na płaszczyźnie x
*/
public int getVx() { return vx; }
 /**
* Metoda ustawiająca pozycje asteroidy na płaszczyźnie x
*/
public void setVx(int i) {vx = i; }
 /**
* Metoda pobierajaca pozycje asteroidy na płaszczyźnie y
*/
public int getVy() { return vy; }
 /**
* Metoda ustawiająca pozycje asteroidy na płaszczyźnie y
*/
public void setVy(int i) {vy = i; }

}