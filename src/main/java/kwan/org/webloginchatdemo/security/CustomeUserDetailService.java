package kwan.org.webloginchatdemo.security;


import kwan.org.webloginchatdemo.entity.Role;
import kwan.org.webloginchatdemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by https://github.com/kwanpham
 */
@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<kwan.org.webloginchatdemo.entity.User> userOptional = userRepo.findByUsername(s);
        if (userOptional.isPresent()){
            kwan.org.webloginchatdemo.entity.User user = userOptional.get();
            return new User(user.getUsername() , user.getPassword() , getUserAuthority(user.getRoles()));
        }
        throw new UsernameNotFoundException("user with username " + s + " does not exist.");
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new ArrayList<GrantedAuthority>(roles);
    }
}
