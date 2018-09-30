package com.sampana.login.utils;


import java.util.Collection;
import java.util.Map;


public class ToolBox {

	/**
	 * Validation for any kind of collection. e.g set,queue,list
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> boolean isCollectionEmpty(Collection<T> obj) {
		return null == obj || obj.isEmpty();
	}

	/**
	 * Validation for Map,HashTable like as collection
	 * 
	 * @param obj
	 * @return
	 */
	public static <T, V> boolean isCollectionEmpty(Map<T, V> obj) {
		return null == obj || obj.isEmpty();
	}

	/**
	 * validation for Object type.
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isObjectEmpty(Object obj) {
		return null == obj || obj.toString().trim().isEmpty();
	}

	/*
	 * public static void main(String[] args) { List<String> lists = new
	 * ArrayList<String>(); lists.add("dfgfdgdfg");
	 * System.out.println(isCollectionEmpty(lists)); }
	 */

	public static boolean isEmptyString(String str) {
		boolean status = false;
		if (str == null || str.isEmpty()) {
			return true;
		}
		return status;
	}
}