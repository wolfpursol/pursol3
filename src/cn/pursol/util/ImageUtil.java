package cn.pursol.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Filename Test.java
 * @Description 图片工具类
 * 
 * @Version 1.0
 * 
 * @Author huqing
 * @Email qing.hu2009@gmail.com
 * 
 * @History
 * 
 */

public class ImageUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageUtil.class);

	public static final float PRE_DEFUALT = 0.85f;

	/**
	 * 
	 * 生成缩略图
	 * 
	 * @param sourceFilePath
	 *            源文件路径
	 * 
	 * @param targetFilePath
	 *            目标文件路径
	 * 
	 * @param width
	 *            目标文件宽
	 * 
	 * @param hight
	 *            目标文件高
	 * 
	 * @throws Exception
	 */

	public static void generateThumbnails(String sourceFilePath,
			String targetFilePath, int width,int hight,boolean flag) throws Exception {

		thumbsl(sourceFilePath, targetFilePath, width, hight,flag, PRE_DEFUALT);

	}

	/**
	 * 
	 * @param sourceFilePath
	 * 
	 * @param targetFilePath
	 * 
	 * @param width
	 *            目标宽
	 * 
	 * @param height
	 *            目标高
	 * 
	 * @param per
	 *            百分比
	 */

	private static void thumb(String sourceFilePath, String targetFilePath,
			int width, int height,

			float per) throws Exception {

		Image src;

		try {

			src = javax.imageio.ImageIO.read(new File(sourceFilePath)); // 构造Image对象

			// 得到源图宽,高

			int oldWidth = src.getWidth(null);

			int oldHeight = src.getHeight(null);

			int newWidth = 0;

			int newHeight = 0;

			double tmpWidth = (oldWidth * 1.00) / (width * 1.00);

			double tmpHeight = (oldHeight * 1.00) / (height * 1.00);

			// 如果图片目标尺寸与原图相同或者目标尺寸不是正方形则不留白处理

			if (oldWidth != width && oldHeight != height) {

				// 图片跟据长宽留白，成一个正方形图。

				BufferedImage oldpic;

				if (oldWidth > oldHeight) {

					oldpic = new BufferedImage(oldWidth, oldWidth,
							BufferedImage.TYPE_INT_RGB);

				} else if (oldWidth < oldHeight) {

					oldpic = new BufferedImage(oldHeight, oldHeight,
							BufferedImage.TYPE_INT_RGB);

				} else {

					oldpic = new BufferedImage(oldWidth, oldHeight,
							BufferedImage.TYPE_INT_RGB);

				}

				Graphics2D g = oldpic.createGraphics();

				g.setColor(Color.white);

				if (oldWidth > oldHeight) {

					g.fillRect(0, 0, oldWidth, oldWidth);

					g.drawImage(src, 0, (oldWidth - oldHeight) / 2, oldWidth,
							oldHeight,

							Color.white, null);

				} else if (oldWidth < oldHeight) {

					g.fillRect(0, 0, oldHeight, oldHeight);

					g.drawImage(src, (oldHeight - oldWidth) / 2, 0, oldWidth,
							oldHeight,

							Color.white, null);

				} else {

					g.drawImage(src.getScaledInstance(oldWidth, oldHeight,
							Image.SCALE_SMOOTH), 0,

					0, null);

				}

				g.dispose();

				src = oldpic;

			}

			// 图片调整为方形结束

			// 计算新图宽,高

			if (oldWidth > width) {

				newWidth = (int) Math.round(oldWidth / tmpWidth);

			} else if (oldWidth < width) {

				newWidth = (int) Math.round(oldWidth / tmpWidth);

			} else {

				newWidth = oldWidth;

			}

			if (oldHeight > height) {

				newHeight = (int) Math.round(oldHeight / tmpHeight);

			} else if (oldHeight < height) {

				newHeight = (int) Math.round(oldHeight / tmpHeight);

			} else {

				newHeight = oldHeight;

			}

			BufferedImage tag = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);

			// 绘制缩小后的图

			// tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);

			tag.getGraphics().drawImage(

			src.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0,
					0, null);

			// 输出文件流

			FileOutputStream outPutStream = new FileOutputStream(targetFilePath);

			JPEGImageEncoder encoder = JPEGCodec
					.createJPEGEncoder(outPutStream);

			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

			/* 压缩质量 */

			jep.setQuality(per, true);

			encoder.encode(tag, jep);

			// 近JPEG编码

			// encoder.encode(tag);

			outPutStream.flush();

			outPutStream.close();

		} catch (IOException e) {

			logger.error("生成缩略图异常 [sourceFilePath = " + sourceFilePath
					+ ", targetFilePath = "

					+ targetFilePath + ", width = " + width + ", height = "
					+ height

					+ ", per = " + per + "]", e);

			throw e;

		}

	}
	
	
	
	/**
	 * 
	 * @param sourceFilePath
	 * 
	 * @param targetFilePath
	 * 
	 * @param width
	 *            目标宽
	 * 
	 * @param height
	 *            目标高
	 * 
	 * @param per
	 *            百分比
	 */

	private static void thumbsl(String sourceFilePath, String targetFilePath,
			int width, int height,boolean flag,float per) throws Exception {

		Image src;

		try {

			src = javax.imageio.ImageIO.read(new File(sourceFilePath)); // 构造Image对象

			// 得到源图宽,高

			int oldWidth = src.getWidth(null);

			int oldHeight = src.getHeight(null);

			int newWidth = 0;

			int newHeight = 0;

			double tmpWidth = (oldWidth * 1.00) / (width * 1.00);

			double tmpHeight = (oldHeight * 1.00) / (height * 1.00);

			// 如果图片目标尺寸与原图相同或者目标尺寸不是正方形则不留白处理

//			if (oldWidth != width && oldHeight != height) {
//
//				// 图片跟据长宽留白，成一个正方形图。
//
//				BufferedImage oldpic;
//
//
//				oldpic = new BufferedImage(oldWidth, oldHeight,
//							BufferedImage.TYPE_INT_RGB);
//
//
//				Graphics2D g = oldpic.createGraphics();
//
//				g.setColor(Color.white);
//
//
//				g.drawImage(src.getScaledInstance(oldWidth, oldHeight,
//							Image.SCALE_SMOOTH), 0,
//
//					0, null);
//
//				g.dispose();
//
//				src = oldpic;
//
//			}

			// 图片调整为方形结束
			if(!flag){
				//按比例
				newWidth = width;
				newHeight = (oldHeight * width)/oldWidth;
				if(newHeight > height){
					newHeight = height;
					newWidth = (oldWidth * height)/oldHeight;
				}
			}else{
				//按给定值
				if(oldWidth > width && oldHeight > height){
					newWidth = width;
					newHeight = height;
				}else{
					newWidth = oldWidth;
					newHeight = oldHeight;
				}
			}

			BufferedImage tag = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);

			// 绘制缩小后的图

			// tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);

			tag.getGraphics().drawImage(

			src.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0,
					0, null);

			// 输出文件流

			FileOutputStream outPutStream = new FileOutputStream(targetFilePath);

			JPEGImageEncoder encoder = JPEGCodec
					.createJPEGEncoder(outPutStream);

			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

			/* 压缩质量 */

			jep.setQuality(per, true);

			encoder.encode(tag, jep);

			// 近JPEG编码

			// encoder.encode(tag);

			outPutStream.flush();

			outPutStream.close();

		} catch (IOException e) {

			logger.error("生成缩略图异常 [sourceFilePath = " + sourceFilePath
					+ ", targetFilePath = "

					+ targetFilePath + ", width = " + width + ", height = "
					+ height

					+ ", per = " + per + "]", e);

			throw e;

		}

	}

	/**
	 * 
	 * 生成缩略图文件名
	 */

	public static String madeThumbFileName(String originalFilename, int width,
			int height) {

		return "t_" + width + "_" + height + "_" + originalFilename;

	}

	/**
	 * 
	 * 生成文件名
	 */

	public static String madeFileName(String originalFilename) {

		return new Long(System.currentTimeMillis()).toString()

		+ originalFilename.substring(originalFilename.lastIndexOf("."),

		originalFilename.length());

	}

	/**
	 * 
	 * 获取图片域名
	 */

	public static String getCustomerImgDomain(String domain, String folder,
			String customerId) {

		return domain + folder + "/" + customerId + "/";

	}

	public static void main(String[] args) {

		try {
			generateThumbnails("D:/111.jpg","D:/111_1.jpg", 900, 600,false);
		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
