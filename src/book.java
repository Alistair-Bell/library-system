/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;


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
	public book(String __name, String __author, String __isbn, long __pages, long __words, book_genre __genre) {
		_name      = __name;
		_author    = __author;
		_isbn      = __isbn;
		_pages     = __pages;
		_words     = __words;
		_genre     = __genre;
		_id        = book.generate_id(this);
	}
	public static long generate_id(final book __in) {
		final long segments = 0x6b05eded9ac0e764l;
		long accumilator = __in._name.hashCode() + __in._author.hashCode() + ((int)(__in._words / __in._pages));
		return (accumilator & segments) + __in._genre.ordinal();
	}
}

