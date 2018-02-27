package cn.pursol.test.web.action;

import com.util.web.action.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;
import com.util.string.encode.Encode;
import com.util.search.PageUtil;
import com.util.search.PageList;
import com.util.search.SearchModel;
import java.util.*;
import cn.pursol.test.bo.Classify;
import cn.pursol.test.service.ClassifyManager;
import cn.pursol.test.web.form.ClassifyActionForm;

/**
*<p>Title: 分类</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class ClassifyAction extends BaseAction {
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
	ClassifyManager manager=(ClassifyManager)getBean("classifyManager");
	PageUtil pageUtil = new PageUtil(httpServletRequest);
	List<SearchModel> condition = new ArrayList<SearchModel>();
	PageList page = manager.getPageClassifys(condition,"",pageUtil.getStartCount(),pageUtil.getPageSize());
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
	Classify model=new Classify();
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
	ClassifyActionForm form=(ClassifyActionForm)actionForm;
	ClassifyManager manager=(ClassifyManager)getBean("classifyManager");
	try {
	Classify model=form.getClassify();
	manager.addClassify(model);
	addLog(httpServletRequest,"增加了一个分类");}
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
	ClassifyManager manager=(ClassifyManager)getBean("classifyManager");
	String objid = Encode.nullToBlank(httpServletRequest.getParameter("objid"));
	try {
	Classify model=manager.getClassify(objid);
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
	ClassifyActionForm form=(ClassifyActionForm)actionForm;
	ClassifyManager manager=(ClassifyManager)getBean("classifyManager");
	try {
	Classify model=form.getClassify();
	manager.updateClassify(model);
	addLog(httpServletRequest,"修改了一个分类");}
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
	ClassifyManager manager=(ClassifyManager)getBean("classifyManager");
String[] checkids = httpServletRequest.getParameterValues("checkid");
for (int i = 0; i < checkids.length; i++) {
	manager.delClassify(checkids[i]);
}
	return list(actionMapping, actionForm, httpServletRequest,httpServletResponse);
}
}