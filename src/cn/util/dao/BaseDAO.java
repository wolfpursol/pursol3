package cn.util.dao;

import com.util.search.PageList;
import java.io.Serializable;
import java.util.List;

public abstract interface BaseDAO
{
  public abstract Object getObject(String paramString, Class paramClass, Serializable paramSerializable);

  public abstract Object addObject(String paramString, Object paramObject);

  public abstract Object saveOrUpdateObject(String paramString, Object paramObject);

  public abstract Object delObject(String paramString, Object paramObject);

  public abstract void delObjects(List paramList);

  public abstract void delObjects(String paramString);

  public abstract Object updateObject(String paramString, Object paramObject);

  public abstract List getObjects(String paramString1, List paramList, String paramString2, int paramInt);
  
  public abstract List getCalendarObjects(String paramString1, List paramList, String paramString2);

  public abstract List getObjects(String paramString1, String paramString2, List paramList, String paramString3, int paramInt);

  public abstract List getObjects(String paramString);
  
  public abstract List getObjectsBySql(String sql);
  
  public abstract List getObjectsBySql(String sql, int pagesize);  
  
  public abstract List getObjectsByHql(String hql);
  
  public abstract List getObjectsByHql(String hql,int count);

  public abstract PageList getPageObjects(String paramString1, String paramString2, List paramList, String paramString3, int paramInt1, int paramInt2);

  public abstract PageList getPageObjects(String paramString1, String paramString2, int paramInt1, int paramInt2);

  public abstract long getRecordNumber(String paramString);
  
  public abstract int getRecordMaxNumber(String paramString);
  
  public int updateByHql(String hql);
  
  public int updateBySql(String sql);
  
  public PageList getPageSqlObjects(String sql1, String sql2, int start, int pagesize);
  
  public List getObjectsByHql(String hql,int startnum,int count);
}

/* Location:           C:\Users\wanghj\Desktop\commons-bzt-5.8\
 * Qualified Name:     com.util.dao.BaseDAO
 * JD-Core Version:    0.6.0
 */