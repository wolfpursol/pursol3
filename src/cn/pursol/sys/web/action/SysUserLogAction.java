package cn.pursol.sys.web.action;

import com.util.web.action.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.util.string.encode.Encode;
import com.util.search.PageUtil;
import com.util.search.PageList;
import com.util.search.SearchModel;
import java.util.*;
import cn.pursol.sys.bo.SysUserLog;
import cn.pursol.sys.service.SysUserLogManager;
import cn.pursol.sys.web.form.SysUserLogActionForm;

/**
*<p>Title: 日志</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class SysUserLogAction extends BaseAction {
	private String moduleid = "0000";

	/**
	* 列表显示
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward list(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLogManager manager=(SysUserLogManager)getBean("sysUserLogManager");
	PageUtil pageUtil = new PageUtil(httpServletRequest);
	List<SearchModel> condition = new ArrayList<SearchModel>();
	PageList page = manager.getPageSysUserLogs(condition,"",pageUtil.getStartCount(),pageUtil.getPageSize());
httpServletRequest.setAttribute("pagelist", page);
httpServletRequest.setAttribute("startcount", pageUtil.getStartCount());
return actionMapping.findForward("list");
}

	/**
	* 增加前
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward beforeAdd(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLog model=new SysUserLog();
	httpServletRequest.setAttribute("model", model);
	httpServletRequest.setAttribute("act", "addSave");
	return actionMapping.findForward("edit");
}

	/**
	* 增加时保存
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward addSave(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLogActionForm form=(SysUserLogActionForm)actionForm;
	SysUserLogManager manager=(SysUserLogManager)getBean("sysUserLogManager");
	try {
	SysUserLog model=form.getSysUserLog();
	manager.addSysUserLog(model);
	addLog(httpServletRequest,"增加了一个日志");}
catch (Exception e1){
}

	return list(actionMapping, actionForm, httpServletRequest,httpServletResponse);
}

	/**
	* 修改前
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward beforeUpdate(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLogManager manager=(SysUserLogManager)getBean("sysUserLogManager");
	String objid = Encode.nullToBlank(httpServletRequest.getParameter("objid"));
	try {
	SysUserLog model=manager.getSysUserLog(objid);
	httpServletRequest.setAttribute("act", "updateSave");
	httpServletRequest.setAttribute("model", model);
	}
catch (Exception e1){
}

	return actionMapping.findForward("edit");
}

	/**
	* 修改时保存
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward updateSave(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLogActionForm form=(SysUserLogActionForm)actionForm;
	SysUserLogManager manager=(SysUserLogManager)getBean("sysUserLogManager");
	try {
	SysUserLog model=form.getSysUserLog();
	manager.updateSysUserLog(model);
	addLog(httpServletRequest,"修改了一个日志");}
catch (Exception e1){
}

	return list(actionMapping, actionForm, httpServletRequest,httpServletResponse);
}

	/**
	* 批量删除
	* @param actionMapping ActionMapping
	* @param actionForm ActionForm
	* @param httpServletRequest HttpServletRequest
	* @param httpServletResponse HttpServletResponse
	* @return ActionForward
	*/
public ActionForward delBatchRecord(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
	SysUserLogManager manager=(SysUserLogManager)getBean("sysUserLogManager");
String[] checkids = httpServletRequest.getParameterValues("checkid");
for (int i = 0; i < checkids.length; i++) {
	manager.delSysUserLog(checkids[i]);
}
	return list(actionMapping, actionForm, httpServletRequest,httpServletResponse);
}
}