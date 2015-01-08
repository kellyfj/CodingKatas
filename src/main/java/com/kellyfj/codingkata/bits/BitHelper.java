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
}
