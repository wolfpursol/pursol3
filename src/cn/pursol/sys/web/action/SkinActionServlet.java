package cn.pursol.sys.web.action;

import java.io.File;
import java.util.StringTokenizer;

import org.apache.struts.action.*; 

import com.util.file.FileDeal;

import javax.servlet.ServletException;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: pursol
 * </p>
 * 
 * @author pursol
 * @version 2.0
 */

public class SkinActionServlet extends ActionServlet {
	static {
        String libpath = System.getProperty("java.library.path");
        if (libpath == null || libpath.length() == 0) {
            throw new RuntimeException("java.library.path is null");
        }
        String path = null;
        StringTokenizer st = new StringTokenizer(libpath, System.getProperty("path.separator"));
        if (st.hasMoreElements()) {
            path = st.nextToken();
        } else {
            throw new RuntimeException("can not split library path:" + libpath);
        }
        String dirPath = SkinActionServlet.class.getResource("/").getPath();
        File dir = new File(dirPath + "\\dll");
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                File file = new File(path + "\\" + f.getName());
                if (!file.exists()) {
                    try {
                        FileDeal.copyFile(f.getPath(), file.getPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

	 public void init() throws ServletException {
		 super.init();
		 System.out.println("javadev.cn版本1.0+++success");
	}

}