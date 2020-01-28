package com.ahnlab.android.objects.assertion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AssertTw {
	public static void main(String args[]) throws Exception {
		String twFilePath = "/Users/juneahn/git/mbila/resource/product_resource/esand/tw_document/V3 Endpoint Security_Android_ko_kr.xml";
		String tagName = "CLOUD_SCAN_DES02";
		String actualResult = "클라우드 기반의 혁신적인 악성코드 위협 분석 및 대응 기술로 신·변종 악성코드 및 다양한 보안 위협에 신속하고 정확하게 대응합니다.&#10;강력해진 악성코드 탐지 능력으로 스마트폰을 더욱 안전하게 보호합니다.";
		
		System.out.println(assertTw(twFilePath, tagName, actualResult));
	}
	
//	public static void main(String args[]) throws Exception {
//		
//		long start = System.currentTimeMillis();
//        Thread.sleep(10000);
//        System.out.println("Sleep time in ms = "+(System.currentTimeMillis()-start));
// 
//		
//		String pageString = "AhnLab V3 Mobile Securi…에서 기기의 사진, 미디어, 파일에 액세스하도록 허용하시겠습니까.?";
//		String actualString = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>\n" + 
//				"<hierarchy index=\"0\" class=\"hierarchy\" rotation=\"0\" width=\"1440\" height=\"2712\">\n" + 
//				"  <android.widget.FrameLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.FrameLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[160,99][1280,2710]\" displayed=\"true\">\n" + 
//				"    <android.widget.FrameLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.FrameLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[160,99][1280,2710]\" displayed=\"true\">\n" + 
//				"      <android.widget.FrameLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.FrameLayout\" text=\"\" resource-id=\"android:id/content\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[160,99][1280,2710]\" displayed=\"true\">\n" + 
//				"        <android.view.ViewGroup index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.view.ViewGroup\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[160,99][1280,2710]\" displayed=\"true\">\n" + 
//				"          <android.widget.ScrollView index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.ScrollView\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[216,1016][1224,1796]\" displayed=\"true\">\n" + 
//				"            <android.widget.LinearLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.android.packageinstaller:id/dialog_container\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[216,1016][1224,1796]\" displayed=\"true\">\n" + 
//				"              <android.widget.FrameLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.FrameLayout\" text=\"\" resource-id=\"com.android.packageinstaller:id/desc_container\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[216,1016][1224,1579]\" displayed=\"true\">\n" + 
//				"                <android.widget.LinearLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.android.packageinstaller:id/perm_desc_root\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[286,1079][1168,1579]\" displayed=\"true\">\n" + 
//				"                  <android.widget.ImageView index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.ImageView\" text=\"\" resource-id=\"com.android.packageinstaller:id/permission_icon\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[286,1079][412,1205]\" displayed=\"true\" />\n" + 
//				"                  <android.widget.TextView index=\"1\" package=\"com.google.android.packageinstaller\" class=\"android.widget.TextView\" text=\"AhnLab V3 Mobile Securi…에서 기기의 사진, 미디어, 파일에 액세스하도록 허용하시겠습니까.?\" resource-id=\"com.android.packageinstaller:id/permission_message\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[412,1079][1168,1579]\" displayed=\"true\" />\n" + 
//				"                </android.widget.LinearLayout>\n" + 
//				"              </android.widget.FrameLayout>\n" + 
//				"              <android.widget.LinearLayout index=\"1\" package=\"com.google.android.packageinstaller\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[216,1579][1224,1796]\" displayed=\"true\">\n" + 
//				"                <android.widget.LinearLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.LinearLayout\" text=\"\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[286,1579][1168,1796]\" displayed=\"true\">\n" + 
//				"                  <android.widget.LinearLayout index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.LinearLayout\" text=\"\" resource-id=\"com.android.packageinstaller:id/button_group\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\" focusable=\"false\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[307,1593][1168,1782]\" displayed=\"true\">\n" + 
//				"                    <android.widget.Button index=\"0\" package=\"com.google.android.packageinstaller\" class=\"android.widget.Button\" text=\"거부\" resource-id=\"com.android.packageinstaller:id/permission_deny_button\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[720,1593][944,1782]\" displayed=\"true\" />\n" + 
//				"                    <android.widget.Button index=\"1\" package=\"com.google.android.packageinstaller\" class=\"android.widget.Button\" text=\"허용\" resource-id=\"com.android.packageinstaller:id/permission_allow_button\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\" focusable=\"true\" focused=\"false\" long-clickable=\"false\" password=\"false\" scrollable=\"false\" selected=\"false\" bounds=\"[944,1593][1168,1782]\" displayed=\"true\" />\n" + 
//				"                  </android.widget.LinearLayout>\n" + 
//				"                </android.widget.LinearLayout>\n" + 
//				"              </android.widget.LinearLayout>\n" + 
//				"            </android.widget.LinearLayout>\n" + 
//				"          </android.widget.ScrollView>\n" + 
//				"        </android.view.ViewGroup>\n" + 
//				"      </android.widget.FrameLayout>\n" + 
//				"    </android.widget.FrameLayout>\n" + 
//				"  </android.widget.FrameLayout>\n" + 
//				"</hierarchy>";
//		
//		System.out.println(actualString.contains(pageString));
//		
//	}
	
	@Parameters({"twFilePath"})
	public static boolean assertTw(String twFilePath, String tagName, String actualResult) throws Exception {
		List<String> expectedOutput = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		String expec = null;
		boolean result;

		expectedOutput = twXmlParser(twFilePath, tagName);
		expec = expectedOutput.toString();
		if (expectedOutput.size() > 1) {
			actual = transrate(actualResult);
			
		} else {
			actual.add(actualResult);
		}
		
		expectedOutput.removeAll(new HashSet(actual));
		
		if (expectedOutput.size() > 0) {
			for (int i = 0; i < actual.size(); i ++) {
				for (int j = 0; j < expectedOutput.size(); j++) {
					if (actual.get(i).contains(expectedOutput.get(j))) {
						expectedOutput.remove(j);
					}
				}
			}
		}
		
		if (expectedOutput.size() == 0) {
//			System.out.println("[result] " + true);
//			System.out.println("[actual result] : " + actual);
//			System.out.println("[expected output] " + expec);
//			System.out.println("[Result Data] : " + expectedOutput);
			
			return result = true;
		} else {
			System.out.println("[result] " + false);
			System.out.println("[actual result] : " + actual);
			System.out.println("[expected output] " + expec);
			System.out.println("[expected output] : " + expectedOutput);
			return result = false;
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
