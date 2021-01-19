package kwan.org.webloginchatdemo.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
public class SecurityUtils {

    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }

    public static boolean isAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}
