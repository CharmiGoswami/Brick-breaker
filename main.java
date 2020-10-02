
import javax.swing.JFrame;
import java.awt.*;

/**
 *
 * @author Charmi Goswami
 */

/**
 * main class
 */
public class main {

    public static void main(String[] args) {
        // TODO code application logic here
        playGame playgame=new playGame();
		JFrame obj=new JFrame();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Breakout Ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                obj.add(playgame);
    }
    
}
