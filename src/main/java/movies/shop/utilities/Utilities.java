package movies.shop.utilities;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class Utilities {
	
	public byte[] copyBaseImage(String rutaOrigen) {
		byte[] info = null;
		
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
			
		} catch (IOException e) {
			System.out.println("No se puede copiar la imagen base: " + e.getMessage());
		}
		
		return info;
	}
}
