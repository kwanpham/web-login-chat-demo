package kwan.org.webloginchatdemo.controller.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * Created by https://github.com/kwanpham
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupRequest {



    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String username;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String password;

}