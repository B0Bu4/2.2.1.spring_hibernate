package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        User userWithCar1 = new User("User5", "Lastname5", "user5@mail.ru");
        userWithCar1.setCar(new Car("Ferrari", 777));

        User userWithCar2 = new User("User6", "Lastname6", "user6@mail.ru");
        userWithCar2.setCar(new Car("Porsche", 911));

        User userWithCar3 = new User("Ivan", "Petrov", "ivanpetrov@mail.ru");
        userWithCar3.setCar(new Car("Lada", 2107));

        userService.add(userWithCar1);
        userService.add(userWithCar2);
        userService.add(userWithCar3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        User userTest = userService.userFromCar(new Car("Lada", 2107));

        System.out.println("User = " + userTest.getFirstName() + " has a car =  " + userTest.getCar().
                getModel() + " " + userTest.getCar().getSeries());

        context.close();
    }
}
