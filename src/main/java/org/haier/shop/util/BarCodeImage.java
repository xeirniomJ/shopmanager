package org.haier.shop.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;
 
import org.apache.commons.lang.StringUtils;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class BarCodeImage {
    /**
     * 图片类型
     */
    public enum ImgType {
        /**
         * 图片格式：.gif
         */
        GIF(".gif"),
        /**
         * 图片格式：.png
         */
        PNG(".png"),
        /**
         * 图片格式：.jpg
         */
        JPG(".jpg"),
        /**
         * 图片格式：.jpeg
         */
        JPEG(".jpeg"), ;
        ImgType(String value) {
            this.value = value;
        }
        private final String value;
 
        public String getValue() {
            return value;
        }
    }
 
    /**
     * 生成商品条形码
     *
     * @param filePath
     *            商品条形码图片存放路径：../xxx/yyy/
     * @param jbarCode
     *            商品条形码：8位、13位
     * @param format
     *            商品条形码图片格式：.gif/.png/.jpg/.jpeg
     * @return 图片存放路径+图片名称+图片文件类型
     */
    public static String createBarCode(String filePath, String jbarCode, String format) {
    	//条码生产图片的名称
        String barCodeName = jbarCode + format;
        try {
            BufferedImage bi = null;
            int len = jbarCode.length();
            String barCode = jbarCode;
            if (len == 12) {
            	//\条码长度为12不做任何处理
            } else if (len == 13) {//\只对长度为13位的数字产生条码？
                int backCode = checkCode(jbarCode);
                int oldCode = Integer.parseInt(jbarCode.substring(len - 1, len));//获取末尾数字，验证码？
                if (oldCode != backCode) {
                    return null;
                }
                barCode = jbarCode.substring(0, jbarCode.length() - 1);
            }
             
 
            JBarcode localJBarcode13 = new JBarcode(EAN13Encoder.getInstance(),
                    WidthCodedPainter.getInstance(),
                    EAN13TextPainter.getInstance());
 
            bi = localJBarcode13.createBarcode(barCode);
            if (ImgType.GIF.value.equals(format)) {
                saveToGIF(bi, filePath, barCodeName);
            } else if (ImgType.PNG.value.equals(format)) {
                saveToPNG(bi, filePath, barCodeName);
            } else if (ImgType.JPG.value.equals(format) || ImgType.JPEG.value.equals(format)) {
                saveToJPEG(bi, filePath, barCodeName);
            }
 
            localJBarcode13.setEncoder(EAN13Encoder.getInstance());
            localJBarcode13.setPainter(WideRatioCodedPainter.getInstance());
            localJBarcode13.setTextPainter(EAN13TextPainter.getInstance());
            localJBarcode13.setShowCheckDigit(false);
            return filePath + barCodeName;
        } catch (Exception localException) {
            localException.printStackTrace();
            return "失敗";
        }
    }
 
    /**
     * 生成JPEG图片
     *
     * @param paramBufferedImage
     * @param paramString
     */
    private static void saveToJPEG(BufferedImage paramBufferedImage, String filePath,
            String fileName) {
        saveToFile(paramBufferedImage, filePath, fileName, "jpeg");
    }
 
    /**
     * 生成PNG图片
     *
     * @param paramBufferedImage
     * @param paramString
     */
    private static void saveToPNG(BufferedImage paramBufferedImage, String filePath,
            String fileName) {
        saveToFile(paramBufferedImage, filePath, fileName, "png");
    }
 
    /**
     * 生成GIF图片
     *
     * @param paramBufferedImage
     * @param paramString
     */
    private static void saveToGIF(BufferedImage paramBufferedImage, String filePath,
            String fileName) {
        saveToFile(paramBufferedImage, filePath, fileName, "gif");
    }
 
    /**
     * 保存图片文件
     *
     * @param paramBufferedImage
     *            图片流
     * @param filePath
     *            文件路径
     * @param imgName
     *            图片参数
     * @param imgFormat
     *            图片格式
     */
    private static void saveToFile(BufferedImage paramBufferedImage, String filePath,
            String imgName, String imgFormat) {
        try {
            FileOutputStream fileOutputStream = null;
            try {
                String rootPath = System.getProperty("user.dir");
                String imgDir = StringUtils.substringBefore(rootPath, "WEB-INF").concat(filePath);
                String dirPath = "";
                System.out.println("imgDir:"+imgDir);
                try {
                    dirPath = URLDecoder.decode(imgDir, "UTF-8");
                } catch (UnsupportedEncodingException uee) {
                    uee.printStackTrace();
                }
                File dirFile = new File(dirPath);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
                String imgPath = dirPath + "/" + imgName;
                fileOutputStream = new FileOutputStream(imgPath);
            } catch (Exception e) {
                System.out.println("Create Img File Error：" + e.toString());
            }
            ImageUtil.encodeAndWrite(paramBufferedImage, imgFormat,fileOutputStream, 96, 96);
            fileOutputStream.close();
        } catch (Exception localException) {
            System.out.println("Save Img File Error：" + localException);
            localException.printStackTrace();
        }
    }
 
    /**
     * 返回校验码
     * @param code
     *            商品条形码
     * @return 校验码： -1：格式不正确，条形码为全部数字 -2：参数不能为空
     *
     */
    private static int checkCode(String code) {
        int checkCode = -1;
        if (code == null || "".equals(code)) {
            return -2;
        } else if (!Pattern.compile("^[0-9]*$").matcher(code).matches()) {
            checkCode = -1;
        } else {
            try {
                int evensum = 0; // 偶数位的和
                int oddsum = 0; // 奇数位的和
                evensum += Integer.parseInt(code.substring(11, 12));
                evensum += Integer.parseInt(code.substring(9, 10));
                evensum += Integer.parseInt(code.substring(7, 8));
                evensum += Integer.parseInt(code.substring(5, 6));
                evensum += Integer.parseInt(code.substring(3, 4));
                evensum += Integer.parseInt(code.substring(1, 2));
                evensum *= 3;
                oddsum += Integer.parseInt(code.substring(10, 11));
                oddsum += Integer.parseInt(code.substring(8, 9));
                oddsum += Integer.parseInt(code.substring(6, 7));
                oddsum += Integer.parseInt(code.substring(4, 5));
                oddsum += Integer.parseInt(code.substring(2, 3));
                oddsum += Integer.parseInt(code.substring(0, 1));
                int sum = evensum + oddsum;
                int ck = 0;
                if (sum % 10 == 0) {
                    ck = sum;
                } else {
                    ck = (sum / 10 + 1) * 10;
                }
                checkCode = ck - sum;
            } catch (NumberFormatException e) {
                System.out.println("BarCode Format Error:" + e.toString());
            } catch (Exception e) {
                System.out.println("Get Check Code Error:" + e.toString());
            }
        }
        return checkCode;
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
    	String filePath="\\";
    	String jbarCode="6931743408447";
    	String format=".jpg";
    	String id=BarCodeImage.createBarCode(filePath, jbarCode, format);
    	System.out.println(id);
    }
 
}
