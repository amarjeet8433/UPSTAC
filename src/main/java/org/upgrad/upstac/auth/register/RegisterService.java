package org.upgrad.upstac.auth.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.UserService;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.roles.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class RegisterService {

    @Autowired
    private UserService userService;


    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);


    public User addUser(RegisterRequest registerRequest) {
/*      User should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.USER)
                status should be set to AccountStatus.APPROVED

        And finally
            Call userService.saveInDatabase to save the new user and return the saved user
*/

        try {
//            if (!userService.findByUserName(registerRequest.getUserName()).equals("") ||
//                    !userService.findByEmail(registerRequest.getEmail()).equals("") ||
//                    !userService.findByPhoneNumber(registerRequest.getPhoneNumber()).equals("")) {
//                throw new AppException("User already exists.");
//            }

            try {
                if (!userService.findByUserName(registerRequest.getUserName()).equals("")) {
                    throw new AppException("User already exists.");
                }
            }
            catch (AppException ex) {
                throw new AppException("User already exists.");
            }

            User user = new User();
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setGender(registerRequest.getGender());
            user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth()));
            user.setUserName(registerRequest.getUserName());
            user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setAddress(registerRequest.getAddress());
            user.setPinCode(registerRequest.getPinCode());
            user.setRoles(userService.getRoleFor(UserRole.USER));
            user.setStatus(AccountStatus.APPROVED);
            user.setCreated(LocalDateTime.now());

            return userService.saveInDatabase(user);
        }
        catch (AppException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public User addDoctor(RegisterRequest registerRequest) {


/*      Doctor should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.DOCTOR)
                status should be set to AccountStatus.INITIATED

        And finally
            Call userService.saveInDatabase to save the newly registered doctor and return the saved value
*/
        try {
//            if (!userService.findByUserName(registerRequest.getUserName()).equals("") ||
//                    !userService.findByEmail(registerRequest.getEmail()).equals("") ||
//                    !userService.findByPhoneNumber(registerRequest.getPhoneNumber()).equals("")) {
//                throw new AppException("User already exists.");
//            }

            User user = new User();
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setGender(registerRequest.getGender());
            user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth()));
            user.setUserName(registerRequest.getUserName());
            user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setAddress(registerRequest.getAddress());
            user.setPinCode(registerRequest.getPinCode());
            user.setRoles(userService.getRoleFor(UserRole.DOCTOR));
            user.setStatus(AccountStatus.INITIATED);
            user.setCreated(LocalDateTime.now());

            return userService.saveInDatabase(user);
        }
        catch (AppException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    public User addTester(RegisterRequest registerRequest) {


/*      Tester should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.TESTER)
                status should be set to AccountStatus.INITIATED

        And finally
            Call userService.saveInDatabase to save newly registered tester and return the saved value
*/

        try {
//            if (!userService.findByUserName(registerRequest.getUserName()).equals("") ||
//                    !userService.findByEmail(registerRequest.getEmail()).equals("") ||
//                    !userService.findByPhoneNumber(registerRequest.getPhoneNumber()).equals("")) {
//                throw new AppException("User already exists.");
//            }

            User user = new User();
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setGender(registerRequest.getGender());
            user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth()));
            user.setUserName(registerRequest.getUserName());
            user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setAddress(registerRequest.getAddress());
            user.setPinCode(registerRequest.getPinCode());
            user.setRoles(userService.getRoleFor(UserRole.TESTER));
            user.setStatus(AccountStatus.INITIATED);
            user.setCreated(LocalDateTime.now());

            return userService.saveInDatabase(user);
        }
        catch (AppException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


}
