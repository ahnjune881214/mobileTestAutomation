package com.ahnlab.android.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;

import io.qameta.allure.Attachment;

public class Screenshot extends AndroidSetup{
	// Screenshot
	public byte[] screenshot(String findBy, String elementId, String fullScrennshotImgFilePath, String elementScreenshotImgFIlePath) throws Exception {		
		Point point = null;
		int eleWidth = 0;
		int eleHeight = 0;
		
		if (findBy.matches("id")) {
			point = getDriver().findElement(By.id(elementId)).getLocation();
			eleWidth = getDriver().findElement(By.id(elementId)).getSize().getWidth();
			eleHeight = getDriver().findElement(By.id(elementId)).getSize().getHeight();
		} else if (findBy.matches("xpath")) {
			point = getDriver().findElement(By.xpath(elementId)).getLocation();
			eleWidth = getDriver().findElement(By.xpath(elementId)).getSize().getWidth();
			eleHeight = getDriver().findElement(By.xpath(elementId)).getSize().getHeight();
		} else if (findBy.matches("accessibilityId")) {
			point = getDriver().findElementByAccessibilityId(elementId).getLocation();
			eleWidth = getDriver().findElementByAccessibilityId(elementId).getSize().getWidth();
			eleHeight = getDriver().findElementByAccessibilityId(elementId).getSize().getHeight();
		} 

		String fullScreenshotNaming = fullScreenshot(fullScrennshotImgFilePath, point, eleWidth, eleHeight);
		String eleScreenshotNaming = elementScreenshot(elementScreenshotImgFIlePath, point, eleWidth, eleHeight);
		
		System.out.println("\t[PreClickEleScreenshot] FullScreenPath : " + fullScreenshotNaming + " / ElementScreenPath : " + eleScreenshotNaming);
		
		attach(fullScreenshotNaming);
		
		return null;
	}
	
	// full Screenshot 
	public String fullScreenshot(String fullImgFilePath, Point point, int eleWidth, int eleHeight) throws Exception {
//		String naming = "./img/testImg/" + Utils.nameingConvention();
		String naming = fullImgFilePath + Utils.nameingConvention();
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
	public String elementScreenshot(String eleImgFilePath, Point point, int eleWidth, int eleHeight) throws Exception {
//		String nameing = "./img/elementImg/" + Utils.nameingConvention();
		String nameing = eleImgFilePath + Utils.nameingConvention();
		
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
	public byte[] attach(String filePath) {
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
	
}
