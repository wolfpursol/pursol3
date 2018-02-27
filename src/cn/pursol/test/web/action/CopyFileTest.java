package cn.pursol.test.web.action;

import cn.pursol.util.CopyFile;
import cn.pursol.util.ImageUtil;

public class CopyFileTest {
	public static void main(String[] args) {
		for (int i = 0; i < 300; i++) {
			try {
				ImageUtil.generateThumbnails("http://q4.qlogo.cn/g?b=qq&nk="+241928000+i+"&s=100","D:/test/"+241928000+i+".png", 100, 100,false);
				System.out.println("执行完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//CopyFile.copy("http://q4.qlogo.cn/g?b=qq&nk="+241928000+i+"&s=100", "D:/test/"+241928000+i+".png");
			
		}
	}
}
