package cn.pursol.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

/**
 * 利用POI解析word文档
 * @author 张学栋
 * @version 1.0
 */
public class WordReader {

	/**
	  * 解析word2003，只能获取纯文本
	  * @param path
	  * @return
	  */
	public static String parseW3Text(String path) {
		String text = null;
		try {
			FileInputStream in = new FileInputStream(new File(path));
			WordExtractor ex = new WordExtractor(in);
			text = ex.getText();
		} catch (FileNotFoundException e) {
			text = null;
			//e.printStackTrace();
		} catch (IOException e) {
			text = null;
			//e.printStackTrace();
		}
		return text;
	}
	 
	/**
	  * 解析word2007，只能获取文本
	  * @param path
	  * @return
	  */
	public static String parseW7Text(String path) {
		String text = null;
		try {
			OPCPackage opcp = POIXMLDocument.openPackage(path);
			POIXMLTextExtractor ex = new XWPFWordExtractor(opcp);
			text = ex.getText();
		} catch (IOException e) {
			text = null;
			//e.printStackTrace();
		} catch (XmlException e) {
			text = null;
			//e.printStackTrace();
		} catch (OpenXML4JException e) {
			text = null;
			//e.printStackTrace();
		}
		return text;
	}
	 
//	public static void main(String[] args) {
//		String text = WordReader.parseW3Text("C:/111.doc");
//		//String text = WordReader.parseW7Text("C:/111.docx");
//		System.out.println(text);
//	}
}
