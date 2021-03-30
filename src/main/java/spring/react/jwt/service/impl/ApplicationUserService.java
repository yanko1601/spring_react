package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.repositories.PlayerRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public ApplicationUserService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = this.playerRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));

        return User.builder()
                .username(player.getEmail())
                .password(player.getPassword())
                .roles(player.getRole().name())
                .build();
    }
}
