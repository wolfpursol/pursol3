<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="com.util.search.PageList"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" import="java.util.Map,java.util.List"%>
<%@ include file="/public/jsp/taglibs.jsp"%>
<%@page import="cn.pursol.test.bo.User"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>列表</title>
 <script type="text/javascript" src="/public/js/jquery-1.7.2.min.js"></script>
 <link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/esayui/jquery-easyui-1.5.3/demo/demo.css">
	<script type="text/javascript" src="/esayui/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<script language=javascript>
	function edit(){
		document.pageForm.action = '/userAction.do?method=beforeAdd';
		document.pageForm.submit();
	}
	function statics(){
		document.pageForm.action = '/userAction.do?method=statics';
		document.pageForm.submit();
	}
	function del(id){
		window.location.href="/userAction.do?method=delBatchRecord&checkid="+id;
	}
</script>
 </head>
 <body>
<%
	PageList pagelist = (PageList)request.getAttribute("pagelist");
	ArrayList list = pagelist.getDatalist();
 %>
<form name="pageForm" method="post">
	<div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:200,align:'center'">姓名</th>
				<th data-options="field:'productid',width:200,align:'center'">年龄</th>
				<th data-options="field:'listprice',width:282,align:'center'">操作</th>
			</tr>
		</thead>
		<tbody>
	<%
		for(int i = 0;i<list.size();i++){
		User user= (User)list.get(i);
			%>
		<tr>
			<td><p style="font-size: 15px"> <%=user.getName() %></p></td>
			<td><%=user.getAge() %>&nbsp;岁</td>
			<td ><div style="text-align:center;height:auto;"><input value="删除" onclick="del(<%=user.getId() %>)" type="button"  ></input></div></td>
			</td>
	 	</tr>
			<%
		}
	 %>
		
		</tbody>
	</table>
<div >
<br/>
	<a href="#" onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
	<a href="#" onclick="javascript:statics()" class="easyui-linkbutton">统计</a>
</div>
<!-- manage_right e-->
</form>
</body>