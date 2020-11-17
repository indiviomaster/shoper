package ru.indivio.market.services;

import ru.indivio.market.entites.SystemUser;
import ru.indivio.market.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);

}
