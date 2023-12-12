package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Герман", "Негреф", (byte) 55);
        userService.saveUser("Владык", "Невладыкин", (byte) 33);
        userService.saveUser("Ката", "Самураевна", (byte) 22);
        userService.saveUser("Дукалис", "Джобс", (byte) 66);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
