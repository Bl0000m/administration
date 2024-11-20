package kz.bloooom.administration.converter.user;

import kz.bloooom.administration.domain.dto.user.UserMeInfoDto;
import kz.bloooom.administration.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMeInfoDtoConverter {

    public UserMeInfoDto convert(User source) {
        UserMeInfoDto target = new UserMeInfoDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPhoneNumber(source.getPhoneNumber());
        return target;
    }
}
