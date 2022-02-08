/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

enum book_genre {
	NON_FICTION,
	BIBLIOGRAPHY,
	COMEDY,
	HISTORICAL,
	ROMANTIC,
	MISC,
}

public class book {
	public String     _name;
	public String     _author;
	public String     _isbn;
	public book_genre _genre;
	public int        _words;
	public int        _pages;

	public book() {
		_name      = "Sample Book";
		_author    = "Alistair Bell";
		_isbn      = "1111-1111-1111";
		_genre     = book_genre.MISC;
		_words     = ~(0);
		_pages     = ~(0);
	}
	public book(final String __name, final String __author, final String __isbn, final book_genre __genre, final int __words, final int __pages) {
		_name      = __name;
		_author    = __author;
		_isbn      = __isbn;
		_genre     = __genre;
		_words     = __words;
		_pages     = __pages;
	}
	public book(final String __src) {
		String[] split = __src.split("_");
		_name      = split[0];
		_author    = split[1];
		_isbn      = split[2];
		_genre     = book_genre.valueOf(split[3].toUpperCase());
		_words     = Integer.valueOf(split[4]);
		_pages     = Integer.valueOf(split[5]);
	}
	@Override
	public String toString() {
		return String.format("%s_%s_%s_%s_%d_%d", _name, _author, _isbn, _genre.toString(), _words, _pages);
	}
	@Override
	public int hashCode() {
		/* This toggles the most significent bit, as the filename uses the hashcode, some OS's may freak out about a starting '-'. */
		int mask = ~(1 << 31);
		return (_words + _pages + _name.charAt(0) + _author.hashCode() + _genre.ordinal() + _isbn.hashCode()) & mask;
	}
}
final class book_functions implements database_function {
	public Object from_string(final String __src) {
		return new book(__src);
	}
}
