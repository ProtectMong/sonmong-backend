package poten012.sonmong.Poten403.domain.security.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import poten012.sonmong.Poten403.domain.user.domain.User;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Long userId;
    private final String phoneNumber;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public CustomUserDetails(User user) {
        this.userId = user.getId();
        this.phoneNumber = user.getPhoneNumber();
        this.authorities = null;
        this.password = null;
        this.username = user.getName();
        this.isAccountNonExpired = false;
        this.isAccountNonLocked = false;
        this.isCredentialsNonExpired = false;
        this.isEnabled = false;
    }
}
