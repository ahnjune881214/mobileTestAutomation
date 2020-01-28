package com.ahnlab.android.objects;

import static org.testng.Assert.assertEquals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ahnlab.setup.AndroidSetup;
import com.ahnlab.utils.Utils;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class AppiumObj extends AndroidSetup {

	// 정리 후 지워야함
	
	// UpdateTable updateTb = new UpdateTable();
	DefaultObj defaultObj = new DefaultObj();

	public static void main(String[] args) {
		// String generatedString = RandomStringUtils.randomAlphanumeric(18);
		// System.out.println(generatedString);
		
		
//		for(int i = 0; i < 10; i++) {
			System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
//		}
		
	}

	@Step("[Click_Id] {2}")
	public static void clickId(String buildNo, String deviceRantalNo, String id, String actualResult) throws Exception {

//		waitId(id);
//		String testImgPath = idScreenshot(deviceRantalNo, id, Utils.generatedImgName()); //
//		String testElePath = idEleScreenshot(deviceRantalNo, id, testImgPath); //

//		attach(testImgPath);
//		defaultObj.wait(id, );
		assertEquals(getDriver().getPageSource().contains(actualResult), true);
		
		getDriver().findElement(By.id(id)).click();
		
//		UpdateTable.updateDetailTestInfo(buildNo, deviceRantalNo, "", "", actualResult, getDriver().getPageSource(),
//				getDriver().getPageSource().contains(actualResult), testImgPath, testElePath);

//		System.out.println("[BuildNo] " + buildNo + " [DeviceRantalNo] " + deviceRantalNo + " [Click_Id] " + id
//				+ " [Img_Path] " + testImgPath + " [Ele_Path] " + testElePath + " [Assert] "
//				+ getDriver().getPageSource().contains(actualResult));
	}

	@Step("DeviceName {0} ClickXpath {1}")
	public void clickXpath(String device, String xpath) throws Exception {
		waitXpath(xpath);
		String testImgPath = xpathScreenshot(device, xpath, Utils.nameingConvention()); //
		xpathEleScreenshot(device, xpath, testImgPath); //
		getDriver().findElement(By.xpath(xpath)).click();
		System.out.println("[Device] " + device + " [Click_Xpath] " + xpath + " [Img_Path] " + testImgPath);
	}

	@Step("DeviceName {0} AccessibilityId {1}")
	public void clickAccessibilityId(String device, String accId) throws Exception {
		waitAccessibilityId(accId);
		String testImgPath = accIdScreenshot(device, accId, Utils.nameingConvention()); //
		accIdEleScreenshot(device, accId, testImgPath); //
		getDriver().findElement(ByAccessibilityId.AccessibilityId(accId)).click();
		System.out.println("[Device] " + device + " [Click_AccessibilityId] " + accId + " [Img_Path] " + testImgPath);
	}

	// wait Id
	public void waitId(String id) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id))).click();
	}

	// wait Xpath
	public void waitXpath(String xpath) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	// wait text
	public void waitText(String text) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("com.ahnlab.v3mobilesecurity.soda:id/tvResult"), "새로운 보안 위협 요소가 없습니다."));
		System.out.println("wait  ddddd ");
	}

	// wait AccessibilityId
	public void waitAccessibilityId(String accessibilityId) throws Exception {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ByAccessibilityId.AccessibilityId(accessibilityId)));
	}

	public String idEleScreenshot(String device, String id, String testImgPath) throws Exception {
		String filePath = "./img/elementImg/" + com.ahnlab.utils.Utils.nameingConvention();
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));

		Point point = getDriver().findElement(By.id(id)).getLocation();
		int eleWidth = getDriver().findElement(By.id(id)).getSize().getWidth();
		int eleHeight = getDriver().findElement(By.id(id)).getSize().getHeight();

		File file = new File(filePath);
		BufferedImage fullImg = ImageIO.read(file);
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", file);

		filePath = "./img/elementImg/" + Utils.nameingConvention();
		File screenshotLocation = new File("./img/elementImg/" + Utils.nameingConvention());
		scrFile.delete();
		FileUtils.copyFile(file, screenshotLocation);
		return filePath;
	}

	public void xpathEleScreenshot(String device, String xpath, String testImgPath) throws Exception {
		String filePath = "./img/elementImg/" + Utils.nameingConvention();
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));

		Point point = getDriver().findElement(By.xpath(xpath)).getLocation();
		int eleWidth = getDriver().findElement(By.xpath(xpath)).getSize().getWidth();
		int eleHeight = getDriver().findElement(By.xpath(xpath)).getSize().getHeight();

		File file = new File(filePath);
		BufferedImage fullImg = ImageIO.read(file);
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", file);
		File screenshotLocation = new File("./img/elementImg/" + Utils.nameingConvention());
		scrFile.delete();
		FileUtils.copyFile(file, screenshotLocation);
	}

	public void accIdEleScreenshot(String device, String accId, String testImgPath) throws Exception {
		String filePath = "./img/elementImg/" + Utils.nameingConvention();
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));

		Point point = getDriver().findElementByAccessibilityId(accId).getLocation();
		int eleWidth = getDriver().findElementByAccessibilityId(accId).getSize().getWidth();
		int eleHeight = getDriver().findElementByAccessibilityId(accId).getSize().getHeight();

		File file = new File(filePath);
		BufferedImage fullImg = ImageIO.read(file);
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", file);
		File screenshotLocation = new File("./img/elementImg/" + Utils.nameingConvention());
		scrFile.delete();
		FileUtils.copyFile(file, screenshotLocation);
	}

	@Attachment(type = "image/png", fileExtension = "png", value = "att")
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

	public String idScreenshot(String device, String id, String path_screenshot) throws Exception, IOException {
		Thread.sleep(1000);
		String filePath = "./img/testImg/" + path_screenshot;

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));
		Thread.sleep(1000);

		File imageFile = new File(filePath);
		BufferedImage img = ImageIO.read(imageFile);

		Point point = getDriver().findElement(By.id(id)).getLocation();
		int eleWidth = getDriver().findElement(By.id(id)).getSize().getWidth();
		int eleHeight = getDriver().findElement(By.id(id)).getSize().getHeight();

		Graphics2D graph = img.createGraphics();
		graph.setColor(Color.RED);
		for (int i = 0; i < 10; i++) {
			graph.drawRect(point.getX() - i, point.getY() - i, eleWidth + (i * 2), eleHeight + (i * 2));
		}
		graph.dispose();
		ImageIO.write(img, "png", new File(filePath));

		return filePath;
	}

	public String xpathScreenshot(String device, String xpath, String path_screenshot) throws Exception, IOException {
		Thread.sleep(1000);
		String filePath = "./img/testImg/" + path_screenshot;

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));
		Thread.sleep(1000);

		File imageFile = new File(filePath);
		BufferedImage img = ImageIO.read(imageFile);

		Point point = getDriver().findElement(By.xpath(xpath)).getLocation();
		int eleWidth = getDriver().findElement(By.xpath(xpath)).getSize().getWidth();
		int eleHeight = getDriver().findElement(By.xpath(xpath)).getSize().getHeight();

		Graphics2D graph = img.createGraphics();
		graph.setColor(Color.RED);
		graph.drawRect(point.getX(), point.getY(), eleWidth, eleHeight);
		graph.dispose();
		ImageIO.write(img, "png", new File(filePath));

		return filePath;
	}

	public String accIdScreenshot(String device, String accId, String path_screenshot) throws Exception, IOException {
		Thread.sleep(1000);
		String filePath = "./img/testImg/" + path_screenshot;

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filePath));
		Thread.sleep(1000);

		File imageFile = new File(filePath);
		BufferedImage img = ImageIO.read(imageFile);

		Point point = getDriver().findElementByAccessibilityId(accId).getLocation();
		int eleWidth = getDriver().findElementByAccessibilityId(accId).getSize().getWidth();
		int eleHeight = getDriver().findElementByAccessibilityId(accId).getSize().getHeight();

		Graphics2D graph = img.createGraphics();
		graph.setColor(Color.RED);
		graph.drawRect(point.getX(), point.getY(), eleWidth, eleHeight);
		graph.dispose();
		ImageIO.write(img, "png", new File(filePath));

		return filePath;
	}
}
