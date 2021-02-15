import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FrameManager extends JPanel
implements ActionListener, KeyListener{
Font titleFont;
Font smallFont;
Timer frameDraw;
public static String input;
FunctionManager FM = new FunctionManager();
FrameManager(){
	titleFont = new Font("Times New Roman", Font.BOLD, 50);
	smallFont = new Font("Times New Roman", Font.BOLD, 20);
}
	

final int MENU = 0;
final int GAME = 1;
final int END = 2;
int currentState = MENU;

void updateMenuState(){
	
}
void updateGameState() {
	FM.update();
}
void updateEndState() {
	
}
void drawMenuState(Graphics g) {
	g.setColor(Color.DARK_GRAY);
	g.fillRect(0, 0, DungeonEscape.HEIGHT, DungeonEscape.WIDTH);
	g.setFont(titleFont);
	g.setColor(Color.RED);
	g.drawString("Dungeon Escape", 15, 50);	
	g.setFont(smallFont);
	g.drawString("Press Enter to Start", 15, 250);
	g.drawString("Press Space to see the instructions", 15, 350);
}
void drawGameState(Graphics g) {
	g.setColor(Color.ORANGE);
	g.fillRect(10, 490, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
}
void drawEndState(Graphics g) {
	if(FM.escapeAccomplished == true) {
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.drawString("You Win!", 15, 50);	
	g.setColor(Color.GREEN);
	g.fillRect(0, 0, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
	}
	else {
		g.setFont(titleFont);
		g.setColor(Color.ORANGE);
		g.drawString("You Lose...", 15, 50);	
		g.setColor(Color.RED);
		g.fillRect(0, 0, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
	}
}


		@Override
	public void paintComponent(Graphics g){
			if(currentState == MENU) {
				drawMenuState(g);
			}
			if(currentState == GAME) {
				drawMenuState(g);
			}
			if(currentState == END) {
				drawMenuState(g);
			}
	}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			    if (currentState == END) {
			        currentState = MENU;
			    } else {
			        currentState++;
			    }
			}
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			    if (currentState == MENU) {
			       JOptionPane.showMessageDialog(null, "Press Enter to Start Dungeon Escape.");
			       JOptionPane.showMessageDialog(null, "Press Space while in the game to review the commands.");
			       JOptionPane.showMessageDialog(null, "Press the I key to look at your inventory.");
			       JOptionPane.showMessageDialog(null, "Press the E key to use a command.");
			    } else if(currentState == GAME){
			    	  JOptionPane.showMessageDialog(null, "Press E to enter a command.");
			    	  JOptionPane.showMessageDialog(null, "You can use the 'use' command to use any useful object.");
			    	  JOptionPane.showMessageDialog(null, "You can use the 'look around' command to see what could possibly be helpful.");
			    	  JOptionPane.showMessageDialog(null, "You can use the 'take' command to take all the useful objects in the room.");
			    	  JOptionPane.showMessageDialog(null, "Press ENTER to exit the game.");
			    }
			}   
			if (e.getKeyCode()==KeyEvent.VK_E) {
			    if (currentState == GAME) {
			       input = JOptionPane.showInputDialog("Type a command. (Use, look around, examine, take");
			       FM.methodCheck();
			    }
			} 
			if (e.getKeyCode()==KeyEvent.VK_I) {
				if(currentState == GAME) {
				FM.checkInventory();
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}