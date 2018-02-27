package cn.pursol.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter; 

import javax.servlet.http.HttpServletRequest;
  
import com.util.date.DateTime;
import com.util.string.PublicResourceBundle;
 
/**
  * @(#)TxtLogsUtil.java
  * 
  * 包名：com.bzt.sys.util
  * 功能描述：
  * 公用方法描述：
  * 
  */
public class TxtLogsUtil {

	private static TxtLogsUtil instance = null;
 
	public static TxtLogsUtil getInstance(){
		if(instance==null){
			instance = new TxtLogsUtil();
		}
		return instance;
	}
	
	/**
	 * @description 记录访问IP地址和访问时间
	 * @param request void 
	 * @author ChengSS
	 */
	public void addIpToTxt(HttpServletRequest request,String requestURI) {
		String isopen = PublicResourceBundle.getProperty("system", "txtlogs.isopen");
		if (isopen != null && "1".equals(isopen)) {
			String ipAddress = IpUtil.getIpAddr(request);
			FileWriter fw = null;
			try {
				// 如果文件存在，则追加内容；如果文件不存在，则创建文件
				String path = PublicResourceBundle.getProperty("system", "txtlogs.path"); 
				if(path==null||"".equals(path.trim())){
					path = request.getSession().getServletContext().getRealPath("/");
				}  
				String filename = "txtlogs"+DateTime.getDate("yyyy-MM-dd")+".txt"; 
				File f = new File(path+filename); 
				fw = new FileWriter(f, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("访问IP地址是：" + ipAddress + "==||==时间：" + DateTime.getDate() + "==||==访问路径是：" + requestURI);
				pw.println("--------------------------------------------------------------------------------------------------------------");
				pw.flush();
				fw.flush();
				pw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	/**
	 * @description 记录访问IP地址和访问时间
	 * @param request void 
	 * @author ChengSS
	 */
	public void addSQLTxt(HttpServletRequest request,String requestURI) {
		String isopen = PublicResourceBundle.getProperty("system", "txtlogs.isopen");
		if (isopen != null && "1".equals(isopen)) {
			String ipAddress = IpUtil.getIpAddr(request);
			FileWriter fw = null;
			try {
				// 如果文件存在，则追加内容；如果文件不存在，则创建文件
				String path = PublicResourceBundle.getProperty("system", "txtlogs.path"); 
				if(path==null||"".equals(path.trim())){
					path = request.getSession().getServletContext().getRealPath("/");
				}  
				String filename = "txtsqllogs"+DateTime.getDate("yyyy-MM-dd")+".txt"; 
				File f = new File(path+filename); 
				fw = new FileWriter(f, true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("访问IP地址是：" + ipAddress + "==||==时间：" + DateTime.getDate() + "==||==路径及参数：" + requestURI);
				pw.println("--------------------------------------------------------------------------------------------------------------");
				pw.flush();
				fw.flush();
				pw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
}
