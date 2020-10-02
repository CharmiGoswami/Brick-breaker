
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Charmi Goswami
 */
/**
 * playGame class 
 */

 class playGame extends JPanel implements KeyListener,ActionListener{
	private boolean play=false;
	private int score=0;

	private int totalBricks=21;

	private Timer time;
	private int delay=8;

	private int playerX=310;

	private int bollposX=120;
	private int bollposY=350;
	private int bollXdir=-1;
	private int bollYdir=-2;

        private mapGenerator map;
        
	public playGame(){
                map=new mapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(delay,this);
		time.start();
		//addActionListener(this);
	}

	public void paint(Graphics g){
                //backgroung
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
                
                //drawing map
                map.draw((Graphics2D)g);
                
                //broder
		g.setColor(Color.red);
		g.fillRect(playerX,550,100,8);
                
                //scores
                g.setColor(Color.WHITE);
                g.setFont(new Font("serif",Font.BOLD,30));
                g.drawString("Score: "+score, 500, 30);

                //boll
		g.setColor(Color.yellow);
		g.fillOval(bollposX,bollposY,15,15);
                
                if(totalBricks <=0 ){
                    play=false;
                    bollposX=0;
                    bollposY=0;
                    g.setColor(Color.RED);
                    g.setFont(new Font("serif",Font.BOLD,40));
                    g.drawString("You won... ", 260, 300);
                    
                    g.setFont(new Font("serif",Font.BOLD,20));
                    g.drawString("Press ENTER to restart ", 230, 350);
                }
                if(bollposY>570){
                    play=false;
                    bollXdir=0;
                    bollYdir=0;
                    g.setColor(Color.RED);
                    g.setFont(new Font("serif",Font.BOLD,40));
                    g.drawString("Game Over..... ", 190, 300);
                    
                    g.setFont(new Font("serif",Font.BOLD,20));
                    g.drawString("Press ENTER to restart ", 230, 350);
                }
                g.dispose();

	}

	public void actionPerformed(ActionEvent e){
                time.start();
                if(play){
                    if(new Rectangle(bollposX,bollposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                        bollYdir=-bollYdir;
                    }
                    
                    A: for(int i=0;i<map.map.length;i++){
                        for(int j=0;j<map.map[0].length;j++){
                            if(map.map[i][j]>0){
                            int brickX=j*map.brickwidth+80;
                            int brickY=i*map.brickhight+50;
                            int brickWidth=map.brickwidth;
                            int brickHight=map.brickhight;
                            
                            Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHight);
                            Rectangle bollRect=new Rectangle(bollposX,bollposY,20,20);
                            Rectangle brickRect=rect;
                            
                            if(bollRect.intersects(brickRect)){
                                map.setBrickValue(0, i, j);
                                totalBricks--;
                                score+=5;
                                
                                if(bollposX + 19<= brickRect.x || bollposX + 1 >=brickRect.x+brickRect.width){
                                    bollXdir =- bollXdir;
                                }
                                else{
                                    bollYdir =- bollYdir;
                                }
                                break A;
                            }
                        }
                        }
                    }
                    bollposX+=bollXdir;
                    bollposY+=bollYdir;
                    if(bollposX<0){
                        bollXdir=-bollXdir;
                    }
                     if(bollposY<0){
                        bollYdir=-bollYdir;
                    }
                      if(bollposX>670){
                        bollXdir=-bollXdir;
                    }
                }
                repaint();
	}
	
        @Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(playerX>=600){
				playerX=600;
			}
			else{
				moveRight();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(playerX<10){
				playerX=10;
			}
			else{
				moveLeft();
			}
		}
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(!play){
                        play=true;
                        bollposX=120;
                        bollposY=350;
                        bollYdir=-2;
                        bollXdir=-1;
                        playerX=310;
                        score=0;
                        totalBricks=21;
                        map=new mapGenerator(3,7 );
                        
                        repaint();
                        
                    }
                }
	}

	public void moveRight(){
		play=true;
		playerX += 20;
	}

	public void moveLeft(){
		play=true;
		playerX -= 20;
	}


	 public void keyTyped(KeyEvent e){

	}
	 public void keyReleased(KeyEvent e){

	}
}