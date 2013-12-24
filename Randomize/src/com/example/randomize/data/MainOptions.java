package com.example.randomize.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main option categories 
 * <p>
 */
public class MainOptions {

	public static List<OptionItem> ITEMS = new ArrayList<OptionItem>();

	public static Map<String, OptionItem> ITEM_MAP = new HashMap<String, OptionItem>();

	static {
		addItem(new OptionItem("option_number", "Numbers"));
		addItem(new OptionItem("2", "Names"));
		addItem(new OptionItem("3", "Dice"));
	}

	private static void addItem(OptionItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	public static class OptionItem {
		public String id;
		public String content;

		public OptionItem(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
