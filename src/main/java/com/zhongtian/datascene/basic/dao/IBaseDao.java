package com.zhongtian.datascene.basic.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zhongtian.datascene.basic.model.Pagination;

/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * 
 * @author Administrator
 *
 * @param <T>
 */
public interface IBaseDao<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体主键
	 */
	public Object save(Object entity);

	/**
	 * 
	 * 删除实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	public void delete(Object entity);

	/**
	 * 
	 * 更新实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	public void update(Object entity);

	/**
	 * 
	 * 保存或更新实体, 实体没有主键时保存，否则更新
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	public void saveOrUpdate(Object entity);

	/**
	 * 
	 * 批量保存实体
	 * 
	 * @param entities
	 *            实体集合
	 */
	public void saveAll(Collection<?> entities);

	/**
	 * 
	 * 批量删除实体
	 * 
	 * @param entities
	 *            实体集合
	 * 
	 */
	public void deleteAll(Collection<?> entities);

	/**
	 * 
	 * 批量更新实体
	 * 
	 * @param entities
	 *            实体集合
	 * 
	 */
	public void updateAll(Collection<?> entities);

	/**
	 * 
	 * 批量保存或更新实体, 实体没有主键时保存，否则更新
	 * 
	 * @param entity
	 *            实体集合
	 * 
	 */
	public void saveOrUpdateAll(Collection<?> entities);

	/**
	 * 
	 * 获取单个实体，根据实体类及实体的主键获取。
	 * 
	 * @param entityClass
	 *            实体类
	 * @param id
	 *            实体主键
	 * @return 实体对象
	 */
	@SuppressWarnings("hiding")
	public <T> T findObject(Class<T> entityClass, Serializable id);

	/**
	 * 获取单个实体，根据查询语句及参数获取。
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            可选的查询参数
	 * @return 单个实体，如果查询结果有多个，则返回第一个实体
	 */
	@SuppressWarnings("hiding")
	public <T> T findObject(String hql, Map<String, Object> params);

	/**
	 * 获取单个实体，根据查询语句及参数获取。
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            可选的查询参数
	 * @return 单个实体，如果查询结果有多个，则返回第一个实体
	 */
	@SuppressWarnings("hiding")
	public <T> T findObject(String hql, Object... params);

	/**
	 * 通过传入的参数获取单个对象
	 * @param hql
	 * @param alias
	 * @param args
	 * @return
	 */
	public Object findObject(String hql,Map<String, Object> alias,Object ...args) ;
	
	/**
	 * 
	 * 查询实体列表
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            可选的查询参数
	 * @return 实体列表
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> findList(String hql, Object... params);

	/**
	 * 
	 * 查询实体列表
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            可选的查询参数
	 * @return 实体列表
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> findList(String hql, Map<String, Object> params);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询语句
	 * @param pageIndex
	 *            当前页码，如果pageIndex<1则不分页，且返回pageSize条记录。
	 * @param pageSize
	 *            每页记录数，如果pageSize<1则返回所有记录。
	 * @param params
	 *            可选的查询参数
	 * @return 实体分页对象
	 */
	@SuppressWarnings("hiding")
	public <T> Pagination<T> findPagination(String hql, int pageIndex, int pageSize, Object... params);

	/**
	 * 分页查询实体
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            可选的查询参数
	 * @param pageIndex
	 *            当前页码，如果pageIndex<2则不分页，且返回pageSize条记录。
	 * @param pageSize
	 *            每页记录数，如果pageSize<1则返回所有记录。
	 * 
	 * @return 实体分页对象
	 */
	@SuppressWarnings("hiding")
	public <T> Pagination<T> findPagination(String hql, Map<String, Object> params, int pageIndex, int pageSize);

	/**
	 * 分页查询实体，自定义总条数查询语句，适合复杂的hql分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param countString
	 *            查询记录总条数语句
	 * @param pageIndex
	 *            当前页码，如果pageIndex<1则不分页，且返回pageSize条记录。
	 * @param pageSize
	 *            每页记录数，如果pageSize<1则返回所有记录。
	 * @param params
	 *            可选的查询参数
	 * @return 实体分页对象
	 */
	@SuppressWarnings("hiding")
	public <T> Pagination<T> findPagination(String hql, String countString, int pageIndex, int pageSize,
			Object... params);

	/**
	 * 分页查询实体，自定义总条数查询语句，适合复杂的hql分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param countString
	 *            查询记录总条数语句
	 * @param params
	 *            可选的查询参数
	 * @param pageIndex
	 *            当前页码，如果pageIndex<2则不分页，且返回pageSize条记录。
	 * @param pageSize
	 *            每页记录数，如果pageSize<1则返回所有记录。
	 * 
	 * @return 实体分页对象
	 */
	@SuppressWarnings("hiding")
	public <T> Pagination<T> findPagination(String hql, String countString, Map<String, Object> params, int pageIndex,
			int pageSize);

	/**
	 * 分页查询实体，自定义总条数查询语句，适合复杂的sql分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param countString
	 *            查询记录总条数语句
	 * @param params
	 *            可选的查询参数
	 * @param pageIndex
	 *            当前页码，如果pageIndex<2则不分页，且返回pageSize条记录。
	 * @param pageSize
	 *            每页记录数，如果pageSize<1则返回所有记录。
	 * 
	 * @return 实体分页对象
	 */
	@SuppressWarnings("hiding")
	public <T> Pagination<T> findPaginationBySql(String sql, final String countSql, final Map<String, Object> params,
			int pageIndex, int pageSize);

	/**
	 * 执行数据库更新操作
	 * 
	 * @param sql
	 * @return 更新的记录条数
	 */
	public int executeUpdateBySql(String sql);

	/**
	 * 执行数据库更新操作
	 * 
	 * @param hql
	 * @return 更新的记录条数
	 */
	public int executeUpdate(String hql);

	/**
	 * 通过序列化的Id获取数据，比如PK
	 * 
	 * @param id
	 * @return
	 */
	public T findObjectById(Serializable id);

	/**
	 * 添加一个实体
	 * @param entity
	 * @return
	 */
	public T saveEntity(T entity);

	/**
	 * 添加一个实体
	 * @param entity
	 * @return
	 */
	public T insert(T entity);

	/**
	 * 添加一组实体
	 * @param entity
	 * @return
	 */
	public void save(List<T> entitys);
	
	/**
	 * 添加一组实体
	 * @param entity
	 * @return
	 */
	public void insert(List<T> entitys);
	
	
	/**
	 * 删除一组实体
	 * @param entity
	 * @return
	 */
	public void delete(List<T> entitys);

	/**
	 * 更新一组实体
	 * @param entity
	 * @return
	 */
	public void update(List<T> entitys);

	/**
	 * 通过单个属性获取对象
	 * @param propertyName
	 *        属性名称
	 * @param value
	 *        属性值
	 * @return
	 */
	public List<T> findByProperty(String propertyName, Object value);

	/**
	 * 通过多个属性获取对象
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(Map<String, Object> conditionMap);

	/**
	 * 
	 * 查询实体列表
	 * 
	 * @param queryString
	 *            查询语句
	 * @param maxResults
	 *            列表最大数
	 * @param params
	 *            可选的查询参数
	 * @return 实体列表
	 */
	public <V> List<V> findListByMax(final String hql, final int maxResults, final Object... params);

	/**
	 * 
	 * 查询实体列表
	 * 
	 * @param queryString
	 *            查询语句
	 * @param maxResults
	 *            列表最大数
	 * @param params
	 *            可选的查询参数
	 * @return 实体列表
	 */
	public <V> List<V> findListByMax(final String hql, final int maxResults, final Map<String, Object> params);
	

	/**
	 * 根据pid和实体获取最大排序序号
	 * @param pid
	 * @param clz
	 * @return
	 */
	public int getMaxOrder(Integer pid,String clz);
	
	/**
	 * 获取相应实体的结果集
	 * @param sql
	 * @param alias
	 * @param clz
	 * @param hasEntity
	 * @param args
	 * @return
	 */
	public <N extends Object>List<N> findListBySql(String sql,
			Map<String, Object> params, Class<?> clz, boolean hasEntity,Object ...args);
	
	/**
	 * 获取相应实体的结果集
	 * @param sql
	 * @param alias
	 * @param clz
	 * @param hasEntity
	 * @param args
	 * @return
	 */
	public <N extends Object>List<N> findListBySql(String sql,Class<?> clz, boolean hasEntity,Object ...args);
	
	/**
	 * 通过hql和传入的参数执行更新操作
	 * @param hql
	 * @param args
	 */
	public void updateByHql(String hql, Object ...args) ;
	
	/**
	 * 通过hql和传入的参数执行更新操作
	 * @param hql
	 * @param args
	 */
	public void updateByHql(String hql, Map<String, Object> params) ;
	
	/**
	 * 通过sql和传入的参数执行更新操作
	 * @param hql
	 * @param args
	 */
	public void updateBySql(String hql, Object ...args) ;
	
	/**
	 * 通过sql和传入的参数执行更新操作
	 * @param hql
	 * @param args
	 */
	public void updateBySql(String hql, Map<String, Object> params) ;
	
	/**
	 * 将Session中的缓存刷入到数据库
	 */
	public void flush();

	/**
	 * 将Session中的缓存清空
	 */
	public void clear();
}
