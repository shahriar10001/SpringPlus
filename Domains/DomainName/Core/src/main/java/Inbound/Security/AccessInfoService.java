package Inbound.Security;

import IRepository.Security.IActorRepository;
import IService.Security.IBaseService;
import RVM.Security.ActorRvm;
import Security.SecurityEntity.RoleEntity;
import Security.SecurityEntity.TokenEntity;
import Security.SecurityEntity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Component
public class AccessInfoService implements IBaseService {
    @Autowired
    IActorRepository _actorRepository;
    @Autowired
    private HttpServletRequest _context;

    public TokenEntity getTokenEntity() {
        TokenEntity tokenEntity = new TokenEntity();
        try {
            Map<String, Object> obj = (Map<String, Object>) _context.getAttribute("climes");

            List<String> roles = new ArrayList<>();
            if (obj.get("role") instanceof String)
                roles.add((String) obj.get("role"));
            else
                roles = (List<String>) obj.get("role");

            String userName = (String) obj.get("user");

            Object department = obj.get("department");

            List<ActorRvm> actors = _actorRepository.getActor(roles, userName);

            tokenEntity.setUser(getUserActor(actors, userName));
            tokenEntity.setRoles(getRoleActors(actors));
            tokenEntity.setApplicationName((String) obj.get("applicationName"));
            tokenEntity.setUserType((String) obj.get("userType"));
            tokenEntity.setToken((String) obj.get("token"));
            tokenEntity.setGivenName((String) obj.get("givenName"));
            tokenEntity.setLastName((String) obj.get("lastName"));
            tokenEntity.setAuthorization((String) obj.get("authorization"));
            tokenEntity.setDepartment(department);

        } catch (Exception ex) {
            tokenEntity.setMessage(ex.getMessage());
        }
        return null;
    }

    private UserEntity getUserActor(List<ActorRvm> actors, String userName) {

        List<ActorRvm> actorUsers = actors.stream().filter(u -> u.getUserName() != null).collect((Collectors.toList()));
        if (actorUsers.size() > 0) {
            ActorRvm actorUser = actorUsers.get(0);
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(actorUser.getUserName());
            userEntity.setActorId(actorUser.getId());
            return userEntity;
        }
        UserEntity userEntity = new UserEntity();
        if (userName.toUpperCase().equals("Guest@carbon.super".toUpperCase())) {
            userEntity.setUserName("مهمان");
            userEntity.setActorId(-1);
            return userEntity;
        }
        ActorRvm actor = new ActorRvm();
        String[] user = userName.split("@");
        if (user.length > 1) userName = user[0];
        actor.setUserName(userName);
        actor.setRoleName(null);
        actor = _actorRepository.saveActor(actor);
        userEntity.setActorId(actor.getId());
        return userEntity;
    }

    private List<RoleEntity> getRoleActors(List<ActorRvm> actors) {
        List<ActorRvm> actorRoles = actors.stream().filter(u -> u.getRoleName() != null).collect((Collectors.toList()));
        if (actorRoles.size() > 0) {
            List<RoleEntity> roleEntities = new ArrayList<>();
            RoleEntity roleEntity;
            for (ActorRvm actor : actorRoles) {
                if (!actor.getRoleName().contains("Internal")) {
                    roleEntity = new RoleEntity();
                    roleEntity.setRoleName(actor.getRoleName());
                    roleEntity.setActorId(actor.getId());
                    roleEntities.add(roleEntity);
                }
            }
            return roleEntities;
        }
        return new ArrayList<>();

    }

}
