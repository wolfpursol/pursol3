package cn.pursol.test.web.form;

import cn.pursol.test.bo.Article;
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

public class ArticleActionForm extends ActionForm {
private String act;
private Article article=new Article();

public String getAct() {
	return act;
}

public void setAct(String act) {	this.act = act;}

public Article getArticle(){
	return this.article;
}

public void setArticle(Article article){
	this.article=article;
}

public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	return null;
}
public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
}
}