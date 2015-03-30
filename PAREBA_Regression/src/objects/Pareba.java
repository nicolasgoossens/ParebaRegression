package objects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.CopyFile;
import utils.XmlFormatter;

public class Pareba {

	public Pareba(File file, int fileNumber)
			throws ParserConfigurationException, SAXException, IOException, InterruptedException {

		try {

			copy.copyParebaTxttoXml(file);
			File xmlPareba = new File(copy.getFileName());

			XmlFormatter xmlFormatter = new XmlFormatter();
			xmlFormatter.format(xmlFormatter.readFile(xmlPareba.getPath(),
					StandardCharsets.UTF_8));

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlPareba);
						
			NodeList nlXml = doc.getChildNodes();
			Node nXml = nlXml.item(0);
			this.element = (Element) nXml;
			
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	private Element element;
	private CopyFile copy = new CopyFile();
	private Account account = new Account();

	public Account getAccount() {
		return account;
	}

	// Method to get the subscription number out of the PAREBA
	public String getSubscriptionNumber(Element element) {
		return element.getElementsByTagName("xsd:Cid").item(0).getTextContent();
	}

	// Method for getting the element
	public Element getElement() {
		return element;
	}

	public String getTimestamp(Element element) {
		return getElement().getAttribute("Timestamp");
	}

}
