package cn.pursol.util;

import javax.servlet.http.*;

public class CookieUtil {

	public CookieUtil() {
	}

	/**
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0)
			return null;
		for (int i = 0; i < cookies.length; i++)
			if (name.equals(cookies[i].getName())
					&& request.getServerName().equals(cookies[i].getDomain()))
				return cookies[i];

		return null;
	}

	/**
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0)
			return null;
		for (int i = 0; i < cookies.length; i++)
			if (name.equals(cookies[i].getName()))
				return cookies[i].getValue();

		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0x278d00);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value != null ? value : "");
		cookie.setMaxAge(maxAge);
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return path != null && path.length() != 0 ? path : "/";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public static void deleteALLCookies(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie _cookie = new Cookie(cookies[i].getName(), "");
				_cookie.setMaxAge(0);
				_cookie.setPath("/");
				response.addCookie(_cookie);
			}

		}
	}
}
