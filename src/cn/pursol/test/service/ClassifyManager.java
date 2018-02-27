package cn.pursol.test.service;

/**
*<p>Title: 分类</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.Classify;
import java.util.List;
import com.util.search.PageList;

import com.util.service.Manager;

public interface ClassifyManager extends Manager {
/**
*根据id获取分类
 *@param classifyid Integer;
*@return Classify
*/
public Classify getClassify(String classifyid);

/**
*增加分类
 *@param classify Classify;
*@return Classify
*/
public Classify addClassify(Classify classify);

/**
*删除分类
 *@param classifyid Integer;
*@return Classify
*/
public Classify delClassify(String classifyid);

/**
*修改分类
 *@param classify Classify;
*@return Classify
*/
public Classify updateClassify(Classify classify);

/**
*获取分类集合
 *@return List
*/
public List getClassifys (List condition,String orderby,int pagesize);

/**
*获取一页分类集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageClassifys (List condition,String orderby,int start,int pagesize);

}