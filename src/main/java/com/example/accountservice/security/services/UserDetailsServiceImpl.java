package com.example.applicationgateway.security.services;

import com.example.applicationgateway.exception.UserNotFound;
import com.example.applicationgateway.models.Account;
import com.example.applicationgateway.models.AccountRole;
import com.example.applicationgateway.models.Role;
import com.example.applicationgateway.repository.AccountRepository;
import com.example.applicationgateway.repository.AccountRoleRepository;
import com.example.applicationgateway.repository.RoleRepository;
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
                .orElseThrow(() -> new UserNotFound("User Not Found with username: " + username));
        var accountRole = accountRoleRepository.findAllByAccountUuid(account.getUuid());
        var uuids = accountRole.stream().map(AccountRole::getRoleUuid).collect(Collectors.toList());
        List<Role> roles = roleRepository.findByUuidIn(uuids);
        return new UserPrincipal(account.getUuid(), account.getUsername(), account.getEmail(), account.getPassword(),
                roles.stream()
                        .map(u -> new SimpleGrantedAuthority(u.getRole())).collect(Collectors.toList()));
    }
}
