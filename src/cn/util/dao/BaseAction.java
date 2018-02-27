package cn.util.dao;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.pursol.sys.bo.SysUserLog;
import cn.pursol.sys.service.SysUserLogManager;
import cn.pursol.util.IpUtil;

import com.util.date.DateTime;

public class BaseAction extends DispatchAction {
	protected final Log log = LogFactory.getLog(getClass());
	public static final String SECURE = "secure";
	private static ApplicationContext ctx = null;

	/**
	 * Convenience method to bind objects in Actions
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		if (ctx == null) {
			// 方法一
			// ServletContext servletContext =
			// request.getSession().getServletContext();
			// ctx =
			// WebApplicationContextUtils.getWebApplicationContext(servletContext);
			// 方法二
			// String[] locations = {"applicationContext-init.xml",
			// "applicationContext-shop.xml", "applicationContext-sys.xml"};
			// ctx = new ClassPathXmlApplicationContext(locations);
			// 方法三

			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
		}
		return ctx.getBean(name);
	}

	/**
	 * Convenience method to initialize messages in a subclass.
	 * @param request the current request
	 * @return the populated (or empty) messages
	 */
	public ActionMessages getMessages(HttpServletRequest request) {
		ActionMessages messages = null;
		HttpSession session = request.getSession();

		if (request.getAttribute(Globals.MESSAGE_KEY) != null) {
			messages = (ActionMessages) request.getAttribute(Globals.MESSAGE_KEY);
			saveMessages(request, messages);
		} else if (session.getAttribute(Globals.MESSAGE_KEY) != null) {
			messages = (ActionMessages) session.getAttribute(Globals.MESSAGE_KEY);
			saveMessages(request, messages);
			session.removeAttribute(Globals.MESSAGE_KEY);
		} else {
			messages = new ActionMessages();
		}

		return messages;
	}

	/**
	 * 增加日志信息 对数据库有增加、删除、修改操作时需要加入日志记录
	 * @param request HttpServletRequest
	 * @param descript String
	 */
	public void addLog(HttpServletRequest request, String descript) {
		SysUserLog model = new SysUserLog();
		model.setCreatedate(DateTime.getDate());
		model.setDescript(descript);
		model.setUserip(IpUtil.getIpAddr(request));

		SysUserLogManager manager = (SysUserLogManager) getBean("sysUserLogManager");
		manager.addSysUserLog(model);
	}

	/**
	 * Gets the method name based on the mapping passed to it
	 */
	private String getActionMethodWithMapping(HttpServletRequest request,
			ActionMapping mapping) {
		return getActionMethod(request, mapping.getParameter());
	}

	/**
	 * Gets the method name based on the prepender passed to it.
	 */
	protected String getActionMethod(HttpServletRequest request, String prepend) {
		String name = null;

		name = request.getParameter(prepend);
		if (name != null) {
			name = name.trim();
			return name.replace(name.charAt(0), Character.toLowerCase(name
					.charAt(0)));
		}

		Enumeration e = request.getParameterNames();

		while (e.hasMoreElements()) {
			String currentName = (String) e.nextElement();

			if (currentName.startsWith(prepend + ".")) {
				if (log.isDebugEnabled()) {
					log.debug("calling method: " + currentName);
				}

				String[] parameterMethodNameAndArgs = StringUtils.split(
						currentName, ".");
				name = parameterMethodNameAndArgs[1];
				break;
			}
		}

		return name;
	}

	/**
	 * Convenience method for getting an action form base on it's mapped scope.
	 * @param mapping The ActionMapping used to select this instance
	 * @param request The HTTP request we are processing
	 * @return ActionForm the form from the specifies scope, or null if nothing found
	 */
	protected ActionForm getActionForm(ActionMapping mapping,
			HttpServletRequest request) {
		ActionForm actionForm = null;

		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				actionForm = (ActionForm) request.getAttribute(mapping
						.getAttribute());
			} else {
				HttpSession session = request.getSession();
				actionForm = (ActionForm) session.getAttribute(mapping
						.getAttribute());
			}
		}

		return actionForm;
	}

	/**
	 * Convenience method for removing the obsolete form bean.
	 * @param mapping  The ActionMapping used to select this instance
	 * @param request The HTTP request we are processing
	 */
	protected void removeFormBean(ActionMapping mapping,
			HttpServletRequest request) {
		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.removeAttribute(mapping.getAttribute());
			} else {
				HttpSession session = request.getSession();
				session.removeAttribute(mapping.getAttribute());
			}
		}
	}

	/**
	 * Convenience method to update a formBean in it's scope
	 * @param mapping The ActionMapping used to select this instance
	 * @param request The HTTP request we are processing
	 * @param form The ActionForm
	 */
	protected void updateFormBean(ActionMapping mapping,
			HttpServletRequest request, ActionForm form) {
		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.setAttribute(mapping.getAttribute(), form);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute(mapping.getAttribute(), form);
			}
		}
	}
}
