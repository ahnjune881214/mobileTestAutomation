package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateXML {
	static String startLine = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n";
	static String endLine = "</resources>";

	public static void main(String[] args) throws IOException {
		String filePath = "/Users/juneahn/git/m_blia/Resource/TechniqueWriting/SODA/MTI_V3 Mobile_ios_en_us.xml";
		String writeFilePath = "/Users/juneahn/git/m_blia/Resource/TechniqueWriting/SODA/abc.xml";
//		usingBufferedReader(filePath);

		FileWriter fw = new FileWriter(writeFilePath, true);
		fw.write(startLine);
		fw.write(usingBufferedReader(filePath));
		fw.write(endLine);
		fw.close();

		System.out.println(usingBufferedReader(filePath));
	}

	private static String usingBufferedReader(String filePath) {

		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains("=")) {
//					Pattern ptn = Pattern.compile("([^\"=][A-Za-z0-9가-힣_\\s][^\"=]+)");
					Pattern ptn = Pattern
							.compile("([(▶|\\[|*|%|A-Z |a-z|0-9|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|一-龠|ぁ-ゔ|ァ-ヴ|ー_\\\\s.+][^=\"\\n]+)");

					Matcher matcher = ptn.matcher(sCurrentLine);

					int i = 0;
					String a = null;
					String b = null;

					while (matcher.find()) {
						i++;
						if (i == 1) {
							a = matcher.group();
						} else {
							b = matcher.group();
						}

						if (b != null) {
//							System.out.println("	<string name=\"" + a + "\">" + b + "</string>");
//							contentBuilder.append("	<string name=\"" + a + "\">" + b.replaceAll("\\\\n", "").replaceAll("&", "&amp;").replaceAll("<br/>", "") + "</string>").append("\n");
							contentBuilder
									.append("	<string name=\"" + a + "\">"
											+ b.replaceAll("&", "&amp;").replaceAll("<br/>", "") + "</string>")
									.append("\n");
						}

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

}
