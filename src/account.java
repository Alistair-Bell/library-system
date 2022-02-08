/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

final class account {
	public String _name;
	public String _email;
	public String _id;
	/* Unix epoch. */
	public long   _age;
	public int    _perms;

	public account() {
		_name   = "Dummy";
		_email  = "dummy@bookstore.org";
		_id     = "DM-0000-0000";
		_perms  = 0;
	}
	public account(final String __src) {
	}
}
final class account_functions implements database_function {
	public Object from_string(String __raw) {
		return new account();
	}
}
