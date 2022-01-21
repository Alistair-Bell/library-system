/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;


public class main {
	public static void main(String[] __argv) {
		database<book> db = new database<book>(1, 1, 1, "res/books");

		book[] books = { 
			new book("Test1", "Alistair", "11111", book_genre.HISTORICAL, 100000, 100),
			new book("Test1", "Steve", "3123123", book_genre.COMEDY, 23, 1000),
		};
		db.insert(-1, books[0]);
		db.insert(-1, books[1]);
		db.dump();
	}
}

