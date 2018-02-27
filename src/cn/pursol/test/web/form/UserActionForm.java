package cn.pursol.test.web.form;

import cn.pursol.test.bo.User;
import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class UserActionForm extends ActionForm {
private String act;
private User user=new User();

public String getAct() {
	return act;
}

public void setAct(String act) {	this.act = act;}

public User getUser(){
	return this.user;
}

public void setUser(User user){
	this.user=user;
}

public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	return null;
}
public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
}
}