package com.example.accountservice.common.security.services;

import com.example.accountservice.common.exception.AuthenticationException;
import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.AccountRole;
import com.example.accountservice.infrastructure.models.Role;
import com.example.accountservice.infrastructure.repository.AccountRepository;
import com.example.accountservice.infrastructure.repository.AccountRoleRepository;
import com.example.accountservice.infrastructure.repository.RoleRepository;
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
    private AccountRepository accountRepository;
    @Autowired
    private AccountRoleRepository accountRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username)
                .orElse(new Account());
        return getUserDetails(account);
    }

    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmailAndStatusIsTrue(username)
                .orElseThrow(AuthenticationException::new);
        return getUserDetails(account);
    }

    private UserDetails getUserDetails(Account account) {
        var accountRole = accountRoleRepository.findAllByAccountUuid(account.getUuid());
        var uuids = accountRole.stream().map(AccountRole::getRoleUuid).collect(Collectors.toList());
        List<Role> roles = roleRepository.findByUuidIn(uuids);
        return new UserPrincipal(account.getUuid(), account.getUsername(), account.getEmail(), account.getPassword(),
                roles
                        .stream()
                        .map(u -> new SimpleGrantedAuthority(u.getRole())).collect(Collectors.toList()));
    }
}
