package fr.paragoumba.threedlab;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ResourceManager {
    
    private static HashMap<String, BufferedImage> images = new HashMap<>();

    public static BufferedImage getImage(String path){
        
        if (images.containsKey(path)){
            
            return images.get(path);
            
        }

        try {

            BufferedImage image = ImageIO.read(ResourceManager.class.getResourceAsStream(path));

            images.put(path, image);

            return image;

        } catch (IOException e){

            e.printStackTrace();
            return null;

        }
    }
}
