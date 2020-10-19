package Dao.Config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

//TODO need to do log and exception handling?
public abstract class AbstractDao<PK extends Serializable, T> implements IHBC<PK, T> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> persistentClass;


    protected Cache<String, List<T>> cache = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();

    public List selectByCriteria(Criteria criteria) {

        List result;

        result = criteria.list();

        return result;
    }

    public List selectByExample(T entity) {

        Example example = Example.create(entity).ignoreCase().enableLike(MatchMode.ANYWHERE);

        Criteria cr = this.statelessSession().createCriteria(entity.getClass()).add(example);

        List result = this.selectByCriteria(cr);

        return result;
    }

    //TODO for detached queries
    protected StatelessSession statelessSession(){
        return sessionFactory.openStatelessSession();
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public T insert(T entity) {
        currentSession().save(entity);
        return entity;
    }

    public T saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        currentSession().update(entity);
        return entity;
    }

    public T remove(T entity) {
        currentSession().delete(entity);
        return entity;
    }

    public T findById(PK key) {
        return currentSession().get(persistentClass, key);
    }

    public List selectAll() {
        Criteria cr = currentSession().createCriteria(persistentClass);

        return cr.list();
    }


    public T selectMaxId() {
        DetachedCriteria maxId = DetachedCriteria.forClass(this.persistentClass)
                .setProjection(Projections.max("id"));

        return (T) currentSession().createCriteria(this.persistentClass)
                .add(Property.forName("id").eq(maxId))
                .list().get(0);
    }

    public Object selectWhere() {
        return currentSession().createCriteria(persistentClass);
    }

    protected int doTransaction(Consumer<Session> function) {
        Session session = null;
        Transaction txn = null;
        int status = 0;
        try {
            session = sessionFactory.withOptions().openSession();

            txn = session.getTransaction();
            txn.begin();
            function.accept(session);
            txn.commit();
            status = 0;
        } catch (Throwable e) {
            if (txn != null) txn.rollback();
            status = 1;
        } finally {
            if (session != null) {
                session.close();
            }
            return status;

        }
    }

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


}
