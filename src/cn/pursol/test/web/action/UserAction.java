package cn.pursol.test.web.action;

import com.util.web.action.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.*;

import javax.servlet.http.*;

import com.util.date.DateTime;
import com.util.string.encode.Encode;
import com.util.search.PageUtil;
import com.util.search.PageList;
import com.util.search.SearchCondition;
import com.util.search.SearchModel;
import java.util.*;
import cn.util.dao.BaseAction;
import cn.pursol.test.bo.User;
import cn.pursol.test.service.UserManager;
import cn.pursol.test.web.form.UserActionForm;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class UserAction extends BaseAction {
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
	UserManager manager=(UserManager)getBean("userManager");
	PageUtil pageUtil = new PageUtil(httpServletRequest);
	List<SearchModel> condition = new ArrayList<SearchModel>();
	PageList page = manager.getPageUsers(condition,"",pageUtil.getStartCount(),pageUtil.getPageSize());
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
	User model=new User();
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
public ActionForward addSave(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
	try {
	UserManager manager=(UserManager)getBean("userManager");
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	User model = new User();
	model.setAge(Integer.parseInt(value));
	model.setName(name);
	model.setCreatetime(DateTime.getDate());
	model.setState("1");
	User addUser = manager.addUser(model);
	if (addUser!=null) {
		String mappingurl ="/test/OK.jsp";
		ActionForward gotourl = new ActionForward(mappingurl);
		gotourl.setPath(mappingurl);
		gotourl.setRedirect(false);
		return gotourl;
	}else {
		return actionMapping.findForward("error");
	}
	}catch (Exception e1){
	}
	return null;
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
	UserManager manager=(UserManager)getBean("userManager");
	String objid = Encode.nullToBlank(httpServletRequest.getParameter("objid"));
	try {
	User model=manager.getUser(objid);
	httpServletRequest.setAttribute("act", "updateSave");
	httpServletRequest.setAttribute("model", model);
	}
catch (Exception e1){
}

	return actionMapping.findForward("edit");
}

	/**
	 * 统计
	 * @param actionMapping
	 * @param actionForward
	 * @param request
	 * @param response
	 * @return
	 */
public ActionForward statics(ActionMapping actionMapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		UserManager manager=(UserManager)getBean("userManager");
		
		List<SearchModel> condition = new ArrayList<SearchModel>();
		SearchCondition.addCondition(condition, "state", "=", "1");
		User user =null;
		List users = manager.getUsers(condition, "createtime", 0);
		JSONArray namelist = new JSONArray();
		JSONArray valuelist = new JSONArray();
		if (users!=null&&users.size()>0) {
			for (int i = 0; i < users.size(); i++) {
				user = (User) users.get(i);
				namelist.add(user.getName());
				valuelist.add(user.getAge());
			}
		}
		request.setAttribute("namelist", namelist);
		request.setAttribute("valuelist", valuelist);
		
		return actionMapping.findForward("statics");
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
	UserActionForm form=(UserActionForm)actionForm;
	UserManager manager=(UserManager)getBean("userManager");
	try {
	User model=form.getUser();
	manager.updateUser(model);
	addLog(httpServletRequest,"修改了一个用户");}
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
	UserManager manager=(UserManager)getBean("userManager");
	String checkid = httpServletRequest.getParameter("checkid");
	
	manager.delUser(checkid);
	return list(actionMapping, actionForm, httpServletRequest,httpServletResponse);
}
}