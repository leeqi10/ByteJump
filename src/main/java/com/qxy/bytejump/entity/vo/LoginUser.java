package com.qxy.bytejump.entity.vo;
/**
 * @author lingqu
 * @date 2022/3/1
 * @apiNote
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.qxy.bytejump.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@description TODO
 *@author zzhi
 *@createDate 2022/3/1
 *@version 1.0
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class LoginUser implements UserDetails {
    private User user;
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }
    public LoginUser(User user) {
        this.user=user;
    }

    @JSONField(serialize = false)
    public List<SimpleGrantedAuthority> permissionList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //将permissions的字符传入SimpleGrantedAuthority
       /* permissionList = new ArrayList<>();*/
        //使用函数式
        List<SimpleGrantedAuthority> permissionList = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return permissionList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
