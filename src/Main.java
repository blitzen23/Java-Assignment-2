import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.annotation.processing.SupportedSourceVersion;

public class Main {

	Scanner in = new Scanner(System.in);
	int choice, shareNum, update, delete;
	String username;
	ArrayList<Participant> p = new ArrayList<>();
	public Main() {
		while (true) {
			menu();
			if (choice == 1) {
				start();
			}
			else if (choice == 2) {
				update();
			}
			else if (choice == 3) {
				delete();
			}
			else {
				close();
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}
	
	public void menu() {
		System.out.println("+======================+");
		System.out.println("+Options               +");
		System.out.println("+======================+");
		System.out.println("+1. Start Sharing      +");
		System.out.println("+2. Update Participant +");
		System.out.println("+3. Delete Participant +");
		System.out.println("+4. Close App          +");
		System.out.println("+======================+");
		while (true) {
			try {
				System.out.print("Choice >> ");
				choice = in.nextInt();
				in.nextLine();
				if (choice >= 1 && choice <= 4) {
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Input must be a number");
				in.nextLine();
			}
		}
	}
	
	public void start() {
		while (true) {
			try {
				System.out.print("Input a number [0 - 100]: ");
				shareNum = in.nextInt();
				in.nextLine();
				if (shareNum >= 0 && shareNum <= 100) {
					break;
				}
			}
			catch (Exception e) {
				System.out.println("Input must be numeric");
				in.nextLine();
			}
		}
		while (true) {
			boolean unique = false;
			System.out.print("Could you give me your username [5 - 20 characters]? ");
			username = in.nextLine();
			for (Participant participant: p) {
				if (username.equals(participant.username)) {
					unique = true;
				}
			}
			if (username.length() >= 5 && username.length() <= 20 && unique == false) {
				break;
			}
			else if (username.length() < 5 || username.length() > 20) {
				System.out.println("Username must be between 5 and 20 characters");
			}
			else {
				System.out.println("Username has already been taken");
			}
		}
		p.add(new Participant(username, shareNum));
		System.out.println("You are the " + p.size() + " that joins the game");
		System.out.println("Your share number : " + shareNum);
	}
	
	public void update() {
		if (p.size() == 0) {
			return;
		}
		else {
			while (true) {
				try {
					System.out.println("+========================================+");
					System.out.println("+ Share List                             +");
					System.out.println("+========================================+");
					for (int i = 0; i < p.size(); i++) {
						System.out.printf("| %-3d | %-20s | %-9d |\n", i + 1, p.get(i).username, p.get(i).shareNum);
					}
					System.out.println("+========================================+");
					System.out.printf("Which participant you would like to update [%d - %d][0 to exit]?", 1, p.size());
					update = in.nextInt();
					in.nextLine();
					if ((update >= 1 && update <= p.size()) || update == 0) {
						break;
					}
				}
				catch (Exception e) {
					System.out.println("Input must be numeric");
					in.nextLine();
				}
			}
			while (true) {
				try {
					System.out.print("Input a number [0 - 100]: ");
					shareNum = in.nextInt();
					in.nextLine();
					if (shareNum >= 0 && shareNum <= 100) {
						break;
					}
				}
				catch (Exception e) {
					System.out.println("Input must be numeric");
					in.nextLine();
				}
			}
			p.get(update - 1).shareNum = shareNum;
			System.out.println("Update successful!");
			in.nextLine();
		}
	}
	
	public void delete() {
		if (p.size() == 0) {
			return;
		}
		else {
			while (true) {
				try {
					System.out.println("+========================================+");
					System.out.println("+ Share List                             +");
					System.out.println("+========================================+");
					for (int i = 0; i < p.size(); i++) {
						System.out.printf("| %-3d | %-20s | %-9d |\n", i + 1, p.get(i).username, p.get(i).shareNum);
					}
					System.out.println("+========================================+");
					System.out.printf("Which participant you would like to delete [%d - %d][0 to exit]?", 1, p.size());
					delete = in.nextInt();
					in.nextLine();
					if ((delete >= 1 && delete <= p.size()) || delete == 0) {
						break;
					}
				}
				catch (Exception e) {
					System.out.println("Input must be numeric");
					in.nextLine();
				}
			}
			p.remove(delete - 1);
			System.out.println("Participant succesfully removed from event");
			in.nextLine();
		}
	}
	
	public void close() {
		if (p.size() == 0) {
			return;
		}
		System.out.println("Here's the completed share list");
		System.out.println("Remember, sharing is caring, happy sharing :D");
		System.out.println("+========================================+");
		System.out.println("+ Share List                             +");
		System.out.println("+========================================+");
		System.out.printf("+ %-20s | Before | After  +\n", "Username");
		Collections.sort(p, Participant.usernameComparator);
		ArrayList<Integer> save = new ArrayList<>();
		for (Participant participant : p) {
			save.add(participant.shareNum);
		}
		Collections.shuffle(save);
		Collections.reverse(save);;
		for (int i = 0; i < p.size(); i++) {
			System.out.printf("| %-20s | %-6d | %-6d |\n", p.get(i).username, p.get(i).shareNum, save.get(i));
		}
		System.out.println("+========================================+");
	}

}
