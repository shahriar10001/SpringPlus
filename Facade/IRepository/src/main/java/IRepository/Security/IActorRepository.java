package IRepository.Security;

import RVM.Security.ActorRvm;

import java.util.List;

public interface IActorRepository {
    List<ActorRvm> getActor(List<String> roles, String userName);
    ActorRvm saveActor(ActorRvm entity);
}
