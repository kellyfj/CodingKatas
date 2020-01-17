package com.kellyfj.codingkata.primitives;

import com.kellyfj.codingkata.primitives.BitHelper;

import junit.framework.TestCase;

public class BitHelperTest extends TestCase {

    public void testBitsChecked() {
        int i = BitHelper.countEnabledBits(7);
        assertEquals(3, i);

        i = BitHelper.countEnabledBits(Integer.MAX_VALUE);
        assertEquals(31, i);

        i = BitHelper.countEnabledBits(0);
        assertEquals(0, i);
    }
    
    public void testParityOrderN() {
        int i = BitHelper.getParityOrderN(0);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrderN(7);
        assertEquals(1, i);
        
        i = BitHelper.getParityOrderN(Integer.MAX_VALUE - 1);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrderN(Integer.MAX_VALUE);
        assertEquals(1, i);      
    }
    
    public void testParityOrderLogN() {
        int i = BitHelper.getParityOrderLogN(0);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrderLogN(7);
        assertEquals(1, i);
        
        i = BitHelper.getParityOrderLogN(Integer.MAX_VALUE - 1);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrderLogN(Integer.MAX_VALUE);
        assertEquals(1, i);      
    }
    
    
    public void testParityOrder1() {
        int i = BitHelper.getParityOrder1(0);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrder1(7);
        assertEquals(1, i);
        
        i = BitHelper.getParityOrder1(Integer.MAX_VALUE - 1);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrder1(Integer.MAX_VALUE);
        assertEquals(1, i);     
        
        i = BitHelper.getParityOrder1(Long.MAX_VALUE - 1);
        assertEquals(0, i);
        
        i = BitHelper.getParityOrder1(Long.MAX_VALUE);
        assertEquals(1, i); 
        
    } 
	
    public void testSwapBits() {
        assert (BitHelper.swapBits(47, 1, 4) == 61);
        assert (BitHelper.swapBits(28, 0, 2) == 25);
    }
    
    public void testReverseBits() {
        long l = BitHelper.reverseBits(0);
        assertEquals(0, l);

        l = BitHelper.reverseBits(1);
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000", Long.toBinaryString(l));

        l = BitHelper.reverseBits(7);
        assertEquals("1110000000000000000000000000000000000000000000000000000000000000", Long.toBinaryString(l));

    }

    public void testMultiplyLongsWithBitOperations() {    
        long l = BitHelper.multiply(5, 25);
        assertEquals(125L, l);
    }
    
	public void testBitsCheckedTwosComp() {	
		
		//0b111
		int i = BitHelper.countEnabledBitsTwosComplement(7);
		assertEquals(3,i);
		
		//0b011.....1 = 31 bits set
		i = BitHelper.countEnabledBitsTwosComplement(Integer.MAX_VALUE);
		assertEquals(31,i);
		
		//0b100000.....000 = MSB set
		i = BitHelper.countEnabledBitsTwosComplement(Integer.MIN_VALUE);
		assertEquals(1,i);
		
		//All bits set
		i = BitHelper.countEnabledBitsTwosComplement(-1);
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
