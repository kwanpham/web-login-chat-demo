package kwan.org.webloginchatdemo.controller.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created by https://github.com/kwanpham
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {


    @NotNull(message = "{constraints.NotEmpty.message}")
    private String username;

    @NotNull(message = "{constraints.NotEmpty.message}")
    private String password;

}
