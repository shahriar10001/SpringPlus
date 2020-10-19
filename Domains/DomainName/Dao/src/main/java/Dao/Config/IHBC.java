package Dao.Config;

import java.io.Serializable;
import java.util.List;

public interface IHBC<PK extends Serializable, T> {

    T insert(T entity);

    T saveOrUpdate(T entity);

    T update(T entity);

    T remove(T entity);

    T findById(PK key);

    List selectAll();

    Object selectWhere();

    T selectMaxId();
}
