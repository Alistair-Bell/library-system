/*
 * Copyright (C) 2022 Alistair Bell <alistair@alistairbell.xyz>
 * License: see `license.txt` at the project.
 */

package alistairbell.xyz;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

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
	public T get(final int __target) {
		try {
			return _objects.get(__target);
		} catch (Exception e) {
			System.out.printf("Database get failed, exception raised, %s.\n", e.toString());
		}
		return null;
	}
	private static boolean create_fs(String __target, boolean __dir) {
		try {
			File f = new File(__target);
			if (!f.exists()) {
				if (__dir) {
					f.mkdirs();
				} else {
					f.createNewFile();
				}
			}
		} catch (Exception e) {
			System.out.printf("Database %s creation failed, exception raised by %s, %s.\n", (__dir) ? "directory" : "file", __target, e.toString());
			return false;
		}
		return true;
	}
	private boolean dump_single(T __target, File __root) {
		String out = String.format("%s/%s", __root.toString(), __target.hashCode());
		int hash = __target.hashCode();
		if (!create_fs(out, false))
			return false;
		try {
			FileWriter w = new FileWriter(out);
			w.write(__target.toString() + "\n");
			w.close();
		} catch (Exception e) {
			System.out.printf("Database dump failed, writing to %s failed, exception rasied, %s.\n", out, e.toString());
		}
		return true;
	}
	public boolean dump() {
		File f = new File(_dir);
		if (!create_fs(_dir, true))
			return false;
		for (T t : _objects) {
			dump_single(t, f);
		}
		return false;
	}
}


