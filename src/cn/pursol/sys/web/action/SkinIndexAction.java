package cn.pursol.sys.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.util.string.encode.Encode;

public class SkinIndexAction {
	/**
	 * 
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("s_userid");
		session.removeAttribute("s_user");
		session.setAttribute("randomcode", RandomStringUtils.randomNumeric(4).toUpperCase());
		
		return actionMapping.findForward("login");
	}
}
