import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FrameManager extends JPanel implements ActionListener, KeyListener {
	Font titleFont;
	Font smallFont;
	Timer frameDraw;
	public static String input;
	FunctionManager FM = new FunctionManager();

	FrameManager() {
		titleFont = new Font("Times New Roman", Font.BOLD, 50);
		smallFont = new Font("Times New Roman", Font.BOLD, 20);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		}
		if (currentState == GAME) {
			drawGameState(g);
		}
		if (currentState == END) {
			drawEndState(g);
		}
	}

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;

	void updateMenuState() {

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
		g.fillRect(0, 0, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString("Level : " + FM.dungeonLevel + "", 15, 50);
	if(FM.lock1Undone != true) {
		g.setColor(Color.lightGray);
	g.fillRect(300, 375, 50, 50);
	g.setColor(Color.BLACK);
	g.fillRect(320, 385, 12, 30);
	}
	if(FM.lock2Undone != true) {
	g.setColor(Color.darkGray);
	g.fillRect(400, 375, 50, 50);
	g.setColor(Color.BLACK);
	g.fillRect(420, 385, 12, 30);
	}
	if(FM.lock3Undone != true) {
	g.setColor(Color.yellow);
	g.fillRect(500, 375, 50, 50);
	g.setColor(Color.BLACK);
	g.fillRect(525, 385, 12, 30);
	}
	g.setColor(Color.DARK_GRAY);
	g.fillRect(715, 645, 240, 40);
	g.setColor(Color.lightGray);
	g.fillRect(735, 650, 200, 30);
	g.setColor(Color.BLACK);
	g.setFont(smallFont);
	g.drawString("Space to review", 750, 675);
	}

	void drawEndState(Graphics g) {
		if (FM.escapeAccomplished == true) {
			g.setFont(titleFont);
			g.setColor(Color.BLUE);
			g.drawString("You Win!", 15, 50);
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
		} else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, DungeonEscape.WIDTH, DungeonEscape.HEIGHT);
			g.setFont(titleFont);
			g.setColor(Color.ORANGE);
			g.drawString("You Lose...", 15, 50);
			g.setFont(smallFont);
			g.drawString("Your got to level " + FM.dungeonLevel + ".", 15, 250);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == END) {
				currentState = MENU;
			} else if (currentState == MENU){
				currentState++;
				FM.reset();
				FM.tellStory();
			}
			else if (currentState == GAME) {
				currentState++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				JOptionPane.showMessageDialog(null, "Press Enter to Start Dungeon Escape.");
				JOptionPane.showMessageDialog(null, "Press Space while in the game to review the commands.");
				JOptionPane.showMessageDialog(null, "Press the I key to look at your inventory.");
				JOptionPane.showMessageDialog(null, "Press the E key to use a command.");
			} else if (currentState == GAME) {
				JOptionPane.showMessageDialog(null, "Press E to enter a command.");
				JOptionPane.showMessageDialog(null, "You can use the 'use' command to use any useful object.");
				JOptionPane.showMessageDialog(null, "You can use the 'look around' command to see what could possibly be helpful.");
				JOptionPane.showMessageDialog(null, "You can use the 'take' command to automatically take all the useful objects in the room.");
				JOptionPane.showMessageDialog(null, "Press ENTER to exit the game.");
			FM.manageLevelObjects();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_E) {
			if (currentState == GAME) {
				input = JOptionPane.showInputDialog("Type a command. (Use, look around, or take)");
				FM.methodCheck();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_I) {
			if (currentState == GAME) {
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
		repaint();
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
	}
}
