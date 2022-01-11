/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

import java.util.ArrayList;

public class database<T> {
	private int    _perms_modify_mask;
	private int    _perms_insert_mask;
	private int    _perms_delete_mask;
	private String _dir;
	private ArrayList<T> _objects;

	database(final int __modify_mask, final int __insert_mask, final int __delete_mask, String __dir) {
		_objects           = new ArrayList<T>();
		_perms_modify_mask = __modify_mask;
		_perms_insert_mask = __insert_mask;
		_perms_delete_mask = __delete_mask;
		_dir               = __dir;
	}
	public boolean insert(final int __perms, final T __target) {
		if ((__perms & _perms_insert_mask) == 0) {
			System.out.println("Database cannot insert object, permission check failed.\n");
			return false;
		}
		/* Check if it exists. */
		if (get(__target) != -1) {
			System.out.println("Database duplicate found, aborting insertion.\n");
			return false;
		}
		return _objects.add(__target);
	}
	public boolean modify(final int __perms, final int __index, final T __override) {
		if ((__perms & _perms_modify_mask) == 0) {
			System.out.println("Database cannot insert object, permission check failed.\n");
			return false;
		}
		/* Upload our new value. */
		_objects.set(__index, __override);
		return true;
	}
	public boolean delete(final int __perms, final int __index) {
		if ((__perms & _perms_delete_mask) == 0) {
			System.out.println("Database cannot insert object, permission check failed.\n");
			return false;
		}
		_objects.remove(__index);
		return true;
	}
	public int get(final T __target) {
		return _objects.indexOf(__target);
	}
	public boolean dump(String __override) {
		String dir = (__override != null) ? __override : _dir;
		for (T t : _objects) {
			System.out.printf("hashcode()->%d\n", t.hashCode()); 
		}
		return false;
	}
}


