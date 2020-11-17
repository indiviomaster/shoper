package ru.indivio.market.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.indivio.market.entites.Role;
import ru.indivio.market.entites.SystemUser;
import ru.indivio.market.entites.User;
import ru.indivio.market.repositories.RoleRepository;
import ru.indivio.market.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsrService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User findByName(String username) {
        return userRepository.findOneByUserName(username);
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    public Iterable<User> findAll() {

        return userRepository.findAll();
    }
    public void delete(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user!= null){
            userRepository.delete(user.get());
        }
    }

}
