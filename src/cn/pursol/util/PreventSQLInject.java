package cn.pursol.util;

/**
 * @(#)TxtLogsUtil.java
 * 
 *   包名：com.bzt.sys.util 功能描述： 公用方法描述：
 * 
 */
public class PreventSQLInject {


	/**
	 * 判断该字符串中是否含有跨站脚本攻击，如果含有跨站脚本攻击就返回true，否则返回false
	 * 
	 * @Title: havaXSS
	 * @Description: TODO
	 * @param @param str
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public static boolean havaXSS(String str) {
		if (str != null && !str.equals("")) {
			String inj_stra[] = Constants.INJ_STR_SCRIPT.split("\\|");
			for (int i = 0; i < inj_stra.length; i++) {
				String inj_s = inj_stra[i];
				if (inj_s != null && inj_s.trim().length() > 0) {
					if (str.trim().toLowerCase().indexOf(inj_s) >= 0) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}
}
