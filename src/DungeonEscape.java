import javax.swing.JFrame;

public class DungeonEscape {
	JFrame frame = new JFrame();
	VisualManager VM = new VisualManager();
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	void setup() {
		frame.add(VM);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(VM);
	}
	DungeonEscape(){
		
	}

	public static void main(String[] args) {
		DungeonEscape DE = new DungeonEscape();
		DE.setup();
	}
}
