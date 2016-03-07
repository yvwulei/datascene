package com.zhongtian.datascene.basic.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.zhongtian.datascene.basic.dao.IBaseDao;
import com.zhongtian.datascene.basic.handler.HibernateHandler;
import com.zhongtian.datascene.basic.model.Pagination;
import com.zhongtian.datascene.basic.model.SystemRequest;
import com.zhongtian.datascene.basic.model.SystemRequestHolder;

public class BaseDaoHibernateImpl<T> implements IBaseDao<T> {
	
	// 日志输出类

	protected static final Log logger = LogFactory.getLog(BaseDaoHibernateImpl.class);

	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	protected SystemRequest getSystemRequest() {
		SystemRequest sr = SystemRequestHolder.getSystemRequest();
		if(sr==null) sr = new SystemRequest();
		return sr;
	}
	
	public BaseDaoHibernateImpl(){
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
	}
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Object save(Object entity) {
		return (T) getSession().save(entity);
	}

	@Override
	public void delete(Object entity) {
		getSession().delete(entity);
	}

	@Override
	public void update(Object entity) {
		getSession().update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void saveAll(Collection<?> entities) {
		for (Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
            Object entity = localIterator.next();
            getSession().save(entity);
        }
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteAll(Collection<?> entities) {
		for (Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
            Object entity = localIterator.next();
            getSession().delete(entity);
        }
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void updateAll(Collection<?> entities) {
        for (Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
            Object entity = localIterator.next();
            getSession().update(entity);
        }
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void saveOrUpdateAll(Collection<?> entities) {
        for (Iterator localIterator = entities.iterator(); localIterator.hasNext();) {
            Object entity = localIterator.next();
            getSession().saveOrUpdate(entity);
        }
		
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> T findObject(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}
	
	@SuppressWarnings({ "hiding", "unchecked","rawtypes" })
	@Override
	public <T> T findObject(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		setParams(query, params);
		List list = query.setMaxResults(1).list();
        if (list.isEmpty())
            return null;
        return ((T) list.get(0));
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> T findObject(String hql, Object ...args) {
		try{
			Query query = getSession().createQuery(hql);
			setArgs(query, args);
			List list = query.setMaxResults(1).list();
	        if (list.isEmpty())
	            return null;
	        return ((T) list.get(0));
		}catch(Exception e){
			logger.error(e);
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findObject(String hql,
			Map<String, Object> params,Object ...args) {
		Query query = getSession().createQuery(hql);
		setParams(query, params);
		setArgs(query, args);
		List list = query.setMaxResults(1).list();
        if (list.isEmpty())
            return null;
        return ((T) list.get(0));
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> List<T> findList(String hql, Object ...params) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < params.length; ++i) {
            query.setParameter(i, params[i]);
        }
        return query.list();
	}

	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	public <T> List<T> findList(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return query.list();
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> Pagination<T> findPagination(String hql, int pageIndex, int pageSize, Object... params) {
		Query query = getSession().createQuery(hql);
        if ((pageSize > 0) && (pageIndex > 0)) {
            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        for (int i = 0; i < params.length; ++i) {
            query.setParameter(i, params[i]);
        }
        List items = query.list();
        long rowsCount = 0L;

        if ((pageSize > 0) && (pageIndex > 0)) {
            String hqlStr = parseSelectCount(hql);
            rowsCount = ((Long) findObject(hqlStr, params)).longValue();
        } else {
            rowsCount = items.size();
        }

        Pagination pagination = new Pagination(pageIndex, pageSize, rowsCount);
        pagination.setItems(items);
        return pagination;
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> Pagination<T> findPagination(String hql, Map<String, Object> params, int pageIndex,
			int pageSize) {
		Query query = getSession().createQuery(hql);
        if ((pageSize > 0) && (pageIndex > 0)) {
            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        setParams(query, params);
        List items = query.list();
        long rowsCount = 0L;

        if ((pageSize > 0) && (pageIndex > 0)) {
            String hqlStr = parseSelectCount(hql);
            rowsCount = ((Long) findObject(hqlStr, params)).longValue();
        } else {
            rowsCount = items.size();
        }
        Pagination pagination = new Pagination(pageIndex, pageSize, rowsCount);
        pagination.setItems(items);
        return pagination;
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> Pagination<T> findPagination(String hql, String countString, int pageIndex,
			int pageSize, Object... params) {
        Query query = getSession().createQuery(hql);

        if ((pageSize > 0) && (pageIndex > 0)) {
            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        for (int i = 0; i < params.length; ++i) {
            query.setParameter(i, params[i]);
        }
        List items = query.list();
        long rowsCount = 0L;

        if ((pageSize > 0) && (pageIndex > 0)) {
            rowsCount = ((Long) findObject(countString, params)).longValue();
        } else
            rowsCount = items.size();

        Pagination pagination = new Pagination(pageIndex, pageSize, rowsCount);
        pagination.setItems(items);
        return pagination;
	}

	@SuppressWarnings({ "hiding", "unchecked", "rawtypes" })
	@Override
	public <T> Pagination<T> findPagination(String hql, String countString,
			Map<String, Object> params, int pageIndex, int pageSize) {
        Query query = getSession().createQuery(hql);
        if ((pageSize > 0) && (pageIndex > 0)) {
            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        setParams(query, params);
        List items = query.list();
        long rowsCount = 0L;
        if ((pageSize > 0) && (pageIndex > 0)) {
            rowsCount = ((Long) findObject(countString, params)).longValue();
        } else{
            rowsCount = items.size();
        }
        Pagination pagination = new Pagination(pageIndex, pageSize, rowsCount);
        pagination.setItems(items);
        return pagination;
	}

	@SuppressWarnings({ "hiding", "serial", "unchecked", "rawtypes" })
	@Override
	public <T> Pagination<T> findPaginationBySql(String sql, final String countSql,
			final Map<String, Object> params, int pageIndex, int pageSize) {
        SQLQuery query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if ((pageSize > 0) && (pageIndex > 0)) {
            query.setFirstResult((pageIndex < 2) ? 0 : (pageIndex - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        if ((params != null) && (!(params.isEmpty()))) {
        	setParams(query, params);
        }
        List items = query.list();
        BigInteger rowsCount = BigInteger.valueOf(0L);
        if ((pageSize > 0) && (pageIndex > 0)) {
            rowsCount = (BigInteger) executeQuery(new HibernateHandler() {
                public Object doInHibernate(Session session) {
                    SQLQuery query = session.createSQLQuery(countSql);
                    if ((params != null) && (!(params.isEmpty()))) {
                    	setParams(query, params);
                    }
                    return query.uniqueResult();
                }
            });
        }
        Pagination pagination = new Pagination(pageIndex, pageSize, rowsCount.intValue());
        pagination.setItems(items);
        return pagination;
	}

	@Override
	public int executeUpdateBySql(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int executeUpdate(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjectById(Serializable id) {
        if (id == null)
            return null;
        return (T) findObject(clz, id);
	}

	@Override
	public T saveEntity(T entity) {
        saveOrUpdate(entity);
        return entity;
	}

	@Override
	public T insert(T entity) {
        save(entity);
        return entity;
	}

	@Override
	public void save(List<T> entitys) {
        for (T entity : entitys) {
            save(entity);
        }
	}

	@Override
	public void insert(List<T> entitys) {
		save(entitys);
	}

	@Override
	public void delete(List<T> entitys) {
        for (T entity : entitys) {
            delete(entity);
        }
	}

	@Override
	public void update(List<T> entitys) {
        for (T entity : entitys) {
            update(entity);
        }
		
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
        String hql = "from  " + clz.getSimpleName() + " where " + propertyName + "=? ";
        return findList(hql, value);
	}

	@Override
	public List<T> findByProperty(Map<String, Object> conditionMap) {
        StringBuilder hql = new StringBuilder();
        hql.append("from  " + clz.getSimpleName());
        if (!conditionMap.isEmpty()) {
            Iterator<String> it = conditionMap.keySet().iterator();
            String key = it.next();
            hql.append(" where  " + key + "=:" + key);
            while (it.hasNext()) {
                key = it.next();
                hql.append(" and  " + key + "=:" + key);
            }
        }
        return findList(hql.toString(), conditionMap);
	}

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public <V> List<V> findListByMax(final String hql, final int maxResults, final Object... params) {
        List<V> list = (List<V>) executeQuery(new HibernateHandler() {
            @Override
            public List<V> doInHibernate(Session paramSession) {
                try {
                    Query query = paramSession.createQuery(hql);
                    for (int i = 0; i < params.length; ++i) {
                        query.setParameter(i, params[i]);
                    }
                    return query.setMaxResults(maxResults).list();
                } catch (RuntimeException re) {
                    throw re;
                }
            }
        });
        return list;
	}

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public <V> List<V> findListByMax(final String hql, final int maxResults, final Map<String, Object> params) {
        
        List<V> list = (List<V>) executeQuery(new HibernateHandler() {
            @Override
            public List<V> doInHibernate(Session paramSession) {
                try {
                    Query query = paramSession.createQuery(hql.toString());
                    for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
                        String key = iterator.next();
                        query.setParameter(key, params.get(key));
                    }
                    return query.setMaxResults(maxResults).list();
                } catch (RuntimeException re) {
                    throw re;
                }
            }

        });
        return list;
	}

	@Override
	public int getMaxOrder(Integer pid,String clz) {
		String hql = "select max(o.orderNum) from "+clz+" o where o.parent.id="+pid;
		if(pid==null||pid==0) hql = "select max(o.orderNum) from "+clz+" o where o.parent is null";
		Object obj = this.findObject(hql);
		if(obj==null) return 0;
		return (Integer)obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <N> List<N> findListBySql(String sql, Map<String, Object> params, Class<?> clz, boolean hasEntity,
			Object... args) {
		sql = initSort(sql);
		SQLQuery query = getSession().createSQLQuery(sql);
		setParams(query, params);
		setArgs(query, args);
		if(hasEntity) 
			query.addEntity(clz);
		else 
			query.setResultTransformer(Transformers.aliasToBean(clz));
		return query.list();
	}
	
	@Override
	public <N extends Object>List<N> findListBySql(String sql,Class<?> clz, boolean hasEntity,Object ...args) {
		return this.findListBySql(sql,null, clz, hasEntity, args);
	}
	
	@Override
	public void updateByHql(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		setArgs(query, args);
		query.executeUpdate();
		
	}

	@Override
	public void updateByHql(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		setParams(query, params);
		query.executeUpdate();
		
	}

	@Override
	public void updateBySql(String hql, Object... args) {
		Query query = getSession().createSQLQuery(hql);
		setArgs(query, args);
		query.executeUpdate();
		
	}

	@Override
	public void updateBySql(String hql, Map<String, Object> params) {
		Query query = getSession().createSQLQuery(hql);
		setParams(query, params);
		query.executeUpdate();
		
	}
	
	
	@Override
	public void flush() {
		this.getSession().flush();
		
	}

	@Override
	public void clear() {
		this.getSession().clear();
		
	}
	
	
	private String initSort(String hql) {
		String order = getSystemRequest().getOrder();
		String sort = getSystemRequest().getSort();
		if(sort!=null&&!"".equals(sort.trim())) {
			hql+=" order by "+sort;
			if(!"desc".equals(order)) hql+=" asc";
			else hql+=" desc";
		}
		return hql;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void setParams(Query query,Map<String,Object> params) {
		if(params!=null) {
			Set<String> keys = params.keySet();
			for(String key:keys) {
				Object val = params.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	private void setArgs(Query query,Object[] args) {
		if(args!=null&&args.length>0) {
			int index = 0;
			for(Object arg:args) {
				query.setParameter(index++, arg);
			}
		}
	}
	
    protected Object executeQuery(HibernateHandler handler) {
        return handler.doInHibernate(getSession());
    }
	
    protected boolean followWithWord(String s, String sub, int pos) {
        int i = 0;
        for (; (pos < s.length()) && (i < sub.length()); ++i) {
            if (s.charAt(pos) != sub.charAt(i))
                return false;
            ++pos;
        }

        if (i < sub.length()) {
            return false;
        }

        if (pos >= s.length()) {
            return true;
        }
        return (!(isAlpha(s.charAt(pos))));
    }

    protected String parseSelectCount(String queryString) {
        String hql = queryString.toLowerCase();
        int noBlankStart = 0;
        for (int len = hql.length(); noBlankStart < len; ++noBlankStart) {
            if (hql.charAt(noBlankStart) > ' ') {
                break;
            }
        }

        int pair = 0;

        if (!(followWithWord(hql, "select", noBlankStart))) {
            pair = 1;
        }
        int fromPos = -1;
        for (int i = noBlankStart; i < hql.length();) {
            if (followWithWord(hql, "select", i)) {
                ++pair;
                i += "select".length();
            } else if (followWithWord(hql, "from", i)) {
                --pair;
                if (pair == 0) {
                    fromPos = i;
                    break;
                }
                i += "from".length();
            } else {
                ++i;
            }
        }
        if (fromPos == -1) {
            throw new IllegalArgumentException("parse count sql error, check your sql/hql");
        }

        String countHql = "select count(*) " + queryString.substring(fromPos);
        return countHql;
    }

    protected boolean isAlpha(char c) {
        return ((c == '_') || (('0' <= c) && (c <= '9')) || (('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')));
    }

}
