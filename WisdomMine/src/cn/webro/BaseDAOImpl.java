package cn.webro;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@IocBean
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private Class<T> clazz;

	@Inject
	public Dao dao;

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		clazz = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	@Override
	public void save(T t) {
		dao.insert(t);
	}

	@Override
	public void update(T t) {
		dao.update(t);
	}

	@Override
	public void delete(T t) {
		dao.update(t);
	}

	@Override
	public List<T> list() {
		return dao.query(clazz, null);
	}

	@Override
	public T listById(String id) {
		return dao.fetch(clazz, id);
	}

	@Override
	public void deleteshanchu(String id) {
		dao.delete(clazz,id);
	}
	@Override
	public void updateIgnoreNull(T t) {
		dao.updateIgnoreNull(t);
	}
}
