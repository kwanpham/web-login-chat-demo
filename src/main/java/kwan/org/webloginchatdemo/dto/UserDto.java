package kwan.org.webloginchatdemo.dto;


import lombok.Data;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(name = "UserDto",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "u.username", type = String.class),
                        @ColumnResult(name = "r.name", type = String.class)
                }
        )
)
@Data
public class UserDto {
    public UserDto(String username, String password) {
        this.username1 = username;
        this.password = password;
    }




    private String username1;


    private String password;


}
