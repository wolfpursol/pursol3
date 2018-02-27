package cn.pursol.test.service.impl;

/**
*<p>Title: 分类</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.Classify;
import com.util.dao.BaseDAO;
import cn.pursol.test.service.ClassifyManager;
import java.util.List;
import com.util.search.PageList;

import com.util.service.impl.ManagerImpl;

public class ClassifyManagerImpl extends ManagerImpl implements ClassifyManager{
private BaseDAO baseDAO;
private String modelname = "分类";
/**
*加载baseDAO 
*@param BaseDAO baseDAO
*/
public void setBaseDAO(BaseDAO baseDAO) {
	this.baseDAO = baseDAO;
}

/**
*根据id获取分类
 *@param classifyid Integer;
*@return Classify
*/
public Classify getClassify(String classifyid){
		return  (Classify)baseDAO.getObject(modelname,Classify.class,classifyid);
}

/**
*增加分类
 *@param classify Classify;
*@return Classify
*/
public Classify addClassify(Classify classify){
		return (Classify)baseDAO.addObject(modelname,classify);
}

/**
*删除分类
 *@param classifyid Integer;
*@return Classify
*/
public Classify delClassify(String classifyid){
		 Classify model=getClassify(classifyid);
		return (Classify)baseDAO.delObject(modelname,model);
}

/**
*修改分类
 *@param classify Classify;
*@return Classify
*/
public Classify updateClassify(Classify classify){
		return (Classify)baseDAO.updateObject(modelname,classify);
}

/**
*获取分类集合
 *@return List
*/
public List getClassifys(List condition,String orderby,int pagesize){
		return baseDAO.getObjects("Classify",condition,orderby,pagesize);
	}

/**
*获取一页分类集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageClassifys (List condition,String orderby,int start,int pagesize){
		return baseDAO.getPageObjects("Classify","classifyid",condition, orderby, start,pagesize);
	}

}