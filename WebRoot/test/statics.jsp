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
<script language="javascript">
	function save(){
		document.pageForm.action = '/userAction.do?method=list';
		document.pageForm.submit();
	}
	function index(){
		window.location.href="/index.jsp";
	}
</script>
 </head>
 <script type="text/javascript" src="/public/js/echarts.js"></script>
 <script type="text/javascript" src="/public/js/jquery-1.7.2.min.js"></script>
  <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/demo/demo.css">
	<script type="text/javascript" src="/esayui/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
 <body>
<form name="pageForm" method="post">
<div >
	<tr>
	<td style="width:66%;"><div style="margin-top:20px;height:200px;width:600px;" id="rightdiv"></div></td>
	</tr>
	
</div>
<a class="easyui-linkbutton" onclick="javascript:index()"> &nbsp;&nbsp;首页&nbsp;&nbsp; </a>
<!-- manage_right e-->
</form>
<script type="text/javascript">

 var rightchart = echarts.init(document.getElementById('rightdiv'));
 var rightoption = {
      title : {
         text: '统计',
         textStyle: {
            fontSize: 14,
            fontWeight: 'bolder',
            color: '#333'          // 主标题文字颜色
        },
         x:'left',
         backgroundColor:'#cccccc'
     },
     tooltip : {
         trigger: 'axis',
         axisPointer : {            // 坐标轴指示器，坐标轴触发有效
             type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
         }
     },
     grid: {
         left: '3%',
         right: '4%',
         bottom: '3%',
         containLabel: true
     },
     xAxis : [
         {
             type : 'category',
             data : ${namelist}
         }
     ],
     yAxis : [
         {
             type : 'value'
         }
         
     ],
     series : [
         {
             name:'年龄',
             type:'bar',
             barWidth: '30%',
             data:${valuelist}
         }
     ]
 };
 rightchart.setOption(rightoption);
</script>
</body>