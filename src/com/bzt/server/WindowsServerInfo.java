package com.bzt.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import com.sun.management.OperatingSystemMXBean;
import org.apache.tools.ant.taskdefs.Get;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.util.date.DateTime;

/** 
 *  
 * SSH远程执行shell类 
 */  
public class WindowsServerInfo {
	 private static final int CPUTIME = 500;
	 private static final int PERCENT = 100;
	 private static final int FAULTLENGTH = 10;
	 
	 
	public static void main(String[] args) {
		getStatic();
	}
	
	
	
	public static void getStatic(){
		try {
		InetAddress addr = InetAddress.getLocalHost();
		 OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean(); 
		 Properties props=System.getProperties();  
         String ip=addr.getHostAddress().toString(); //获取本机ip  
         String hostName=addr.getHostName().toString(); //获取本机计算机名称  
         System.out.println("操作系统的构架：==="+props.getProperty("os.arch"));
         
         System.out.println("本机IP："+ip+"\n本机名称:"+hostName);  
         System.out.println("操作系统的名称："+props.getProperty("os.name"));  
         System.out.println("操作系统的版本："+props.getProperty("os.version")); 
         System.out.println("最大内存："+osmxb.getTotalPhysicalMemorySize()/1024/1024+"MB");
         System.out.println("JDK路径："+System.getProperty("java.home"));//获取jdk路径     此处有时会获取jre路径
         System.out.println("JDK版本："+System.getProperty("java.version"));  
         System.out.println("OS  name:"+System.getProperty("os.name"));  
         System.out.println(getCpuRatioForWindows());
         System.out.println(getMemery());
         System.out.println(getDisk());
         
         Runtime r = Runtime.getRuntime();
         System.out.println("本地ip地址:"+ ip);
         System.out.println("本地主机名:"+ addr.getHostName());
         System.out.println("JVM可以使用的总内存:"+ r.totalMemory());
         System.out.println("JVM可以使用的剩余内存:"+ r.freeMemory());
         System.out.println("JVM可以使用的处理器个数:"+ r.availableProcessors());
         System.out.println("Java的运行环境版本："+ props.getProperty("java.version"));
         System.out.println("Java的运行环境供应商："+ props.getProperty("java.vendor"));
         System.out.println("Java供应商的URL："+ props.getProperty("java.vendor.url"));
         System.out.println("Java的安装路径："+ props.getProperty("java.home"));
         System.out.println("Java的虚拟机规范版本："+ props.getProperty("java.vm.specification.version"));
         System.out.println("Java的虚拟机规范供应商："+ props.getProperty("java.vm.specification.vendor"));
         System.out.println("Java的虚拟机规范名称："+ props.getProperty("java.vm.specification.name"));
         System.out.println("Java的虚拟机实现版本："+ props.getProperty("java.vm.version"));
         System.out.println("Java的虚拟机实现供应商："+ props.getProperty("java.vm.vendor"));
         System.out.println("Java的虚拟机实现名称："+ props.getProperty("java.vm.name"));
         System.out.println("Java运行时环境规范版本："+ props.getProperty("java.specification.version"));
         System.out.println("Java运行时环境规范供应商："+ props.getProperty("java.specification.vender"));
         System.out.println("Java运行时环境规范名称："+ props.getProperty("java.specification.name"));
         System.out.println("Java的类格式版本号："+ props.getProperty("java.class.version"));
         System.out.println("Java的类路径："+ props.getProperty("java.class.path"));
         System.out.println("加载库时搜索的路径列表："+ props.getProperty("java.library.path"));
         System.out.println("默认的临时文件路径："+ props.getProperty("java.io.tmpdir"));
         System.out.println("一个或多个扩展目录的路径："+ props.getProperty("java.ext.dirs"));
         System.out.println("操作系统的名称："+ props.getProperty("os.name"));
         System.out.println("操作系统的构架："+ props.getProperty("os.arch"));
         System.out.println("操作系统的版本："+ props.getProperty("os.version"));
         System.out.println("文件分隔符："+ props.getProperty("file.separator"));
         System.out.println("路径分隔符："+ props.getProperty("path.separator"));
         System.out.println("行分隔符："+ props.getProperty("line.separator"));
         System.out.println("用户的账户名称："+ props.getProperty("user.name"));
         System.out.println("用户的主目录："+ props.getProperty("user.home"));
         System.out.println("用户的当前工作目录："+ props.getProperty("user.dir"));
         
         
         System.out.println("采集时间："+DateTime.getDate());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	 // 获取内存使用率
	 public static String getMemery() {
	  OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
	    .getOperatingSystemMXBean();
	  // 总的物理内存+虚拟内存
	  long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
	  // 剩余的物理内存
	  long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
	  Double compare = (Double) (1 - freePhysicalMemorySize * 1.0
	    / totalvirtualMemory) * 100;
	  String str = "内存已使用:" + compare.intValue() + "%";
	  return str;
	 }

	 // 获取文件系统使用率
	 public static List<String> getDisk() {
	  // 操作系统
	  List<String> list = new ArrayList<String>();
	  for (char c = 'A'; c <= 'Z'; c++) {
	   String dirName = c + ":/";
	   File win = new File(dirName);
	   if (win.exists()) {
	    long total = (long) win.getTotalSpace();
	    long free = (long) win.getFreeSpace();
	    System.out.println(total/1024/1024);
	    System.out.println(free/1024/1024);
	    Double compare = (Double) (1 - free * 1.0 / total) * 100;
	    String str = c + ":盘  已使用 " + compare.intValue() + "%";
	    list.add(str);
	   }
	  }
	  return list;
	 }

	 // 获得cpu使用率
	 public static String getCpuRatioForWindows() {
	  try {
	   String procCmd = System.getenv("windir")
	     + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
	   // 取进程信息
	   long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
	   Thread.sleep(CPUTIME);
	   long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
	   if (c0 != null && c1 != null) {
	    long idletime = c1[0] - c0[0];
	    long busytime = c1[1] - c0[1];
	    return "CPU使用率:"
	      + Double.valueOf(
	        PERCENT * (busytime) * 1.0
	          / (busytime + idletime)).intValue()
	      + "%";
	   } else {
	    return "CPU使用率:" + 0 + "%";
	   }
	  } catch (Exception ex) {
	   ex.printStackTrace();
	   return "CPU使用率:" + 0 + "%";
	  }
	 }

	 // 读取cpu相关信息
	 private static long[] readCpu(final Process proc) {
	  long[] retn = new long[2];
	  try {
	   proc.getOutputStream().close();
	   InputStreamReader ir = new InputStreamReader(proc.getInputStream());
	   LineNumberReader input = new LineNumberReader(ir);
	   String line = input.readLine();
	   if (line == null || line.length() < FAULTLENGTH) {
	    return null;
	   }
	   int capidx = line.indexOf("Caption");
	   int cmdidx = line.indexOf("CommandLine");
	   int rocidx = line.indexOf("ReadOperationCount");
	   int umtidx = line.indexOf("UserModeTime");
	   int kmtidx = line.indexOf("KernelModeTime");
	   int wocidx = line.indexOf("WriteOperationCount");
	   long idletime = 0;
	   long kneltime = 0;
	   long usertime = 0;
	   while ((line = input.readLine()) != null) {
	    if (line.length() < wocidx) {
	     continue;
	    }
	    // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
	    // ThreadCount,UserModeTime,WriteOperation
	    String caption = substring(line, capidx, cmdidx - 1).trim();
	    String cmd = substring(line, cmdidx, kmtidx - 1).trim();
	    if (cmd.indexOf("wmic.exe") >= 0) {
	     continue;
	    }
	    String s1 = substring(line, kmtidx, rocidx - 1).trim();
	    String s2 = substring(line, umtidx, wocidx - 1).trim();
	    if (caption.equals("System Idle Process")
	      || caption.equals("System")) {
	     if (s1.length() > 0)
	      idletime += Long.valueOf(s1).longValue();
	     if (s2.length() > 0)
	      idletime += Long.valueOf(s2).longValue();
	     continue;
	    }
	    if (s1.length() > 0)
	     kneltime += Long.valueOf(s1).longValue();
	    if (s2.length() > 0)
	     usertime += Long.valueOf(s2).longValue();
	   }
	   retn[0] = idletime;
	   retn[1] = kneltime + usertime;
	   return retn;
	  } catch (Exception ex) {
	   ex.printStackTrace();
	  } finally {
	   try {
	    proc.getInputStream().close();
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	  }
	  return null;
	 }

	 /**
	  * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
	  *
	  * @param src
	  *            要截取的字符串
	  * @param start_idx
	  *            开始坐标（包括该坐标)
	  * @param end_idx
	  *            截止坐标（包括该坐标）
	  * @return
	  */
	 private static String substring(String src, int start_idx, int end_idx) {
	  byte[] b = src.getBytes();
	  String tgt = "";
	  for (int i = start_idx; i <= end_idx; i++) {
	   tgt += (char) b[i];
	  }
	  return tgt;
	 }
}

      
   