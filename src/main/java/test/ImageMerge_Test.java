package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageMerge_Test {

	public static void main(String args[]) throws Exception {
		BufferedImage img1 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/00a5a140e1e54a1b91bf38c53561ce53.png"));
		BufferedImage img2 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/0f022ceaa5074d14beda7964cbe46ee5.png"));
//		BufferedImage img3 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/9add16f1d9264fe99f1f80f342d49f66.png"));
//		BufferedImage img4 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/dbc7879e69f8438786db41cf94fae873.png"));
//		BufferedImage img5 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/6de528f4d9e94b04ae613ac31685cc54.png"));
//		BufferedImage img6 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/07ba5ea193e94777a48568fd6b739d1d.png"));
//		BufferedImage img7 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/f18b6dac5d574b31b236bcd16e03ffc8.png"));
		
//		BufferedImage abcd3 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png"));
		
//		기존 / 추가 
		
		BufferedImage joinedImg = joinBufferedImage(img1, img2);
//		BufferedImage joinedImg = joinBufferedImage(abcd3, img1);
		ImageIO.write(joinedImg, "png", new File("/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png"));
	}

	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
		int offset = 35;
		int width = img1.getWidth() + img2.getWidth() + offset;
		int height = Math.max(img1.getHeight(), img2.getHeight());
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		g2.setPaint(Color.black);
		g2.fillRect(0, 0, width, height);
		g2.setColor(oldColor);
		g2.drawImage(img1, null, 0, 0);
		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
		g2.dispose();
		return newImage;
	}
	
	
	
//	public static void main(String args[]) throws Exception {
//		BufferedImage img1 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/25af6a375bdc4742bd61701fb33578ad.png"));
//		BufferedImage img2 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/11d56131d71d4253972caba21ce9d729.png"));
//		BufferedImage img3 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/9add16f1d9264fe99f1f80f342d49f66.png"));
//		BufferedImage img4 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/dbc7879e69f8438786db41cf94fae873.png"));
//		BufferedImage img5 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/6de528f4d9e94b04ae613ac31685cc54.png"));
//		BufferedImage img6 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/07ba5ea193e94777a48568fd6b739d1d.png"));
//		BufferedImage img7 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FullScreenImg/f18b6dac5d574b31b236bcd16e03ffc8.png"));
//		
//		BufferedImage abcd3 = ImageIO.read(new File("/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png"));
//		
////		기존 / 추가 
//		
//		BufferedImage joinedImg = joinBufferedImage(abcd3, img7);
////		BufferedImage joinedImg = joinBufferedImage(abcd3, img1);
//		ImageIO.write(joinedImg, "png", new File("/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg/joined1.png"));
//	}
//
//	public static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
//		int offset = 35;
//		int width = img1.getWidth() + img2.getWidth() + offset;
//		int height = Math.max(img1.getHeight(), img2.getHeight());
//		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = newImage.createGraphics();
//		Color oldColor = g2.getColor();
//		g2.setPaint(Color.black);
//		g2.fillRect(0, 0, width, height);
//		g2.setColor(oldColor);
//		g2.drawImage(img1, null, 0, 0);
//		g2.drawImage(img2, null, img1.getWidth() + offset, 0);
//		g2.dispose();
//		return newImage;
//	}
}