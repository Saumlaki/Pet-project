import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:C:/Git/Pet-project/PriceDynamic/src/main/resources/DB.sqlite").
                setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC").
                setProperty("hibernate.current_session_context_class", "thread").
                setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect").
                setProperty("hibernate.show_sql", "true").
                setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(Price.class).addAnnotatedClass(Product.class).addAnnotatedClass(Shop.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Shop shop = new Shop("Пятерочка");
        session.save(shop);
        session.getTransaction().commit();
    }

}