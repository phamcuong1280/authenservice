package com.example.accountservice.common.security.services;

import com.example.accountservice.common.exception.AuthenticationException;
import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.AccountRole;
import com.example.accountservice.infrastructure.models.Role;
import com.example.accountservice.infrastructure.repository.JpaAccountRepository;
import com.example.accountservice.infrastructure.repository.JpaAccountRoleRepository;
import com.example.accountservice.infrastructure.repository.JpaRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private JpaAccountRepository jpaAccountRepository;
    @Autowired
    private JpaAccountRoleRepository jpaAccountRoleRepository;
    @Autowired
    private JpaRoleRepository jpaRoleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = jpaAccountRepository.findByEmail(username)
                .orElse(new Account());
        return getUserDetails(account);
    }

    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
        Account account = jpaAccountRepository.findByEmailAndStatusIsTrue(username)
                .orElseThrow(AuthenticationException::new);
        return getUserDetails(account);
    }

    private UserDetails getUserDetails(Account account) {
        var accountRole = jpaAccountRoleRepository.findAllByAccountUuid(account.getUuid());
        var uuids = accountRole.stream().map(AccountRole::getRoleUuid).collect(Collectors.toList());
        List<Role> roles = jpaRoleRepository.findByUuidIn(uuids);
        return new UserPrincipal(account.getUuid(), account.getName(), account.getEmail(), account.getPassword(),
                roles
                        .stream()
                        .map(u -> new SimpleGrantedAuthority(u.getRole())).collect(Collectors.toList()));
    }
}
