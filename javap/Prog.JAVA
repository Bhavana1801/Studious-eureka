import javax.swing.*;
public class Prog {
	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("What is your name?:");
		String input = JOptionDialog("How old are you!");
		int age = Integer.parseInt(input);
		System.out.println("Hello"+name+".");
		System.out.println("Next year you will be"+(age+1));
		System.exit(0);
	}
}