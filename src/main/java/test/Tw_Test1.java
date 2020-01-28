package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Tw_Test1 {
	public static void main(String args[]) throws Exception {
		String filePath = "/Users/juneahn/git/git/mobile_m-blia/Resource/TechniqueWriting/SODA/VMS_Android30_Korea_ko_kr.xml";
		String expression = "LOCK_PASW_CACH_DES01";
		
		System.out.println(twXmlParser(filePath, expression));
		
	}
	
	// XML Parser (Assertion에 이용할 예정)
	public static String twXmlParser(String filePath, String expression)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		FileInputStream file = new FileInputStream(new File(filePath));
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(file);
		XPath xPath = XPathFactory.newInstance().newXPath();

//		String evaluation = xPath.compile("//*[@name='" + expression + "']").evaluate(xmlDocument,XPathConstants.STRING).toString();
		String evaluation = (String) xPath.compile("//*[@name='" + expression + "']").evaluate(xmlDocument,XPathConstants.STRING);
//		System.out.println(evaluation);
		
//		return null;

		return evaluation;
	}
}
