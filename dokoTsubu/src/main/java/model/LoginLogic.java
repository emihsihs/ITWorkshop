package model;

import dao.UserDAO;

public class LoginLogic {
    public User execute(String name, String pass) {
        UserDAO userDAO = new UserDAO();
        return userDAO.findUser(name, pass);
    }
}
