package cn.pursol.test.web.form;

import cn.pursol.test.bo.Classify;
import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
*<p>Title: 分类</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

public class ClassifyActionForm extends ActionForm {
private String act;
private Classify classify=new Classify();

public String getAct() {
	return act;
}

public void setAct(String act) {	this.act = act;}

public Classify getClassify(){
	return this.classify;
}

public void setClassify(Classify classify){
	this.classify=classify;
}

public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	return null;
}
public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
}
}