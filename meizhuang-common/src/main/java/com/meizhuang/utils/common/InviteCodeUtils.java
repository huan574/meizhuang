package com.meizhuang.utils.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InviteCodeUtils {

	private static List<char[]> list = new ArrayList<char[]>();

	static {
		list.add(new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' });
		list.add(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' });
		list.add(new char[] { 'J', 'L', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A' });
		list.add(new char[] { 'k', 'u', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't' });
		list.add(new char[] { 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'U', 'K' });
		list.add(new char[] { 'v', 'w', 'x', 'y', 'z', 'V', 'W', 'X', 'Y', 'Z' });
	}

	public static String genInviteCode(Integer uid) {
		if (uid == null) {
			return null;
		}
		char[] nums = uid.toString().toCharArray();
		Random random = new Random();
		StringBuffer code = new StringBuffer("");
		for (char c : nums) {
			code.append(list.get(random.nextInt(list.size()))[Integer.parseInt(String.valueOf(c))]);
		}
		return code.toString();
	}

}
