package IService.Security;

import Security.SecurityEntity.TokenEntity;
import org.springframework.stereotype.Service;

@Service
public interface IBaseService {
    TokenEntity getTokenEntity();

}
