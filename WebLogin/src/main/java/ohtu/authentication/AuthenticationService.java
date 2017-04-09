package ohtu.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        Pattern userPat = Pattern.compile("^([A-Za-z0-9]){3,}$");
        Pattern passPat = Pattern.compile("^(?=.*[A-Za-z])(?=.*([0-9]|[@#$%^&+=]))(.{0,})$");

        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }
        Matcher userMat = userPat.matcher(username);
        if (!userMat.matches()) {
            status.addError("username should have at least 3 characters");
        }
        if(!password.equals(passwordConfirmation)){
            status.addError("password and password confirmation do not match");
        }
        
        if(password.length() < 8){
            status.addError("password should have at least 8 characters");
        }
        
        Matcher passMat = passPat.matcher(password);
        if(!passMat.matches()){
            status.addError("password can not contain only letters");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }

        return status;
    }

}
