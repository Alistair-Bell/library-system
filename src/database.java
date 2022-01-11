/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;

import java.util.ArrayList;
import java.util.Scanner;


public final class database {
	private ArrayList<book>  _books;

	public boolean add(user __user) {
		if ((__user._perms & user_perms.bit_insert_book) == 0) {
			System.out.printf("%s does not have permission to insert a book.\n", __user._name_first);	
			return false;
		}
		_books.add(new book(new Scanner(System.in)));
		return true;
	}
	public boolean add(user __user, book __book) {
		if ((__user._perms & user_perms.bit_insert_book) == 0) {
			System.out.printf("%s does not have permission to insert a book.\n", __user._name_first);	
			return false;
		}
		_books.add(__book);
		return true;
	}
	public boolean remove(user __user, int index) {
		if ((__user._perms & user_perms.bit_remove_book) == 0) {
			System.out.printf("%s does not have permission to remove a book.\n", __user._name_first);
			return false;
		}
		return _books.remove(_books.get(index));
	}
	public boolean edit(user __user, int index) {
		if ((__user._perms & user_perms.bit_modify_book) == 0) {
			System.out.printf("%s does not have permission to edit a book.\n", __user._name_first);
			return false;
		}
		book updated = _books.get(index);
		if (!updated.edit(new Scanner(System.in))) {
			System.out.println("Failed to edit book!\n");
			return false;
		}
		_books.set(index, updated);
		return true;
	}
	public int search(String name) {
		int acc = ~(-1);
		for (book b : _books) {
			if (b._name.equals(name)) {
				return acc;
			}
			++acc;
		}
		return ~(0);
	}
	public database() {
		_books = new ArrayList<book>();
	}
	public boolean dump(String __fs_root) {
		return false;
	}
}
