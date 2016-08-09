/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;

/**
 *
 * @author Jasiu
 */


/**
* Główna klasa programu, znajduje sie tu metoda main.
*/
public class Spaceship extends Canvas implements Stage,KeyListener {

/**
* Zmienna służąca do obliczania Fps
*/
public long usedTime; 
/**
* Zmienna użyta w celu wyeliminowania efektu migania
*/
public BufferStrategy strategia;
/**
* Zmienna służąca do obsługi grafiki
*/
private SpriteCache spriteCache;
/**
* Zmienna do zliczania tworzących się obiektów
*/
private ArrayList actors;
/**
* Zmienna odnosząca się do klasy player 
*/
private Player player;
/**
* Zmienna odpowiadająca za zakończenie gry
*/
private boolean gameEnded=false;
/**
* Zmienne służace obsłudze tła
*/
private BufferedImage background, backgroundTile;
/**
* Zmienna odpowiadająca za wysokośc gry przy tworzenie tła
*/
private int backgroundY;
/**
* Zmienna z nazwą gracza
*/
private String imie="Anonymous";
/**
* Zmienna pomocnicza
*/
private int t;
/**
* Zmienna zliczająca całkowity czas gry
*/
private long totalTime;

/**
     * Tworzy okno programu
     */
public Spaceship()
{ 
   
spriteCache = new SpriteCache();
 JFrame okno=new JFrame(".: Spaceship :.");
 JPanel panel=(JPanel)okno.getContentPane();
   setBounds(0,0,Stage.szerokosc,Stage.wysokosc);
 panel.setPreferredSize(new Dimension(Stage.szerokosc,Stage.wysokosc));
panel.setLayout(null);
panel.add(this);

  okno.setBounds(0,0,Stage.szerokosc,Stage.wysokosc);
 okno.setVisible(true);  
 okno.setResizable(false);
 okno.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e){
System.exit(0);
}
});
 
 
 createBufferStrategy(2);
strategia = getBufferStrategy();
requestFocus();
addKeyListener(this);

BufferedImage cursor =
spriteCache.createCompatible(10,10,Transparency.BITMASK);
Toolkit t = Toolkit.getDefaultToolkit();
Cursor c = t.createCustomCursor(cursor,new Point(5,5),"null");
setCursor(c);
setIgnoreRepaint(true);
} 


/**
* metoda inicjalizująca świat wraz z obiektami
*/
public void initWorld() {
actors = new ArrayList();
for (int i = 0; i < 10; i++){
Asteroid m = new Asteroid(this);
m.setX( (int)(Math.random()*Stage.szerokosc) );
m.setY( -50 );
m.setVx( (int)(Math.random()*3)+1 );
m.setVy( (int)(Math.random()*3)+1 );
actors.add(m);
}
player = new Player(this);
player.setX(Stage.szerokosc/2);
player.setY(Stage.wysokosc - 2*player.getHeight());

backgroundTile = spriteCache.getSprite("proba.jpg");
background = spriteCache.createCompatible(
Stage.szerokosc,
Stage.wysokosc+backgroundTile.getHeight(),
Transparency.OPAQUE);
Graphics2D g = (Graphics2D)background.getGraphics();
g.setPaint( new TexturePaint( backgroundTile,
new
Rectangle(0,0,backgroundTile.getWidth(),backgroundTile.getHeight())));
g.fillRect(0,0,background.getWidth(),background.getHeight());
backgroundY = backgroundTile.getHeight();
}

/**
* Metoda odnosząca się do klasy Player powstała aby pobierać funkcje z tej klasy
*/
public Player getPlayer() { return player;}
/**
*  Metoda potwierdzająca przegrana/ zakończenie gry
*/
public void gameOver() { gameEnded = true;}

/**
* Metoda rysująca świat, gracza oraz Status
*/
public void paintWorld() {
Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
g.drawImage( background,
0,0,Stage.szerokosc,Stage.wysokosc,
0,backgroundY,Stage.szerokosc,backgroundY+Stage.wysokosc,this);
for (int i = 0; i < actors.size(); i++) {
Actor m = (Actor)actors.get(i);
m.paint(g);
}
player.paint(g);
paintStatus(g);
strategia.show();
}

/**
* Metoda służąca do obliczania Fps
*/
public void paintfps(Graphics2D g) {
g.setFont( new Font("Arial",Font.BOLD,12));
g.setColor(Color.white);
if (usedTime > 0)
g.drawString(String.valueOf(1000/usedTime)+" fps",Stage.szerokosc-50,Stage.wysokosc_gry);
else
g.drawString("--- fps",Stage.szerokosc-50,Stage.wysokosc_gry);

}

/**
* Metoda służaca do obliczania całkowitego wyniku
*/
public void paintScore(Graphics2D g) {
g.setFont(new Font("Arial",Font.BOLD,20));
//g.setPaint(Color.green);
g.drawImage(spriteCache.getSprite("score.png"),10,Stage.wysokosc_gry ,this);
//g.drawString("Score:",20,Stage.wysokosc_gry + 20);
g.setPaint(Color.yellow);
g.drawString(player.getScore()+"",120,Stage.wysokosc_gry + 20);
}

/**
* Metoda pobierająca i rysujaca wartośc wskaźnika dmuchania
*/
public void paintBlow(Graphics2D g) {
g.setFont(new Font("Arial",Font.BOLD,20));
g.setPaint(Color.green);
//g.drawString("Blow:",500,Stage.wysokosc_gry + 20);
g.drawImage(spriteCache.getSprite("blow.png"),550,Stage.wysokosc_gry ,this);
g.setPaint(Color.yellow);
g.drawString(player.getBlow()+"",650,Stage.wysokosc_gry + 20);
}


/**
* Metoda pobierająca i rysująca wartośc tarczy/życia gracza
*/
public void paintShields(Graphics2D g) {
g.setPaint(Color.red);
g.fillRect(330,Stage.wysokosc_gry,Player.MAX_SHIELDS,30);
g.setPaint(Color.yellow);
g.fillRect(330,Stage.wysokosc_gry,player.getShields(),30);
g.setFont(new Font("Arial",Font.BOLD,20));
g.setPaint(Color.green);
//g.drawString("Shields:",170,Stage.wysokosc_gry+20);
g.drawImage(spriteCache.getSprite("shields.png"),190,Stage.wysokosc_gry ,this);
}

/**
* Metoda pobierająca i wyświetlająca ilośc pozostałych ataków specjalnych
*/
public void paintAmmo(Graphics2D g) {
int xBase = 500+Player.MAX_SHIELDS+10;
for (int i = 0; i < player.getClusterBombs();i++) {

g.drawImage( spriteCache.getSprite("ogien3.png") ,xBase+i*40,Stage.wysokosc_gry+2,this);
}
}

/**
* Metoda zawierająca metody score,shield,fps,blow i ammo
*/
public void paintStatus(Graphics2D g) {
paintScore(g);
paintShields(g);
paintfps(g);
paintBlow(g);
paintAmmo(g);
}


/**
* Metoda służaca do dodania nowego aktora
*/
public void addActor(Actor a) {
actors.add(a);
}

/**
* Metoda odświeżająca świat i niszcząca niepotrzebne obiekty
*/
public void updateWorld() {
int i = 0;
while (i < actors.size()) {
Actor m = (Actor)actors.get(i);
if (m.isMarkedForRemoval()) {
actors.remove(i);
} else {
m.act();
i++;
}
}
player.act();
}

/**
* Metoda służaca do obsługi grafiki
*/
public SpriteCache getSpriteCache() {
return spriteCache;
}

/**
* Metoda sprawdzająca i wykrywajaca kolizje obiektów
*/
public void checkCollisions() {
Rectangle playerBounds = player.getBounds();
for (int i = 0; i < actors.size(); i++) {
Actor a1 = (Actor)actors.get(i);
Rectangle r1 = a1.getBounds();
if (r1.intersects(playerBounds)) {
player.collision(a1);
a1.collision(player);

}
for (int j = i+1; j < actors.size(); j++) {
Actor a2 = (Actor)actors.get(j);
Rectangle r2 = a2.getBounds();
if (r1.intersects(r2)) {
a1.collision(a2);
a2.collision(a1);
}
}
}
}

/**
* Metoda obsługi nazwy/imienia użytkownika
*/
public void name() {
imie=  JOptionPane.showInputDialog("Podaj imie", imie);      
}

/**
* Metoda służaca do zapisu zakończonej rozgrywki do pliku
*/
public void zapisdopliku() throws FileNotFoundException {
SimpleDateFormat simpleDateThere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleDateThere.setTimeZone( TimeZone.getTimeZone("Poland") );
        System.out.println( simpleDateThere.format(new Date()) );    
    PrintWriter zapis = new PrintWriter(new FileOutputStream(new File("wyniki.txt"), true));
    zapis.println(totalTime+" - "+player.getBlowTime()+" - "+imie+" - "+simpleDateThere.format(new Date())+" - "+player.getScore());
    zapis.close();
}

/**
* Metoda obsługi gry
*/
public void game() {
usedTime=1000;
t = 0;
initWorld();
name();
long start = System.currentTimeMillis();
while (isVisible() && !gameEnded) {
    t++;
    
long startTime = System.currentTimeMillis();

backgroundY--;
if (backgroundY < 0)
backgroundY = backgroundTile.getHeight();
updateWorld();
checkCollisions();
paintWorld();
usedTime = System.currentTimeMillis()-startTime;
do {
Thread.yield();
} while (System.currentTimeMillis()-startTime< 17);
}
paintGameOver();
long end= System.currentTimeMillis();
totalTime=((end-start)/1000);
gameEnded=false;
try {zapisdopliku();}
catch(FileNotFoundException ex) {System.out.println("Nie znaleziono pliku");}

game();
}

/**
* Metoda wyświetlająca napis Game Over
*/
public void paintGameOver() {
Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
g.setColor(Color.white);
g.setFont(new Font("Arial",Font.BOLD,20));
g.drawString("GAME OVER",Stage.szerokosc/2-50,Stage.wysokosc/2);
strategia.show();
}



public void keyPressed(KeyEvent e) {
player.keyPressed(e);
}
public void keyReleased(KeyEvent e) {
player.keyReleased(e);
}
public void keyTyped(KeyEvent e) {}



    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Spaceship gra=new Spaceship();
        gra.game();
        // TODO code application logic here
    }
    
}
