import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FunctionManager {
	String useInput;
	int dungeonLevel;
	int ropeCuts = 0;
	int timesTickled = 0;
	ArrayList<String> currentObjects;
	ArrayList<String> inventory;
	boolean potSmashed = false;
	boolean ropeCut = false;
	boolean toolMade = false;
	boolean guardTickled = false;
	public boolean escapeAccomplished = false;
	boolean lock1Undone = false;
	boolean lock2Undone = false;
	boolean lock3Undone = false;
	boolean storyTold = false;
	boolean level0Set = false;
	boolean level1Set = false;
	boolean level2set = false;
	boolean view1 = false;
	boolean view2 = false;
	boolean view3 = false;

	FunctionManager() {
		dungeonLevel = 0;
		currentObjects = new ArrayList<String>();
		inventory = new ArrayList<String>();
	}

	void update() {
		manageLevelObjects();
	}

//Miscellaneous
	void reset() {
		dungeonLevel = 0;
		ropeCuts = 0;
		timesTickled = 0;
		potSmashed = false;
		ropeCut = false;
		toolMade = false;
		guardTickled = false;
		escapeAccomplished = false;
		lock1Undone = false;
		lock2Undone = false;
		lock3Undone = false;
		level0Set = false;
		level1Set = false;
		level2set = false;
		view1 = false;
		view2 = false;
		view3 = false;
		currentObjects.clear();
		manageLevelObjects();
		inventory.clear();
	}

	void tellStory() {
		if (storyTold == false) {
			JOptionPane.showMessageDialog(null, "You open your eyes to see yourself in a dungeon.");
			JOptionPane.showMessageDialog(null,
					"'You are prisoner number 24602, and you have been arrested for stealing a loaf of bread and a wedge of cheese,' the guard says to you.");
			JOptionPane.showMessageDialog(null,
					"'You are being put in a high security dungeon, with three levels, each level is equipped with a puzzle to keep you not bored,' the guard continues.");
			JOptionPane.showMessageDialog(null, "'Now, goodbye,' he says, and he walks out the door.");
			JOptionPane.showMessageDialog(null, "Use the space key to review your options.");
			storyTold = true;
		} else {
			JOptionPane.showMessageDialog(null, "You know the beginning story already.");
		}
		if(escapeAccomplished == true) {
			JOptionPane.showMessageDialog(null, "You got out of jail and convinced the local authorities that you were innocent.");
			JOptionPane.showMessageDialog(null, "You never had to see that jail again.");
			JOptionPane.showMessageDialog(null, "You found a nearby village that you settled down in and you lived happily ever after.");
			DungeonEscape.FM2.currentState = DungeonEscape.FM2.END;
			
		}
	}

//Objects
	void manageLevelObjects() {
		if (dungeonLevel == 0) {
			if (level0Set != true) {
				currentObjects.add("Hammer");
				currentObjects.add("Pot");
				currentObjects.add("FirstLock");
				level0Set = true;
			}
		}
		if (potSmashed == true) {
			if (!inventory.contains("Key1")) {
				currentObjects.remove("Pot");
				JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
				inventory.add("Key1");
			}
		}
		if (lock1Undone == true) {
			if (currentObjects.contains("FirstLock")) {
				currentObjects.remove("FirstLock");
			}
		}
		if (dungeonLevel == 1) {
			if (level1Set != true) {
				if (currentObjects.contains("Hammer")) {
					currentObjects.remove("Hammer");
				}
				currentObjects.add("Knife");
				if (currentObjects.contains("Knife")) {
					System.out.println("objectMade");
				}
				currentObjects.add("Rope");
				currentObjects.add("SecondLock");
				level1Set = true;
			}
			if (ropeCut == true) {
				if (!inventory.contains("Key2")) {
					currentObjects.remove("Rope");
					JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
					inventory.add("Key2");
				}
			}
			if (lock2Undone == true) {
				if (currentObjects.contains("SecondLock")) {
					currentObjects.remove("SecondLock");
				}
			}
		}
		if (dungeonLevel == 2) {
			if(currentObjects.contains("Knife")) {
				currentObjects.remove("Knife");
			}
			if (level2set != true) {
				currentObjects.add("Stick");
				currentObjects.add("Nail");
				currentObjects.add("Feather");
				currentObjects.add("Guard");
				level2set = true;
			}
			if (guardTickled == true) {
				if (!inventory.contains("Key3")) {
				JOptionPane.showMessageDialog(null, "You found the key and put it it your inventory.");
					inventory.add("Key3");
				}
			}
		}
	}

//Functions
	public void methodCheck() {
		if (DungeonEscape.FM2.input.equalsIgnoreCase("Look Around")) {
			System.out.println(currentObjects.size());
			for (int i = 0; i < currentObjects.size(); i++) {
				JOptionPane.showMessageDialog(null, "There is a " + currentObjects.get(i) + ".");
				if (dungeonLevel == 0) {
					view1 = true;
				}
				if (dungeonLevel == 1) {
					view2 = true;
				}
				if (dungeonLevel == 2) {
					view3 = true;
				}
			}
			DungeonEscape.FM2.input = "";
		}
		if (DungeonEscape.FM2.input.equalsIgnoreCase("Take")) {
			String takeInput = JOptionPane.showInputDialog("Take what?");
			if (dungeonLevel == 0) {
				if (view1 == true) {
					if (takeInput.equalsIgnoreCase("Hammer")) {
						if (currentObjects.contains("Hammer")) {
							currentObjects.remove("Hammer");
							inventory.add("Hammer");
							JOptionPane.showMessageDialog(null, "Took a Hammer!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "There's nothing else to take.");
				}
			}
			if (dungeonLevel == 1) {
				if (view2 == true) {
					if (takeInput.equalsIgnoreCase("Knife")) {
						if (currentObjects.contains("Knife")) {
							currentObjects.remove("Knife");
							inventory.add("Knife");
							JOptionPane.showMessageDialog(null, "Took a Knife!");
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "There's nothing else to take.");
			}
			if (dungeonLevel == 2) {
				if (view3 == true) {
					if (takeInput.equalsIgnoreCase("Stick")) {
						if (currentObjects.contains("Stick")) {
							currentObjects.remove("Stick");
							inventory.add("Stick");
							JOptionPane.showMessageDialog(null, "Took a Stick!");
						}
					}
					if (takeInput.equalsIgnoreCase("Nail")) {
						if (currentObjects.contains("Nail")) {
							currentObjects.remove("Nail");
							inventory.add("Nail");
							JOptionPane.showMessageDialog(null, "Took a Nail!");
						}
					}
					if (takeInput.equalsIgnoreCase("Feather")) {
						if (currentObjects.contains("Feather")) {
							currentObjects.remove("Feather");
							inventory.add("Feather");
							JOptionPane.showMessageDialog(null, "Took a Feather!");
						}
					}
				}
			}
		}

		if (DungeonEscape.FM2.input.equalsIgnoreCase("Use")) {
			useInput = JOptionPane.showInputDialog("Use what? (Hint: Check Inventory)");
			if (dungeonLevel == 0) {
				if (useInput.equalsIgnoreCase("Hammer")) {
					if (inventory.contains("Hammer")) {
						if (potSmashed == false) {
							JOptionPane.showMessageDialog(null, "You broke the pot with your Hammer.");
							potSmashed = true;
						}
					}
				}
				if (useInput.equalsIgnoreCase("Key1")) {
					if (inventory.contains("Key1")) {
						JOptionPane.showMessageDialog(null, "You take your key and unlock the first Lock!");
						lock1Undone = true;
						inventory.remove("Key1");
						dungeonLevel = 1;
						JOptionPane.showMessageDialog(null, "You moved to the next dungeon!");
					}
				}
			}
			if (dungeonLevel == 1) {
				if (useInput.equalsIgnoreCase("Knife")) {
					if (inventory.contains("Knife")) {
						if (ropeCuts < 3) {
							JOptionPane.showMessageDialog(null, "You cut the rope, but not enough.");
							ropeCuts++;
						}
						if (ropeCuts >= 3) {
							JOptionPane.showMessageDialog(null, "You cut the rope, and a key drops from the ceiling!");
							ropeCut = true;
						}
					}
				}
				if (useInput.equalsIgnoreCase("Key2")) {
					if (inventory.contains("Key2")) {
						JOptionPane.showMessageDialog(null, "You take your key and unlock the second Lock!");
						lock2Undone = true;
						inventory.remove("Key2");
						dungeonLevel = 2;
						JOptionPane.showMessageDialog(null, "You moved to the next dungeon!");
					}
				}
			}
		}
		if (dungeonLevel == 2) {
			if (toolMade == false) {
				if (useInput.equalsIgnoreCase("Hammer")) {
					if (inventory.contains("Hammer")) {
						if (inventory.contains("Stick")) {
							if (inventory.contains("Nail")) {
								if (inventory.contains("Feather")) {
									JOptionPane.showMessageDialog(null, "You constructed the Tickler!");
									inventory.add("TickleDevice");
									toolMade = true;
								} else {
									JOptionPane.showMessageDialog(null, "You don't have all the parts.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "You don't have all the parts.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "You don't have all the parts.");
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"You didn't take the hammer! You'll have to go all the way back...");
						dungeonLevel = 0;
					}
				}
			}
				if (useInput.equalsIgnoreCase("Tickler")) {
					if(timesTickled < 4) {
					JOptionPane.showMessageDialog(null, "You tickle the guard, but he doesn't drop the key.");
					timesTickled++;
					}
					if (timesTickled >= 4) {
						JOptionPane.showMessageDialog(null,
								"The guard jerks his knee, and the key goes into your cell!");
						guardTickled = true;
					}
				}
			
			if (useInput.equalsIgnoreCase("Key3")) {
				if (inventory.contains("Key3")) {
					JOptionPane.showMessageDialog(null, "You take your key and unlock the third Lock!");
					inventory.remove("Key3");
					lock3Undone = true;
					JOptionPane.showMessageDialog(null, "You see the light beyond the door in front of you...");
					JOptionPane.showMessageDialog(null, "That's the way out!");
					JOptionPane.showMessageDialog(null, "You beat the game!!!");
					JOptionPane.showMessageDialog(null, "Congratulations!!!");
					escapeAccomplished = true;
				tellStory();
				}

			}
		}
	}
	public void checkInventory() {
		if (inventory.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing is in your inventory.");
		} else {
			for (int i = 0; i < inventory.size(); i++) {
				JOptionPane.showMessageDialog(null, "You have a " + inventory.get(i) + ".");
			}
		}
	}

	public void adminSkip() {
		inventory.add("Hammer");
		if (dungeonLevel == 0) {
			potSmashed = true;
			lock1Undone = true;
			dungeonLevel++;
		}
		if (dungeonLevel == 1) {
			ropeCut = true;
			lock2Undone = true;
			dungeonLevel++;
		}
	}
}
