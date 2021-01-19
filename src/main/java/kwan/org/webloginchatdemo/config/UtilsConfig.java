package kwan.org.webloginchatdemo.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;

/**
 * Created by https://github.com/kwanpham
 */
public class UtilsConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
