/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

/* An database type must impliment these function pointers. */
public abstract interface database_function {
	public Object from_string(final String __src);
}
