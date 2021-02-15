import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FunctionManager{
	FrameManager FrameManager = new FrameManager();
	int dungeonLevel;
	int ropeCuts = 0;
	int timesTickled = 0;
	ArrayList<String> currentObjects;
	ArrayList<String> inventory;
	boolean potSmashed = false;
	boolean ropeCut = false;
	boolean guardTickled = false;
	public boolean escapeAccomplished = false;
	boolean lock1Undone = false;
	boolean lock2Undone = false;
	boolean lock3Undone = false;

	FunctionManager() {
		dungeonLevel = 0;
		currentObjects = new ArrayList<String>();
		inventory = new ArrayList<String>();
	}
void update() {
	manageLevelObjects();
}
//Objects
	void manageLevelObjects() {
		if (dungeonLevel == 0) {
			currentObjects.add("Hammer");
			currentObjects.add("Pot");
			currentObjects.add("FirstLock");
			
			if(potSmashed == true) {
				currentObjects.remove("Pot");
				JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
				inventory.add("Key1");
			}
			if(lock1Undone == true) {
				currentObjects.remove("FirstLock");
			}
		}
		if (dungeonLevel == 1) {
			if(currentObjects.contains("Hammer")) {
				currentObjects.remove("Hammer");
			}
			currentObjects.add("Knife");
			currentObjects.add("Rope");
			currentObjects.add("SecondLock");
			if(ropeCut == true) {
				currentObjects.remove("Rope");
				JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
				inventory.add("Key2");
			}
			if(lock2Undone == true) {
				currentObjects.remove("SecondLock");
			}
		}
		if (dungeonLevel == 2) {
			currentObjects.add("Stick");
			currentObjects.add("Nail");
			currentObjects.add("Feather");
			currentObjects.add("Guard");
			if(guardTickled == true) {
				JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
				inventory.add("Key3");
			}
		}
	}

//Functions
	public void methodCheck() {
		if (input.equalsIgnoreCase("Look Around")) {
			for(int i = 0; i < currentObjects.size(); i++) {
				JOptionPane.showMessageDialog(null, "There is a " + currentObjects.get(i) + ".");
			}
		}
		if (input.equalsIgnoreCase("Take")) {
			if (dungeonLevel == 0) {
				if(currentObjects.contains("Hammer")) {
					currentObjects.remove("Hammer");
					inventory.add("Hammer");
					JOptionPane.showMessageDialog(null, "Took a Hammer!");
				}
				else {
					JOptionPane.showMessageDialog(null, "There's nothing else to take.");
				}
				}
			if(dungeonLevel == 1) {
				if(currentObjects.contains("Knife")) {
					currentObjects.remove("Knife");
					inventory.add("Knife");
					JOptionPane.showMessageDialog(null, "Took a Knife!");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "There's nothing else to take.");
			}
			if(dungeonLevel == 2) {
			if(currentObjects.contains("Stick")) {
				currentObjects.remove("Stick");
				inventory.add("Stick");
				JOptionPane.showMessageDialog(null, "Took a Stick!");
			}
			if(currentObjects.contains("Nail")) {
				currentObjects.remove("Nail");
				inventory.add("Nail");
				JOptionPane.showMessageDialog(null, "Took a Nail!");
			}
			if(currentObjects.contains("Feather")) {
				currentObjects.remove("Feather");
				inventory.add("Feather");
				JOptionPane.showMessageDialog(null, "Took a Feather!");
			}
			else {
				JOptionPane.showMessageDialog(null, "There's nothing else to take.");
			}
			}
			}
		
	
		if (input.equalsIgnoreCase("Use")) {
if(dungeonLevel == 0) {
	if(inventory.contains("Hammer")) {
		JOptionPane.showMessageDialog(null, "You broke the pot with your Hammer.");
		potSmashed = true;
	}
	if(inventory.contains("Key1")) {
		JOptionPane.showMessageDialog(null, "You take your key and unlock the first Lock!");
		lock1Undone = true;
	}
}
if(dungeonLevel == 1) {
	if(inventory.contains("Knife")) {
		if(ropeCuts == 0) {
		JOptionPane.showMessageDialog(null, "You cut the rope, but you need to cut it more.");
		if(ropeCuts == 1) {
			JOptionPane.showMessageDialog(null, "You cut the rope, and a key drops from the ceiling!");
			ropeCut = true;
		}
	}
	if(inventory.contains("Key2")) {
		JOptionPane.showMessageDialog(null, "You take your key and unlock the second Lock!");
		lock2Undone = true;
	}
}
		}
if(dungeonLevel == 2) {
	if(inventory.contains("Hammer")) {
		if(inventory.contains("Stick")) {
			if(inventory.contains("Nail")) {
				if(inventory.contains("Feather")) {
					JOptionPane.showMessageDialog(null, "You constructed the Tickle Device!");
					inventory.add("TickleDevice");
				}
				else {
					JOptionPane.showMessageDialog(null, "You don't have all the parts.");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "You don't have all the parts.");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "You don't have all the parts.");
		}
	}
	else {
		JOptionPane.showMessageDialog(null, "You didn't take the hammer! You'll have to go all the way back...");
	dungeonLevel = 0;
	}
	if(inventory.contains("TickleDevice")) {
		if(timesTickled <= 4) {
			JOptionPane.showMessageDialog(null, "You tickle the guard, but he doesn't drop the key");
		}
		else {
			JOptionPane.showMessageDialog(null, "The guard jerks his knee, and the key goes into your cell!");
			guardTickled = true;
		}
	}
	if(inventory.contains("Key3")) {
		JOptionPane.showMessageDialog(null, "You take your key and unlock the third Lock!");
		lock3Undone = true;
		JOptionPane.showMessageDialog(null, "You see the light beyond the door in front of you...");
		JOptionPane.showMessageDialog(null, "That's the way out!");
		JOptionPane.showMessageDialog(null, "You beat the game!!!");
		JOptionPane.showMessageDialog(null, "Congratulations!!!");
		escapeAccomplished = true;
	}
}
		}
		}
	
	public void checkInventory() {
		for(int i = 0; i < inventory.size(); i++) {
			JOptionPane.showMessageDialog(null, "You have a " + inventory.get(i) + ".");
		}
	}
}

