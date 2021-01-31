package com.ajira.network;

import java.util.Scanner;

import com.ajira.exceptions.AbstractCommandException;
import com.ajira.exceptions.InvalidCommandSyntaxException;
import com.ajira.node.DeviceType;
/**
 * 
 * @author yatharth.govil
 *
 */
public class NetworkRunner {

	/**
	 * implements the console for application
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Create a Scanner object

		String str;
		String coms[];
		Network network = new Network();
		try {
			while (true) {

				try {
					str = scan.nextLine();
					coms = str.split(" ");
					switch (coms[0]) {
					case "Add":
						if (coms.length < 3)
							throw new InvalidCommandSyntaxException("Invalid command syntax");
						if (coms[1].equalsIgnoreCase("COMPUTER")) {
							if (network.add(coms[2], DeviceType.COMPUTER) == 1)
								System.out.println("Successfully added " + coms[2]);
						} else if (coms[1].equalsIgnoreCase("REPEATER")) {
							if (network.add(coms[2], DeviceType.REPEATER) == 1)
								System.out.println("Successfully added " + coms[2]);
						} else {
							throw new InvalidCommandSyntaxException("Invlaid command sytax");
						}
						break;
					case "SET_DEVICE_STRENGTH":
						if (coms.length < 3)
							System.out.println("Invalid command syntax");
						try {
							if (network.setStrength(coms[1], Integer.parseInt(coms[2])) == 1)
								System.out.println("Success");

						} catch (NumberFormatException e) {
							throw new InvalidCommandSyntaxException("Please add valid strenth");
						}
						break;
					case "CONNECT":
						if (coms.length < 3)
							System.out.println("Invalid command syntax");
						if (network.connect(coms[1], coms[2]) == 1)
							System.out.println("Succesfully connected");
						break;

					case "INFO_ROUTE":
						if (coms.length < 3)
							System.out.println("Invalid command syntax");
						network.infoRoute(coms[1], coms[2]);
						break;
					default:
						throw new InvalidCommandSyntaxException("Invalid Syntax");

					}

				} catch (AbstractCommandException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Terminated by exception--" + e.getMessage());
		} finally {
			scan.close();
		}

	}
}
