package cn.pursol.sys.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.util.string.encode.Encode;
import com.util.web.action.BaseAction;

public class SysAdminLoginAction extends BaseAction {
	
	/**
	 * 
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String redirecturl = Encode.nullToBlank(httpServletRequest.getParameter("redirecturl"));//登录后的跳转地址
		httpServletRequest.setAttribute("redirecturl", redirecturl);
		
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("s_userid");
		session.removeAttribute("s_sysuserinfo");
		session.removeAttribute("s_classid");
		session.removeAttribute("s_classinfo");
		session.removeAttribute("s_unitid");
		session.removeAttribute("s_sysunitinfo");
		session.removeAttribute("s_productlist");
		session.setAttribute("randomcode", RandomStringUtils.randomNumeric(4).toUpperCase());
		
		return actionMapping.findForward("index");
	}
}
