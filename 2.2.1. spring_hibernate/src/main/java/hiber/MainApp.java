package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
public static void main(String[] args) throws SQLException {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    UserService userService = context.getBean(UserService.class);

    User Max = new User("Max", "Kolt", "Max@mail.ru");
    User Tom = new User("Tom", "Port", "Tom@mail.ru");
    User Bob = new User("Bob", "Mar", "Bob@mail.ru");
    User Jim = new User("Jim", "Bim", "Jim@mail.ru");

    Car Nissan = new Car("Primera", 73);
    Car Renault = new Car("Sandero", 63);
    Car BMW = new Car("X", 5);
    Car Audi = new Car("Q", 7);
    Car Mitsubishi = new Car("Lancer", 10);

    userService.add(Max.setCar(Nissan).setUser(Max));
    userService.add(Tom.setCar(BMW).setUser(Tom));
    userService.add(Bob.setCar(Audi).setUser(Bob));
    userService.add(Jim.setCar(Mitsubishi).setUser(Jim));

    List<User> users = userService.listUsers();
    for (User user : users) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println();
    }
    for (User user : userService.listUsers()) {
        System.out.println(user + " " + user.getCar());
    }

    User user = userService.getUserByCar("x", 5);
    System.out.println(user.toString());

    try {
        User notFoundUser = userService.getUserByCar("Sandero", 63);
    } catch (NoResultException e) {
    }

    context.close();
}
}
