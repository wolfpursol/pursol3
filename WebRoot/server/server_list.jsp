<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.bzt.server.bo.ServerInfo,com.bzt.sys.util.Constants"%>
<html:html>

<HEAD>
<TITLE>服务器信息</TITLE>

<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script type=text/javascript src="/skin/vod003/js/jquery-1.8.0.min.js"></script>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;
function addRecord(){
  document.pageForm.action="serverInfoAction.do?method=beforeAdd";
  document.pageForm.submit();
}
function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="serverInfoAction.do?method=list";
  document.pageForm.submit();
}
function userManager(unitid){
	var diag = new top.Dialog();
	diag.Title = "服务器信息";
	diag.URL = "sysUnitUserInfoAction.do?method=main&unitid="+unitid;
	diag.Width = 800;
	diag.Height = 520;
	diag.CancelEvent = function(){
		diag.close();
	};
	diag.show();
}
function batchAddRecord(){
  document.getElementById('fade1').style.display = 'block';
  document.pageForm.action="/serverInfoAction.do?method=selectFile";
  document.pageForm.submit();
}

function changeArea(type, areano){
	if(type == '1'){
		if(areano != ''){
			 new Ajax.Request(
					 "/serverInfoAction.do?method=getArea&areano=" + areano + "&ram=" + Math.random(),
						{
						method:"get",
						contentType: "application/x-www-form-urlencoded; charset=utf-8",
						asynchronous:false,//true为异步请求
						onComplete:function(data){
							var responseObj = data.responseText;
							if(data != ''){
								document.getElementById('areano2').innerHTML = responseObj;
							}else{
								document.getElementById('areano2').innerHTML = "<option value=''>请选择</option>";
							}
						}
						}
					);
		}else{
			document.getElementById('areano2').innerHTML = "<option value=''>请选择</option>";
		}
		document.getElementById('areano3').innerHTML = "<option value=''>请选择</option>";
	}
	if(type == '2'){
		if(areano != ''){
			new Ajax.Request(
					"/serverInfoAction.do?method=getArea&areano=" + areano + "&ram=" + Math.random(),
						{
						method:"get",
						contentType: "application/x-www-form-urlencoded; charset=utf-8",
						asynchronous:false,//true为异步请求
						onComplete:function(data){
							var responseObj = data.responseText;
							if(data != ''){
								document.getElementById('areano3').innerHTML = responseObj;
							}else{
								document.getElementById('areano3').innerHTML = "<option value=''>请选择</option>";
							}
						}
						}
					);
		}else{
			document.getElementById('areano3').innerHTML = "<option value=''>请选择</option>";
		}
	}
}

</SCRIPT>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<%@ include file="/edu/res/tip.jsp"%>
<FORM name="pageForm" method=post>
<table width="98%" align="center">
  <TR>
    <TD class="page_title" colspan="2">服务器信息</TD>
 </TR>
 <TR>
    <TD class="page_blank"></TD>
  </TR>
 <tr>
   <td>
      <INPUT class="btn_all" onclick="setState(true)" type="button" value="全选" name="selectall">
      <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
      <INPUT class="btn_add"  onclick="addRecord()" type="button" value="新增" name="btnadd">
      <INPUT class="btn_del"  onclick="delRecord('serverInfoAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
    </td>
     <TD align="right">
     </TD>
 </tr>
</table>
<TABLE class="page_table" cellSpacing=1 cellPadding=1 width="97%" align="center">
<tr >
    <TD class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
    <TD class="table_title"  width="60">服务器名称</TD>
    <TD class="table_title" width="50">IP地址</TD>
    <TD class="table_title" width="60">机器名称</TD>
    <TD class="table_title" width="60">时间</TD>
    <TD class="table_title" width="40">备注</TD>
    <TD class="table_title" width="25">操作</TD>
</tr>
<!--循环列出所有数据-->
  <%Integer startcount = (Integer)request.getAttribute("startcount"); %>
  <logic:iterate id="model" name="pagelist" property="datalist" indexId="index">
     <TR onMouseover="chgTRbg(this,'on')" class="table_list" onMouseout="chgTRbg(this,'off')" bgcolor="#ffffff">
     <TD align="center">
       <input type="checkbox" name="checkid" value="<bean:write property="id" name="model"/>" <bean:write property="flags" name="model"/>>
     </TD>
     <TD align="center" title="<bean:write name='model' property='servername'/>"><a href="/html/<bean:write name="model" property="id"/>.htm" target="_blank"><bean:write name="model" property="servername"/></a></TD>
     <TD align="center"><bean:write name="model" property="ipaddress"/></TD>
     <TD align="center" title="<bean:write name="model" property="nativename"/>"><bean:write name="model" property="nativename"/></TD>
     <TD align="center"><bean:write name="model" property="createtime"/></TD>
     <TD align="center" style="height:30px; overflow:hidden ; display:block" title="<bean:write name="model" property="remark" />"><bean:write name="model" property="remark"/></TD>
      <td align="center">
      <input type="button" class="btn_text" value="详情" onclick="previewThisRecord('serverInfoAction.do',<bean:write name="model" property="id"/>)" />
      </td>
     </TR>
     </logic:iterate>
</TABLE>
<TABLE width="98%" border=0 align="center">
  <TR>
    <TD align=center>
      <java2page:pager url="serverInfoAction.do?method=list" name="pagelist" />
      <input type="hidden" name="startcount" id="startcount" value="<bean:write name="startcount"/>">
    </TD>
  </TR>
</TABLE>
</FORM>
</BODY>
</html:html>
