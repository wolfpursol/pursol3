package cn.pursol.sys.service.impl;

/**
*<p>Title: 日志</p>
*<p>Description: </p>
*<p>Copyright: Copyright (c) 2017</p>
*<p>Company: pursol </p>
*@author pursol
*@version 2.0
*/

import cn.pursol.sys.bo.SysUserLog;
import com.util.dao.BaseDAO;
import cn.pursol.sys.service.SysUserLogManager;
import java.util.List;
import com.util.search.PageList;

import com.util.service.impl.ManagerImpl;

public class SysUserLogManagerImpl extends ManagerImpl implements SysUserLogManager{
private BaseDAO baseDAO;
private String modelname = "日志";
/**
*加载baseDAO 
*@param BaseDAO baseDAO
*/
public void setBaseDAO(BaseDAO baseDAO) {
	this.baseDAO = baseDAO;
}

/**
*根据id获取日志
 *@param logid Integer;
*@return SysUserLog
*/
public SysUserLog getSysUserLog(String logid){
		return  (SysUserLog)baseDAO.getObject(modelname,SysUserLog.class,logid);
}

/**
*增加日志
 *@param sysUserLog SysUserLog;
*@return SysUserLog
*/
public SysUserLog addSysUserLog(SysUserLog sysUserLog){
		return (SysUserLog)baseDAO.addObject(modelname,sysUserLog);
}

/**
*删除日志
 *@param logid Integer;
*@return SysUserLog
*/
public SysUserLog delSysUserLog(String logid){
		 SysUserLog model=getSysUserLog(logid);
		return (SysUserLog)baseDAO.delObject(modelname,model);
}

/**
*修改日志
 *@param sysUserLog SysUserLog;
*@return SysUserLog
*/
public SysUserLog updateSysUserLog(SysUserLog sysUserLog){
		return (SysUserLog)baseDAO.updateObject(modelname,sysUserLog);
}

/**
*获取日志集合
 *@return List
*/
public List getSysUserLogs(List condition,String orderby,int pagesize){
		return baseDAO.getObjects("SysUserLog",condition,orderby,pagesize);
	}

/**
*获取一页日志集合
 *@param start int
*@param pagesize int
*@return PageList
*/
public PageList getPageSysUserLogs (List condition,String orderby,int start,int pagesize){
		return baseDAO.getPageObjects("SysUserLog","logid",condition, orderby, start,pagesize);
	}

}