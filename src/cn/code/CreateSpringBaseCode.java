package cn.code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * <p>
 * Title: 工具软件
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: JAVA2CMS开发组
 * </p>
 * 
 * @author pursol
 * @version 2.0
 */
public class CreateSpringBaseCode { 
	// 需修改数据
	private String basepath = "D:/skygzxctool/myeclipse10workzxc/pursol2/src/cn/pursol/sys/";
	private String basepackage = "cn.pursol.sys.";
	private boolean createfile = true;
	private String url = "jdbc:mysql://localhost:3306/pursol2?useUnicode=true&characterEncoding=utf-8";
	private String user = "root";
	private String password = "123123";
	private String tablename = "sys_user_log";
	private String objname = "SysUserLog";
	private String objnamecn = "日志"; 
 
	// ////////////////////////////////////////////////
	// 基本不变数据
	String n[] = new String[200];// 列名
	String t[] = new String[200];// 列类型
	int isize = 0;
	private static PrintWriter pw;
	private String title = objnamecn;
	private String company = "pursol";
	private String hbmxmlpath = basepath + "bo/mappings/";
	private String bopath = basepath + "bo/";
	private String daopath = basepath + "dao/";
	private String daohibernatepath = basepath + "dao/hibernate/";
	private String managerpath = basepath + "service/";
	private String managerimplpath = basepath + "service/impl/";
	private String actionformpath = basepath + "web/form/";
	private String actionpath = basepath + "web/action/";

	public CreateSpringBaseCode() {
	}

	public void getTableField(String tablename) {
		try {
			String sql = "SELECT * FROM " + tablename;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(url, user, password); // 连接数据库
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount(); // 获取字段数
			isize = numberOfColumns;
			for (int i = 1; i <= numberOfColumns; i++) {
				n[i - 1] = rsmd.getColumnName(i);// 列名
				t[i - 1] = getJavaObjectType(rsmd.getColumnTypeName(i).toLowerCase());
				System.out.println(rsmd.getColumnTypeName(i));
			}
			rs.close(); // 关闭结果集
			stmt.close(); // 关闭statement
			conn.close(); // 关闭连接
		} catch (Exception e) {
		}
	}

	/**
	 * 字段对象
	 * 
	 * @param columntype
	 * @return
	 */
	private String getJavaObjectType(String columntype) {
		String javaObjectType = "String";
		if ("varchar".equals(columntype) || "char".equals(columntype) || "text".equals(columntype)) {
			javaObjectType = "String";
		} else if ("integer".equals(columntype) || "int".equals(columntype)|| "tinyint".equals(columntype)) {
			javaObjectType = "Integer";
		} else if ("folat".equals(columntype)) {
			javaObjectType = "Float";
		}
		return javaObjectType;
	}

	/**
	 * 创建文件
	 * 
	 * @param filepath
	 * @param filename
	 * @param code
	 */
	private static void creatCodeFile(String filepath, String filename, String code) {
		try {
			if (pw != null) {
				try {
					pw.close();
				} catch (Exception e) {
				}
				pw = null;
			}
			File fileDir = new File(filepath);
			// 如果不存在指定的目录时,创建一个
			if (!fileDir.exists()) {
				if (!fileDir.mkdirs()) {
					System.out.println("文件创建失败!");
				}
			}
			String sFileName = filepath + "/" + filename;
			pw = new PrintWriter(new FileWriter(sFileName, true), true);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(sFileName), "UTF-8");
			out.write(code);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("文件生成失败，请检查路径是否可写后再重试");
		}
	}

	/**
	 * 创建hbm.xml文件
	 */
	private void createHbmXmlFile() {
		StringBuffer code = new StringBuffer();
		code.append("<?xml version=\"1.0\"?>\r\n");
		code.append("<!DOCTYPE hibernate-mapping PUBLIC\r\n");
		code.append("\t\t\"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\r\n");
		code.append("\t\t\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\r\n");
		code.append("<hibernate-mapping>\r\n");
		code.append("<class name=\"");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append("\" table=\"");
		code.append(tablename.toUpperCase());
		code.append("\">\r\n");
		code.append("<id name=\"");
		code.append(n[0].toLowerCase());
		code.append("\" type=\"java.lang.");
		code.append(t[0]);
		code.append("\" column=\"");
		code.append(n[0]);
		code.append("\">  <generator class=\"native\" /> </id>\r\n");
		for (int i = 1; i < isize; i++) {
			code.append("<property  name=\"");
			code.append(n[i].toLowerCase());
			code.append("\" type=\"java.lang.");
			code.append(t[i]);
			code.append("\" column=\"");
			code.append(n[i].toLowerCase());
			code.append("\"/>\r\n");
		}
		code.append("</class>\r\n");
		code.append("</hibernate-mapping>");
		if (createfile) {
			creatCodeFile(hbmxmlpath, objname + ".hbm.xml", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createBoFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("bo;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title:");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import java.io.Serializable;\r\n");
		code.append("import org.apache.commons.lang.builder.ToStringBuilder;\r\n");
		code.append("import org.apache.commons.lang.builder.EqualsBuilder;\r\n");
		code.append("import org.apache.commons.lang.builder.HashCodeBuilder;\r\n");
		code.append("import com.util.bo.BaseObject;\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append(" extends BaseObject implements Serializable, Cloneable {\r\n");
		code.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		// 定义变量
		for (int i = 0; i < isize; i++) {
			code.append("\tprivate ");
			code.append(t[i]);
			code.append(" ");
			code.append(n[i].toLowerCase());
			code.append(";\r\n");
		}
		// 构造函数
		code.append("\r\n\tpublic ");
		code.append(objname);
		code.append("(){\r\n\t}\r\n\r\n ");
		// get,set函数
		for (int i = 0; i < isize; i++) {
			code.append("\tpublic ");
			code.append(t[i]);
			code.append(" get");
			code.append(n[i].substring(0, 1).toUpperCase());
			code.append(n[i].substring(1, n[i].length()).toLowerCase());
			code.append("() {\r\n");
			code.append("\t\treturn this.");
			code.append(n[i].toLowerCase());
			code.append(";\r\n\t}\r\n\r\n");
			code.append("\tpublic void set");
			code.append(n[i].substring(0, 1).toUpperCase());
			code.append(n[i].substring(1, n[i].length()).toLowerCase());
			code.append("(");
			code.append(t[i]);
			code.append(" ");
			code.append(n[i].toLowerCase());
			code.append(") {\r\n");
			code.append("\t\tthis.");
			code.append(n[i].toLowerCase());
			code.append("=");
			code.append(n[i].toLowerCase());
			code.append(";\r\n\t}\r\n\r\n");
		}
		code.append("public String getId() {\r\n");
		code.append("return String.valueOf(this.");
		code.append(n[0].toLowerCase());
		code.append(");\r\n");
		code.append("}\r\n");
		// toString
		code.append("\tpublic String toString() {\r\n");
		code.append("\t\treturn new ToStringBuilder(this)\r\n");
		code.append("\t\t.append(\"");
		code.append(n[0].toLowerCase());
		code.append("\",get");
		code.append(n[0].substring(0, 1).toUpperCase());
		code.append(n[0].substring(1, n[0].length()).toLowerCase());
		code.append("()).toString();\r\n\t}\r\n\r\n");
		// equals
		code.append("\tpublic boolean equals(Object other) {\r\n");
		code.append("\t\tif (!(other instanceof ");
		code.append(objname);
		code.append("))\r\n\t\treturn false;\r\n\t\t");
		code.append(objname);
		code.append(" castOther = (");
		code.append(objname);
		code.append(")other;\r\n");
		code.append("\t\treturn new EqualsBuilder().append(");
		code.append(n[0].toLowerCase());
		code.append(", castOther.");
		code.append(n[0].toLowerCase());
		code.append(").isEquals();\r\n\t}\r\n\r\n");
		code.append("public int hashCode() {\r\n");
		code.append("\t\treturn new HashCodeBuilder().append(");
		code.append(n[0].toLowerCase());
		code.append(").toHashCode();\r\n\t}\r\n\r\n");
		code.append("}");
		if (createfile) {
			creatCodeFile(bopath, objname + ".java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createDaoFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("dao;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\r\nimport java.util.List;\r\n");
		code.append("import com.util.search.PageList;\r\n");
		code.append("import com.util.dao.DAO;\r\n\r\n");
		code.append("public interface ");
		code.append(objname);
		code.append("DAO extends DAO {\r\n");
		// 取对象
		code.append("/**\r\n");
		code.append("*根据id获取");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\r\n");
		// 增加
		code.append("/**\r\n");
		code.append("*增加");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" add");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\r\n");
		// 删除
		code.append("/**\r\n");
		code.append("*删除");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" del");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\r\n");
		// 修改
		code.append("/**\r\n");
		code.append("*修改");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" update");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\r\n");
		// 取对象集合
		code.append("/**\r\n");
		code.append("*获取");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@return List\r\n*/\r\n");
		code.append("public List get");
		code.append(objname);
		code.append("s (List condition,String orderby,int pagesize);\r\n\r\n");
		// 取一页对象集合
		code.append("/**\r\n");
		code.append("*获取一页");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@param start int\r\n");
		code.append("*@param pagesize int\r\n");
		code.append("*@return PageList\r\n*/\r\n");
		code.append("public PageList getPage");
		code.append(objname);
		code.append("s (List condition,String orderby,int start,int pagesize);\r\n\r\n");
		code.append("}");
		if (createfile) {
			creatCodeFile(daopath, objname + "DAO.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createDaoHibernateFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("dao.hibernate;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\r\nimport ");
		code.append(basepackage);
		code.append("dao.");
		code.append(objname);
		code.append("DAO;\r\n");
		code.append("import org.hibernate.Query;\r\n");
		code.append("import org.hibernate.Session;\r\n");
		code.append("import java.util.List;\r\n");
		code.append("import java.util.ArrayList;\r\n");
		code.append("import com.util.search.PageList;\r\n\r\n");
		code.append("import com.util.dao.hibernate.DAOHibernate;\r\n\r\n");
		code.append("import com.util.search.SearchModel;\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append("DAOHibernate extends DAOHibernate implements ");
		code.append(objname);
		code.append("DAO{\r\n");
		// 取对象
		code.append("/**\r\n");
		code.append("*根据id获取");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(");
		code.append(t[0]);
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\t");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("=(");
		code.append(objname);
		code.append(")getHibernateTemplate().get(");
		code.append(objname);
		code.append(".class,");
		code.append(n[0].toLowerCase());
		code.append(");\n\r\tif(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("==null){\n\r\t\tlogger.warn(\"没有找到");
		code.append(objnamecn);
		code.append("\");");
		code.append("\t}\r\nreturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n}\r\n\r\n");
		// 增加
		code.append("/**\r\n");
		code.append("*增加");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" add");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\tif(logger.isDebugEnabled()) {\r\n\t\tlogger.debug(\"增加");
		code.append(objnamecn);
		code.append("\");\r\n\t}\r\n\tgetHibernateTemplate().save(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n\t}\r\n\r\n");
		// 删除
		code.append("/**\r\n");
		code.append("*删除");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" del");
		code.append(objname);
		code.append("(");
		code.append(t[0]);
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\t");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("=get");
		code.append(objname);
		code.append("(");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\tif(logger.isDebugEnabled()) {\r\n\t\tlogger.debug(\"删除");
		code.append(objnamecn);
		code.append("\");\r\n\t}\r\ngetHibernateTemplate().delete(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\nreturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n}\r\n\r\n");
		// 修改
		code.append("/**\r\n");
		code.append("*修改");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" update");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\tif(logger.isDebugEnabled()) {\r\n\t\tlogger.debug(\"修改");
		code.append(objnamecn);
		code.append("\");\r\n\t}\r\n\tgetHibernateTemplate().update(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n}\r\n\r\n");
		// 取对象集合
		code.append("/**\r\n");
		code.append("*获取");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@return List\r\n*/\r\n");
		code.append("public List get");
		code.append(objname);
		code.append("s(List condition,String orderby,int pagesize){\r\n\t\treturn getHibernateTemplate().find(\" FROM ");
		code.append(objname);
		code.append("\");\r\n\t}\r\n\r\n");
		// 取一页对象集合
		code.append("/**\r\n");
		code.append("*获取一页");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@param start int\r\n");
		code.append("*@param pagesize int\r\n");
		code.append("*@return PageList\r\n*/\r\n");
		code.append("public PageList getPage");
		code.append(objname);
		code.append("s (List condition,String orderby,int start,int pagesize){\r\n");
		code.append("\tList lst = null;\r\n");
		code.append("\tPageList page = null;\r\n");
		code.append("\tint totalCount = 0;\r\n");
		code.append("\tQuery query = null;\r\n");
		code.append("\tString sql = null;\r\n");
		code.append("\tSession s = this.getSession();\r\n");
		code.append("\ttry{\r\n");
		code.append("\t\tsql=\"");
		code.append(" FROM ");
		code.append(objname);
		code.append(" as a WHERE 1=1 \";\n\r");
		code.append("\t for (int i = 0; i < condition.size(); i++) {\n\r");
		code.append("\t SearchModel model = (SearchModel) condition.get(i);\n\r");
		code.append("\t if (!\"\".equals(model.getValue())) {\n\r");
		code.append("\t  sql += \" AND a.\" + model.getItem() + \" \" + model.getRelation() +\"'\" + model.getValue() + \"'\";\n\r");
		code.append("\t }\n\r");
		code.append("\t }\n\r");
		code.append("\t\ttotalCount =((Integer)s.createQuery(\"SELECT count(a.");
		code.append(n[0].toLowerCase());
		code.append(")\"+ sql).iterate().next()).intValue();\r\n\r\n");
		code.append("\t if(!\"\".equals(orderby)){\n\r");
		code.append("\t sql+=\" ORDER BY \" +orderby;\n\r");
		code.append("\t }\n\r");
		code.append("\t\tquery=s.createQuery(\"SELECT a \" + sql);\r\n");
		code.append("\t\tquery.setFirstResult(start);\r\n");
		code.append("\t\tquery.setMaxResults(pagesize);\r\n\r\n");
		code.append("\t\tlst = query.list();\r\n");
		code.append("\t\tpage = new PageList((ArrayList)lst, start, pagesize, totalCount);\r\n");
		code.append("\t}\r\ncatch (Exception ex) {\r\n\t\tex.printStackTrace();\r\n}");
		code.append("\tfinally {\r\nreleaseSession(s);\r\n}\n");
		code.append("\treturn page;\n}\n");
		code.append("}");
		if (createfile) {
			creatCodeFile(daohibernatepath, objname + "DAOHibernate.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createManagerFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("service;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\nimport java.util.List;\r\n");
		code.append("import com.util.search.PageList;\r\n\r\n");
		code.append("import com.util.service.Manager;\r\n\r\n");
		code.append("public interface ");
		code.append(objname);
		code.append("Manager extends Manager {\r\n");
		// 取对象
		code.append("/**\r\n");
		code.append("*根据id获取");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\r\n");
		// 增加
		code.append("/**\r\n");
		code.append("*增加");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" add");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\r\n");
		// 删除
		code.append("/**\r\n");
		code.append("*删除");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" del");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\r\n");
		// 修改
		code.append("/**\r\n");
		code.append("*修改");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" update");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n\r\n");
		// 取对象集合
		code.append("/**\r\n");
		code.append("*获取");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@return List\r\n*/\r\n");
		code.append("public List get");
		code.append(objname);
		code.append("s (List condition,String orderby,int pagesize);\r\n\r\n");
		// 取一页对象集合
		code.append("/**\r\n");
		code.append("*获取一页");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@param start int\r\n");
		code.append("*@param pagesize int\r\n");
		code.append("*@return PageList\r\n*/\r\n");
		code.append("public PageList getPage");
		code.append(objname);
		code.append("s (List condition,String orderby,int start,int pagesize);\r\n\r\n");
		code.append("}");
		if (createfile) {
			creatCodeFile(managerpath, objname + "Manager.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createManagerImplFile_dao() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("service.impl;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\r\nimport ");
		code.append(basepackage);
		code.append("dao.");
		code.append(objname);
		code.append("DAO;\r\nimport ");
		code.append(basepackage);
		code.append("service.");
		code.append(objname);
		code.append("Manager;\r\nimport java.util.List;\r\nimport com.util.search.PageList;\r\n\r\n");
		code.append("import com.util.service.impl.ManagerImpl;\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append("ManagerImpl extends ManagerImpl implements ");
		code.append(objname);
		code.append("Manager{\r\n");
		code.append("private ");
		code.append(objname);
		code.append("DAO ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO;\r\n");
		code.append("/**\r\n");
		code.append("*加载");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO;\r\n");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public void set");
		code.append(objname);
		code.append("DAO (");
		code.append(objname);
		code.append("DAO ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO){\r\nthis.");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO=");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO;\r\n}\r\n\r\n");
		// 取对象
		code.append("/**\r\n");
		code.append("*根据id获取");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(final ");
		code.append(t[0]);
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.get");
		code.append(objname);
		code.append("(");
		code.append(n[0].toLowerCase());
		code.append(");\r\n}\r\n\r\n");
		// 增加
		code.append("/**\r\n");
		code.append("*增加");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" add");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.add");
		code.append(objname);
		code.append("(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n}\r\n\r\n");
		// 删除
		code.append("/**\r\n");
		code.append("*删除");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" del");
		code.append(objname);
		code.append("(final ");
		code.append(t[0]);
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.del");
		code.append(objname);
		code.append("(");
		code.append(n[0].toLowerCase());
		code.append(");\r\n}\r\n\r\n");
		// 修改
		code.append("/**\r\n");
		code.append("*修改");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" update");
		code.append(objname);
		code.append("(final ");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.update");
		code.append(objname);
		code.append("(");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n}\r\n\r\n");
		// 取对象集合
		code.append("/**\r\n");
		code.append("*获取");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@return List\r\n*/\r\n");
		code.append("public List get");
		code.append(objname);
		code.append("s(List condition,String orderby,int pagesize){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.get");
		code.append(objname);
		code.append("s(condition,orderby,pagesize);\r\n\t}\r\n\r\n");
		// 取一页对象集合
		code.append("/**\r\n");
		code.append("*获取一页");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@param start int\r\n");
		code.append("*@param pagesize int\r\n");
		code.append("*@return PageList\r\n*/\r\n");
		code.append("public PageList getPage");
		code.append(objname);
		code.append("s (List condition,String orderby,int start,int pagesize){\r\n\t\treturn ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO.getPage");
		code.append(objname);
		code.append("s(condition,orderby,start,pagesize);\r\n\t}\r\n\r\n");
		code.append("}");
		if (createfile) {
			creatCodeFile(managerimplpath, objname + "ManagerImpl.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createManagerImplFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("service.impl;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\r\nimport com.util.dao.BaseDAO;");
		code.append("\r\nimport ");
		code.append(basepackage);
		code.append("service.");
		code.append(objname);
		code.append("Manager;\r\nimport java.util.List;\r\nimport com.util.search.PageList;\r\n\r\n");
		code.append("import com.util.service.impl.ManagerImpl;\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append("ManagerImpl extends ManagerImpl implements ");
		code.append(objname);
		code.append("Manager{\r\n");
		code.append("private BaseDAO baseDAO;\r\n");
		code.append("private String modelname = \"");
		code.append(objnamecn);
		code.append("\";\r\n");
		code.append("/**\r\n");
		code.append("*加载baseDAO \r\n");
		code.append("*@param BaseDAO baseDAO");
		code.append("\r\n*/\r\n");
		code.append("public void setBaseDAO(BaseDAO baseDAO) {\r\n");
		code.append("\tthis.baseDAO = baseDAO;\r\n");
		code.append("}\r\n\r\n");
		// 取对象
		code.append("/**\r\n");
		code.append("*根据id获取");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\treturn  (");
		code.append(objname);
		code.append(")baseDAO.getObject(modelname,");
		code.append(objname);
		code.append(".class,");
		code.append(n[0].toLowerCase());
		code.append(");\r\n}\r\n\r\n");
		// 增加
		code.append("/**\r\n");
		code.append("*增加");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" add");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\treturn (");
		code.append(objname);
		code.append(")baseDAO.addObject(modelname,");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n}\r\n\r\n");
		// 删除
		code.append("/**\r\n");
		code.append("*删除");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(n[0].toLowerCase());
		code.append(" ");
		code.append(t[0]);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" del");
		code.append(objname);
		code.append("(");
		// code.append(t[0]);
		code.append("String");
		code.append(" ");
		code.append(n[0].toLowerCase());
		code.append("){\r\n\t\t ");
		code.append(objname);
		code.append(" model=get");
		code.append(objname);
		code.append("(");
		code.append(n[0].toLowerCase());
		code.append(");\r\n\t\treturn (");
		code.append(objname);
		code.append(")baseDAO.delObject(modelname,model);\r\n}\r\n\r\n");
		// 修改
		code.append("/**\r\n");
		code.append("*修改");
		code.append(objnamecn);
		code.append("\r\n ");
		code.append("*@param ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(" ");
		code.append(objname);
		code.append(";\r\n");
		code.append("*@return ");
		code.append(objname);
		code.append("\r\n*/\r\n");
		code.append("public ");
		code.append(objname);
		code.append(" update");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n\t\treturn (");
		code.append(objname);
		code.append(")baseDAO.updateObject(modelname,");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(");\r\n}\r\n\r\n");
		// 取对象集合
		code.append("/**\r\n");
		code.append("*获取");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@return List\r\n*/\r\n");
		code.append("public List get");
		code.append(objname);
		code.append("s(List condition,String orderby,int pagesize){\r\n\t\treturn baseDAO.getObjects(\"");
		code.append(objname);
		code.append("\",condition,orderby,pagesize);\r\n\t}\r\n\r\n");
		// 取一页对象集合
		code.append("/**\r\n");
		code.append("*获取一页");
		code.append(objnamecn);
		code.append("集合\r\n ");
		code.append("*@param start int\r\n");
		code.append("*@param pagesize int\r\n");
		code.append("*@return PageList\r\n*/\r\n");
		code.append("public PageList getPage");
		code.append(objname);
		code.append("s (List condition,String orderby,int start,int pagesize){\r\n\t\treturn baseDAO.getPageObjects(\"");
		code.append(objname);
		code.append("\",\"");
		code.append(n[0].toLowerCase());
		code.append("\",condition, orderby, start,pagesize);\r\n\t}\r\n\r\n}");
		if (createfile) {
			creatCodeFile(managerimplpath, objname + "ManagerImpl.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createHbmInclude() {
		System.out.println("<value>" + basepackage.replace('.', '/') + "bo/mappings/" + objname + ".hbm.xml</value>");
	}

	public void createDaoInclude() {
		StringBuffer code = new StringBuffer();
		code.append("<bean id=\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("DAO\" class=\"");
		code.append(basepackage);
		code.append("dao.hibernate.");
		code.append(objname);
		code.append("DAOHibernate\">\n\r");
		code.append("\t<property name=\"sessionFactory\" ref=\"sessionFactory\"/>\n\r");
		code.append("</bean>");
		System.out.println(code.toString());
	}

	public void createManagerInclude() {
		StringBuffer code = new StringBuffer();
		code.append("<bean id=\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\" parent=\"txProxyTemplate\">\n\r\t<property name=\"target\">\n\r\t\t<bean class=\"");
		code.append(basepackage);
		code.append("service.impl.");
		code.append(objname);
		code.append("ManagerImpl\">\n\r\r\r");
		code.append("\t\t\t<property name=\"baseDAO\" ref=\"baseDAO\"/>");
		code.append("\n\r\t\t</bean>\n\r\t</property>\n\r</bean>");
		System.out.println(code.toString());
	}

	public void createManagerInterface() {
		StringBuffer code = new StringBuffer();
		code.append("/**\r\n");
		code.append("*得到");
		code.append(objnamecn);
		code.append("接口\r\n*/\r\n");
		code.append("public static ");
		code.append(objname);
		code.append("Manager get");
		code.append(objname);
		code.append("Manager(){\n\r");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)ctx.getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\n\rreturn manager;\n\r}");
		System.out.println(code.toString());
	}

	private void createActionFormFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("web.form;\r\n\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\nimport org.apache.struts.action.*;\r\n");
		code.append("import javax.servlet.http.*;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append("ActionForm extends ActionForm {\r\n");
		// 定义对象
		code.append("private String act;\r\n");
		code.append("private ");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("=new ");
		code.append(objname);
		code.append("();\r\n\r\n");
		// getAct
		code.append("public String getAct() {\r\n");
		code.append("\treturn act;\r\n");
		code.append("}\r\n\r\n");
		// setAct
		code.append("public void setAct(String act) {");
		code.append("\tthis.act = act;");
		code.append("}\r\n\r\n");
		// getObj
		code.append("public ");
		code.append(objname);
		code.append(" get");
		code.append(objname);
		code.append("(){\r\n");
		code.append("\treturn this.");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n}\r\n\r\n");
		// setObj
		code.append("public void set");
		code.append(objname);
		code.append("(");
		code.append(objname);
		code.append(" ");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("){\r\n");
		code.append("\tthis.");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("=");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append(";\r\n}\r\n\r\n");
		// validate
		code.append("public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {\r\n");
		code.append("\treturn null;\r\n");
		code.append("}\r\n");
		// reset
		code.append("public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {\r\n");
		code.append("}\r\n}\r\r");
		if (createfile) {
			creatCodeFile(actionformpath, objname + "ActionForm.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createActionFormInclude() {
		StringBuffer code = new StringBuffer();
		code.append("<form-bean name=\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("ActionForm\" type=\"");
		code.append(basepackage);
		code.append("web.form.");
		code.append(objname);
		code.append("ActionForm\" />");
		System.out.println(code.toString());
	}

	private void createActionFile() {
		StringBuffer code = new StringBuffer();
		code.append("package ");
		code.append(basepackage);
		code.append("web.action;\r\n\r\n");
		code.append("import com.util.web.action.*;\r\n");
		code.append("import org.apache.struts.action.*;\r\n");
		code.append("import javax.servlet.http.*;\r\n");
		code.append("import com.util.string.encode.Encode;\r\n");
		code.append("import com.util.search.PageUtil;\r\n");
		code.append("import com.util.search.PageList;\r\n");
		code.append("import com.util.search.SearchModel;\r\n");
		code.append("import java.util.*;\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("bo.");
		code.append(objname);
		code.append(";\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("service.");
		code.append(objname);
		code.append("Manager;\r\n");
		code.append("import ");
		code.append(basepackage);
		code.append("web.form.");
		code.append(objname);
		code.append("ActionForm;\r\n\r\n");
		code.append("/**\r\n");
		code.append("*<p>Title: ");
		code.append(title);
		code.append("</p>\r\n");
		code.append("*<p>Description: </p>\r\n");
		code.append("*<p>Copyright: Copyright (c) 2017</p>\r\n");
		code.append("*<p>Company: ");
		code.append(company);
		code.append(" </p>\r\n");
		code.append("*@author pursol\r\n");
		code.append("*@version 2.0\r\n");
		code.append("*/\r\n\r\n");
		code.append("public class ");
		code.append(objname);
		code.append("Action extends BaseAction {\r\n");
		code.append("\tprivate String moduleid = \"0000\";\r\n\r\n");
		// list
		code.append("\t/**\r\n");
		code.append("\t* 列表显示\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward list(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\r\n");
		code.append("\tPageUtil pageUtil = new PageUtil(httpServletRequest);\r\n");
		code.append("\tList<SearchModel> condition = new ArrayList<SearchModel>();\r\n");
		code.append("\tPageList page = manager.getPage");
		code.append(objname);
		code.append("s(condition,\"\",pageUtil.getStartCount(),pageUtil.getPageSize());\r\n");
		code.append("httpServletRequest.setAttribute(\"pagelist\", page);\r\n");
		code.append("httpServletRequest.setAttribute(\"startcount\", pageUtil.getStartCount());\r\n");
		code.append("return actionMapping.findForward(\"list\");\r\n");
		code.append("}\r\n\r\n");
		// beforeAdd
		code.append("\t/**\r\n");
		code.append("\t* 增加前\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward beforeAdd(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append(" model=new ");
		code.append(objname);
		code.append("();\r\n");
		code.append("\thttpServletRequest.setAttribute(\"model\", model);\r\n");
		code.append("\thttpServletRequest.setAttribute(\"act\", \"addSave\");\r\n");
		code.append("\treturn actionMapping.findForward(\"edit\");\r\n}\r\n\r\n");
		//
		code.append("\t/**\r\n");
		code.append("\t* 增加时保存\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward addSave(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append("ActionForm form=(");
		code.append(objname);
		code.append("ActionForm)actionForm;\r\n");
		code.append("\t");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\r\n");
		code.append("\ttry {\r\n");
		code.append("\t");
		code.append(objname);
		code.append(" model=form.get");
		code.append(objname);
		code.append("();\r\n");
		code.append("\tmanager.add");
		code.append(objname);
		code.append("(model);\r\n");
		code.append("\taddLog(httpServletRequest,\"增加了一个");
		code.append(objnamecn);
		code.append("\");}\r\ncatch (Exception e1){\r\n");
		code.append("}\r\n\r\n");
		code.append("\treturn list(actionMapping, actionForm, httpServletRequest,httpServletResponse);\r\n}\r\n\r\n");
		code.append("\t/**\r\n");
		code.append("\t* 修改前\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward beforeUpdate(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\r\n");
		code.append("\tString objid = Encode.nullToBlank(httpServletRequest.getParameter(\"objid\"));\r\n");
		code.append("\ttry {\r\n");
		code.append("\t");
		code.append(objname);
		code.append(" model=manager.get");
		code.append(objname);
		code.append("(objid);\r\n");
		code.append("\thttpServletRequest.setAttribute(\"act\", \"updateSave\");\r\n");
		code.append("\thttpServletRequest.setAttribute(\"model\", model);\r\n");
		code.append("\t}\r\ncatch (Exception e1){\r\n");
		code.append("}\r\n\r\n");
		code.append("\treturn actionMapping.findForward(\"edit\");\r\n}\r\n\r\n");
		code.append("\t/**\r\n");
		code.append("\t* 修改时保存\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward updateSave(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append("ActionForm form=(");
		code.append(objname);
		code.append("ActionForm)actionForm;\r\n");
		code.append("\t");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\r\n");
		code.append("\ttry {\r\n");
		code.append("\t");
		code.append(objname);
		code.append(" model=form.get");
		code.append(objname);
		code.append("();\r\n");
		code.append("\tmanager.update");
		code.append(objname);
		code.append("(model);\r\n");
		code.append("\taddLog(httpServletRequest,\"修改了一个");
		code.append(objnamecn);
		code.append("\");}\r\ncatch (Exception e1){\r\n");
		code.append("}\r\n\r\n");
		code.append("\treturn list(actionMapping, actionForm, httpServletRequest,httpServletResponse);\r\n}\r\n\r\n");
		code.append("\t/**\r\n");
		code.append("\t* 批量删除\r\n");
		code.append("\t* @param actionMapping ActionMapping\r\n");
		code.append("\t* @param actionForm ActionForm\r\n");
		code.append("\t* @param httpServletRequest HttpServletRequest\r\n");
		code.append("\t* @param httpServletResponse HttpServletResponse\r\n");
		code.append("\t* @return ActionForward\r\n");
		code.append("\t*/\r\n");
		code.append("public ActionForward delBatchRecord(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {\r\n");
		code.append("\t");
		code.append(objname);
		code.append("Manager manager=(");
		code.append(objname);
		code.append("Manager)getBean(\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Manager\");\r\n");
		code.append("String[] checkids = httpServletRequest.getParameterValues(\"checkid\");\n\r");
		code.append("for (int i = 0; i < checkids.length; i++) {\r\n");
		code.append("\tmanager.del");
		code.append(objname);
		code.append("(checkids[i]);\r\n}\r\n");
		code.append("\treturn list(actionMapping, actionForm, httpServletRequest,httpServletResponse);\r\n}\r\n}");
		if (createfile) {
			creatCodeFile(actionpath, objname + "Action.java", code.toString());
		} else {
			System.out.println(code.toString());
		}
	}

	private void createActionInclude() {
		StringBuffer code = new StringBuffer();
		code.append("<action name=\"");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("ActionForm\" parameter=\"method\" path=\"/");
		code.append(objname.substring(0, 1).toLowerCase());
		code.append(objname.substring(1, objname.length()));
		code.append("Action\" scope=\"request\" type=\"");
		code.append(basepackage);
		code.append("web.action.");
		code.append(objname);
		code.append("Action\" validate=\"false\">\r\n");
		code.append("\t<forward contextRelative=\"true\" name=\"list\" path=\"\"/>\r\n");
		code.append("\t<forward contextRelative=\"true\" name=\"edit\" path=\"\"/>\r\n");
		code.append("</action>");
		System.out.println(code.toString());
	}

	public void creatCode() {
		getTableField(tablename);
		createHbmXmlFile();
		createBoFile();
		createManagerFile();
		createManagerImplFile();
		createManagerInclude();
		createActionFormFile();
		createActionFormInclude();
		createActionFile();
		createActionInclude();
	}

	public static void main(String[] args) {
		CreateSpringBaseCode createSpringBaseCode1 = new CreateSpringBaseCode();
		createSpringBaseCode1.creatCode();
	}
}
