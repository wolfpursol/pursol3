package cn.pursol.test.service.impl;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.User;
import com.util.dao.BaseDAO;
import cn.pursol.test.service.UserManager;
import java.util.List;
import com.util.search.PageList;

import com.util.service.impl.ManagerImpl;

public class UserManagerImpl extends ManagerImpl implements UserManager{
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
 *@param id Integer;
*@return User
*/
public User getUser(String id){
	
		return  (User)baseDAO.getObject(modelname,User.class,Integer.parseInt(id));
}

/**
*增加用户
 *@param user User;
*@return User
*/
public User addUser(User user){
		return (User)baseDAO.addObject(modelname,user);
}

/**
*删除用户
 *@param id Integer;
*@return User
*/
public User delUser(String id){
		 User model=getUser(id);
		return (User)baseDAO.delObject(modelname,model);
}

/**
*修改用户
 *@param user User;
*@return User
*/
public User updateUser(User user){
		return (User)baseDAO.updateObject(modelname,user);
}

/**
*获取用户集合
 *@return List
*/
public List getUsers(List condition,String orderby,int pagesize){
		return baseDAO.getObjects("User",condition,orderby,pagesize);
	}

/**
*获取一页用户集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageUsers (List condition,String orderby,int start,int pagesize){
		return baseDAO.getPageObjects("User","id",condition, orderby, start,pagesize);
	}

}