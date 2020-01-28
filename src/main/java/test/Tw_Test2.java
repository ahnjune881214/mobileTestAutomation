package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class Tw_Test2 {
	public static void main(String args[]) throws Exception {
		String filePath = "/Users/juneahn/git/m_blia/Resource/TechniqueWriting/SODA/VMS_Android30_Korea_ko_kr.xml";
		String xmlId = "SURVEY_SCAN_DES01";
		String ass = "설치한 앱 외에 검사할 대상을 추가로 선택";
		
		List<String> expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		String results = null;

		expected = twXmlParser(filePath, xmlId);

		if (expected.size() > 1) {
			actual = transrate(ass);
			
		} else {
			actual.add(ass);
		}
		
		expected.removeAll(new HashSet(actual));
		
		if (expected.size() > 0) {
			for (int i = 0; i < actual.size(); i ++) {
				for (int j = 0; j < expected.size(); j++) {
					if (actual.get(i).contains(expected.get(j))) {
						expected.remove(j);
					}
				}
			}
		}
		
		if (expected.size() == 0) {
			System.out.println("[result]] " + true);
			System.out.println("[actual result] : " + actual);
			System.out.println("[expected output] : " + expected);
		} else {
			System.out.println("[result] " + false);
			System.out.println("[actual result] : " + actual);
			System.out.println("[expected output] : " + expected);
		}
	}
	
	public static List<String> twXmlParser(String filePath, String expression)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {	
		
		FileInputStream file = new FileInputStream(new File(filePath));

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(file);
		XPath xPath = XPathFactory.newInstance().newXPath();

		String xmlValue = (String) xPath.evaluate("//*[@name='" + expression + "']", xmlDocument, XPathConstants.STRING);

		List<String> transrator = new ArrayList<String>();			
		
		if (xmlValue.contains("%s") || xmlValue.contains("%d") || xmlValue.contains("\\\\n")) {
			transrator = transrate(xmlValue);
			
			return transrator;
		} else if (xmlValue.contains("\\")) {
			transrator.add(xmlValue.replaceAll("\\\\", ""));

			return transrator;
		} else {
			transrator.add(xmlValue);
			
			return transrator;
		}
	}

	public static List<String> transrate(String xmlValue) {
		// ['], [\\] 공백으로 치환
		String trans = xmlValue.replaceAll("\\\\n", " ").replaceAll("\\'", " ").replaceAll("\\\\", " ").replaceAll("%s", " ")
				.replaceAll("%d", " ").replaceAll("“", " ").replaceAll("”", " ").replaceAll(",", "");

		List<String> set = new ArrayList<String>(Arrays.asList(trans.split(" ")));
		
		for (int i = 0; i < set.size(); i++){
		    if (set.get(i).matches("")){
		    	set.remove(i);
		        i--;
		    }
		}
		
		return set;
	}
}