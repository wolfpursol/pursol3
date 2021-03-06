<%@page import="com.bzt.gpw.bo.GpwAreaInfo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.util.string.encode.Encode"%>
<%@page import="com.bzt.server.bo.ServerInfo"%>
<%@ include file="../../public/jsp/taglibs.jsp"%>
<html:html>

<HEAD>
<TITLE>直属学校</TITLE>
<%@ include file="../../public/jsp/meta.jsp"%>
<%@ include file="../../public/jsp/style.jsp"%>
<SCRIPT language=javascript src="/public/js/checkform.js"></SCRIPT>
<Script language="JavaScript"  src="/public/js/autocheckform.js"></Script>
<Script language="JavaScript"  src="/public/js/prototype.js"></Script>

<SCRIPT language=javascript>

function saveRecord(){
  if(document.getElementById("servername").value==""){
  alert("服务器名称 不能为空");
  return;
  }
  if(document.getElementById("nativename").value==""){
  alert("机器名称 不能为空");
  return;
  }
	

  obj = document.all("serverInfoActionForm");
  obj.action='serverInfoAction.do?method=updateSave';
  obj.submit();
}
</SCRIPT>
</HEAD>
<BODY leftMargin=0 topMargin=0>
<%ServerInfo model = (ServerInfo)request.getAttribute("model"); %>
<TABLE class="page_maintable">
  <TR>
    <TD class="page_title">服务器信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/serverInfoAction.do" method="post" >
       <TABLE width="100%" border="0" align=center border=0>
            <tr>
              <td class="table_edit_right">服务器名称:</td>
              <td class="table_edit_left"><input type="text" class="input" CK_NAME="服务器名称" CK_TYPE="NotEmpty" size="40"  name="servername" id="servername" value="<bean:write property="servername"  name="model"/>">*</td>
            </tr>
              <tr>
              <td class="table_edit_right">IP地址:</td>
              <td class="table_edit_left"> <bean:write property="ipaddress"  name="model"/>
            </tr>
              <tr>
              <td class="table_edit_right">机器名称:</td>
              <td class="table_edit_left"><input type="text" class="input" size="40" name="nativename" id="nativename" value="<bean:write property="nativename"  name="model"/>">(不建议修改)</td>
            </tr>
            <tr>
              <td class="table_edit_right">时间:</td>
              <td class="table_edit_left">
                <bean:write property="createtime"  name="model"/>
              </td>
            </tr>
            <tr>
              <td class="table_edit_right">备注:</td>
              <td class="table_edit_left">
                <textarea name="remark" style="width:550px;height:120px;" wrap="physical"><bean:write property="remark"  name="model"/></textarea>
              </td>
            </tr>
            <tr height="40">
               <td colspan="2" align="center">
                   <input type="button" value="&nbsp;保&nbsp;存&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                   <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
               </td>
             </tr>
       </table>
        <input type="hidden" name="objid" value="<bean:write property="id"  name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
