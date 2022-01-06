/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;

public class main {
	public static void main(String[] __argv) {
		book b1 = new book();
		book b2 = new book("Using GCC", "Richard Stallman", "null", 120l, (long)(120 * 200 + 3), book_genre.NON_FICTION);
		System.out.printf("[%d %d]\n", b1._id, b2._id);
		return;
	}
}
