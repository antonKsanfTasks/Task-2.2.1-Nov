package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.model.Car;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);

      carService.add(new Car("Model1", 1));
      carService.add(new Car("Model2", 2));
      carService.add(new Car("Model3", 3));
      carService.add(new Car("Model4", 4));

      List<Car> listCars = carService.listCars();

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", listCars.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", listCars.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", listCars.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", listCars.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println("Ищем владельца модели №4");
      System.out.println(userService.getUserByCar("Model4", 4).toString());

      context.close();
   }
}
