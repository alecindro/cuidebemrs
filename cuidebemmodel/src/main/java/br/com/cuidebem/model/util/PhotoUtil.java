package br.com.cuidebem.model.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class PhotoUtil {
	
	private static final int IMG_WIDTH = 120;
	private static final int IMG_HEIGHT = 120;

	public static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}
	
	public static void resize(InputStream photo, ByteArrayOutputStream baos) throws IOException{
		BufferedImage originalImage = ImageIO.read(photo);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImage =resizeImageWithHint(originalImage, type);
		ImageIO.write( resizeImage, "jpg", baos );
	}
	
	public static byte[] base64(byte[] photo){
		return java.util.Base64.getEncoder().encode(photo);
	}
	
	public static byte[] decoderBase64(byte[] photo){
		return java.util.Base64.getDecoder().decode(photo);
	}
	
	public static String getExtension(String typeImage){
		if(typeImage.startsWith("image/")){
		String[] values = typeImage.split("/");
		return values[1];
		}
		return typeImage;
	}
}

