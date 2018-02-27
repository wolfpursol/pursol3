package cn.pursol.util;


import java.io.*;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


/**
 * 利用pdfbox来抽取pdf文件
 * @author 张学栋
 * @version 1.0
 */
public class PdfReader {

	/**
	  * 解析pdf，获取纯文本
	  * @param path
	  * @return
	  */
	public static String GetTextFromPdf(String path) {
		String content = null;
		try {
			PDDocument pdfdocument = null;
			FileInputStream is = new FileInputStream(path);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			pdfdocument = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			content = stripper.getText(pdfdocument); //利用剥离器获取文档 
			return content;
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String args[]) {
//		String ts = PdfReader.GetTextFromPdf("c:/111.pdf");
//		System.out.println(ts);
//	}
}
