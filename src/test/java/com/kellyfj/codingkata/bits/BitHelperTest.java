package com.kellyfj.codingkata.bits;

import junit.framework.TestCase;

public class BitHelperTest extends TestCase {

	public void testBitsChecked() {		
		int i = BitHelper.chkEnabledBits(7);
		assertEquals(3,i);
		
		i = BitHelper.chkEnabledBits(Integer.MAX_VALUE);
		assertEquals(31,i);
	}
	
	public void testBitsCheckedTwosComp() {	
		
		//0b111
		int i = BitHelper.chkEnabledBitsTwosComplement(7);
		assertEquals(3,i);
		
		//0b011.....1 = 31 bits set
		i = BitHelper.chkEnabledBitsTwosComplement(Integer.MAX_VALUE);
		assertEquals(31,i);
		
		//0b100000.....000 = MSB set
		i = BitHelper.chkEnabledBitsTwosComplement(Integer.MIN_VALUE);
		assertEquals(1,i);
		
		//All bits set
		i = BitHelper.chkEnabledBitsTwosComplement(-1);
		assertEquals(32,i);
	}
	
	
	public void testBinaryMultiple() {
		
		String s = BitHelper.binaryMultiply("0111", "0110");
		
		int answer = Integer.parseInt(s,2);
		System.out.println("Binary "+s+" is "+answer);
		
		assertEquals("101010",s);
		assertEquals(42,answer);
	}
}
