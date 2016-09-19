

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Metric {

    private String imagePath1;
    private String imagePath2;
    private BufferedImage img1 = null;
    private BufferedImage img2 = null;

    public Metric(String imgp1, String imgp2) {
    	
        this.imagePath1 = imgp1;
        this.imagePath2 = imgp2;
    }

    public void readImages() {
        try {

            File f1 = new File(this.imagePath1);
            File f2 = new File(this.imagePath2);

           
            img1 = ImageIO.read(f1);
            img2 = ImageIO.read(f2);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double calculMSE(int channels) {

        int width1 = img1.getWidth();
        int width2 = img2.getWidth();
        int height1 = img1.getHeight();
        int height2 = img2.getHeight();
        double powdiff = -1;

        if ((width1 != width2) || (height1 != height2)) {

            System.err.println("Error: Images dimensions mismatch");

        } else {

            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgb1 = img1.getRGB(x, y);
                    int rgb2 = img2.getRGB(x, y);

                    /* diff = Math.abs(rgb2 - rgb1);
                     quadiff= quadiff + Math.pow(diff, 2);*/
                    int r1 = (rgb1 >> 16) & 0xff;
                    int g1 = (rgb1 >> 8) & 0xff;
                    int b1 = (rgb1) & 0xff;

                    int r2 = (rgb2 >> 16) & 0xff;
                    int g2 = (rgb2 >> 8) & 0xff;
                    int b2 = (rgb2) & 0xff;

                    powdiff += Math.pow((r1 - r2), 2);
                    powdiff += Math.pow((g1 - g2), 2);
                    powdiff += Math.pow((b1 - b2), 2);
                }
            }
        }
        
        double n = width1 * height1 * channels;
        double mse = powdiff / n;

        return mse;
    }

    public double calculRMSE(double mse) {

        double rmse = Math.sqrt(mse);
        return rmse;
    }

    public double calculPSNR(int d, double rmse) {

        double psnr = 20 * Math.log10(d / rmse);
        return psnr;
    }

}
