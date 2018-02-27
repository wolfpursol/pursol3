package cn.pursol.sys.web.form;

import cn.pursol.sys.bo.SysUserLog;
import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
*<p>Title: 日志</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class SysUserLogActionForm extends ActionForm {
private String act;
private SysUserLog sysUserLog=new SysUserLog();

public String getAct() {
	return act;
}

public void setAct(String act) {	this.act = act;}

public SysUserLog getSysUserLog(){
	return this.sysUserLog;
}

public void setSysUserLog(SysUserLog sysUserLog){
	this.sysUserLog=sysUserLog;
}

public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	return null;
}
public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
}
}