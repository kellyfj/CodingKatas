package com.kellyfj.codingkata.bits;


public class BitHelper {

	public static int chkEnabledBits(int chkInt) {
		int numEnabledBits = 0;

		while (chkInt > 0) {
			numEnabledBits += chkInt & 1;
			chkInt = chkInt >> 1;
		}

		return numEnabledBits;
	}

	public static int chkEnabledBitsTwosComplement(int chkInt) {
		int numEnabledBits = 0;
		int numChkedBits = 0;
		while ((chkInt != 0) && (numChkedBits < 32)) {
			numEnabledBits += chkInt & 1;
			chkInt = chkInt >> 1;
			numChkedBits++;
		}
		return numEnabledBits;
	}
	
	/**
	 * Example
	 * 				"0111"
	 * 			x	"0110"
	 * 			==========
	 * 			     0000
	 *              0111
	 *             0111
	 *            0000 
	 *          ==========
	 *          101010
	 *          
	 */
	public static String binaryMultiply(String s1, String s2) {

		String answer = "0";
		int shift = 0;
		for (int i = s2.length() - 1; i >= 0; i--) {

			StringBuffer tmp = new StringBuffer(s1.length());
			// shift
			for (int s = 0; s < shift; s++) {
				tmp.append("0");
			}

			for (int j = s1.length() - 1; j >= 0; j--) {
				// calculate
				if (s2.charAt(i) == '0' || s1.charAt(j) == '0') {
					tmp.append("0");
				} else {
					tmp.append("1");
				}
			}
			// add
			answer = binaryAdd(answer, tmp.reverse().toString());
			shift++;
		}
		return answer;
	}

	private static String binaryAdd(String answer, String tmp) {
		System.out.println(tmp);
		
		int i_answer = Integer.parseInt(answer,2);
		int i_tmp = Integer.parseInt(tmp, 2);
		System.out.println("Adding "+ i_answer + " to " +i_tmp );
		int intermed = i_answer+i_tmp;
		return Integer.toBinaryString(intermed);
	}
}
