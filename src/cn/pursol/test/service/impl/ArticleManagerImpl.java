package cn.pursol.test.service.impl;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.Article;
import com.util.dao.BaseDAO;
import cn.pursol.test.service.ArticleManager;
import java.util.List;
import com.util.search.PageList;

import com.util.service.impl.ManagerImpl;

public class ArticleManagerImpl extends ManagerImpl implements ArticleManager{
private BaseDAO baseDAO;
private String modelname = "用户";
/**
*加载baseDAO 
*@param BaseDAO baseDAO
*/
public void setBaseDAO(BaseDAO baseDAO) {
	this.baseDAO = baseDAO;
}

/**
*根据id获取用户
 *@param cid Integer;
*@return Article
*/
public Article getArticle(String cid){
		return  (Article)baseDAO.getObject(modelname,Article.class,cid);
}

/**
*增加用户
 *@param article Article;
*@return Article
*/
public Article addArticle(Article article){
		return (Article)baseDAO.addObject(modelname,article);
}

/**
*删除用户
 *@param cid Integer;
*@return Article
*/
public Article delArticle(String cid){
		 Article model=getArticle(cid);
		return (Article)baseDAO.delObject(modelname,model);
}

/**
*修改用户
 *@param article Article;
*@return Article
*/
public Article updateArticle(Article article){
		return (Article)baseDAO.updateObject(modelname,article);
}

/**
*获取用户集合
 *@return List
*/
public List getArticles(List condition,String orderby,int pagesize){
		return baseDAO.getObjects("Article",condition,orderby,pagesize);
	}

/**
*获取一页用户集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageArticles (List condition,String orderby,int start,int pagesize){
		return baseDAO.getPageObjects("Article","cid",condition, orderby, start,pagesize);
	}

}