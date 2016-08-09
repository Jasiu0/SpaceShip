/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import java.awt.event.KeyEvent;


/**
 *
 * @author Jasiu
 */

 /**
* Klasa odpowiedzialna za gracza (statek kosmiczny) i wszystkie jego parametry oraz ataki
*/
public class Player extends Actor {
     /**
* Zmiena odpowiedzialna za wskazanie pozycje startowej pocisku (1 z 2 opcji= lewe działo i prawe działo)
*/
    protected int lp=0;
     /**
* Zmienna ustalająca prędkośc gracza (statku kosmicznego)
*/
protected static final int PLAYER_SPEED = 4;
 /**
* Zmienna odpowiedzialna za uaktualnienie pozycji w płaszczyźnie x
*/
protected int vx;
 /**
* Zmienna odpowiedzialna za uaktualnienie pozycji w płaszczyznie y
*/
protected int vy;
 /**
* zmienne odpowiedzialne za kierunek ruchu gracza 
*/
private boolean up,down,left,right;
 /**
* zmienna wskazująca aktualny wynik rozgrywki
*/
private int score;
 /**
* zmienna wskazująca na ilośc pozostałych ataków specjalnych
*/
private int bombs;
 /**
* zmienna mówiąca o maxymalnej wartości tarczy
*/
public static final int MAX_SHIELDS = 200;
 /**
* zmienna mówiąca o maksymalnej ilości ataków specjalnych
*/
public static final int MAX_BOMBS = 2;
 /**
* zmienna wskazująca na aktualną wartość tarczy
*/
private int shields;
 /**
* zmienna wskazujaca na aktualny poziom dmuchania
*/
private int blow=0;
 /**
* zmienna pomocnicza użyta przy ataku specjalnym
*/
private int time=0;
 /**
* zmienna wskazująca na czas przez jaki użytkownik dmuchał
*/
private long blowtime=0;
 /**
* zmienna wskazująca czy osoba dmucha czy nie
*/
private int checkblow=0;
 /**
* zmienna pomocnicza przy obliczaniu czasu dmuchania
*/
long poczatek;
 /**
* zmienna pomocnicza przy obliczaniu czasu dmuchania
*/
long koniec;

 /**
* Konstruktor zawierający odnosnik do grafiki statku i odnoszacy sie do interfejsu
*/
public Player(Stage stage) {
super(stage);
setSpriteName( "statek.png");
shields = MAX_SHIELDS;
bombs=MAX_BOMBS;

}
 /**
* metoda aktualizujaca pozycje oraz wykonujaca funkcje zwiazane z atakami 
*/
public void act() {
super.act();
time=time+1;

if (getBlow()>0 && checkblow==0)
{
checkblow=1;    
poczatek= System.currentTimeMillis();
}

if(getBlow()==0 && checkblow==1)
{
koniec= System.currentTimeMillis();
checkblow=0;
blowtime+=(koniec-poczatek)/1000;
}

if (getBlow()>0 && getShields()<=0 && checkblow==1)
{
   checkblow=0;
koniec= System.currentTimeMillis();
blowtime+=(koniec-poczatek)/1000;
}


System.out.println(blowtime);
x+=vx;
y+=vy;
if (x < 0 )
x = 0;
if (x > Stage.szerokosc - getWidth())
x = Stage.szerokosc - getWidth();
if (y < 0 )
y = 0;
if ( y > Stage.wysokosc_gry-getHeight())
y = Stage.wysokosc_gry - getHeight();

if(time>15)
{
if(getBlow()>=40 && getBlow()<=60) fire();
if(getBlow()>=65 && getBlow()<=85)
    if(getShields()<200) addShields(5);
if(getBlow()>=90) fireCluster();
stage.getPlayer().addScore(1);

time=0;
}
}

 /**
* Metoda pobierająca prędkosc w płaszczyźnie x
*/
public int getVx() { return vx; }
 /**
* Metoda wskazująca prędkosc w płaszczyźnie x
*/
public void setVx(int i) {vx = i; }
 /**
* Metoda pobierajaca prędkosc w płaszczyźnie y
*/
public int getVy() { return vy; }
 /**
* Metoda wskazująca prędkosc w płaszczyźnie y
*/
public void setVy(int i) {vy = i; }
 /**
* Metoda pobierająca aktualną wartość czasu dmuchania
*/
public long getBlowTime() { return blowtime; }
 /**
* Metoda nadpisujaca aktualną wartość czasu dmuchania
*/
public void setBlowTime(long i) { blowtime = i; }
 /**
* Metoda pobierająca aktualną wartość wyniku rozgrywki
*/
public int getScore() { return score; }
 /**
* Metoda nadpisująca aktualną wartość czasu dmuchania
*/
public void setScore(int i) { score = i; }
 /**
* Metoda pobierająca aktualną wartość tarczy statku
*/
public int getShields() { return shields; }
 /**
* Metoda nadpisująca aktualną wartość tarczy
*/
public void setShields(int i) { shields = i; }
 /**
* Metoda pobierająca aktualną wartość dmuchu
*/
public int getBlow() { return blow; }
 /**
* Metoda nadpisująca aktualną wartość dmuchu
*/
public void setBlow(int i) { blow = i; }
 /**
* Metoda pobierająca aktualną liczbę ataków specjalnych
*/
public int getClusterBombs() { return bombs; }
 /**
* Metoda nadpisująca aktualną liczbę ataków specjalnych
*/
public void setClusterBombs(int i) { bombs = i; }

 /**
* Metoda służaca do nadpisania ruchu gracza (statku)
*/
protected void updateSpeed() {
vx=0;vy=0;
if (down) vy = PLAYER_SPEED;
if (up) vy = -PLAYER_SPEED;
if (left) vx = -PLAYER_SPEED;
if (right) vx = PLAYER_SPEED;
}

 /**
* Metoda nadpisująca aktualny wynik o określoną wartość
*/
public void addScore(int i) { score += i; }
 /**
* Metoda nadpisująca aktualną wartość tarczy o określoną wartość
*/
public void addShields(int i) { shields += i; }
 /**
* Metoda nadpisująca aktualną siłę dmuchu o określoną wartość
*/
public void addBlow(int i) { blow += i; }

 /**
* Metoda odpowiedzialna za powstawanie pocisku
*/
public void fire() {
Bullet b = new Bullet(stage);
switch(lp)
{
    case 0: 
b.setX(x+45);
lp=1;
break;

    case 1:
b.setX(x+65);
lp=0;
break;
}

b.setY(y - b.getHeight());
stage.addActor(b);
}

 /**
* Metoda odpowiedzialna za powstawanie ataku specjalnego
*/
public void fireCluster() {
if (bombs == 0)
return;
bombs--;
stage.addActor( new Bomb(stage, Bomb.UP_LEFT, x,y));
stage.addActor( new Bomb(stage, Bomb.UP,x,y));
stage.addActor( new Bomb(stage, Bomb.UP_RIGHT,x,y));
stage.addActor( new Bomb(stage, Bomb.LEFT,x,y));
stage.addActor( new Bomb(stage, Bomb.RIGHT,x,y));
stage.addActor( new Bomb(stage, Bomb.DOWN_LEFT,x,y));
stage.addActor( new Bomb(stage, Bomb.DOWN,x,y));
stage.addActor( new Bomb(stage, Bomb.DOWN_RIGHT,x,y));
}

 /**
* Metoda odpowiedzialna wykrycie kolizji 
*/
public void collision(Actor a) {
if (a instanceof Asteroid ) {
a.remove();
addScore(40);
addShields(-40);

if (getShields() < 0)
stage.gameOver();
}
}



 /**
* Metoda odpowiedzialna za reakcje na puszczenie klawisza
*/
public void keyReleased(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_DOWN : down = false; break;
case KeyEvent.VK_UP : up = false; break;
case KeyEvent.VK_LEFT : left = false; break;
case KeyEvent.VK_RIGHT : right = false; break;
}
updateSpeed();
}


/**
* Metoda odpowiedzialna za reakcje na nasisniecie któregos z przyciskow ( ruch e-dmuchawki)
*/
public void keyPressed(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_UP : up = true; break;
case KeyEvent.VK_LEFT : left = true; break;
case KeyEvent.VK_RIGHT : right = true; break;
case KeyEvent.VK_DOWN : down = true;break;
case KeyEvent.VK_SPACE : fire(); break;
case KeyEvent.VK_B : fireCluster(); break;
case KeyEvent.VK_A :
if(getBlow()!=100)
    addBlow(5);
break;
    
case KeyEvent.VK_Z : 
    if(getBlow()!=0)
     addBlow(-5);
break;
}
updateSpeed();
}

}

