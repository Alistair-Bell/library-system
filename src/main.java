/*
 * License: read `license.txt` at the root of the project.
 */

package alistairbell.xyz;

import java.util.*;


public class main {

	public static int _errno             = 0;
	public static Scanner _scanner       = new Scanner(System.in);
	public static String[] _menu_options = { "(B)ooks", "(U)sers", "(E)xit" };
	public static String[] _book_options = { "(A)dd", "(E)dit", "(R)emove" };
	public static String[] _user_options = { "(A)dd", "(E)dit", "(R)emove" };
	public static database _db           = new database();
	public static ArrayList<user> _users = new ArrayList<user>();
	
	public static char get_char(String __msg, String[] __opt) {
		char res;
		display_options(__msg, __opt);
		try {
			res = _scanner.nextLine().charAt(0);	
			return res;
		} catch (Exception e) {
			System.out.printf("Exception thrown, %s.\n", e.toString());
			_errno = ~(0);
		}
		return (char)0;
	}
	public static int get_index(String __message) {
		System.out.println(__message);
		int res = _db.search(_scanner.nextLine());
		return res;
	}
	public static void display_options(String __msg, String[] __opt) {
		System.out.println(__msg);
		for (String s : __opt) {
			System.out.println(s);
		}
	}
	public static boolean book_option(user __user) {
		char val = Character.toLowerCase(get_char("What book options do you want to do?", _book_options));
		int index = 0;
		switch (val) {
			case 'a': {
				return _db.add(__user);
			}
			case 'e': {
				if ((index = get_index("What is the book name?")) < 0) {
					return false;
				}
				_db.edit(__user, index);
				break;
			}
			case 'r': {
				if ((index = get_index("What is the book name?")) < 0) {
					return false;
				}
				return _db.remove(__user, index);
			}
			default : {
				System.out.printf("Invalid character \'%c\'.\n", val);
			}
		}
		return false;
	}
	public static boolean user_option(user __user) {
		char val = Character.toLowerCase(get_char("What book options do you want to do?", _user_options));
		int index = 0;
		switch (val) {
			case 'a': {
				if ((__user._perms & user_perms.bit_insert_user) == 0) {
					System.out.printf("%s cannot add users.\n", __user._name_first);
					return false;
				}
				_users.add(new user(new Scanner(System.in)));
				return true;
			}
			case 'e': {
				if ((__user._perms & user_perms.bit_modify_user) == 0) {
					System.out.printf("%s cannot modify users.\n", __user._name_first);
					return false;
				}
				break;
			}
			case 'r': {
				break;
			}
			default: {
				System.out.printf("Invalid character \'%c\'.\n", val);
			}
		}
		return false;
	}
	public static void main(String[] __argv) {
		user u = new user("alistair", "bell", "alistair@alistairbell.xyz", Integer.MAX_VALUE);
		_db.add(u, new book());
		System.out.printf("Logged in as %s.\n", u._name_first);

		char val = Character.toLowerCase(get_char("Welcome to book system!", _menu_options));
		if (_errno < 0)
			return;

		switch (val) {
			case 'b': {
				book_option(u);
				return;
			}
			case 'u': {
				user_option(u);
				break;
			}
			case 'e': {
				System.out.printf("Goodbye %s.\n", u._name_first);
				return;
			}
			default: {
				System.out.printf("Invalid character \'%c\'.\n", val);
			}
		}
		return;
	}
}
