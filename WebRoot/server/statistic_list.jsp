<%@page import="com.bzt.server.bo.ServerInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>服务器详情</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<script language="javaScript" type="text/javascript" SRC="/public/js/comm.js"></SCRIPT>
<SCRIPT language=javascript>
var num=<bean:write name="pagelist" property="pageCount" />;

function searchRecord(){
  document.pageForm.startcount.value = "0";
  document.pageForm.action="/serverRunStatisticsAction.do?method=list";
  document.pageForm.submit();
}
</SCRIPT>

</HEAD>

<BODY leftMargin=0 topMargin=0 scroll=auto>
<FORM name="pageForm" method=post>
<TABLE width="98%" align="center">
  <TR>
    <TD class="page_title">服务器详情列表</TD>
  </TR>
  <TR>
    <TD height="30">
      <TABLE class="table_search_title" width="100%" cellSpacing=1 cellPadding=1  >
          <tr>
               <td >系统管理>>服务器详情</td>
            </tr>
       </table>
        <TABLE class="table_search" width="100%" cellSpacing=1 cellPadding=1  >
            <tr>
              <td class="bg_basecolor" align="left">
                <table cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="80" height="25" align="right">选择服务器：</td>
                    <td>
                    <select name="serverid" onchange="searchRecord()">
                    <option value="">全部</option>
                    <%
                    	List serverInfos = (List)request.getAttribute("serverInfos");
                    	String serverid = (String)request.getAttribute("serverid");
                    	if(serverInfos!=null&&serverInfos.size()>0){
                    	ServerInfo serverInfo = null;
                    	for(int i=0;i<serverInfos.size();i++){
                    	serverInfo=(ServerInfo)serverInfos.get(i);
                    %>
                    <option value="<%= serverInfo.getId() %>" <%if(serverInfo.getId().equals(serverid)){ %>selected=true<%} %>><%= serverInfo.getServername()%></option>
                    <%}
                    } %>
                    </select>
                  </tr>
                </table>
              </td>
              </tr>
        </table>
    </TD>
  </TR>
  <TR>
    <TD class="page_blank"></TD>
  </TR>
  <TR>
     <TD vAlign=top align="left">
       <INPUT class="btn_all" onClick="setState(true)" type="button" value="全选" name="selectall">
       <INPUT class="btn_none"  onclick="setState(false)" type="button" value="全不选" name="selectnone">
       <INPUT class="btn_del"  onclick="delRecord('serverRunStatisticsAction.do?method=delBatchRecord')" type="button" value="删除" name="btndel">
      </TD>
   </TR><!--serach end-->
</table>

 <TABLE class="page_table" cellSpacing=1 cellPadding=1 width="98%" align="center">
	<tr>
      <TD align="center" class="table_title" width="35" ><input type="checkbox" name="select"  onclick="setState(this.checked)"></TD>
      <TD align="center" class="table_title" width="150">机器名</TD>
      <TD align="center" class="table_title" width="130">JDK版本</TD>
      <TD align="center" class="table_title" width="50">Cpu使用率</TD>
      <TD align="center" class="table_title" width="80">物理内存使用率</TD>
      <TD align="center" class="table_title" width="110">最大JVM内存</TD>
      <TD align="center" class="table_title" width="60">JVM内存使用率</TD>
      <TD align="center" class="table_title" width="80">系统类型</TD>
       <TD align="center" class="table_title" width="65">统计时间</TD>
      <TD align="center" width="50" class="table_title">操作</TD>
	</tr>
	<!--循环列出所有数据-->
    <logic:iterate id="model" property="datalist" name="pagelist" >
    <TR onMouseover="chgTDbg(this,'on')" class="table_list" onMouseout="chgTDbg(this,'off')" bgcolor="#ffffff" >
      <TD align="center"><input type="checkbox" name="checkid" value='<bean:write property="staticid" name="model"/>'></TD>
      <TD align="left">&nbsp;<bean:write name="model" property="machinename"/></TD>
      <TD align="center">&nbsp;<bean:write name="model" property="jdkversion"/></TD>
      <TD align="center"><bean:write name="model" property="cpuutilizationrate"/>%</TD>
      <TD align="center"><bean:write name="model" property="memutilizationrate"/>%</TD>
      <TD align="center"><bean:write name="model" property="maxjvmmemory"/>&nbsp;Mb</TD>
      <TD align="center"><bean:write name="model" property="jvmmemoryutilizationrate"/>%</TD>
      <TD align="center">
      <logic:equal value="1" name="model" property="ostype">windows</logic:equal>
      <logic:equal value="2" name="model" property="ostype">linux</logic:equal>
      </TD>
      <TD align="center"><bean:write name="model" property="createtimme"/></TD>
      <td align="center">
        <input type="button" value="详情" class="btn_text" onclick="javascript:previewThisRecord('serverRunStatisticsAction.do','<bean:write name="model" property="staticid" />')" />
      </td>
     </TR>
     </logic:iterate>
 </TABLE>
 <TABLE width="98%">
  <TR>
    <TD vAlign=top borderColor=#ffffff align="center">
       <java2page:pager url="serverRunStatisticsAction.do?method=list" name="pagelist"/>
       <input type="hidden" id="startcount" name="startcount" value="<bean:write name="startcount"/>" />
   </TD>
  </TR>
  <!--page end-->
</TABLE>

</form>
</BODY>
</html:html>


