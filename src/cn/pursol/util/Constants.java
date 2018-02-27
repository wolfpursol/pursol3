package cn.pursol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap; 

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;

import com.util.date.DateTime;
import com.util.string.PublicResourceBundle;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:数据常量
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: JAVA2CMS开发组
 * </p>
 * 
 * @author 高丞梁
 * @version 1.0
 */
public class Constants {
	public static String hzlinkUrl = "http://duijie.zjer.cn:8085/";
	//可修改参数，_sk，_ly,_gex 
	public static final String PRODUCTNAME = PublicResourceBundle.getProperty("constants", "productname");
	public static final String VERSIONTYPE=PublicResourceBundle.getProperty("constants", "versiontype");
	public static final String VERSION=PublicResourceBundle.getProperty("constants", "version");
	public static final String SUPPORT=PublicResourceBundle.getProperty("constants", "support");
	public static final String ADDRESS=PublicResourceBundle.getProperty("constants", "address");
	public static final String ZIPCODE=PublicResourceBundle.getProperty("constants", "zipcode");
	public static final String CONTACTS=PublicResourceBundle.getProperty("constants", "contacts");
	public static final String TELEPHONE=PublicResourceBundle.getProperty("constants", "telephone");
	public static final String FAX=PublicResourceBundle.getProperty("constants", "fax");
	public static final String EMAIL=PublicResourceBundle.getProperty("constants", "email");
	public static final String COPYRIGHT=PublicResourceBundle.getProperty("constants", "copyright");
	public static final String ICP=PublicResourceBundle.getProperty("constants", "icp");
	public static final String SYSTEMVERSION=PublicResourceBundle.getProperty("system", "system.version");//集群版/单学校版
	public static final String AREAOPEN=PublicResourceBundle.getProperty("system", "system.areaopen");
	public static final String STUDYSECTION=PublicResourceBundle.getProperty("system", "system.studysection");//学段
	public static final String SYSTEMCURRENTUNITID=PublicResourceBundle.getProperty("system", "system.currentunitid");//单位id
	public static final String SKINVERSION=PublicResourceBundle.getProperty("system", "skin.version");//皮肤风格,默认职教高地 1-职教高地 2-师科学堂
	public static final String SKINUSEROPEN=PublicResourceBundle.getProperty("system", "skin.useropen");//统一用户，如行政班开关导入学生0关闭 1显示
	public static final String SKINCLASSOPEN=PublicResourceBundle.getProperty("system", "skin.classopen");//统一用户，行政班创建开关0关闭 1显示

	//师课阳光SSO相关
	public static final String SKSSOOPEN=PublicResourceBundle.getProperty("ssoconfig", "sk_sso.open");//是否打开sso 0-不对接，1-需要对接	
	public static final String SKSSODOMAIN=PublicResourceBundle.getProperty("ssoconfig", "sk_sso.domain");//认证的域名或ip
	public static final String SKSSOSYSID=PublicResourceBundle.getProperty("ssoconfig", "sk_sso.sysid");//本系统在统一认证系统的唯一标识码
	public static final String SKSECRETID=PublicResourceBundle.getProperty("ssoconfig", "sk_sso.secret");//密钥

	public static final String SKINLOGIN=PublicResourceBundle.getProperty("system", "skin.login");//登录页面的选择 7佛山 8新乡 9石景山教委 10京铁电校 11湖南安化 12贵阳13朝阳实验小学
	public static final String MULTBINDINGLOGINNAME=PublicResourceBundle.getProperty("constants", "multbindingloginname");//多个微信能绑定一个用户的登录名
	
	//ffmpeg路径
	public static final String FFMPEGPATH=PublicResourceBundle.getProperty("system", "ffmpeg.path");
		
	//ftp相关参数
	public static final String FTPHOST=PublicResourceBundle.getProperty("constants", "ftphost");
	public static final String FTPPORT=PublicResourceBundle.getProperty("constants", "ftpport");
	public static final String FTPUSERNAME=PublicResourceBundle.getProperty("constants", "ftpusername");
	public static final String FTPPASSWORD=PublicResourceBundle.getProperty("constants", "ftppassword");

	//京铁电校的接口参数
	public static final String JTDXKEY=PublicResourceBundle.getProperty("jtdxsynchro", "key");
	public static final String JTDXTOKEN=PublicResourceBundle.getProperty("jtdxsynchro", "token");
	public static final String JTDXDATA=PublicResourceBundle.getProperty("jtdxsynchro", "data");
	public static final String JTDXCHECKURL=PublicResourceBundle.getProperty("jtdxsynchro", "checkurl");
	public static final String JTDXSENDURL=PublicResourceBundle.getProperty("jtdxsynchro", "sendurl");
	
	//XSS SQL注入
	public static final String INJ_STR_SCRIPT = PublicResourceBundle.getProperty("system", "system.inj_str_script");
	public static final String INJ_STR_SQL = PublicResourceBundle.getProperty("system", "system.inj_str");
	
	//不可修改参数
	// 对象类型
	public static final String OBJTYPE_MODULE = "module"; // 模块
	public static final String OBJTYPE_ELEMENT = "element"; // 组件
	public static final String OBJTYPE_VIEWSTYLE = "viewstyle"; // 显示样式
	public static final String OBJTYPE_ROLE = "role"; // 角色
	public static final String OBJTYPE_USER = "user"; // 用户
	public static final String OBJTYPE_USER_GROUP = "user_group"; // 用户组
	public static final String OBJTYPE_ROLE_MODULE = "role_module"; // 角色模块
	public static final String DEFAULT_UNITID = "00000000000000000000000000000000"; // 系统单位id
	public static final String SYS_PRODUCT = "cms"; // 用户
	public static final String DEFAULT_PRODUCTID = "1";
	public static final String DEFAULT_SKIN = "blue";
	public static final String DEFAULT_IMGEXT = "|jpg|jpeg|bmp|gif|png|";//图片扩展名称
	public static final int DEFAULT_TKBOOKINFO_WIDTH = 300;//教材设备生成二维码宽度 300

	public static final String DEFAULT_USER_ROLE = "|49|";

	public static final String CLASS_LAYOUTID = "|14|20|21|"; //班级主页，模版id
	public static final String SCHOOL_LAYOUTID = "|17|18|22|23|24|25|26|27|29|30|"; //学校主页，模版id

	public static final String DEFAULT_COMPUTER = "3";// 默认视频服务器
	public static final Integer DEFAULT_KNOPOINT = 1;// 默认知识点ID
	public static final int DEFAULT_FILMNO = 10000000;// 8位数
	public static final double PASSSCORE = 0.6;// 及格率为总分的60%
	public static final double EXCELLENT = 0.8;// 优秀为总分的80%

	public static final int SPLIT_MONTH = 7;//将自然年分割成学校年的分割月份

	public static String getProductname(){ //产品
		return PublicResourceBundle.getProperty("constants", "productname");
	}
	public static String getCopyright(){ //版权
		return PublicResourceBundle.getProperty("constants", "copyright");
	}

	public static String[] CODETYPE_BLANK = {};
	// 状态
	public static String CODETYPE_STATUS = "status";
	public static String[] CODETYPE_STATUS_ID = { "1", "2" };
	public static String[] CODETYPE_STATUS_NAME = { "正常", "禁用" };
	// 状态
	public static String CODETYPE_RSTATE = "rstate";
	public static String[] CODETYPE_RSTATE_ID = { "1", "3" };
	public static String[] CODETYPE_RSTATE_NAME = { "正常", "删除" };
	// 节点类型
	public static String CODETYPE_NODETYPE = "nodetype";
	public static String[] CODETYPE_NODETYPE_ID = { "1", "2", "3" };
	public static String[] CODETYPE_NODETYPE_NAME = { "章", "节", "知识点" };

	// 单位的行政属性
	public static String CODETYPE_UNITADMINISTRATIVE = "administrative";
	public static String[] CODETYPE_UNITADMINISTRATIVE_ID = { "0", "1"};
	public static String[] CODETYPE_UNITADMINISTRATIVE_NAME = { "非行政单位", "行政单位"};

	//学校性质
	public static String CODETYPE_SCHOOLPROPERTY = "schoolproperty";
	public static String[] CODETYPE_SCHOOLPROPERTY_ID = { "1", "2", "3"};
	public static String[] CODETYPE_SCHOOLPROPERTY_NAME = { "基础教育", "职业教育", "高等教育"};

	//学校类型
	public static String CODETYPE_SCHOOLTYPE = "schooltype";
	public static String[] CODETYPE_SCHOOLTYPE_ID = { "1", "2", "3", "4", "5"};
	public static String[] CODETYPE_SCHOOLTYPE_NAME = { "小学", "初中", "高中", "中职", "高职"};
	//日程表
	public static String CODETYPE_CALENDARTYPE = "calendartype";
	public static String[] CODETYPE_CALENDARTYPE_ID = { "0", "1", "2","3"};
	public static String[] CODETYPE_CALENDARTYPE_NAME = {"会议","课程","日常","日程表"};

	public static String CODETYPE_CALENDARCOLORTYPE = "calendarcolortype";
	public static String[] CODETYPE_CALENDARCOLORTYPE_ID = { "0", "1", "2","3"};
	public static String[] CODETYPE_CALENDARCOLORTYPE_NAME = {"#360","#f30","#06c","#BA55D3"};

	// 学科
	public static String CODETYPE_SUBJECT = "subject";
	public static String[] CODETYPE_SUBJECT_ID = { "11", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E","1Z", 
		"21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F","2Z",
		"31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F","3Z"};
	public static String[] CODETYPE_SUBJECT_NAME = { "小学数学", "小学语文", "小学英语", "小学科学", "小学思品", "小学信息", "小学美术", "小学音乐", "小学劳技", "小学健康", "小学体育", "小学书法","小学综合",
		"初中数学", "初中物理", "初中化学", "初中语文", "初中英语", "初中生物", "初中政治", "初中历史", "初中地理", "初中信息", "初中美术", "初中音乐", "初中劳技", "初中书法", "初中体育","初中综合",
		"高中数学", "高中物理", "高中化学", "高中语文", "高中英语", "高中生物", "高中政治", "高中历史", "高中地理", "高中信息", "高中美术", "高中音乐", "高中劳技", "高中通技", "高中体育","高中综合"};
	//铁路职业学校
	//	public static String[] CODETYPE_SUBJECT_ID = { "22", "23", "24", "26"};
	//	public static String[] CODETYPE_SUBJECT_NAME = { "供电技术", "轨道交通", "电气运行", "公共基础"};

	public static String CODETYPE_TKTYPE = "tktype";
	public static String[] CODETYPE_TKTYPE_ID = { "A", "B","J" };
	public static String[] CODETYPE_TKTYPE_NAME = { "单选题", "多选题", "判断题" };
	// 模块状态
	public static String CODETYPE_MSTATE = "mstate";
	public static String[] CODETYPE_MSTATE_ID = { "1", "2", "3" };
	public static String[] CODETYPE_MSTATE_NAME = { "正常", "关闭", "删除" };
	// 用户状态
	public static String CODETYPE_USTATE = "ustate";
	public static String[] CODETYPE_USTATE_ID = { "1", "2", "3","4"};
	public static String[] CODETYPE_USTATE_NAME = { "正常", "禁用", "删除","未通过"};
	// 用户状态
	public static String CODETYPE_TSTATE = "tstate";
	public static String[] CODETYPE_TSTATE_ID = { "0", "1", "2", "3" };
	public static String[] CODETYPE_TSTATE_NAME = { "未激活", "正常", "禁用", "删除" };
	// 性别
	public static String CODETYPE_SEX = "sex";
	public static String[] CODETYPE_SEX_ID = { "0", "1", "2" };
	public static String[] CODETYPE_SEX_NAME = { "女", "男", "保密" };
	// 难度
	public static String CODETYPE_DIFFICULTY = "difficulty";
	public static String[] CODETYPE_DIFFICULTY_ID = { "1", "2", "3", "4"};
	public static String[] CODETYPE_DIFFICULTY_NAME = { "容易","一般","较难","极难" };
	// 知识要求
	public static String CODETYPE_LEVEL = "level";
	public static String[] CODETYPE_LEVEL_ID = { "A", "B", "C", "D" };
	public static String[] CODETYPE_LEVEL_NAME = { "A", "B", "C", "D" };
	// 真实年级
	public static String CODETYPE_REALGRADEID = "realgradeid";
	public static String[] CODETYPE_REALGRADEID_ID = { "11", "12", "13", "14", "15", "16", "21", "22", "23", "31", "32", "33" };
	public static String[] CODETYPE_REALGRADEID_NAME = { "一年级", "二年级", "三年级", "四年级", "五年级", "六年级", "七年级", "八年级", "九年级", "高一年级", "高二年级", "高三年级" };
	//铁路职业学校
	//	public static String[] CODETYPE_REALGRADEID_ID = { "21", "22", "23" };
	//	public static String[] CODETYPE_REALGRADEID_NAME = { "轨道交通", "供电技术", "电气运行" };

	// 是否
	public static String CODETYPE_BOOLEAN = "boolean";
	public static String[] CODETYPE_BOOLEAN_ID = { "0", "1" };
	public static String[] CODETYPE_BOOLEAN_NAME = { "否", "是" };

	// 私有，共享
	public static String CODETYPE_SHARE = "share";
	public static String[] CODETYPE_SHARE_ID = { "1", "2", "3" };
	public static String[] CODETYPE_SHARE_NAME = { "私有", "待审核", "共享" };
	// 显示,隐藏
	public static String CODETYPE_VIEW = "view";
	public static String[] CODETYPE_VIEW_ID = { "0", "1" };
	public static String[] CODETYPE_VIEW_NAME = { "隐藏", "显示" };
	// 审核,未审核
	public static String CODETYPE_CHECK = "check";
	public static String[] CODETYPE_CHECK_ID = { "0", "1","2","3","4" };
	public static String[] CODETYPE_CHECK_NAME = { "待审核", "审核通过","审核未通过","禁用","删除" };
	// 回复,未回复
	public static String CODETYPE_REVERT = "revert";
	public static String[] CODETYPE_REVERT_ID = { "0", "1" };
	public static String[] CODETYPE_REVERT_NAME = { "未回复", "回复" };
	// 窗口模式
	public static String CODETYPE_WINDOWMODE = "windowmode";
	public static String[] CODETYPE_WINDOWMODE_ID = { "0", "1", "2", "3", "4", "9" };
	public static String[] CODETYPE_WINDOWMODE_NAME = { "480*320", "600*480", "800*600", "980*720", "1024*768", "内嵌" };
	// 相册分类
	public static String CODETYPE_ALBUMTYPE = "albumtype";
	public static String[] CODETYPE_ALBUMTYPE_ID = { "1", "2", "3", "4", "5", "6", "7", "8" };
	public static String[] CODETYPE_ALBUMTYPE_NAME = { "最爱", "人物", "风景", "动物", "游记", "卡通", "生活", "其他" };
	// 用户类型
	public static String CODETYPE_USERTYPE = "usertype";
	public static String[] CODETYPE_USERTYPE_ID = { "0", "1", "2", "3", "5"};
	public static String[] CODETYPE_USERTYPE_NAME = { "管理者", "老师", "学生", "家长", "学科组长"};
	// 班级用户状态
	public static String CODETYPE_CUSTATE = "custate";
	public static String[] CODETYPE_CUSTATE_ID = { "-1", "0", "1" };
	public static String[] CODETYPE_CUSTATE_NAME = { "审核未通过", "待审核", "审核通过" };

	// 任务类型
	public static String CODETYPE_TASKTYPE = "tasktype";
	public static String[] CODETYPE_TASKTYPE_ID = { "1", "2", "3" };
	public static String[] CODETYPE_TASKTYPE_NAME = { "考试", "作业", "练习" };
	// 任务状态
	public static String CODETYPE_TASKSTATE = "taskstate";
	public static String[] CODETYPE_TASKSTATE_ID = { "0", "1", "2" };
	public static String[] CODETYPE_TASKSTATE_NAME = { "未开始", "进行中", "已结束" };
	// 试卷展现方式
	public static String CODETYPE_PORTRAY = "portray";
	public static String[] CODETYPE_PORTRAY_ID = { "0", "1" };
	public static String[] CODETYPE_PORTRAY_NAME = { "全部展示", "单题展示" };

	// 课时分段类型
	public static String CODETYPE_SECTION = "section";
	public static String[] CODETYPE_SECTION_ID = { "1", "2", "3" };
	public static String[] CODETYPE_SECTION_NAME = { "课前", "课中", "课后" };
	// 视频提示类型
	public static String CODETYPE_TIPTYPE = "tiptype";
	public static String[] CODETYPE_TIPTYPE_ID = { "1", "2", "3", "4" };
	public static String[] CODETYPE_TIPTYPE_NAME = { "普通提示", "弹窗提示", "弹窗作答", "弹窗选择" };
	// 用户管理权限授权
	public static String CODETYPE_PTYPE = "ptype";
	public static String[] CODETYPE_PTYPE_ID = { "1", "2", "3", "4" };
	public static String[] CODETYPE_PTYPE_NAME = { "资源", "微课", "图片", "试题" };

	// 用户管理权限授权
	public static String CODETYPE_TERM = "term";
	public static String[] CODETYPE_TERM_ID = { "1", "2" };
	public static String[] CODETYPE_TERM_NAME = { "上学期", "下学期" };

	//调查类型，1匿名，2实名
	public static String CODETYPE_RESEARCHTYPE = "researchtype";
	public static String[] CODETYPE_RESEARCHTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_RESEARCHTYPE_NAME = { "匿名", "实名" };

	//调查题型,0下拉选择，1单选，2多选，3问答
	public static String CODETYPE_QUESTIONTYPE = "questiontype";
	public static String[] CODETYPE_QUESTIONTYPE_ID = { "1", "2","3" };
	public static String[] CODETYPE_QUESTIONTYPE_NAME = { "单选","多选", "问答" };

	//文件类型
	public static String RESTYPE_PTYPE = "restype";
	public static String[] RESTYPE_PTYPE_ID = { "1", "2", "3", "4" };
	public static String[] RESTYPE_PTYPE_NAME = { "图片", "视频", "文档", "其他" };

	//资源文件类型
	public static String CODETYPE_FILETYPE = "filetype";
	//public static String[] CODETYPE_FILETYPE_ID = { "1", "2", "3", "4","5","6","7","8" };
	//public static String[] CODETYPE_FILETYPE_NAME = { "电子课文", "课前预习", "生字学习", "教学课件","课文朗读","课后练习","拓展阅读","其它素材" };
	public static String[] CODETYPE_FILETYPE_ID = { "1", "2", "3", "4","5","6" };
	public static String[] CODETYPE_FILETYPE_NAME = { "教学教案", "教学课件", "教学设计", "教学案例","教学习题","教学素材" };

	//1指定选择，2自由输入
	public static String CODETYPE_UNITTYPE = "unittype";
	public static String[] CODETYPE_UNITTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_UNITTYPE_NAME = { "下拉选择", "人工输入" };

	//0选修，1必修
	public static String CODETYPE_REQUIRED = "required";
	public static String[] CODETYPE_REQUIRED_ID = { "0", "1" };
	public static String[] CODETYPE_REQUIRED_NAME = { "选修", "必修" };

	//0待审核，1审核通过，2审核未通过（3假删除，这儿不放入）
	public static String CODETYPE_STATE = "state";
	public static String[] CODETYPE_STATE_ID = { "0", "1", "2" };
	public static String[] CODETYPE_STATE_NAME = { "待审核", "审核通过", "审核未通过" };
	// 组件类型
	public static String CODETYPE_ITEMTYPE = "itemtype";
	public static String[] CODETYPE_ITEMTYPE_ID = { "1", "2", "3","4","5"};
	public static String[] CODETYPE_ITEMTYPE_NAME = { "基础模块", "资讯模块", "图库模块","广告模块","班级应用"};

	// 头图类型
	public static String CODETYPE_HEADTYPE = "headtype";
	public static String[] CODETYPE_HEADTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_HEADTYPE_NAME = { "图片", "FLASH" };

	// 字体名
	public static String CODETYPE_FONTNAME = "fontname";
	public static String[] CODETYPE_FONTNAME_ID = { "宋体", "黑体" };
	public static String[] CODETYPE_FONTNAME_NAME = { "宋体", "黑体" };

	// 标题显示位置
	public static String CODETYPE_TITLETXTSEAT = "titletxtseat";
	public static String[] CODETYPE_TITLETXTSEAT_ID = {"0", "1", "2" };
	public static String[] CODETYPE_TITLETXTSEAT_NAME = {"不显示", "靠左", "居中" };

	// 背景样式
	public static String CODETYPE_BGTYPE = "bgtype";
	public static String[] CODETYPE_BGTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_BGTYPE_NAME = { "背景色", "背景图片" };

	// 背景图显示样式，1单图，2水平平铺，3纵向平铺，4水平+纵向平铺
	public static String CODETYPE_BACKGROUNDSTYLE = "backgroundstyle";
	public static String[] CODETYPE_BACKGROUNDSTYLE_ID = { "1", "2", "3", "4" };
	public static String[] CODETYPE_BACKGROUNDSTYLE_NAME = { "单图", "水平平铺", "纵向平铺", "水平+纵向平铺" };

	// 菜单类型
	public static String CODETYPE_MENUITEMTYPE = "menuitemtype";
	public static String[] CODETYPE_MENUITEMTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_MENUITEMTYPE_NAME = { "文字", "图片" };

	// 链接类型
	public static String CODETYPE_MENUURLTYPE = "menuurltype";
	public static String[] CODETYPE_MENUURLTYPE_ID = { "1", "2" };
	public static String[] CODETYPE_MENUURLTYPE_NAME = { "资讯栏目", "外部链接" };

	// 更多显示样式 
	public static String CODETYPE_MORESTYLE = "morestyle";
	public static String[] CODETYPE_MORESTYLE_ID = {"0", "1", "2" };
	public static String[] CODETYPE_MORESTYLE_NAME = {"不显示", "更多", "more" };

	// 站点状态
	public static String CODETYPE_WSTATE = "wstate";
	public static String[] CODETYPE_WSTATE_ID = { "1", "2", "3" };
	public static String[] CODETYPE_WSTATE_NAME = { "正常", "暂停", "删除" };

	// 课程资源类型
	public static String CODETYPE_COURSERESTYLE = "courserestype";
	public static String[] CODETYPE_COURSERESTYLE_ID = {"1", "2", "3", "4", "5", "6" };
	public static String[] CODETYPE_COURSERESTYLE_NAME = {"教学课件", "电子教案", "教学录像", "教学设计", "例题习题", "文献资料" };

	//校本课程成绩类型
	public static String CODETYPE_SCORETYPE = "scoretype";
	public static String[] CODETYPE_SCORETYPE_ID = {"1", "2", "3" };
	public static String[] CODETYPE_SCORETYPE_NAME = {"通过式", "优良中差式", "分数式"  };

	//校本课程通过式成绩
	public static String CODETYPE_SCOREPASSTYPE = "scorepasstype";
	public static String[] CODETYPE_SCOREPASSTYPE_ID = {"1", "2" };
	public static String[] CODETYPE_SCOREPASSTYPE_NAME = {"通过", "不通过" };

	//校本课程优良中差式成绩
	public static String CODETYPE_SCOREGRADETYPE = "scoregradetype";
	public static String[] CODETYPE_SCOREGRADETYPE_ID = {"1", "2", "3", "4" };
	public static String[] CODETYPE_SCOREGRADETYPE_NAME = {"优", "良", "中", "差" };//优90-100  良80-89  中60-79  差60以下


	//博客样式
	public static String CODETYPE_BLOGTYPE = "blogtype";
	public static String[] CODETYPE_BLOGTYPE_ID = {"1", "2","3","4","5","6","7","8","9","0" };
	public static String[] CODETYPE_BLOGTYPE_NAME = {"风景", "运动","心情","校园","时尚","生活","科技","节日","古典","其它" };

	//背景状态
	public static String CODETYPE_DESKSTATE = "deskstate";
	public static String[] CODETYPE_DESKSTATE_ID = {"0", "1"};
	public static String[] CODETYPE_DESKSTATE_NAME = {"保存", "当前"};


	//判断题答案
	public static String CODETYPE_JUDGE = "judge";
	public static String[] CODETYPE_JUDGE_ID = {"0", "1"};
	public static String[] CODETYPE_JUDGE_NAME = {"错误", "正确"};

	//评论类型
	public static String CODETYPE_COMMENTTYPE = "commenttype";
	public static String[] CODETYPE_COMMENTTYPE_ID = {"1", "2", "3", "4", "5", "6"};
	public static String[] CODETYPE_COMMENTTYPE_NAME = {"评论", "建议", "备注", "反思", "评价", "小结"};

	//0待审核，1审核通过
	public static String CODETYPE_COMMENTSTATUS = "commentstatus";
	public static String[] CODETYPE_COMMENTSTATUS_ID = { "0", "1" };
	public static String[] CODETYPE_COMMENTSTATUS_NAME = { "待审核", "已审核" };

	//资源热门标签
	public static String CODETYPE_RESTAG = "restag";
	public static String[] CODETYPE_RESTAG_ID = {"1", "2", "3"};
	public static String[] CODETYPE_RESTAG_NAME = {"电子课文", "参考素材", "拓展阅读资源"};

	// 56个民族
	public static String CODETYPE_NATION = "nation";
	public static String[] CODETYPE_NATION_ID = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
		"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
		"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
		"31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
		"41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
		"51", "52", "53", "54", "55", "56", "99"
	};
	public static String[] CODETYPE_NATION_NAME = { "汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族",
		"满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族",
		"佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族",
		"达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族",
		"塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族",
		"独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族", "其他"
	};

	//微笑之星
	public static String CODETYPE_STARTYPE = "startype";
	public static String[] CODETYPE_STARTYPE_ID = {"1", "2", "3", "4", "5", "6"};
	public static String[] CODETYPE_STARTYPE_NAME = {"微笑全能之星", "文明守纪之星", "勤奋学习之星", "公益服务之星", "特长发展之星", "家庭孝星"};

	// 试卷类型
	public static String CODETYPE_PAPERTYPE = "papertype";
	public static String[] CODETYPE_PAPERTYPE_ID = { "11", "12", "13", "14", "15", "16", "17", "18", "19",
		"21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B",
		"31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B"};
	public static String[] CODETYPE_PAPERTYPE_NAME = { "专题试卷", "单元试卷", "同步测试", "开学考试", "月考试卷", "期中考试", "期末考试", "水平会考", "竞赛测试",
		"专题试卷","中考模拟","中考真卷", "单元试卷", "同步测试", "开学考试", "月考试卷", "期中考试", "期末考试", "水平会考", "竞赛测试",
		"专题试卷", "单元试卷", "同步测试", "开学考试", "月考试卷", "期中考试", "期末考试", "水平会考", "竞赛测试", "高考模拟", "高考真卷",};
	//资源热门标签
	public static String CODETYPE_PAPERLINK = "paperlink";
	public static String[] CODETYPE_PAPERLINK_ID = {"1", "2", "3"};
	public static String[] CODETYPE_PAPERLINK_NAME = {"电子课文", "参考素材", "拓展阅读资源"};

	public static String CODETYPE_RELATIONTYPE = "relationtype";
	public static String[] CODETYPE_RELATIONTYPE_ID = {"1", "2", "3"};
	public static String[] CODETYPE_RELATIONTYPE_NAME = {"爸爸", "妈妈", "其他"};

	public static String CODETYPE_WEEK = "week";
	public static String[] CODETYPE_WEEK_ID = {"1", "2", "3", "4", "5", "6", "7"};
	public static String[] CODETYPE_WEEK_NAME = {"一", "二", "三", "四", "五", "六", "日"};

	public static String CODETYPE_ZWNUM = "zwnum";
	public static String[] CODETYPE_ZWNUM_ID = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11", "12", "13", "14", "15", "16", "17", "18", "19", "20","21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
	public static String[] CODETYPE_ZWNUM_NAME = {"一", "二", "三", "四", "五", "六", "七","八","九","十","十一", "十二", "十三", "十四", "十五", "十六", "十七","十八","十九","二十","二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七","二十八","二十九","三十"};


	//课程班状态
	public static String CODETYPE_COURSECLASSSTATE = "courseclassstate";
	public static String[] CODETYPE_COURSECLASSSTATE_ID = { "0", "1", "2", "3" };
	public static String[] CODETYPE_COURSECLASSSTATE_NAME = { "待审核", "进行中", "已结束", "已删除" };

	//EduResourceInfo中的resourcetype
	public static String CODETYPE_RESOURCETYPE = "resourcetype";
	public static String[] CODETYPE_RESOURCETYPE_ID = { "vod", "res", "task", "package", "external" };
	public static String[] CODETYPE_RESOURCETYPE_NAME = { "微课", "资源", "作业", "课程包", "外部链接" };

	//EduSearchWord中的searchtype
	public static String CODETYPE_SEARCHTYPE = "searchtype";
	public static String[] CODETYPE_SEARCHTYPE_ID = { "school", "course", "vod", "res", "task", "package", "external" };
	public static String[] CODETYPE_SEARCHTYPE_NAME = { "学校", "课程", "微课", "课件", "作业", "课程包", "外部链接" };

	//转码状态
	public static String CODETYPE_CONVERTSTATUS = "convertstatus";
	public static String[] CODETYPE_CONVERTSTATUS_ID = { "9", "0", "1", "2" };
	public static String[] CODETYPE_CONVERTSTATUS_NAME = { "等待转码", "转码中", "转码成功", "转码失败" };


	//分类的一级：学科，三位编码，在后台EduGradeInfo中，用三位表示，以S开头
	public static String CODETYPE_CLASSIFYSUBJECT = "classifysubject";
	public static String[] CODETYPE_CLASSIFYSUBJECT_ID = 	{ "S01", "S02", "S03", "S04", "S05", "S06", "S07", "S08", "S09", "S10", "S11", "S12", "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20", "S21","S99", "S90"};
	public static String[] CODETYPE_CLASSIFYSUBJECT_NAME = 	{ "语文", "数学", "英语", "物理", "化学", "生物", "政治", "历史", "地理", "信息", "音乐", "美术", "体育", "实践", "通技", "科学", "劳技", "书法", "健康", "科技", "手工", "综合", "学前教育"};

	

	//京铁电校同步接口数据的分类
	public static String CODETYPE_SYNCHROTYPE = "synchrotype";
	public static String[] CODETYPE_SYNCHROTYPE_ID = { "1", "5", "3", "6"};//这个值也对应接口文档中调用接口的TableId
	public static String[] CODETYPE_SYNCHROTYPE_NAME = { "教师", "学生", "班级", "专业"};

	public static List getGradeBySubjectid(String subjectid) {
		List<HashMap> lst = new ArrayList<HashMap>();
		String subsub = subjectid.substring(0, 1);
		if(subjectid!=""){
			for (int i = 0; i < CODETYPE_REALGRADEID_ID.length; i++) {
				if (!"".equals(CODETYPE_REALGRADEID_ID[i])) {
					if (CODETYPE_REALGRADEID_ID[i].startsWith(subsub)) {
						HashMap hm = new HashMap();
						hm.put("id", CODETYPE_REALGRADEID_ID[i]);
						hm.put("name", CODETYPE_REALGRADEID_NAME[i]);
						lst.add(hm);
						continue;
					}
				}
			}
		}
		return lst;
	}



	/**
	 * @description 输入扩展名称，判断是否为图片
	 * @param imgext
	 * @return Boolean 
	 * @author ChengSS
	 */
	public static Boolean judgeIsImg(String imgext){
		if(imgext!=null&&imgext.trim().length()>0){
			imgext = "|"+imgext.trim()+"|";
			String imageext = "|jpg|jpeg|bmp|gif|png|";//图片扩展名称
			if(imageext.indexOf(imgext)>-1){
				return true;
			}else{
				return false;			
			}			
		}else{
			return false;
		}
	}

	/**
	 * @description 输入扩展名称，判断是否为文档
	 * @param imgext
	 * @return Boolean 
	 * @author ChengSS
	 */
	public static Boolean judgeIsDocument(String imgext){
		if(imgext!=null&&imgext.trim().length()>0){
			imgext = "|"+imgext.trim()+"|";
			String imageext = "|txt|doc|docx|pdf|xls|xlsx|ppt|pptx|";//文档扩展名称
			if(imageext.indexOf(imgext)>-1){
				return true;
			}else{
				return false;			
			}			
		}else{
			return false;
		}
	}

	/**
	 * @description 获取年份
	 * @param t
	 * @return List 
	 * @author ChengSS
	 */
	public static List getYearList(int t,HttpSession session){
		if(0>=t){
			t = 10;
		} 
		int theyear = Integer.parseInt(getCurrentSchoolYear(session)); // 获取当前学年
		List yearlist = new ArrayList();
		for(int i=0;i<t;i++){
			yearlist.add(String.valueOf(theyear - i));
		}
		return yearlist;
	}

	/**
	 * @description 输入扩展名称，判断是否为视频
	 * @param imgext
	 * @return Boolean 
	 * @author ChengSS
	 */
	public static Boolean judgeIsVideo(String imgext){
		if(imgext!=null&&imgext.trim().length()>0){
			imgext = "|"+imgext.trim()+"|";
			String imageext = "|avi|wmv|asf|asx|mpg|mpeg|3gp|mov|vob|mkv|ts|rmvb|rm|wmv9|flv|mp4|";//视频扩展名称
			if(imageext.indexOf(imgext)>-1){
				return true;
			}else{
				return false;			
			}			
		}else{
			return false;
		}
	}

	public static String getSubjectImg(String subjectid){
		return "/app/img/" + subjectid + ".png";
	}

	/**
	 * @description 字符串型list转 ","分割的字符串
	 * @param stringList
	 * @return String 
	 * @author ChengSS
	 */
	public static String listToString(List<String> stringList){
		if (stringList==null) {
			return null;
		}
		StringBuilder result=new StringBuilder();
		boolean flag=false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			}else {
				flag=true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * @description 字符串型list转 ","分割的字符串
	 * @param stringList
	 * @return String 
	 * @author ChengSS
	 */
	public static String listToInt(List<Integer> intList){
		if (intList==null) {
			return null;
		}
		StringBuilder result=new StringBuilder();
		boolean flag=false;
		for (int integer : intList) {
			if (flag) {
				result.append(",");
			}else {
				flag=true;
			}
			result.append(integer);
		}
		return result.toString();
	}

	/**
	 * 获取当前学年，如果在表中已经有了，那么就取表中的当前学年，否则根据当前自然年来判断，自然年按照SPLIT_MONTH参数来分割，如果当前月份<=这个分割月，那么学年就-1，否则学年就是当前自然年
	 * @param session
	 * @return
	 */
	public static String getCurrentSchoolYear(HttpSession session){
		/*String currentSchoolYear="";
		String unitid = (String) session.getAttribute("s_unitid");
		EduSchoolSetManager eduSchoolSetManager = (EduSchoolSetManager) AppContext.getApplicationContext().getBean("eduSchoolSetManager");
		EduSchoolSet eduSchoolSet = eduSchoolSetManager.getSemester(unitid,"");
		if(eduSchoolSet != null && eduSchoolSet.getItemvalue1()!=null && !"".equals(eduSchoolSet.getItemvalue1())){
			currentSchoolYear=eduSchoolSet.getItemvalue1();// 当前学年
		}else{*/
			int thisyear = DateTime.getYearOfDate();
			int monthOfYear = DateTime.getMonthOfYear();
			if(monthOfYear <= SPLIT_MONTH){
				thisyear = thisyear-1;
			}
			//currentSchoolYear=thisyear+"";// 当前学年
		
		return thisyear+"";
	}

	/**
	 * 获取学科、课程名称
	 * @param schoolproperty
	 * @return
	 */
	public static String getSubjectname(String schoolproperty){
		if("1".equals(schoolproperty)){
			return "学科";
		}
		else{
			return "课程";
		}
	}

	/**
	 * 获取年级，专业名称
	 * @param schoolproperty
	 * @return
	 */
	public static String getGradename(String schoolproperty){
		if("1".equals(schoolproperty)){
			return "年级";
		}
		else{
			return "专业";
		}
	}

	/**
	 * 获取学段、专业大类名称
	 * @param schoolproperty
	 * @return
	 */
	public static String getGradeTypename(String schoolproperty){
		if("1".equals(schoolproperty)){
			return "学段";
		}
		else{
			return "专业大类";
		}
	}

	/**
	 * 根据获取名称为学科或课程
	 * @param subjecttype
	 * @return
	 */
	public static String getSubjecttypename(String subjecttype){
		if("1".equals(subjecttype)||"2".equals(subjecttype)||"3".equals(subjecttype)){
			return "学科";
		}
		else{
			return "课程";
		}
	}


	public static String getGradetypename(String subjecttype){
		if("1".equals(subjecttype)||"2".equals(subjecttype)||"3".equals(subjecttype)){
			return "年级";
		}
		else{
			return "专业";
		}
	}


	/**
	 * @description 判断文件名中
	 * @param filename
	 * @return Boolean 
	 * @author ChengSS
	 */
	public static Boolean isJudgeLegitimate(String filename){
		if(filename.indexOf(".jsp")>-1||filename.indexOf(".js")>-1||filename.indexOf(".exe")>-1||filename.indexOf(".bat")>-1||filename.indexOf(".jspx")>-1||filename.indexOf(".htm")>-1||filename.indexOf(".shtml")>-1){
			return true;
		}else{
			return false;			
		}
	}

	public static String getHomepage(){
		String homepage = PublicResourceBundle.getProperty("system", "system.homepage");
		return homepage;
	}
	/**
	 * @description 
	 * @param result
	 * @return String 
	 * @author ChengSS
	 */
	public static String getDafaultNo(String result){
		if(result == null || result.equals("")){
			return "0100";
		}		
		Integer number =(Integer) ConvertUtils.convert(result,Integer.class);//将字符串转换为整型
		int n = number.intValue()+100;
		if(n >9999){
			return "0010";
		}		
		String str = n + "";
		int m = 4 - str.length();
		for(int i=0;i<m;i++){
			str = "0" + str;
		}
		return str;
	}

	/** 验证图片后缀
	 * @description
	 * @return 
	 */
	public static List getFileExt() {
		List list = new ArrayList();
		list.add("jpg");
		list.add("jpeg");
		list.add("gif");
		list.add("png");
		return list;
	}

	public static List getDocExt(){ 
		List list = new ArrayList();
		list.add("doc");
		list.add("docx");
		list.add("xls");
		list.add("xlsx");
		list.add("txt");
		list.add("rar");
		list.add("zip"); 
		return list;
	}

}
