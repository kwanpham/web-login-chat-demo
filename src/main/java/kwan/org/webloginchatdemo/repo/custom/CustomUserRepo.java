package kwan.org.webloginchatdemo.repo.custom;

import kwan.org.webloginchatdemo.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
@Repository
public class CustomUserRepo {

    @PersistenceContext
    private EntityManager em;

    public List<UserDto> selectQuerryNativeMapDto() {
        List<UserDto> dtos =  em.createNativeQuery("SELECT u.username, r.name from ip_user u INNER JOIN ip_user_role ur ON\n" +
                "u.id = ur.user_id\n" +
                "INNER JOIN ip_role r ON\n" +
                "ur.role_id = r.id\n" +
                "WHERE r.id=1\n" +
                " " , "UserDto").getResultList();
        return dtos;


    }
}
