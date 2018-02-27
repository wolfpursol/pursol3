package cn.pursol.test.service;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.Article;
import java.util.List;
import com.util.search.PageList;

import com.util.service.Manager;

public interface ArticleManager extends Manager {
/**
*根据id获取用户
 *@param cid Integer;
*@return Article
*/
public Article getArticle(String cid);

/**
*增加用户
 *@param article Article;
*@return Article
*/
public Article addArticle(Article article);

/**
*删除用户
 *@param cid Integer;
*@return Article
*/
public Article delArticle(String cid);

/**
*修改用户
 *@param article Article;
*@return Article
*/
public Article updateArticle(Article article);

/**
*获取用户集合
 *@return List
*/
public List getArticles (List condition,String orderby,int pagesize);

/**
*获取一页用户集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageArticles (List condition,String orderby,int start,int pagesize);

}