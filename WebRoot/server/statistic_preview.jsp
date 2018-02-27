<%@page import="java.util.List"%>
<%@page import="com.bzt.server.bo.ServerDiscSize"%>
<%@page import="com.bzt.server.bo.ServerRunStatistics"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>服务器信息</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>
function saveRecord(){
  obj = document.all("serverInfoActionForm");
  obj.action='serverInfoAction.do?method=beforeUpdate';
  obj.submit();
}
</SCRIPT>
<style type="text/css">
/*--友好表格样式--*/
.sAdminNice{width:600px;margin:0 auto;color:#666; font-size:12px; border-collapse:collapse; background-color:#fff;/*细线表格代码*/}
.sAdminNice td{border:1px solid #d7ebff;/*细线表格线条颜色*/ padding:8px;}
.sAdminNice th{padding:8px;background:#f7fcff;font-size:14px;border:1px solid #d7ebff;}
.sAdminNice thead, .sAdminNice th{text-align:left;}
</style>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">服务器详细信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/serverInfoAction.do" method="post" >
       <TABLE width="600" border="0" align=center border=0 class="sAdminNice">
            <tr>
              <td class="">服务器名称:</td>
              <td class="table_edit_left"><bean:write property="machinename"  name="model"/></td>
            </tr> 
              <tr>
              <td class="">IP地址:</td>
              <td class="table_edit_left"><bean:write property="ipaddress"  name="model"/></td>
            </tr>
            <tr>
              <td class="">操作系统名称:</td>
              <td class="table_edit_left"><bean:write property="osversion"  name="model"/></td>
            </tr>
            <tr>
              <td class="">JDK版本:</td>
              <td class="table_edit_left"><bean:write property="jdkversion"  name="model"/></td>
            </tr>
            <tr>
              <td class="">JDK安装路径:</td>
              <td class="table_edit_left"><bean:write property="jdkpath"  name="model"/></td>
            </tr>
            <tr>
              <td class="">Cpu使用率:</td>
              <td class="table_edit_left"><bean:write property="cpuutilizationrate"  name="model"/>%</td>
            </tr>
            <tr>
              <td class="">最大物理内存:</td>
              <td class="table_edit_left"><bean:write property="maxphymemory"  name="model"/>&nbsp;Mb</td>
            </tr>
             <tr>
              <td class="">物理内存使用率:</td>
              <td class="table_edit_left"><bean:write property="memutilizationrate"  name="model"/>%</td>
            </tr>
             <tr>
              <td class="">最大JVM内存:</td>
              <td class="table_edit_left"><bean:write property="maxjvmmemory"  name="model"/></a>&nbsp;Mb</td>
            </tr>
             <tr>
              <td class="">jvm内存使用率:</td>
              <td class="table_edit_left"><bean:write property="jvmmemoryutilizationrate"  name="model"/>%</td>
            </tr>
            
            <%  
            	List serverDiscSizes = (List)request.getAttribute("serverDiscSizes");
            	if(serverDiscSizes!=null&&serverDiscSizes.size()>0){
            	ServerDiscSize serverDiscSize = null;
            	for(int i=0;i<serverDiscSizes.size();i++){
            	 serverDiscSize = (ServerDiscSize)serverDiscSizes.get(i);
            	
            	%>
            	<tr>
	              <td class=""><%=serverDiscSize.getDriveletter() %>&nbsp;盘 &nbsp;使用百分比:</td>
	              <td class="table_edit_left"><%=serverDiscSize.getPercentage() %>% &nbsp;&nbsp;&nbsp;&nbsp;磁盘容量：&nbsp;<%=serverDiscSize.getActualsize()%>Mb &nbsp;&nbsp;&nbsp;&nbsp;剩余容量：<%=serverDiscSize.getRessize() %>Mb</td>
	            </tr>
            	
            	<%}
            	 } %>
            <tr>
              <td class="">统计时间:</td>
              <td class="table_edit_left"><bean:write property="createtimme"  name="model"/></td>
            </tr>
             <tr height="40">
                <td colspan="2" align="center">
                    <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
              </tr>
       </table>
         <input type="hidden" name="objid" value="<bean:write property="staticid" name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
