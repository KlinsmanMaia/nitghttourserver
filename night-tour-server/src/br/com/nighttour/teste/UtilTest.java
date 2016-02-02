package br.com.nighttour.teste;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import br.com.nighttour.model.dto.FotoDTO;
import br.com.nighttour.model.entity.Picture;

public class UtilTest {

	public static Picture getDefaultPicture(){
		Picture picture = new Picture();
		picture.setTimestamp(System.currentTimeMillis());
		try{
			BufferedImage originalImage = ImageIO.read(new File("testfiles\\picture.JPG"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			picture.setPicture(imageInByte);
			baos.close();
		} catch(Exception ex){
			ex.printStackTrace();
			
		}
		return picture;
	}
	
	public static FotoDTO getFotoDTOFromPicture(Picture picture){
		FotoDTO fotoDTO = new FotoDTO();
		fotoDTO.setImagem(picture.getPicture());
		fotoDTO.setTimestamp(picture.getTimestamp());
		return fotoDTO;
	}
	
}
