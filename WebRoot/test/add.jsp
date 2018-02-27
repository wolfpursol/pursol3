<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" import="java.util.Map,java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@page import="cn.pursol.test.bo.User"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表</title>
<script language=javascript>
	function save(){
		document.pageForm.action = '/userAction.do?method=addSave';
		document.pageForm.submit();
	}
</script>
 </head>
 <body>
<div align="center" style="width:100%;height: 200px;margin-top: 20%;">
<form name="pageForm" method="post">
	姓名：<input value="" name="name" id="name"></input><br />
	年龄：<input value="" name="value" id="value" maxlength="3"></input>
	<br/>
	 <a onclick="save()">增加</a>
<!-- manage_right e-->
</form>
</div>
</body>