package ma.youcode.gathergrid.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.config.MyQualifier;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.repositories.UserRepository;

import javax.validation.Valid;
import java.util.Optional;


public class UserService {

    private  UserRepository userRepository;
    private IOrganizationService organizationService;

    @Inject
    public UserService(UserRepository userRepository,@MyQualifier IOrganizationService organizationService) {
        this.userRepository = userRepository;
        this.organizationService = organizationService;
    }

    public UserService() {
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public User registerUser(@Valid User user) {
        System.out.println("Registering user: " + user);
        userRepository.save(user);
        return user;
    }

    public Optional<User> findUserByUsername(String userName) {
        return userRepository.findByName(userName);
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public User updateUserInfo(@Valid User user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User usr = optionalUser.get();
        usr.setName(user.getName());
        usr.setUsername(user.getUsername());
        usr.setPassword(user.getPassword());
        usr.setEmail(user.getEmail());
        userRepository.update(usr);
        return user;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteAccount(@Valid User user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
    }

    public boolean isValidUser(@Valid User user) {
        System.out.println("Validating user: " + user);
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        return byUsername.isPresent()
                && user.getPassword().equals(byUsername.get().getPassword());
    }

    public boolean validLoginDetails(@Valid User user) {
        System.out.println("Validating user: " + user);
        return !userRepository.findByUsername(user.getUsername()).isPresent()
                && user.getPassword() !=null && user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }
}
