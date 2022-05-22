package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User userFromCar(Car car) {

      List<User> userList = sessionFactory.getCurrentSession().createQuery(
                      "FROM User U WHERE U.userCar.model = :model and U.userCar.series = :series")
              .setParameter("model", car.getModel())
              .setParameter("series", car.getSeries())
              .getResultList();

      return userList.get(0);
   }

}
