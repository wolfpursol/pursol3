package cn.pursol.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.util.file.FileDeal;

/**
 × 获取文件大小的名称
 */
public class FileUtil {

	/**
	 * 获取文件大小的名称
	 * @param filesize 文件大小
	 * @param point 小数点后几位数
	 * @return
	 */
	public static String getFileSizeName(double filesize, int point){
		String name = null;
		if(filesize < 500*1024) 
			name = getPointFileSize(filesize, 1024, point) + "KB";
		if(filesize >= 500*1024 && filesize < 1024*1024*1024) 
			name = getPointFileSize(filesize, 1024*1024, point) + "MB";
		if(filesize >= 1024*1024*1024) 
			name = getPointFileSize(filesize, 1024*1024*1024, point) + "G";
		
		return name;
	}
	
	/**
	 * 获取用户还能上传文件的大小，网盘所剩空间大小
	 * @param networkDiskSize 用户初始化网盘大小
	 * @param uploadfilesize 用户已上传文件大小
	 * @param point 小数点后几位数
	 * @return
	 */
	public static double getUserCanUploadFileSize(int networkDiskSize, double uploadfilesize, int point){
		double canUploadFileSize = 0;
		double uploadfilesize0 = Double.valueOf(getPointFileSize(uploadfilesize, 1024*1024, point));
		double networkDiskSize0 = new Double(networkDiskSize).doubleValue();
		if(networkDiskSize0 > uploadfilesize0){
			canUploadFileSize = networkDiskSize0 - uploadfilesize0;
		}
		
		return canUploadFileSize;
	}
	
	/**
	 * 获取指定小数点位的文件大小
	 * @param filesize
	 * @param basesize
	 * @param point
	 * @return
	 */
	private static String getPointFileSize(double filesize, double basesize, int point){
		if (point < 0) {
            point = 0;
        }
  
        BigDecimal b1 = new BigDecimal(Double.toString(filesize));  
        BigDecimal b2 = new BigDecimal(Double.toString(basesize));  
        return new Double(b1.divide(b2, point, BigDecimal.ROUND_HALF_UP).doubleValue()).toString();
		
		//String size = null;
		//double a = filesize/basesize;
		//String s = a + "";
		//String[] ss = s.split("\\.");
		//if(point > 0){
		//	if(ss[1].length() < point) size = s;
		//	if(ss[1].length() > point){
		//		size = ss[0] + "." + ss[1].substring(0, point);
		//	}
		//}else {
		//	size = ss[0];
		//}
		
		//return size;
	}
	
	/**
	 * 获取某文件夹下的所有文件
	 * @return
	 */
	public static List<File> getFileOfFolder(String filepath){
		List<File> files = new ArrayList<File>();
		File[] file = (new File(filepath)).listFiles();
		for(int i=0; i<file.length; i++){
			if (file[i].isFile()) {
				files.add(file[i]);
			}
			if (file[i].isDirectory()) {
				files.addAll(getFileOfFolder(file[i].getPath()));
			}
		}
		return files;
	}
	
	/**
	 * 判断当前解压的网络课程的首文件(index.html)是否在根目录下，
	 * 如果不在，则把根目录下的文件夹下的所有文件都拷贝到根目录中
	 * @param zippath
	 */
	public static void changeCoursePath(String zippath){
		File[] files = (new File(zippath)).listFiles();
		if(files.length == 2){
			for(int i=0; i<files.length; i++){
				if(files[i].isDirectory()){
					CopyFile.copy(files[i].getPath(), zippath);
					FileDeal.deleteFile(files[i].getPath());
				}
			}
		}
	}
}
