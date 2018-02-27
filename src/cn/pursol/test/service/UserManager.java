package cn.pursol.test.service;

/**
*<p>Title: 用户</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.test.bo.User;
import java.util.List;
import com.util.search.PageList;

import com.util.service.Manager;

public interface UserManager extends Manager {
/**
*根据id获取用户
 *@param id Integer;
*@return User
*/
public User getUser(String id);

/**
*增加用户
 *@param user User;
*@return User
*/
public User addUser(User user);

/**
*删除用户
 *@param id Integer;
*@return User
*/
public User delUser(String id);

/**
*修改用户
 *@param user User;
*@return User
*/
public User updateUser(User user);

/**
*获取用户集合
 *@return List
*/
public List getUsers (List condition,String orderby,int pagesize);

/**
*获取一页用户集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageUsers (List condition,String orderby,int start,int pagesize);

}