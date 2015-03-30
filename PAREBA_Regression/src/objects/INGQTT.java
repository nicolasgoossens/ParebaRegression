package objects;

import java.util.EnumMap;

public enum INGQTT {
	BBRUBEBB("BBRUBEBB"), INGBNL2A("INGBNL2A"), BBRUNL2X("BBRUNL2X"), INGBFRPP(
			"INGBFRPP"), INGBDEFF("INGBDEFF"), INGBGB22("INGBGB22"), INGBIE2D(
			"INGBIE2D"), BBRUPTPL("BBRUPTPL"), BBRUESMX("BBRUESMX"), INGBITMM(
			"INGBITMM"), BBRUCHGT("BBRUCHGT"), INGBATWW("INGBATWW"), CELLLULL(
			"CELLLULL"), INGBHUHB("INGBHUHB"), INGBCZPP("INGBCZPP"), INGBBGSF(
			"INGBBGSF"), ESSEDKKK("ESSEDKKK"), EEUHEE2X("EEUHEE2X"), ESSEFIHX(
			"ESSEFIHX"), ESSENOKX("ESSENOKX"), INGBPLPW("INGBPLPW"), INGBROBU(
			"INGBROBU"), ESSESESS("ESSESESS"), INGBSKBX("INGBSKBX");

	private final String bicCode;

	INGQTT(String bicCode) {
		this.bicCode = bicCode;
	}

	public static final String[] INGQTT_bicCodes = new String[INGQTT.values().length];
	static {
		for (int i = 0; i < INGQTT.values().length; i++)
			INGQTT_bicCodes[i] = INGQTT.values()[i].bicCode;
	}

	public static final EnumMap<INGQTT, String> bicCodes = new EnumMap<INGQTT, String>(
			INGQTT.class);

	static {
		for (INGQTT i : INGQTT.values())
			bicCodes.put(i, i.bicCode);
	}
}
