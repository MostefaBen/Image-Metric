


public class ImageMetric3 {
     public static void main(String args[])
  {
      //String filename="C:/Users/client/Desktop/lena/";
      String img1="Images/lena10.jpg";
      String img2="Images/lena14.jpg";
      
     
      Metric mit =  new Metric(img1,img2);
      mit.readImages();
      
      int channels=3;//because it's RGB color space
      double mse = mit.calculMSE(channels);
      double rmse= mit.calculRMSE(mse);
      int max=255;//becouse pixel is between [0-255]
      double psnr= mit.calculPSNR(max,rmse);
      
      System.out.println("MSE: "+mse);
      System.out.println("RMSE: "+rmse);
      System.out.println("PSNR: "+psnr);
  }
}
