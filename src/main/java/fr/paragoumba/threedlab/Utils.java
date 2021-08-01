package fr.paragoumba.threedlab;

import java.io.*;

public class Utils {

    private Utils(){}

    public static void copyResource(String resource){

        InputStream is = Exporter.class.getResourceAsStream("/" + resource);

        if (is != null){

            try(BufferedInputStream in = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream("export/" + resource);
                BufferedOutputStream out = new BufferedOutputStream(fos)) {

                out.write(in.readAllBytes());

                is.close();

            } catch (IOException e){

                e.printStackTrace();

            }
        }
    }
}
