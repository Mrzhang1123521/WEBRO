package cn.webro;

import java.util.List;

public interface BaseDAO<T> {

	/**
	 * 添加	
	 * @param t 添加的对象
	 */
	public void save(T t);
	/**
	 * 更新 更新对象  全更新  没有的字段会设置为空
	 * @param t
	 */
	public void update(T t);
	/**
	 * 更新一个对象，并且忽略所有 null 字段。
	 * 注意: 基本数据类型都是不可能为null的,这些字段肯定会更新
	 * @param t
	 */
	public void updateIgnoreNull(T t);
	/**
	 * 其实是更新 用于标志性删除的
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 真的删除
	 * @param id
	 */
	public void deleteshanchu(String id);
	/**
	 * 取出表所有数据
	 * @return
	 */
	public List<T> list();
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T listById(String id);

}
