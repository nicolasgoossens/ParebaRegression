package objects;

import java.util.EnumMap;

public enum BICCODE {
	BBRUBEBB("BBRUBEBB"), INGBNL2A("INGBNL2A"), BBRUNL2X("BBRUNL2X"), INGBFRPP(
			"INGBFRPP"), INGBDEFF("INGBDEFF"), INGBGB22("INGBGB22"), INGBIE2D(
			"INGBIE2D"), BBRUPTPL("BBRUPTPL"), BBRUESMX("BBRUESMX"), INGBITMM(
			"INGBITMM"), BBRUCHGT("BBRUCHGT"), INGBATWW("INGBATWW"), CELLLULL(
			"CELLLULL"), INGBHUHB("INGBHUHB"), INGBCZPP("INGBCZPP"), INGBBGSF(
			"INGBBGSF"), ESSEDKKK("ESSEDKKK"), EEUHEE2X("EEUHEE2X"), ESSEFIHX(
			"ESSEFIHX"), ESSENOKX("ESSENOKX"), INGBPLPW("INGBPLPW"), INGBROBU(
			"INGBROBU"), ESSESESS("ESSESESS"), INGBSKBX("INGBSKBX");

	private final String bicCode;

	BICCODE(String bicCode) {
		this.bicCode = bicCode;
	}

	public static final EnumMap<BICCODE, String> bicCodes = new EnumMap<BICCODE, String>(
			BICCODE.class);

	static {
		for (BICCODE i : BICCODE.values())
			bicCodes.put(i, i.bicCode);
	}
}
