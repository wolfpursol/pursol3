package cn.pursol.util;

public class FileExtUtil {

	 private static String vidio = "|avi|wmv|asf|asx|3gp|mov|vob|mkv|rmvb|wmv9|rm|ts|mpg|mpeg|flv|mp4|";//��Ƶ��swf|��Ϊ����չʾ
	 private static String audio = "|mp3|wma|wav|midi|";//��Ƶ
	 private static String documents = "|doc|docx|ppt|pptx|xls|xlsx|pdf|txt|";//�ĵ�
	 private static String image = "|jpeg|jpg|gif|png|tiff|raw|bmp|";//ͼƬ
	
	 //1 "�ĵ�", 2"ͼƬ", 3"��Ƶ", 4"��Ƶ", 5"����"
	public static String fileExt(String fileExt) {
		fileExt = "|"+fileExt.trim().toLowerCase()+"|";
		if(documents.indexOf(fileExt)>-1){
			return "1";
		}else if(image.indexOf(fileExt)>-1){
			return "2";
		}else if(audio.indexOf(fileExt)>-1){
			return "3";
		}else if(vidio.indexOf(fileExt)>-1){
			return "4";
		}else{
			return "5";
		}
	}
	
	/**
	 * ��ȡ��Դ�ļ�����
	 * @param fileExt �ļ���չ��
	 * @return 1ͼƬ��2��Ƶ��3�ĵ���4������5��Ƶ��6����
	 */
	public static String getRestype(String fileExt) {
		fileExt = "|"+fileExt.trim().toLowerCase()+"|";
		if(documents.indexOf(fileExt)>-1){
			return "3";
		}else if(image.indexOf(fileExt)>-1){
			return "1";
		}else if(audio.indexOf(fileExt)>-1){
			return "5";
		}else if(vidio.indexOf(fileExt)>-1){
			return "2";
		}else if("|swf|".equals(fileExt)){
			return "6";
		}else{
			return "4";
		}
	}

	public static void main(String[] args) {
		//FileExtUtil file = new FileExtUtil();
		//String fileExt = file.fileExt("DOCX");
		//System.out.println("fileext -- "+fileExt);
	}
}
