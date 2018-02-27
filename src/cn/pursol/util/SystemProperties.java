package cn.pursol.util;

import com.util.string.PublicResourceBundle;

/**
 * 云平台系统配置文件常量类
 * @author zhangxuedong
 */
public class SystemProperties {

	//前台评论是否允许匿名评论，1=允许匿名评论，0=不允许匿名评论
	public static String commentanony = null;
	//前台评论是否需要审核，1=需要审核，0=无需审核
	public static String commentcheck = null;
	//是否显示练习模式 0隐藏，1显示
	public static String exercisetypedisplay = null;
	//短信是否打开 0不打开，1打开
	public static String smsopen = null;
	static{
		if(commentanony == null){
			commentanony = PublicResourceBundle.getProperty("system", "comment.anony");
		}
		if(commentcheck == null){
			commentcheck = PublicResourceBundle.getProperty("system", "comment.check");
		}
		if(exercisetypedisplay == null){
			exercisetypedisplay = PublicResourceBundle.getProperty("system", "exercisetype.display");
		}
		if(smsopen == null){
			smsopen = PublicResourceBundle.getProperty("system", "sms.open");
		}
	}
}
