package com.kellyfj.codingkata.primitives;

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
	
	/**
	 * Time Complexity: O(1)
	 */
    private static int[] precomputedParity;
    static {
        precomputedParity = new int[1 << 16];
        for (int i = 0; i < (1 << 16); ++i) {
            precomputedParity[i] = getParityOrderLogN(i);
        }
    }

    /**
     * NOTE: We switched to using Long not int (long is 64-bit, int is 32-bit)
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
            x ^= bitMask; //XOR flips bits 1^1 -> 0,  0^1 -> 1
        }
        return x;
    }

    /**
     * Time Complexity: O(n) where n is number of bits
     */
    private static long reverseBits(long x, int n) {
        for (int i = 0, j = n; i < j; ++i, --j) {
            x = swapBits(x, i, j);
        }
        return x;
    }

    /**
     * Time Complexity: O(n/L) = n bits, L = word size
     */
    private static long[] precomputedReverse = new long[(1 << 16)];
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
	
	/**
	 * Example		"0111"
	 * 			x	"0110"
	 * 			==========
	 * 			     0000
	 *              0111
	 *             0111
	 *            0000 
	 *          ==========
	 *          101010          
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
		int i_answer = Integer.parseInt(answer,2);
		int i_tmp = Integer.parseInt(tmp, 2);
		System.out.println("Adding "+ i_answer + " to " +i_tmp );
		int intermed = i_answer+i_tmp;
		return Integer.toBinaryString(intermed);
	}
	
	/**
	 * EPIJ 4.5: Compute X*Y without any arithmetic operations
	 * 
	 * Our grade-school algorithm uses shift and add.
	 * To do so with add we need to iterate through the bits of x adding 2^k*y to 
	 * the result if the kth bit of x = 1
	 * @param x
	 * @param y
	 * @return
	 */
    public static long multiply(long x, long y) {
        long sum = 0;
        while (x != 0) {
            // Examine each bit of x
            if ((x & 1) != 0) {
                sum = add(sum, y);
            }
            x >>>= 1; // unsigned right shift operator. It always fills 0 irrespective of the sign of
                      // the number.
            y <<= 1;
        }
        return sum;
    }

    //TODO: Understand how this works - the text in EPIJ (pp. 31) is awful
    private static long add(long a, long b) {
        long sum = 0, carryIn = 0, k = 1, tempA = a, tempB = b;

        while (tempA != 0 || tempB != 0) {
            long ak = a & k; //Get kth bit
            long bk = b & k; 
            sum |= (ak ^ bk ^ carryIn);  
            long carryOut = (ak & bk) | (ak & carryIn) | (bk & carryIn);
            carryIn = carryOut << 1;
            k <<= 1;
            tempA >>>= 1;
            tempB >>>= 1;
        }

        return sum | carryIn;
    }
}
