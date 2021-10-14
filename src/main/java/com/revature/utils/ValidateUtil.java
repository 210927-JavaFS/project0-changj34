package com.revature.utils;

import java.util.Scanner;

public class ValidateUtil {
	
	public int stringToInt(Scanner scan) {
		boolean loop = true;
		int id = 0;
		String response;
		while (loop) {
			try {
				response = scan.nextLine();
				id = Integer.parseInt(response);
				if (id <= 0) {
					System.out.println("Invalid input. Try again.");
					continue;
				}
				loop = false;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
			}
		}
		return id;
	}
}
