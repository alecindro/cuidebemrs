package br.com.cuidebem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class LoadImageTest {

	
	public static void main(String[] args){
		String _url = "http://lar.cuidebemapp.com/rs/images/paciente/2";
		try {
			URL url = new URL(_url);
			URLConnection conn = url.openConnection();
			InputStream stream = conn.getInputStream();
			final BufferedImage bufferedImage = ImageIO.read(stream);
			  ImageIO.write(bufferedImage, "jpg", new File("d:/image.jpg"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
