package cn.pursol.sys.service;

/**
*<p>Title: 日志</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.sys.bo.SysUserLog;
import java.util.List;
import com.util.search.PageList;

import com.util.service.Manager;

public interface SysUserLogManager extends Manager {
/**
*根据id获取日志
 *@param logid Integer;
*@return SysUserLog
*/
public SysUserLog getSysUserLog(String logid);

/**
*增加日志
 *@param sysUserLog SysUserLog;
*@return SysUserLog
*/
public SysUserLog addSysUserLog(SysUserLog sysUserLog);

/**
*删除日志
 *@param logid Integer;
*@return SysUserLog
*/
public SysUserLog delSysUserLog(String logid);

/**
*修改日志
 *@param sysUserLog SysUserLog;
*@return SysUserLog
*/
public SysUserLog updateSysUserLog(SysUserLog sysUserLog);

/**
*获取日志集合
 *@return List
*/
public List getSysUserLogs (List condition,String orderby,int pagesize);

/**
*获取一页日志集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageSysUserLogs (List condition,String orderby,int start,int pagesize);

}