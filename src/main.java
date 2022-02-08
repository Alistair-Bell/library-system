/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

public class main {
	private static menu m = new menu();

	public static void main(String[] __argv) {
		database<book> db = new database<book>(1, 1, 1, "res/books");
		db.load(new book_functions());
		switch (m.get_option()) {
			case UNKNOWN: {
				return;
			}
			case LIST: {
				db.list();
				return;
			}
		}
	}
}

