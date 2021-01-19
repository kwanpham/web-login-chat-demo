package kwan.org.webloginchatdemo.repo;

import kwan.org.webloginchatdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by https://github.com/kwanpham
 */
public interface UserRepo extends JpaRepository<User , Long> {


    Optional<User> findByUsername(String username);
}
