package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageBorder {
	public static void main(String[] args) throws IOException {
		String imagePath = "/Users/juneahn/git/m_blia/Resource/TestImage/FailingImg14c7e96b72264dbe948ea38b514380e0.png";
		BufferedImage myPicture = ImageIO.read(new File(imagePath));
		Graphics2D g = (Graphics2D) myPicture.getGraphics();
		g.setStroke(new BasicStroke(0));
		g.setColor(Color.RED);
		for (int i = 0; i < 10; i++) {
			
			g.drawRect(10 - i, 10 - i, myPicture.getWidth() - 20 - i, myPicture.getHeight() - 20 - i);
		}
		ImageIO.write(myPicture, "png", new File(imagePath));
		
	}
}
