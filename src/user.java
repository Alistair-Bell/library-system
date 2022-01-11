/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;

import java.util.ArrayList;
import java.util.Scanner;

final class user_perms {
	/* Dictates the functionality accounts can have. */
	static final int bit_loan          = 1 << 0;
	static final int bit_renew         = 1 << 1;
	static final int bit_modify_book   = 1 << 2;
	static final int bit_insert_book   = 1 << 3;
	static final int bit_remove_book   = 1 << 4;
	static final int bit_insert_user   = 1 << 5;
	static final int bit_modify_user   = 1 << 6;
	static final int bit_remove_user   = 1 << 7;

	/* Predefined bit sets for user accounts. */
	static final int account_default   = (bit_loan | bit_renew);
	static final int account_librarian = (bit_modify_book | bit_insert_book | bit_remove_book);
	static final int account_root      = ~(0);
};

public final class user {
	public String          _name_first;
	public String          _name_last;
	public String          _email;
	public ArrayList<Long> _loaned;
	public int             _perms;

	/* Creates a dummy user. */
	public user() {
		_name_first  = "root";
		_name_last   = "root";
		_email       = "root@librarysystem.org";
		_loaned      = null;
		_perms       = user_perms.account_root;
	}
	public user(String __name_first, String __name_last, String __email, int __perms) {
		_name_first  = __name_first;
		_name_last   = __name_last;
		_email       = __email;
		_perms       = __perms;
	}
	public user(Scanner __s) {
		System.out.println("What is their first name?");
		_name_first = __s.nextLine();
		System.out.println("What is their last name?");
		_name_last  = __s.nextLine();
		System.out.println("What is their email?");
		_email      = __s.nextLine();
		_perms      = user_perms.account_default;
	}
}
