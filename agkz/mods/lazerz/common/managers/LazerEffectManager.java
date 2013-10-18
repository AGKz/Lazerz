package agkz.mods.lazerz.common.managers;

public class LazerEffectManager {
	
	public String binaryNumber;
	
	public LazerEffectManager(int number) {
		// 12 - Should return 3 and 4
		binaryNumber = Integer.toBinaryString(number);	
		// 1100
	}
	
	public boolean shouldColorize() {
		if (binaryNumber.charAt(binaryNumber.length() - 1) == 1) 
			return true;
		else 
			return false;
	}
	public boolean shouldHighPower() {
		if (binaryNumber.charAt(binaryNumber.length() - 2) == 1) 
			return true;
		else 
			return false;
	}
	public boolean shouldMagic() {
		if (binaryNumber.charAt(binaryNumber.length() - 3) == 1) 
			return true;
		else 
			return false;
	}
	public boolean shouldBio() {
		if (binaryNumber.charAt(binaryNumber.length() - 4) == 1) 
			return true;
		else 
			return false;
	}
	/*
	 * 1 - Colorize - Colored Beam - Unimplemented
	 * 2 - HighPowered - Creeper like lighting things
	 * 4 - Magical - Stars 
	 * 8 - Bio - Potion
	 * 16 - Option 5 - 
	 * 32 - Option 6
	 */
}
