import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;

import objects.BICCODE;
import objects.Pareba;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import utils.Logger;
import utils.QttReader;

public class Test {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException, InterruptedException {

		while (true) {
			int fileNumber = 1;
			File dir = new File(".\\Input");
			QttReader qttReader = null;
			File[] directoryListing = dir.listFiles();
			if (directoryListing.length > 0 && directoryListing != null) {
				Logger lg = new Logger();
				lg.createLogging();
				for (File child : directoryListing) {
					int lengte = 0;
					Pareba pareba = new Pareba(child, fileNumber);
					lg.writeSubscriptionAndTimestamp(pareba);
					while (lengte < pareba.getElement()
							.getElementsByTagName("axsd:AccNbrs").getLength()) {

						System.out.println((pareba.getAccount().pseudo(
										pareba.getAccount().accountNumbers(
												pareba.getElement(), lengte))));
						if (pareba.getAccount().pseudoLength(
								pareba.getAccount().pseudo(
										pareba.getAccount().accountNumbers(
												pareba.getElement(), lengte))) == 12) {
							
							Node type = pareba
									.getAccount()
									.type(pareba
											.getAccount()
											.qTransType(
													pareba.getAccount()
															.accountNumbers(
																	pareba.getElement(),
																	lengte)));

							ArrayList<String> qttPareba = new ArrayList<>();

							while (type != null) {

								if (!type.getNodeName().contains("#text")) {
									qttPareba.add(type.getTextContent());
								}
								type = type.getNextSibling();
							}

							Collections.sort(qttPareba);
							
							qttReader = new QttReader();
							qttReader.ingQttReader(pareba
									.getAccount()
									.bicCode(
											pareba.getAccount()
													.accountNumbers(
															pareba.getElement(),
															lengte)), qttPareba);
							
							String[] qttCountry = null;

							switch (pareba
									.getAccount()
									.bicCode(
											pareba.getAccount()
													.accountNumbers(
															pareba.getElement(),
															lengte))
									.substring(4, 6)) {

							case "BE":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BBRUBEBB") == 0) {
									if (Integer
											.parseInt(pareba
													.getAccount()
													.pseudo(pareba
															.getAccount()
															.accountNumbers(
																	pareba.getElement(),
																	lengte))
													.substring(3, 4)) != 5
											&& Integer
													.parseInt(pareba
															.getAccount()
															.pseudo(pareba
																	.getAccount()
																	.accountNumbers(
																			pareba.getElement(),
																			lengte))
															.substring(3, 4)) != 6) {

										String[] qttBelgiumZichtRekening = {
												"BEP00", "BEP01", "BEP02",
												"BEP03", "BEP04", "BEP05",
												"BEP06", "BEP07", "BEP08",
												"BEP09", "BEP10", "BEP11",
												"BEP12", "BED00", "BEC00",
												"BES00", "BES01", "BES02",
												"BES04", "BES05", "BES07",
												"BES09", "BES11", "BES14",
												"BES15", "BES16", "BES17",
												"BES18", "BES19", "BES20",
												"BES21", "BES22", "BES23",
												"BES24", "BEN00", "BEN01",
												"BEN02", "BEN04", "BEN05",
												"BEN07", "BEN09", "BEN11",
												"BEN14", "BEN15", "BEN16",
												"BEN17", "BEN18", "BEN19",
												"BEN20", "BEN21", "BEN22",
												"BEN23", "BEN24", "BEF00",
												"DIV0I0", "DIV0I1", "DIV00c",
												"DIV00x", "DIV00p", "DIV01p",
												"DIVXML", "DIV0B0", "DIV00M",
												"DIV0BD", "DIV0BC", "DIV0BL",
												"DIV00L" };

										qttCountry = qttBelgiumZichtRekening;

										if (pareba
												.getAccount()
												.qTransType(
														pareba.getAccount()
																.accountNumbers(
																		pareba.getElement(),
																		lengte))
												.getNextSibling() != null) {
											System.out
													.println(pareba
															.getAccount()
															.bep13(pareba
																	.getAccount()
																	.qTransType(
																			pareba.getAccount()
																					.accountNumbers(
																							pareba.getElement(),
																							lengte)))
															.getTextContent());
											System.out
													.println(pareba
															.getAccount()
															.bep13BenefAccount(
																	pareba.getAccount()
																			.qTransType(
																					pareba.getAccount()
																							.accountNumbers(
																									pareba.getElement(),
																									lengte)))
															.getTextContent());

											if (pareba
													.getAccount()
													.bep13BenefAccount(
															pareba.getAccount()
																	.qTransType(
																			pareba.getAccount()
																					.accountNumbers(
																							pareba.getElement(),
																							lengte)))
													.getTextContent()
													.substring(27, 30)
													.compareTo("084") == 0
													|| pareba
															.getAccount()
															.bep13BenefAccount(
																	pareba.getAccount()
																			.qTransType(
																					pareba.getAccount()
																							.accountNumbers(
																									pareba.getElement(),
																									lengte)))
															.getTextContent()
															.substring(27, 30)
															.compareTo("087") == 0
													|| pareba
															.getAccount()
															.bep13BenefAccount(
																	pareba.getAccount()
																			.qTransType(
																					pareba.getAccount()
																							.accountNumbers(
																									pareba.getElement(),
																									lengte)))
															.getTextContent()
															.substring(27, 30)
															.compareTo("035") == 0) {

												ArrayList<String> qttBep13 = new ArrayList<>();
												qttBep13.add(pareba
														.getAccount()
														.bep13(pareba
																.getAccount()
																.qTransType(
																		pareba.getAccount()
																				.accountNumbers(
																						pareba.getElement(),
																						lengte)))
														.getTextContent());

												String[] qttBep13Account = { "BEP13" };

												if (Arrays.equals(
														qttBep13.toArray(),
														qttBep13Account)) {
													System.out
															.println("BEP13 QTT is present!!");
												} else {
													System.out
															.println("BEP13 QTT is missing!!!");
												}

											}
										}

									} else {

										String[] qttBelgiumGroenBoekje = {
												"BEP00", "BES00" };

										qttCountry = qttBelgiumGroenBoekje;
									}
								} else {

									String[] qttBelgium_1 = { "BES00", "BES01",
											"BES02", "BES04", "BES05", "BES07",
											"BES09", "BES11", "BES14", "BES15",
											"BES16", "BES17", "BES18", "BES19",
											"BES20", "BES21", "BES22", "BES23",
											"BES24", "BEN00", "BEN01", "BEN02",
											"BEN04", "BEN05", "BEN07", "BEN09",
											"BEN11", "BEN14", "BEN15", "BEN16",
											"BEN17", "BEN18", "BEN19", "BEN20",
											"BEN21", "BEN22", "BEN23", "BEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttBelgium_1;
								}

								break;

							case "NL":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BBRUNL2X") == 0
										|| pareba
												.getAccount()
												.bicCode(
														pareba.getAccount()
																.accountNumbers(
																		pareba.getElement(),
																		lengte))
												.compareTo("INGBNL2A") == 0) {
									String[] qttHolland = { "NLS00", "NLS01",
											"NLS02", "NLS04", "NLS05", "NLS07",
											"NLS09", "NLS11", "NLS14", "NLS15",
											"NLS16", "NLS17", "NLS18", "NLS19",
											"NLS20", "NLS21", "NLS22", "NLS23",
											"NLS24", "NLN00", "NLN01", "NLN02",
											"NLN04", "NLN05", "NLN07", "NLN09",
											"NLN11", "NLN14", "NLN15", "NLN16",
											"NLN17", "NLN18", "NLN19", "NLN20",
											"NLN21", "NLN22", "NLN23", "NLN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00N",
											"DIV00M", "DIV00n" };

									qttCountry = qttHolland;
								} else {

									String[] qttHolland = { "NLS00", "NLS01",
											"NLS02", "NLS04", "NLS05", "NLS07",
											"NLS09", "NLS11", "NLS14", "NLS15",
											"NLS16", "NLS17", "NLS18", "NLS19",
											"NLS20", "NLS21", "NLS22", "NLS23",
											"NLS24", "NLN00", "NLN01", "NLN02",
											"NLN04", "NLN05", "NLN07", "NLN09",
											"NLN11", "NLN14", "NLN15", "NLN16",
											"NLN17", "NLN18", "NLN19", "NLN20",
											"NLN21", "NLN22", "NLN23", "NLN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttHolland;
								}
								break;

							case "FR":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBFRPP") == 0) {
									String[] qttFrance = { "FRS00", "FRS01",
											"FRS02", "FRS04", "FRS05", "FRS07",
											"FRS09", "FRS11", "FRS14", "FRS15",
											"FRS16", "FRS17", "FRS18", "FRS19",
											"FRS20", "FRS21", "FRS22", "FRS23",
											"FRS24", "FRN00", "FRN01", "FRN02",
											"FRN04", "FRN05", "FRN07", "FRN09",
											"FRN11", "FRN14", "FRN15", "FRN16",
											"FRN17", "FRN18", "FRN19", "FRN20",
											"FRN21", "FRN22", "FRN23", "FRN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00F",
											"DIV00M", "DIV00f", "DIV00A",
											"DIV00t" };

									qttCountry = qttFrance;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "DE":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBDEFF") == 0) {
									String[] qttGermany = { "DES00", "DES01",
											"DES02", "DES04", "DES05", "DES07",
											"DES09", "DES11", "DES14", "DES15",
											"DES16", "DES17", "DES18", "DES19",
											"DES20", "DES21", "DES22", "DES23",
											"DES24", "DEN00", "DEN01", "DEN02",
											"DEN04", "DEN05", "DEN07", "DEN09",
											"DEN11", "DEN14", "DEN15", "DEN16",
											"DEN17", "DEN18", "DEN19", "DEN20",
											"DEN21", "DEN22", "DEN23", "DEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00G",
											"DIV00M", "DIV00g" };

									qttCountry = qttGermany;
								} else {
									String[] qttGermany = { "DES00", "DES01",
											"DES02", "DES04", "DES05", "DES07",
											"DES09", "DES11", "DES14", "DES15",
											"DES16", "DES17", "DES18", "DES19",
											"DES20", "DES21", "DES22", "DES23",
											"DES24", "DEN00", "DEN01", "DEN02",
											"DEN04", "DEN05", "DEN07", "DEN09",
											"DEN11", "DEN14", "DEN15", "DEN16",
											"DEN17", "DEN18", "DEN19", "DEN20",
											"DEN21", "DEN22", "DEN23", "DEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttGermany;
								}

							case "GB":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBGB22") == 0) {
									String[] qttGreatBritain = { "GBS00",
											"GBS01", "GBS02", "GBS04", "GBS05",
											"GBS07", "GBS09", "GBS11", "GBS14",
											"GBS15", "GBS16", "GBS17", "GBS18",
											"GBS19", "GBS20", "GBS21", "GBS22",
											"GBS23", "GBS24", "GBN00", "GBN01",
											"GBN02", "GBN04", "GBN05", "GBN07",
											"GBN09", "GBN11", "GBN14", "GBN15",
											"GBN16", "GBN17", "GBN18", "GBN19",
											"GBN20", "GBN21", "GBN22", "GBN23",
											"GBN24", "BEF00", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML",
											"DIV00U", "DIV00M", "DIV00u" };

									qttCountry = qttGreatBritain;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "IE":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBIE2D") == 0) {
									String[] qttIreland = { "IES00", "IES01",
											"IES02", "IES04", "IES05", "IES07",
											"IES09", "IES11", "IES14", "IES15",
											"IES16", "IES17", "IES18", "IES19",
											"IES20", "IES21", "IES22", "IES23",
											"IES24", "IEN00", "IEN01", "IEN02",
											"IEN04", "IEN05", "IEN07", "IEN09",
											"IEN11", "IEN14", "IEN15", "IEN16",
											"IEN17", "IEN18", "IEN19", "IEN20",
											"IEN21", "IEN22", "IEN23", "IEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00J",
											"DIV00M" };

									qttCountry = qttIreland;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "PT":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BBRUPTPL") == 0) {
									String[] qttPortugal = { "PTS00", "PTS01",
											"PTS02", "PTS04", "PTS05", "PTS07",
											"PTS09", "PTS11", "PTS14", "PTS15",
											"PTS16", "PTS17", "PTS18", "PTS19",
											"PTS20", "PTS21", "PTS22", "PTS23",
											"PTS24", "PTN00", "PTN01", "PTN02",
											"PTN04", "PTN05", "PTN07", "PTN09",
											"PTN11", "PTN14", "PTN15", "PTN16",
											"PTN17", "PTN18", "PTN19", "PTN20",
											"PTN21", "PTN22", "PTN23", "PTN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00T",
											"DIV00M" };

									qttCountry = qttPortugal;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "ES":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BBRUESMX") == 0) {

									String[] qttSpain = { "ESS00", "ESS01",
											"ESS02", "ESS04", "ESS05", "ESS07",
											"ESS09", "ESS11", "ESS14", "ESS15",
											"ESS16", "ESS17", "ESS18", "ESS19",
											"ESS20", "ESS21", "ESS22", "ESS23",
											"ESS24", "ESN00", "ESN01", "ESN02",
											"ESN04", "ESN05", "ESN07", "ESN09",
											"ESN11", "ESN14", "ESN15", "ESN16",
											"ESN17", "ESN18", "ESN19", "ESN20",
											"ESN21", "ESN22", "ESN23", "ESN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00E",
											"DIV00M", "DIV00e", "DIV00d",
											"DIVPES", "DIVDES" };

									qttCountry = qttSpain;
								} else {

									String[] qttSpain = { "ESS00", "ESS01",
											"ESS02", "ESS04", "ESS05", "ESS07",
											"ESS09", "ESS11", "ESS14", "ESS15",
											"ESS16", "ESS17", "ESS18", "ESS19",
											"ESS20", "ESS21", "ESS22", "ESS23",
											"ESS24", "ESN00", "ESN01", "ESN02",
											"ESN04", "ESN05", "ESN07", "ESN09",
											"ESN11", "ESN14", "ESN15", "ESN16",
											"ESN17", "ESN18", "ESN19", "ESN20",
											"ESN21", "ESN22", "ESN23", "ESN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttSpain;
								}
								break;

							case "IT":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBITMM") == 0) {

									String[] qttItaly = { "ITS00", "ITS01",
											"ITS02", "ITS04", "ITS05", "ITS07",
											"ITS09", "ITS11", "ITS14", "ITS15",
											"ITS16", "ITS17", "ITS18", "ITS19",
											"ITS20", "ITS21", "ITS22", "ITS23",
											"ITS24", "ITN00", "ITN01", "ITN02",
											"ITN04", "ITN05", "ITN07", "ITN09",
											"ITN11", "ITN14", "ITN15", "ITN16",
											"ITN17", "ITN18", "ITN19", "ITN20",
											"ITN21", "ITN22", "ITN23", "ITN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00l",
											"DIV00M", "DIV00I", "DIV00i" };

									qttCountry = qttItaly;

								} else {

									String[] qttItaly = { "ITS00", "ITS01",
											"ITS02", "ITS04", "ITS05", "ITS07",
											"ITS09", "ITS11", "ITS14", "ITS15",
											"ITS16", "ITS17", "ITS18", "ITS19",
											"ITS20", "ITS21", "ITS22", "ITS23",
											"ITS24", "ITN00", "ITN01", "ITN02",
											"ITN04", "ITN05", "ITN07", "ITN09",
											"ITN11", "ITN14", "ITN15", "ITN16",
											"ITN17", "ITN18", "ITN19", "ITN20",
											"ITN21", "ITN22", "ITN23", "ITN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttItaly;

								}

								break;

							case "CH":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BBRUCHGT") == 0) {

									String[] qttSwitzerland = { "CHS00",
											"CHS01", "CHS02", "CHS04", "CHS05",
											"CHS07", "CHS09", "CHS11", "CHS14",
											"CHS15", "CHS16", "CHS17", "CHS18",
											"CHS19", "CHS20", "CHS21", "CHS22",
											"CHS23", "CHS24", "CHN00", "CHN01",
											"CHN02", "CHN04", "CHN05", "CHN07",
											"CHN09", "CHN11", "CHN14", "CHN15",
											"CHN16", "CHN17", "CHN18", "CHN19",
											"CHN20", "CHN21", "CHN22", "CHN23",
											"CHN24", "BEF00", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML",
											"DIV00R", "DIV00r" };

									qttCountry = qttSwitzerland;

								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "AT":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBATWW") == 0) {

									String[] qttAustria = { "ATS00", "ATS01",
											"ATS02", "ATS04", "ATS05", "ATS07",
											"ATS09", "ATS11", "ATS14", "ATS15",
											"ATS16", "ATS17", "ATS18", "ATS19",
											"ATS20", "ATS21", "ATS22", "ATS23",
											"ATS24", "ATN00", "ATN01", "ATN02",
											"ATN04", "ATN05", "ATN07", "ATN09",
											"ATN11", "ATN14", "ATN15", "ATN16",
											"ATN17", "ATN18", "ATN19", "ATN20",
											"ATN21", "ATN22", "ATN23", "ATN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00O",
											"DIV00M", "DIV00o" };

									qttCountry = qttAustria;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "LU":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("CELLLULL") == 0) {
									String[] qttLuxemburg = { "LUS00", "LUS01",
											"LUS02", "LUS04", "LUS05", "LUS07",
											"LUS09", "LUS11", "LUS14", "LUS15",
											"LUS16", "LUS17", "LUS18", "LUS19",
											"LUS20", "LUS21", "LUS22", "LUS23",
											"LUS24", "LUN00", "LUN01", "LUN02",
											"LUN04", "LUN05", "LUN07", "LUN09",
											"LUN11", "LUN14", "LUN15", "LUN16",
											"LUN17", "LUN18", "LUN19", "LUN20",
											"LUN21", "LUN22", "LUN23", "LUN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV00K",
											"DIV00k", "DIVLUC", "DIVLUD" };

									qttCountry = qttLuxemburg;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "HU":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBHUHB") == 0) {
									String[] qttHungary = { "HUS00", "HUS01",
											"HUS02", "HUS04", "HUS05", "HUS07",
											"HUS09", "HUS11", "HUS14", "HUS15",
											"HUS16", "HUS17", "HUS18", "HUS19",
											"HUS20", "HUS21", "HUS22", "HUS23",
											"HUS24", "HUN00", "HUN01", "HUN02",
											"HUN04", "HUN05", "HUN07", "HUN09",
											"HUN11", "HUN14", "HUN15", "HUN16",
											"HUN17", "HUN18", "HUN19", "HUN20",
											"HUN21", "HUN22", "HUN23", "HUN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV003",
											"DIV004" };

									qttCountry = qttHungary;

								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "CZ":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBCZPP") == 0) {

									String[] qttCzech = { "CZS00", "CZS01",
											"CZS02", "CZS04", "CZS05", "CZS07",
											"CZS09", "CZS11", "CZS14", "CZS15",
											"CZS16", "CZS17", "CZS18", "CZS19",
											"CZS20", "CZS21", "CZS22", "CZS23",
											"CZS24", "CZN00", "CZN01", "CZN02",
											"CZN04", "CZN05", "CZN07", "CZN09",
											"CZN11", "CZN14", "CZN15", "CZN16",
											"CZN17", "CZN18", "CZN19", "CZN20",
											"CZN21", "CZN22", "CZN23", "CZN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML", "DIV005",
											"DIV006" };

									qttCountry = qttCzech;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}

								break;

							case "BR":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("FNBBBRSPSPO") == 0) {
									String[] qttBrasil = { "BEF00", "BRN00",
											"BRN01", "BRN02", "BRN04", "BRN05",
											"BRN07", "BRN09", "BRN11", "BRN14",
											"BRN15", "BRN16", "BRN17", "BRN18",
											"BRN19", "BRN20", "BRN21", "BRN22",
											"BRN23", "BRN24", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML" };

									qttCountry = qttBrasil;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}

								break;

							case "US":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BOFAUS6S") == 0) {
									String[] qttUsa = { "BEF00", "USN00",
											"USN01", "USN02", "USN04", "USN05",
											"USN07", "USN09", "USN11", "USN14",
											"USN15", "USN16", "USN17", "USN18",
											"USN19", "USN20", "USN21", "USN22",
											"USN23", "USN24", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML" };

									qttCountry = qttUsa;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "MX":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("BOFAMXMX") == 0) {
									String[] qttMexico = { "BEF00", "MXN00",
											"MXN01", "MXN02", "MXN04", "MXN05",
											"MXN07", "MXN09", "MXN11", "MXN14",
											"MXN15", "MXN16", "MXN17", "MXN18",
											"MXN19", "MXN20", "MXN21", "MXN22",
											"MXN23", "MXN24", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML" };

									qttCountry = qttMexico;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "AR":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("FNBBARBA") == 0) {
									String[] qttArgentina = { "BEF00", "ARN00",
											"ARN01", "ARN02", "ARN04", "ARN05",
											"ARN07", "ARN09", "ARN11", "ARN14",
											"ARN15", "ARN16", "ARN17", "ARN18",
											"ARN19", "ARN20", "ARN21", "ARN22",
											"ARN23", "ARN24", "DIV0I0",
											"DIV0I1", "DIV00c", "DIV00x",
											"DIV00p", "DIV01p", "DIVXML" };

									qttCountry = qttArgentina;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "BG":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBBGSF") == 0) {
									String[] qttBulgaria = { "BGS00", "BGS01",
											"BGS02", "BGS04", "BGS05", "BGS07",
											"BGS09", "BGS11", "BGS14", "BGS15",
											"BGS16", "BGS17", "BGS18", "BGS19",
											"BGS20", "BGS21", "BGS22", "BGS23",
											"BGS24", "BGN00", "BGN01", "BGN02",
											"BGN04", "BGN05", "BGN07", "BGN09",
											"BGN11", "BGN14", "BGN15", "BGN16",
											"BGN17", "BGN18", "BGN19", "BGN20",
											"BGN21", "BGN22", "BGN23", "BGN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttBulgaria;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "DK":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("ESSEDKKK") == 0) {
									String[] qttDenmark = { "DKS00", "DKS01",
											"DKS02", "DKS04", "DKS05", "DKS07",
											"DKS09", "DKS11", "DKS14", "DKS15",
											"DKS16", "DKS17", "DKS18", "DKS19",
											"DKS20", "DKS21", "DKS22", "DKS23",
											"DKS24", "DKN00", "DKN01", "DKN02",
											"DKN04", "DKN05", "DKN07", "DKN09",
											"DKN11", "DKN14", "DKN15", "DKN16",
											"DKN17", "DKN18", "DKN19", "DKN20",
											"DKN21", "DKN22", "DKN23", "DKN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttDenmark;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "EE":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("EEUHEE2X") == 0) {
									String[] qttEstonia = { "EES00", "EES01",
											"EES02", "EES04", "EES05", "EES07",
											"EES09", "EES11", "EES14", "EES15",
											"EES16", "EES17", "EES18", "EES19",
											"EES20", "EES21", "EES22", "EES23",
											"EES24", "EEN00", "EEN01", "EEN02",
											"EEN04", "EEN05", "EEN07", "EEN09",
											"EEN11", "EEN14", "EEN15", "EEN16",
											"EEN17", "EEN18", "EEN19", "EEN20",
											"EEN21", "EEN22", "EEN23", "EEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttEstonia;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "FI":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("ESSEFIHX") == 0) {
									String[] qttFinland = { "FIS00", "FIS01",
											"FIS02", "FIS04", "FIS05", "FIS07",
											"FIS09", "FIS11", "FIS14", "FIS15",
											"FIS16", "FIS17", "FIS18", "FIS19",
											"FIS20", "FIS21", "FIS22", "FIS23",
											"FIS24", "FIN00", "FIN01", "FIN02",
											"FIN04", "FIN05", "FIN07", "FIN09",
											"FIN11", "FIN14", "FIN15", "FIN16",
											"FIN17", "FIN18", "FIN19", "FIN20",
											"FIN21", "FIN22", "FIN23", "FIN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttFinland;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "NO":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("ESSENOKX") == 0) {
									String[] qttNorway = { "NOS00", "NOS01",
											"NOS02", "NOS04", "NOS05", "NOS07",
											"NOS09", "NOS11", "NOS14", "NOS15",
											"NOS16", "NOS17", "NOS18", "NOS19",
											"NOS20", "NOS21", "NOS22", "NOS23",
											"NOS24", "NON00", "NON01", "NON02",
											"NON04", "NON05", "NON07", "NON09",
											"NON11", "NON14", "NON15", "NON16",
											"NON17", "NON18", "NON19", "NON20",
											"NON21", "NON22", "NON23", "NON24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttNorway;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "PL":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBPLPW") == 0) {
									String[] qttPoland = { "PLS00", "PLS01",
											"PLS02", "PLS04", "PLS05", "PLS07",
											"PLS09", "PLS11", "PLS14", "PLS15",
											"PLS16", "PLS17", "PLS18", "PLS19",
											"PLS20", "PLS21", "PLS22", "PLS23",
											"PLS24", "PLN00", "PLN01", "PLN02",
											"PLN04", "PLN05", "PLN07", "PLN09",
											"PLN11", "PLN14", "PLN15", "PLN16",
											"PLN17", "PLN18", "PLN19", "PLN20",
											"PLN21", "PLN22", "PLN23", "PLN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttPoland;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "RO":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBROBU") == 0) {
									String[] qttRomania = { "ROS00", "ROS01",
											"ROS02", "ROS04", "ROS05", "ROS07",
											"ROS09", "ROS11", "ROS14", "ROS15",
											"ROS16", "ROS17", "ROS18", "ROS19",
											"ROS20", "ROS21", "ROS22", "ROS23",
											"ROS24", "RON00", "RON01", "RON02",
											"RON04", "RON05", "RON07", "RON09",
											"RON11", "RON14", "RON15", "RON16",
											"RON17", "RON18", "RON19", "RON20",
											"RON21", "RON22", "RON23", "RON24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttRomania;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "SE":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("ESSESESS") == 0) {
									String[] qttSweden = { "SES00", "SES01",
											"SES02", "SES04", "SES05", "SES07",
											"SES09", "SES11", "SES14", "SES15",
											"SES16", "SES17", "SES18", "SES19",
											"SES20", "SES21", "SES22", "SES23",
											"SES24", "SEN00", "SEN01", "SEN02",
											"SEN04", "SEN05", "SEN07", "SEN09",
											"SEN11", "SEN14", "SEN15", "SEN16",
											"SEN17", "SEN18", "SEN19", "SEN20",
											"SEN21", "SEN22", "SEN23", "SEN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttSweden;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							case "SK":

								if (pareba
										.getAccount()
										.bicCode(
												pareba.getAccount()
														.accountNumbers(
																pareba.getElement(),
																lengte))
										.compareTo("INGBSKBX") == 0) {
									String[] qttSlovakia = { "SKS00", "SKS01",
											"SKS02", "SKS04", "SKS05", "SKS07",
											"SKS09", "SKS11", "SKS14", "SKS15",
											"SKS16", "SKS17", "SKS18", "SKS19",
											"SKS20", "SKS21", "SKS22", "SKS23",
											"SKS24", "SKN00", "SKN01", "SKN02",
											"SKN04", "SKN05", "SKN07", "SKN09",
											"SKN11", "SKN14", "SKN15", "SKN16",
											"SKN17", "SKN18", "SKN19", "SKN20",
											"SKN21", "SKN22", "SKN23", "SKN24",
											"BEF00", "DIV0I0", "DIV0I1",
											"DIV00c", "DIV00x", "DIV00p",
											"DIV01p", "DIVXML" };

									qttCountry = qttSlovakia;
								} else {
									System.out
											.println("Missing the QTT list!!!!");
								}
								break;

							default:

								System.out
										.println("Geen QTT's om mee te vergelijken! ");

								break;
							}

							if (qttCountry != null) {
								Arrays.sort(qttCountry);
								qttValidation(qttPareba, qttCountry);
								lg.writeLog(pareba, lengte,
										qttValidation(qttPareba, qttCountry));
							}
						}

						lengte++;

					}
					fileNumber++;
					child.delete();

				}
			}
			Thread.sleep(5000);
		}
	}

	private static String qttValidation(ArrayList<String> qttPareba,
			String[] qttCountry) {
		String validation = "";
		if (Arrays.equals(qttPareba.toArray(), qttCountry)) {
			validation = "Same";
		} else {
			for (int i = 0; i < qttCountry.length; i++) {
				validation = validation.concat(qttCountry[i] + " NOT same!!");
			}
		}

		/*
		 * if (Arrays.equals(qttPareba.toArray(), qttCountry)) { validation =
		 * "Same"; } else { for (int i = 0; i < qttPareba.toArray().length; i++)
		 * {
		 * 
		 * for (int j = 0; j < qttCountry.length; j++) {
		 * 
		 * if (qttPareba.toArray()[i] != qttCountry[j]) {
		 * qttPareba.contains(qttCountry[j]); validation = validation.concat(" "
		 * + qttPareba.toArray()[i].toString() + " NOT same");
		 * 
		 * } } }
		 */
		return validation;

	}
}
