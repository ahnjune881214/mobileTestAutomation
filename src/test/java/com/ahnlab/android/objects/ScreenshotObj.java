package com.ahnlab.android.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.capabilities.ExtentReport;
import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;

public class ScreenshotObj extends AndroidSetup {
	static WebDriverWait wait = new WebDriverWait(getDriver(), 20);
	
	public static String screenshot(String behaviorMethod, String behaviorElement) throws Exception {		
		Point point = null;
		int eleWidth = 0;
		int eleHeight = 0;
		
		if (behaviorMethod.matches("m_click_id") | behaviorMethod.matches("m_sendkey_id")) {
			point = getDriver().findElement(By.id(behaviorElement)).getLocation();
			eleWidth = getDriver().findElement(By.id(behaviorElement)).getSize().getWidth();
			eleHeight = getDriver().findElement(By.id(behaviorElement)).getSize().getHeight();
		} else if (behaviorMethod.matches("m_click_xpath") | behaviorMethod.matches("m_sendkey_xpath")) {
			point = getDriver().findElement(By.xpath(behaviorElement)).getLocation();
			eleWidth = getDriver().findElement(By.xpath(behaviorElement)).getSize().getWidth();
			eleHeight = getDriver().findElement(By.xpath(behaviorElement)).getSize().getHeight();
		} else if (behaviorMethod.matches("m_click_accid") | behaviorMethod.matches("m_sendkey_accid")) {
			point = getDriver().findElementByAccessibilityId(behaviorElement).getLocation();
			eleWidth = getDriver().findElementByAccessibilityId(behaviorElement).getSize().getWidth();
			eleHeight = getDriver().findElementByAccessibilityId(behaviorElement).getSize().getHeight();
		} 

		String fullScreenshotNaming = fullScreenshot("./Resource/TestImage/FullScreenImg/", point, eleWidth, eleHeight);
		String eleScreenshotNaming = elementScreenshot("./Resource/TestImage/ElementImg/", point, eleWidth, eleHeight);
		
		fullImg = fullScreenshotNaming;
		eleImg = eleScreenshotNaming;
		
//		System.out.println("\t[PreClickEleScreenshot] FullScreenPath : " + fullScreenshotNaming + " / ElementScreenPath : " + eleScreenshotNaming);
		
		attach(fullScreenshotNaming);
		
		return eleImg;
	}
	
	public static String objectScreenshot(String behaviorMethod, String behaviorElement) throws Exception {		
		Point point = null;
		int eleWidth = 0;
		int eleHeight = 0;
		String fileName = null;
		
		
		if (behaviorMethod.contains("m_capture_ele")) {
			if (behaviorMethod.contains("m_capture_ele_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				point = getDriver().findElement(By.id(behaviorElement)).getLocation();
				eleWidth = getDriver().findElement(By.id(behaviorElement)).getSize().getWidth();
				eleHeight = getDriver().findElement(By.id(behaviorElement)).getSize().getHeight();
			} else if (behaviorMethod.contains("m_capture_ele_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				
				point = getDriver().findElement(By.xpath(behaviorElement)).getLocation();
				eleWidth = getDriver().findElement(By.xpath(behaviorElement)).getSize().getWidth();
				eleHeight = getDriver().findElement(By.xpath(behaviorElement)).getSize().getHeight();
			}
			fileName = elementScreenshot("./Resource/TestImage/ElementImg/", point, eleWidth, eleHeight);
			eleImg = fileName;
			ExtentReport.pass("[ele_Screenshot] " + behaviorElement, "/Users/juneahn/git/m_blia/" + eleImg);
		} else if (behaviorMethod.contains("m_capture_src")) {
			if (behaviorMethod.contains("m_capture_src_id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(behaviorElement)));
				
				point = getDriver().findElement(By.id(behaviorElement)).getLocation();
				eleWidth = getDriver().findElement(By.id(behaviorElement)).getSize().getWidth();
				eleHeight = getDriver().findElement(By.id(behaviorElement)).getSize().getHeight();
			} else if (behaviorMethod.contains("m_capture_src_xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(behaviorElement)));
				
				point = getDriver().findElement(By.xpath(behaviorElement)).getLocation();
				eleWidth = getDriver().findElement(By.xpath(behaviorElement)).getSize().getWidth();
				eleHeight = getDriver().findElement(By.xpath(behaviorElement)).getSize().getHeight();
			}
			fileName = fullScreenshot("./Resource/TestImage/FullScreenImg/", point, eleWidth, eleHeight);
			fullImg = fileName;
			ExtentReport.pass("[full_Screenshot] " + behaviorElement, "/Users/juneahn/git/m_blia/" + fullImg);
		} 
		return fileName;		
	}
	
	// full Screenshot 
	public static String fullScreenshot(String fullImgFilePath, Point point, int eleWidth, int eleHeight) throws Exception {
//		String naming = "./img/testImg/" + Utils.nameingConvention();
		String naming = fullImgFilePath + Utils.nameingConvention() + ".png";
		File fullImg = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fullImg, new File(naming));
		
		File fullImageFile = new File(naming);
		BufferedImage buffFullImg = ImageIO.read(fullImageFile);
		
		Graphics2D graph = buffFullImg.createGraphics();
		graph.setColor(Color.RED);
		for (int i = 0; i < 10; i++) {
			graph.drawRect(point.getX() - i, point.getY() - i, eleWidth + (i * 2), eleHeight + (i * 2));
		}
		graph.dispose();
		ImageIO.write(buffFullImg, "png", new File(naming));

		return naming;
	}
	
	// element Screenshot
	public static String elementScreenshot(String eleImgFilePath, Point point, int eleWidth, int eleHeight) throws Exception {
//		String nameing = "./img/elementImg/" + Utils.nameingConvention();
		String nameing = eleImgFilePath + Utils.nameingConvention() + ".png";
		
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(nameing));
		
		File file = new File(nameing);
		BufferedImage fullImg = ImageIO.read(file);
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", file);

//		nameing = eleImgFilePath + Utils.nameingConvention();
//		File screenshotLocation = new File("./img/elementImg/" + Utils.nameingConvention());
		File screenshotLocation = new File(nameing);
		scrFile.delete();
		FileUtils.copyFile(file, screenshotLocation);
		
		return nameing;
	}
	
	public static String mergeImgArray(ArrayList<String> mergeImages) throws Exception {
		String img1 = null;
		String img2 = null;
		String img3 = null;
		String img4 = null;
		
		for (int i = 0; i < mergeImages.size(); i++) {
			if (i == 0) {
				img1 = mergeImages.get(i);
			} else if (i == 1) {
				img2 = mergeImages.get(i);
				img3 = mergeRun(img1, img2);
			}  else {
				img2 = mergeImages.get(i);
				img3 = mergeRun(img3, img2);
			}
			
			if (i == mergeImages.size()-1) {
//				System.out.println("img : " + img3);
				img4 = img3;
			}
			
		}
		return img4;
		
//		return null;
	}
	
	public static String mergeRun(String beforeImg, String afterImg) throws Exception {
		BufferedImage img1 = ImageIO.read(new File(beforeImg));
		BufferedImage img2 = ImageIO.read(new File(afterImg));
		
		BufferedImage joinedImg = mergeImage(img1, img2);
		
		String fileName = "./Resource/TestImage/FullScreenImg/" + Utils.nameingConvention() + ".png";
		
		ImageIO.write(joinedImg, "png", new File(fileName));
		
		return fileName;
	}
	
	// image 파일 머지
	// img + img + img ... 
	public static BufferedImage mergeImage(BufferedImage img1, BufferedImage img2) {
		int offset = 35;
		int width = img1.getWidth() + img2.getWidth() + offset;
		int height = Math.max(img1.getHeight(), img2.getHeight());
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		g2.setPaint(Color.BLACK);
		g2.fillRect(0, 0, width, height);
		g2.setColor(oldColor);
		g2.drawImage(img1, null, 0, 0);
		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		g2.dispose();
		
		return newImage;
	}
	
//	@Attachment(type = "image/png", fileExtension = "png", value = "att")
	public static byte[] attach(String filePath) {
	    try {
	    	File imageFile = new File(filePath);
	        BufferedImage image = ImageIO.read(imageFile);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        baos.flush();
	        byte[] imageInByte = baos.toByteArray();
	        baos.close();
	        return imageInByte;
	    } catch (IOException e) {
	        return null;
	    }
	}
	
	//error 시 스크린샷
	public static String errScreenshot() throws Exception {
		String naming = "./Resource/TestImage/FailingImg" + Utils.nameingConvention() + ".png";
		File fullImg = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fullImg, new File(naming));
		
		BufferedImage myPicture = ImageIO.read(new File(naming));
		Graphics2D g = (Graphics2D) myPicture.getGraphics();
		g.setStroke(new BasicStroke(0));
		g.setColor(Color.RED);
		for (int i = 0; i < 10; i++) {
			
			g.drawRect(10 - i, 10 - i, myPicture.getWidth() - 20 - i, myPicture.getHeight() - 20 - i);
		}
		ImageIO.write(myPicture, "png", new File(naming));
		
		
		return naming;
	}
	
}
