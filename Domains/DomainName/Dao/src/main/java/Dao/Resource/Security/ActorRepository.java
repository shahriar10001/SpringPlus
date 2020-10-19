package Dao.Resource.Security;

import Dao.Config.AbstractDao;
import DaoEntity.Security.Actor;
import IRepository.Security.IActorRepository;
import RVM.Security.ActorRvm;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Repository
@Transactional
public class ActorRepository extends AbstractDao<Integer, Actor> implements IActorRepository {


    @Override
    public List<ActorRvm> getActor(List<String> roles, String userName) {
        String[] name = userName.split("@");
        if (name.length > 1)
            userName = name[0];

        Query query = currentSession().createQuery(
                "from Actor  where  RoleName in (:roles ) or UserName =:userName ")
                .setParameterList("roles", roles)
                .setParameter("userName", userName);

        List<Actor> obj = query.list();
        for (String strRole : roles) {
            List<Actor> isCheck = obj.stream().filter(u -> u.getRoleName() != null && u.getRoleName().toUpperCase().equals(strRole.toUpperCase()))
                    .collect(Collectors.toList());
            if (isCheck.size() == 0) {
                Actor actor = new Actor();
                actor.setRoleName(strRole);
                actor.setId(-1);
                obj.add(actor);
            }
        }
        return null;
    }

    public ActorRvm saveActor(ActorRvm entity) {
        doTransaction(session -> {
            session.saveOrUpdate(entity);
        });
        return entity;
    }
}
