package cap.capgemini.poe.aston.properties;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cap.capgemini.poe.aston.entities.User;
import cap.capgemini.poe.aston.exceptions.UserResourceNotFoundException;
import cap.capgemini.poe.aston.repositories.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    IUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // Let people login with email
        User user = this.userRepository.findByEmail(userEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email : " + userEmail));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserResourceNotFoundException());

        return UserPrincipal.create(user);
    }
}
