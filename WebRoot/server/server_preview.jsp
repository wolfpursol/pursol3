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
    <TD class="page_title">服务器信息</TD>
  </TR>
  <TR>
    <TD vAlign=top align="center">
      <html:form action="/serverInfoAction.do" method="post" >
       <TABLE width="600" border="0" align=center border=0 class="sAdminNice">
            <tr>
              <td class="">服务器名称:</td>
              <td class="table_edit_left"><bean:write property="servername"  name="model"/></td>
            </tr>
              <tr>
              <td class="">IP地址:</td>
              <td class="table_edit_left"><bean:write property="ipaddress"  name="model"/></td>
            </tr>
            <tr>
              <td class="">机器名称:</td>
              <td class="table_edit_left"><bean:write property="nativename"  name="model"/></td>
            </tr>
            <tr>
              <td class="">时间:</td>
              <td class="table_edit_left"><bean:write property="createtime"  name="model"/></td>
            </tr>
            <tr>
              <td class="">备注:</td>
              <td class="table_edit_left"><bean:write property="remark"  name="model"/></td>
            </tr>
             <tr height="40">
                <td colspan="2" align="center">
                    <input type="button" value="&nbsp;修&nbsp;改&nbsp;" id="btnsave" name="btnsave" class="btn_save" onClick="saveRecord()">
                    <input type="button" value="&nbsp;返&nbsp;回&nbsp;" name="btnreturn" class="btn_cancel" onClick="javascript:history.go(-1)">
                </td>
              </tr>
       </table>
         <input type="hidden" name="objid" value="<bean:write property="id" name="model"/>"/>
       </html:form>
    </TD>
    </TR>
</TABLE>
</BODY>
</html:html>
