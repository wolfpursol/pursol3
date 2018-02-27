package cn.pursol.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

/**
 * 利用POI解析ppt文档
 * @author 张学栋
 * @version 1.0
 */
public class PowerpointReader {

	/**
	  * 解析powerpoint2003，只能获取纯文本
	  * @param path
	  * @return
	  */
	public static String parseP3Text(String path) {
		StringBuffer content = new StringBuffer();
		try{
			InputStream is = new FileInputStream(new File(path));
	    	SlideShow ss = new SlideShow(new HSLFSlideShow(is));//is 为文件的InputStream，建立SlideShow
	    	Slide[] slides = ss.getSlides();//获得每一张幻灯片
	    	for(int i = 0;i < slides.length;i++){
	    		TextRun[] t = slides[i].getTextRuns();//为了取得幻灯片的文字内容，建立TextRun
	    		for(int j = 0;j < t.length;j++){  
	    			content.append(t[j].getText());//这里会将文字内容加到content中去
	    		}
	    		content.append(slides[i].getTitle());
	    	}
	    	return content.toString();
	 	}catch(Exception ex){
	    	//ex.printStackTrace();
	   	}
		return null;
	}
	
	/**
	  * 解析powerpoint2007，只能获取纯文本
	  * 【当前方法可以解析doc、docx、ppt、pptx、xls、xlsx所有文档】
	  * @param path
	  * @return
	  */
	public static String parseP7Text(String path) {
		try{
			File inputFile = new File(path);
			POITextExtractor extractor = ExtractorFactory.createExtractor(inputFile);
	    	return extractor.getText();
	 	}catch(Exception ex){
	    	//ex.printStackTrace();
	   	}
		return null;
	}
	
//	public static void main(String[] args) {
//		String text = PowerpointReader.parseP3Text("C:/111.ppt");
//		//String text = PowerpointReader.parseP7Text("C:/111.pptx");
//		System.out.println(text);
//	}
}
