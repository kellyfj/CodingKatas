package com.kellyfj.codingkata.bits;

public class BitHelper {

    /**
     * Time Complexity: O(n) where n is number of bits set
     */
	public static int countEnabledBits(int number) {
		int numEnabledBits = 0;

		while (number > 0) {
			numEnabledBits += number & 1;
			number = number >> 1;
		}

		return numEnabledBits;
	}
	
    /**
     * EPIJ 4.1: Compute the Parity of a word
     */

    /**
     * Time Complexity: O(n)
     */
    public static int getParityOrderN(int number) {
        return countEnabledBits(number) & 1;
    }
	
	/**
	 * Time Complexity: O(log n)
	 */
	public static int getParityOrderLogN(int number) {
	    boolean parity = false; 
	    
        while (number > 0) {
            parity = !parity; 
            number = number & (number-1); //Unset rightmost set bit
        }

        
        return parity ? 1 : 0;
	}
	
    private static int[] precomputedParity;

    static {
        precomputedParity = new int[1 << 16];
        for (int i = 0; i < (1 << 16); ++i) {
            precomputedParity[i] = getParityOrderLogN(i);
        }
    }

    /**
     * NOTE: We switched to using Long not int (long is 64-bit, int is 32-bit)
     * @param x
     * @return
     */
    public static int getParityOrder1(long x) {
        final int WORD_SIZE = 16; //long has four 16-bit words
        final int BIT_MASK = 0xFFFF; //Used to extract the last bits
        return (short) 
                 (precomputedParity[(int) ((x >>> (3 * WORD_SIZE)) & BIT_MASK)]
                ^ precomputedParity[(int) ((x >>> (2 * WORD_SIZE)) & BIT_MASK)]
                ^ precomputedParity[(int) ((x >>> WORD_SIZE) & BIT_MASK)] 
                ^ precomputedParity[(int) (x & BIT_MASK)]);
    }
    
    /**
     * EPIJ 4.3 Reverse Bits
     */
    public static long swapBits(long x, int i, int j) {
        // Extract the i-th and j-th bits, and see if they differ.
        long ithBit = x >>> i;
        long jthBit = x >>> j;

        if ((ithBit & 1) != (jthBit & 1)) {
            // i-th and j-th bits differ. We will swap them by flipping their values.
            // Select the bits to flip with bitMask. Since x^1 = 0 when x = 1 and 1
            // when x = 0, we can perform the flip XOR.
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        return x;
    }

    private static long[] precomputedReverse = new long[(1 << 16)];

    private static long reverseBits(long x, int n) {
        for (int i = 0, j = n; i < j; ++i, --j) {
            x = swapBits(x, i, j);
        }
        return x;
    }

    static {
        for (int i = 0; i < (1 << 16); ++i) {
            precomputedReverse[i] = reverseBits(i, 15);
        }
    }

    public static long reverseBits(long x) {
      final int WORD_SIZE = 16;
      final int BIT_MASK = 0xFFFF;
      return   precomputedReverse[(int)(x & BIT_MASK)] << (3 * WORD_SIZE)
             | precomputedReverse[(int)((x >>> WORD_SIZE) & BIT_MASK)] << (2 * WORD_SIZE)
             | precomputedReverse[(int)((x >>> (2 * WORD_SIZE)) & BIT_MASK)] << WORD_SIZE
             | precomputedReverse[(int)((x >>> (3 * WORD_SIZE)) & BIT_MASK)];
    }
    
    /****************
     */
	public static int countEnabledBitsTwosComplement(int chkInt) {
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
