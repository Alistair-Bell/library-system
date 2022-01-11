/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;

import java.util.Scanner;

/* All books must have a genre. */
enum book_genre {
	COMEDY,
	BIBLIOGRAPHY,
	ROMANTIC,
	ADVENTURE,
	FICTION,
	HISTORICAL,
	NON_FICTION,
}
public final class book {
	public String     _name;
	public String     _author;
	public String     _isbn;
	public long       _id;
	public long       _pages;
	public long       _words;
	public book_genre _genre;

	/* Create a dummy book, this can be used for testing. */
	public book() {
		_name      = "Dummy";
		_author    = "Alistair Bell";
		_isbn      = "null";
		_words     = 160321;
		_pages     = (_words / 200) + 1;
		_genre     = book_genre.COMEDY;
		/* Do this field last as the generator uses other fields to create an unique id. */
		_id        = book.generate_id(this);
	}
	public book(Scanner __s) {
		System.out.println("What should the name of the book be?");
		_name      = __s.nextLine();
		System.out.println("Who wrote the book?");
		_author    = __s.nextLine();
		System.out.println("What is the ISBN?");
		_isbn      = __s.nextLine();
		System.out.println("How many pages?");
		_pages     = __s.nextInt();
		System.out.println("How many words?");
		_words     = __s.nextInt();
		System.out.println("What genre is it?\nCOMEDY\nBIBLIOGRAPHY\nROMANTIC\nADVENTURE\nFICTION\nHISTORICAL\nNON_FICTION");
		_genre    = book_genre.valueOf(__s.next());
		_id       = book.generate_id(this);
	}
	public book(String __name, String __author, String __isbn, long __pages, long __words, book_genre __genre) {
		_name      = __name;
		_author    = __author;
		_isbn      = __isbn;
		_pages     = __pages;
		_words     = __words;
		_genre     = __genre;
		_id        = book.generate_id(this);
	}
	public boolean edit(Scanner __s) {
		System.out.println("What property to modify?");
		String field = __s.nextLine().toLowerCase();
		if (field.equals("name")) {
			System.out.println("What is the new value?");	
			_name = __s.nextLine();
		} else if (field.equals("author")) {
			System.out.println("What is the new value?");	
			_author = __s.nextLine();
		} else if (field.equals("genre")) {
			System.out.println("What is the new value?");	
			_genre = book_genre.valueOf(__s.next());
		} else if (field.equals("isbn")) {
			System.out.println("What is the new value?");	
			_isbn = __s.nextLine();
		} else {
			return false;
		}
		return true;
	}
	public static long generate_id(final book __in) {
		long accumilator = __in._name.hashCode() + __in._author.hashCode() + ((int)(__in._words / __in._pages));
		accumilator += __in._genre.ordinal();
		/* Manually fix sign. */
		accumilator &= ~(1 << 63);
		return accumilator;
	}
}

