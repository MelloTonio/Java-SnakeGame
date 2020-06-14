import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Gameplay extends JPanel implements KeyListener, ActionListener  {
    private ImageIcon titleImage;
    // Array creation for the max size of the snake, based on the window size
    private int[] snakexlength = new int[750]; //snake x axis array
    private int[] snakeylength = new int[750]; //snake y axis array

    private int proglast = 84;
    private int score = 0;
    private int progs = 2;

    // Boolean for the keys pressed, gameplay input
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean gameover = false;

    //Enemy array positions
    private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,
                                400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,
                                850};
    private int [] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,
                                400,425,450,475,500,525,550,575,600,625};
       
    //Generate Enemy icon
    private ImageIcon enemyImage;

    Random gerador = new Random();
    Random wololo = new Random();
      private int ypos = 1 + wololo.nextInt(22 - 1); //generate a random number for the array index for the initial (ypos)
      private int xpos = 1 + gerador.nextInt(22 - 1); //generate a random number for the array index for the initial (xpos)
       
      
      

    private int moves = 0;
    
    //initial body of the snake, starts with 3 "ram"
    private int lengthSnake = 3;

    // Initiate the snake mouths
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon backgroundImage;
    private ImageIcon progSupremo;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;

    public Gameplay() {

        addKeyListener(this); //The keylistener will recieve itself and record the keypressed
        setFocusable(true); //gives the panel a windows focus
        setFocusTraversalKeysEnabled(false); //set the keys focus on windows
        timer = new Timer(delay, this); //Delay for initial input
        timer.start(); //start the timer

    }

    public void paint(Graphics g) {
        //Sets sthe initial position 
        if(moves == 0){
            snakexlength[2] = 50;
            snakexlength[1] = 75;
            snakexlength[0] = 100;

            snakeylength[2] = 100;
            snakeylength[1] = 100;
            snakeylength[0] = 100;
        }        

        
        // Title image Border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        // Drawing title image
        titleImage = new ImageIcon("snaketitle.jpg"); // call the jpg image for title
        titleImage.paintIcon(this, g, 25, 11); // 25 and 11 are the title positions

        // Draw border for gameplay

        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577); // 24 and 74 are the coordinates and the border is 851px by 577px

        // Draw background for gameplay
        backgroundImage = new ImageIcon("background.png"); // call the png image for the bg
        backgroundImage.paintIcon(this, g, 25, 75);  //sets on postion 25 and 75

        // Draw scores
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Javas Coletados: " + (lengthSnake - 3),700,30);

        //Draw score till last
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Progs restantes: " + proglast,50,40);


        //Draw length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Progs Coletados: " + progs ,700,50);


        rightmouth = new ImageIcon("rightmouth.png");  //call the png rightmouth for an initial state
        rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);  //set the snake initial position

        for(int a = 0; a < lengthSnake; a++){

            if(a == 0 && right){

                rightmouth = new ImageIcon("rightmouth.png");  //call the rightmouth png for the snake
                rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]); //put the snake on the 'a' postion of the array
            }
            if(a == 0 && left){

                leftmouth = new ImageIcon("leftmouth.png");  //call the leftmouth png for the snake
                leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]); //put the snake on the 'a' postion of the array
            }
            if(a == 0 && down){

                downmouth = new ImageIcon("downmouth.png");  //call the downmouth png for the snake
                downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]); //put the snake on the 'a' postion of the array
            }
            if(a == 0 && up){

                upmouth = new ImageIcon("upmouth.png");  //call the upmouth png for the snake
                upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]); //put the snake on the 'a' postion of the array
            }

            if (a != 0){
                snakeimage = new ImageIcon("snakeImage.png"); //call the png file for the snake's body when added 
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);  //put the snake body on the 'a' position of the array 
            }

        }
        
        enemyImage = new ImageIcon("enemy.png"); //call the enemy .png

        if((enemyypos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0])){
            lengthSnake++;
            proglast--;
            progs++;
            ypos =  1 + wololo.nextInt(22 - 1);  //generate the next random array index 
            xpos =  1 + gerador.nextInt(22 - 1);  //generate the next random array index 
            
        }
         
        for(int b = 1; b < lengthSnake; b++){
            if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]){
                right = false;
                left = false;
                up = false;
                down = false;

                g.setColor(Color.black);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Eli banks morreu!", 255, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Aperte Space para tentar novamente", 300, 340);

                gameover = true;
                

            }
        }
        if(lengthSnake - 1 == 86) {
            right = false;
            left = false;
            up = false;
            down = false;

            progSupremo = new ImageIcon("final.jpg");
            progSupremo.paintIcon(this, g, 25, 75);
            gameover = true;

        }

        if(!gameover)
        {
            enemyImage.paintIcon(this, g, enemyxpos[xpos]+40, enemyypos[ypos]); //+40 on the array position due to conflicts on the in game points collect process
            g.setColor(Color.white);
          
        }
        if(gameover)
        {
            progs++;

        }
        g.dispose(); //dispose the graphical content
    }

    //Snake movement actions
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right){
            for(int r = lengthSnake-1; r>=0; r-- ){  //change the snake's y axis 
                snakeylength[r+1] = snakeylength[r];
            }
            for(int r = lengthSnake; r>=0; r--){
                if(r ==0){
                    snakexlength[r] = snakexlength[r] + 25; // initial position of the snake if it's the start
                }
                else{
                    snakexlength[r] = snakexlength[r-1];
                }
                if(snakexlength[r] > 850){  // teleports the snake back if exceeds the border limit
                    snakexlength[r] = 25;  //left border by default
                }
            }

            repaint(); // rewrite all
        }
        if(left){

            for(int r = lengthSnake-1; r>=0; r-- ){  //change the snake's y axis 
                snakeylength[r+1] = snakeylength[r];
            }
            for(int r = lengthSnake; r>=0; r--){
                if(r ==0){
                    snakexlength[r] = snakexlength[r] - 25; // initial position of the snake if it's the start
                }
                else{
                    snakexlength[r] = snakexlength[r-1];
                }
                if(snakexlength[r] < 25){  //teleports the snake back if exceeds the border limit
                    snakexlength[r] = 850;  //right border position by default
                }
            
        }
        repaint(); // rewrite all
    }
        if(up){
            for(int r = lengthSnake-1; r>=0; r-- ){  //change the snake's x axis 
                snakexlength[r+1] = snakexlength[r];
            }
            for(int r = lengthSnake; r>=0; r--){
                if(r ==0){
                    snakeylength[r] = snakeylength[r] - 25; // initial position of the snake if it's the start
                }
                else{
                    snakeylength[r] = snakeylength[r-1];
                }
                if(snakeylength[r] < 75){  // teleports the snake back if exceeds the border limit
                    snakeylength[r] = 625;  // bottom border position by default
                }
            
            }
            repaint(); // rewrite all
        }
        if(down){

            for(int r = lengthSnake-1; r>=0; r-- ){  //change the snake's x axis 
            snakexlength[r+1] = snakexlength[r];
        }
        for(int r = lengthSnake; r>=0; r--){
            if(r ==0){
                snakeylength[r] = snakeylength[r] + 25; // initial position of the snake if it's the start
            }
            else{
                snakeylength[r] = snakeylength[r-1];
            }
            if(snakeylength[r] > 625){  // teleports the snake back if exceeds the border limit
                snakeylength[r] = 75;  // top border position by default
            }
        }
            repaint(); // rewrite all
        }

        

    }

    @Override
    public void keyTyped(KeyEvent e) {
        

    }


    //Key grabber for the snake's movement
    @Override
    public void keyPressed(KeyEvent e) {
        if(gameover){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            score = 0;
            lengthSnake = 3;
            repaint();
            right = true;
            gameover = false;
            moves = 0;
            progs = 2;
            proglast = 86;
            


        }}
        if(!gameover){
        if(e.getKeyCode() == KeyEvent.VK_D){
            moves ++;
            right = true;
            if(!left){
                right = true;
            } else{
                right = false;
                left = true;
            }
            down = false;
            up = false;
            gameover = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            moves ++;
            left = true;
            if(!right){
                left = true;
            } else{
                left = false;
                right = true;
            }
            down = false;
            up = false;
            gameover = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            moves ++;
            down = true;
            if(!up){
                down = true;
            } else{
                down = false;
                up = true;
            }
            right = false;
            left = false;
            gameover = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            moves ++;
            up = true;
            if(!down){
                up = true;
            } else{
                up = false;
                down = true;
            }
            left = false;
            right = false;
            gameover = false;
            
        }
        }
    }
        
    

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
