package objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Account {

	public Account() {
	}

	private String pseudo;
	
	public Node accountNumbers(Element element, int lengte) {
		return element.getElementsByTagName("axsd:AccNbrs").item(lengte);
	}

	// Method to set the pseudo account for current IBAN account
	public String pseudo(Node accountNumbers) {
		return accountNumbers.getFirstChild().getNextSibling().getFirstChild()
				.getNextSibling().getTextContent(); 
	}
	
	// Method to get the pseudo account for current IBAN account
	public String getPseudo() {
		return pseudo;
	}
	
	// Method to get the length of the pseudo account
	public int pseudoLength(String pseudo) {
		return pseudo.length();
	}

	// Method to get the Biccode of the account
	public String bicCode(Node accountNumbers) {
		return accountNumbers.getNextSibling().getNextSibling().getFirstChild()
				.getTextContent().substring(0, 8);
	}

	// Method to get the first qTransType section for an account
	public Node qTransType(Node accountNumbers) {
		return accountNumbers.getNextSibling().getNextSibling()
				.getNextSibling().getNextSibling().getNextSibling()
				.getNextSibling().getNextSibling().getFirstChild()
				.getFirstChild().getNextSibling().getNextSibling()
				.getFirstChild();
	}

	// Method to get the first type node of the account
	public Node type(Node qTransType) {
		return qTransType.getFirstChild().getFirstChild();
	}

	// Method to get the BEP13 type 'if' present'
	public Node bep13(Node qTransType) {
		return qTransType.getNextSibling().getNextSibling().getFirstChild()
				.getNextSibling().getFirstChild().getNextSibling();
	}

	// Method to get the BEP13 Beneficiary account
	public Node bep13BenefAccount(Node qTransType) {
		return qTransType.getNextSibling().getNextSibling().getFirstChild()
				.getNextSibling().getNextSibling().getNextSibling()
				.getFirstChild().getNextSibling().getFirstChild()
				.getNextSibling().getFirstChild().getNextSibling()
				.getNextSibling().getNextSibling();
	}
}
