package BadgeAndMembershipClient.common;

import java.util.List;

public class Util {
	public static final int SPACENUM = 30;

	/**
	 * @param input
	 * @return true if input string is integer.
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static int getSpaceCount(String s) {
		int charLength = s.length();
		return Math.abs(SPACENUM - charLength);

	}

	public static String getPaddedString(List<String> listOfString) {
		StringBuilder strBldr = new StringBuilder();
		strBldr.append("| ");
		for (String s : listOfString) {
			if(s.length() >= SPACENUM) {
				s = s.substring(0, SPACENUM - 4) + "...";
			}		
			
			strBldr.append(s);
			int count = getSpaceCount(s);
			for (int i = 1; i < count; i++) {
				strBldr.append(" ");
			}
			strBldr.append("| ");
		}
		strBldr.append("\n" + getRowDividerLine(listOfString.size()));
		return strBldr.toString();
	}

	public static String getRowDividerLine(int columnCount) {
		StringBuilder strbldr = new StringBuilder();
		for (int i = 1; i < SPACENUM * columnCount + 6; i++) {
			strbldr.append("-");
		}
		return strbldr.toString();
	}

	public static void printRow(List<String> row) {
		String sa = Util.getPaddedString(row);
		System.out.println(sa);
	}

	public static void printTable(List<List<String>> rows) {
		if (rows.size() == 0)
			return;
		System.out.println(getRowDividerLine(rows.get(0).size()));

		for (List<String> row : rows) {
			Util.printRow(row);
		}
	}
}
