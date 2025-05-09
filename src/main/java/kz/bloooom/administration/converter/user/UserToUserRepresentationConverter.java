package kz.bloooom.administration.converter.user;

import kz.bloooom.administration.domain.entity.AbstractUserEntity;
import kz.bloooom.administration.domain.entity.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
public class UserToUserRepresentationConverter {

    public <T extends AbstractUserEntity> UserRepresentation convert(T source) {
        UserRepresentation target = new UserRepresentation();
        target.setEnabled(true);
        target.setUsername(source.getEmail());
        target.setFirstName(source.getName());
        target.setLastName(source.getName());
        target.setEmail(source.getEmail());
        return target;
    }
}
