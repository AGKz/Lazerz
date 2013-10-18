package agkz.mods.lazerz.common;

public class LazerType {
	public static final int BIOLOGICAL = 1;
	public static final int ENDER = 2;
	public static final int CHEAT = 3;
	public static final int HANDGUN = 4;
	public static final int MILITARY = 5;
	public static final int MARK2 = 6;
	public static final int NUCLEAR = 7;
	public static final int UTILITY = 8;
	
	public static String getUnlocalizedFromID(int id) {
		switch(id) {
		case BIOLOGICAL: return "lazer.biological";
		case ENDER: return "lazer.ender";
		case CHEAT: return "lazer.cheat";
		case HANDGUN: return "lazer.handgun";
		case MILITARY: return "lazer.military";
		case MARK2: return "lazer.mark2";
		case UTILITY: return "lazer.utility";
		default: return "lazer.lazer";
		}
	}
}