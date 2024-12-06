package fr.charly.businessCase.service;

import fr.charly.businessCase.DTO.UserDTO;
import fr.charly.businessCase.DTO.UserUpdateDTO;
import fr.charly.businessCase.entity.User;
import fr.charly.businessCase.exception.AlreadyActiveException;
import fr.charly.businessCase.exception.ExpiredCodeException;
import fr.charly.businessCase.repository.UserRepository;
import fr.charly.businessCase.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface <User, String, UserDTO, UserUpdateDTO>, UserDetailsService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        User user =  new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setBirthedAt(userDTO.getBirthAt());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRole("[\"ROLE_USER\"]");
        user.setCreatedAt(LocalDateTime.now());
        user.setActivationCode(UUID.randomUUID().toString());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(UserUpdateDTO o, String id) {
        User user = findOneById(id);
        user.setFirstName(o.getFirstName());
        user.setLastName(o.getLastName());
        user.setBirthedAt(o.getBirthAt());
        user.setPhone(o.getPhone());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String s) {
        return null;
    }

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User activate(String code) {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(() -> new AlreadyActiveException("Ce code d'activation n'existe pas !"));

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(15))) {
            throw new ExpiredCodeException("La durée du code a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndActivationCodeIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("Les cochons sont dans la baie"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );

    }
}
