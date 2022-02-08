/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

import java.util.Scanner;

enum menu_option {
	UNKNOWN,
	LIST,
	ADD,
	REMOVE,
	EDIT,
	EXIT,
}

public final class menu {
	private Scanner _scanner;

	public menu() {
		_scanner = new Scanner(System.in);
	}

	public menu_option get_option() {
		System.out.println("What do you want todo?\n(1)List\n(2)Add\n(3)Remove\n(4)Edit\n(5)Exit");
		try {
			int opt = Integer.valueOf(_scanner.nextLine());
			return menu_option.values()[opt];
		} catch (Exception e) {
			System.out.printf("Unable to get menu option, exception thrown, %s.\n", e.toString());
			return menu_option.UNKNOWN;
		}
	}
}
