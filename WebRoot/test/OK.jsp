<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" import="java.util.Map,java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@page import="cn.pursol.test.bo.User"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/demo/demo.css">
	<script type="text/javascript" src="/esayui/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<title>列表</title>
<script language="javascript">
	function save(){
		document.pageForm.action = '/userAction.do?method=list';
		document.pageForm.submit();
	}
</script>
 </head>
 <body>
<form name="pageForm" method="post">
<div >
	 <a class="easyui-linkbutton" onclick="save()">&nbsp;增加成功&nbsp;&nbsp;</a>
</div>
<!-- manage_right e-->
</form>
</body>