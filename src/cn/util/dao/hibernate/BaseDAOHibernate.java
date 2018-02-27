package cn.util.dao.hibernate;

import com.util.dao.BaseDAO;
import com.util.search.PageList;
import com.util.search.SearchModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAOHibernate extends HibernateDaoSupport
implements BaseDAO
{
	protected final Log log = LogFactory.getLog(getClass());

	public Object getObject(String modelname, Class clazz, Serializable id)
	{
		Object o = getHibernateTemplate().get(clazz, id);

		if (o == null) {
			this.logger.warn("没有找到该对象信息:" + modelname);
		}
		return o;
	}

	public Object addObject(String modelname, Object o)
	{
		if (o != null) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("增加一个对象信息:" + modelname);
			}
			getHibernateTemplate().save(o);
		}
		return o;
	}

	public Object delObject(String modelname, Object o)
	{
		if (o != null) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("删除一个对象信息:" + modelname);
			}
			getHibernateTemplate().delete(o);
		}
		return o;
	}

	public Object updateObject(String modelname, Object o)
	{
		if (o != null) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("修改系统单位信息:" + modelname);
			}
			getHibernateTemplate().update(o);
		}
		return o;
	}

	public List getObjects(String objname, List condition, String orderby, int pagesize)
	{
		String sql = "SELECT a FROM " + objname + " as a WHERE 1=1 ";
		for (int i = 0; i < condition.size(); i++) {
			SearchModel model = (SearchModel)condition.get(i);
			if (!"".equals(model.getValue())) {
				if (("in".equals(model.getRelation().toLowerCase())) || 
						("not in".equals(
								model.getRelation().toLowerCase().trim())))
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " (" + model.getValue() + 
					")";
				else {
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " '" + model.getValue() + 
					"'";
				}
			}
		}
		if (!"".equals(orderby)) {
			sql = sql + " ORDER BY " + orderby;
		}

		if (pagesize == 0) {
			return getHibernateTemplate().find(sql);
		}
		List lst = null;
		Query query = null;
		Session s = getSession();
		try {
			query = s.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(pagesize);
			lst = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return lst;
	}
	
	public List getCalendarObjects(String objname, List condition, String orderby)
	{
		String sql = "SELECT a FROM " + objname + " as a WHERE 1=1 ";
		for (int i = 0; i < condition.size(); i++) {
			SearchModel model = (SearchModel)condition.get(i);
			if (!"".equals(model.getValue())) {
				if (("in".equals(model.getRelation().toLowerCase())) || 
						("not in".equals(
								model.getRelation().toLowerCase().trim())))
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " (" + model.getValue() + 
					")";
				else {
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " '" + model.getValue() + 
					"'";
				}
			}
		}
		if (!"".equals(orderby)) {
			sql = sql + " ORDER BY " + orderby;
		}

		
		List lst = null;
		Query query = null;
		Session s = getSession();
		try {
			query = s.createQuery(sql);
			query.setFirstResult(0);
			lst = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return lst;
	}

	public List getObjects(String objname, String fieldname, List condition, String orderby, int pagesize)
	{
		String sql = "SELECT a." + fieldname + " FROM " + objname + 
		" as a WHERE 1=1 ";
		for (int i = 0; i < condition.size(); i++) {
			SearchModel model = (SearchModel)condition.get(i);
			if (!"".equals(model.getValue())) {
				if (("in".equals(model.getRelation().toLowerCase())) || 
						("not in".equals(
								model.getRelation().toLowerCase().trim())))
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " (" + model.getValue() + 
					")";
				else if (("<".equals(model.getRelation().toLowerCase())) || 
						(">".equals(model.getRelation().toLowerCase().trim())))
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " " + model.getValue() + 
					" ";
				else {
					sql = sql + " AND a." + model.getItem() + " " + 
					model.getRelation() + " '" + model.getValue() + 
					"'";
				}
			}
		}
		if (!"".equals(orderby)) {
			sql = sql + " ORDER BY " + orderby;
		}

		if (pagesize == 0) {
			return getHibernateTemplate().find(sql);
		}
		List lst = null;
		Query query = null;
		Session s = getSession();
		try {
			query = s.createQuery(sql);
			query.setFirstResult(0);
			query.setMaxResults(pagesize);
			lst = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return lst;
	}

	public PageList getPageObjects(String objname, String objid, List condition, String orderby, int start, int pagesize)
	{
		List lst = null;
		PageList page = null;
		long totalCount = 0L;
		Query query = null;
		String sql = null;
		Session s = getSession();
		try {
			sql = " FROM " + objname + " as a WHERE 1=1 ";
			for (int i = 0; i < condition.size(); i++) {
				SearchModel model = (SearchModel)condition.get(i);
				if (!"".equals(model.getValue())) {
					if (("in".equals(model.getRelation().toLowerCase())) || 
							("not in".equals(
									model.getRelation().toLowerCase().trim())))
						sql = sql + " AND a." + model.getItem() + " " + 
						model.getRelation() + " (" + model.getValue() + 
						")";
					else if (("<".equals(model.getRelation().toLowerCase())) || 
							(">".equals(
									model.getRelation().toLowerCase().trim())))
						sql = sql + " AND a." + model.getItem() + " " + 
						model.getRelation() + " " + model.getValue() + 
						" ";
					else {
						sql = sql + " AND a." + model.getItem() + " " + 
						model.getRelation() + " '" + model.getValue() + 
						"'";
					}
				}
			}
			Long t = 
				(Long)s
				.createQuery("SELECT count(a." + objid + ")" + sql)
				.iterate().next();
			if (!"".equals(orderby)) {
				sql = sql + " ORDER BY " + orderby;
			}
			query = s.createQuery("SELECT a " + sql);
			query.setFirstResult(start);
			query.setMaxResults(pagesize);

			lst = query.list();
			page = new PageList((ArrayList)lst, start, pagesize, t.longValue());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return page;
	}

	public PageList getPageObjects(String sql1, String sql2, int start, int pagesize)
	{
		List lst = null;
		PageList page = null;
		long totalCount = 0L;
		Query query = null;
		Session s = getSession();
		try {
			Long t = (Long)s.createQuery(sql1).iterate().next();

			query = s.createQuery(sql2);
			query.setFirstResult(start);
			query.setMaxResults(pagesize);

			lst = query.list();
			page = new PageList((ArrayList)lst, start, pagesize, t.longValue());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return page;
	}

	public List getObjects(String hql)
	{
		return getHibernateTemplate().find(hql);
	}
	
	public List getObjectsBySql(String sql){
		List list = null;
		Session s = getSession();
		try
		{
			list = s.createSQLQuery(sql).list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return list;  
	}
	
	public List getObjectsBySql(String sql,int pagesize){
		List list = null;
		Query query1 = null;

		Session s = getSession();
		try {
			if (pagesize == 0) {
				query1 = s.createSQLQuery(sql);
				list = query1.list();
			} else {
				query1 = s.createSQLQuery(sql);
				query1.setFirstResult(0);
				query1.setMaxResults(pagesize);
				list = query1.list();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return list; 
	}

	public void delObjects(List lst)
	{
		getHibernateTemplate().deleteAll(lst);
	}

	public void delObjects(String hql)
	{
		List lst = getObjects(hql);
		getHibernateTemplate().deleteAll(lst);
	}

	public Object saveOrUpdateObject(String modelname, Object o) {
		if (o != null) {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("修改对象信息:" + modelname);
			}
			getHibernateTemplate().saveOrUpdate(o);
		}
		return o;
	}

	public long getRecordNumber(String objname, String objid, List condition)
	{
		String sql = null;
		Long t = null;
		Session s = getSession();
		try {
			sql = "SELECT count(a." + objid + ") FROM " + objname + 
			" as a WHERE 1=1 ";
			for (int i = 0; i < condition.size(); i++) {
				SearchModel model = (SearchModel)condition.get(i);
				if (!"".equals(model.getValue())) {
					if ("in".equals(model.getRelation().toLowerCase()))
						sql = sql + " AND a." + model.getItem() + " " + 
						model.getRelation() + " (" + model.getValue() + 
						")";
					else {
						sql = sql + " AND a." + model.getItem() + " " + 
						model.getRelation() + " '" + model.getValue() + 
						"'";
					}
				}
			}
			t = (Long)s.createQuery(sql).iterate().next();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return t.longValue();
	}

	public long getRecordNumber(String hql)
	{
		Long t = null;
		Session s = getSession();
		try
		{
			t = (Long)s.createQuery(hql).iterate().next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return t.longValue();
	}
	
	public int getRecordMaxNumber(String hql)
	{
		Integer t =null;
		Session s = getSession();
		try
		{
			t =(Integer) s.createQuery(hql).iterate().next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return t;
	}

	public int updateByHql(String hql)
	{
		int t = 0;
		Session s = getSession();
		try
		{
			t = s.createQuery(hql).executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return t;
	}

	public int updateBySql(String sql)
	{
		int t = 0;
		Session s = getSession();
		try
		{
			t = s.createSQLQuery(sql).executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return t;
	}
	
	public PageList getPageSqlObjects(String sql1, String sql2, int start, int pagesize) {
		List lst1 = null;
		List lst2 = null;
		PageList page = null;

		Query query1 = null;
		Query query2 = null;
		Session s = getSession();
		try {
			query1 = s.createSQLQuery(sql1);
			lst1 = query1.list();
			java.math.BigInteger t = (java.math.BigInteger)lst1.get(0);
			//Long t = (Long) s.createSQLQuery(sql1).iterate().next();
			query2 = s.createSQLQuery(sql2);
			query2.setFirstResult(start);
			query2.setMaxResults(pagesize);
			lst2 = query2.list();
			page = new PageList((ArrayList) lst2, start, pagesize, t.longValue());
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return page;
	}

	public List getObjectsByHql(String hql) {
		// TODO Auto-generated method stub
		List list = null;
		Session s = getSession();
		try
		{
			list = s.createQuery(hql).list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return list;  
	}
	
	public List getObjectsByHql(String hql,int count) {
		// TODO Auto-generated method stub
		List list = null;
		Session s = getSession();		
		try
		{
			if(count ==0){
				list = s.createQuery(hql).list();
			}else {
				Query query = null;
				query = s.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(count);
				list = query.list();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return list;  
	}
	
	public List getObjectsByHql(String hql,int startnum,int count) {
		List list = null;
		Session s = getSession();		
		try
		{
			if(count ==0){
				list = s.createQuery(hql).list();
			}else {
				Query query = null;
				query = s.createQuery(hql);
				query.setFirstResult(startnum);
				query.setMaxResults(count);
				list = query.list();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			releaseSession(s);
		}
		return list;  
	}
}