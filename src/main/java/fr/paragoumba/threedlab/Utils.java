package fr.paragoumba.threedlab;

import java.io.*;
import java.util.Scanner;

public class Utils {

    private Utils(){}

    public static void copyResource(String resource){

        try(InputStream is = Exporter.class.getResourceAsStream("/" + resource);
            Scanner in = new Scanner(is);
            FileOutputStream fos = new FileOutputStream("export/" + resource);
            PrintWriter out = new PrintWriter(fos)){

            while (in.hasNext()){

                out.println(in.nextLine());

            }

        } catch (IOException e){

            e.printStackTrace();

        }
    }
}
