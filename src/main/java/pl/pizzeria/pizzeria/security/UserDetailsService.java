package pl.pizzeria.pizzeria.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import pl.pizzeria.pizzeria.models.User;
import pl.pizzeria.pizzeria.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    /**
     * @param email user is finding by email
     * @return Spring Security user object
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
            user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                    user.get().getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("User not exist in database");
        }
    }
}
