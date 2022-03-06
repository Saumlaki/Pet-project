import entity.Woman;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class test {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Woman.class).buildSessionFactory();

        //Создаем объект который хотим сохранить
        Woman woman = new Woman("Alexey", "222-322", "1000");

        //Сессия необходима для выполнения операции с БД
        //Кратко живущий объект
        try (Session session = factory.getCurrentSession()) {


            //Сохраняем объект
            session.beginTransaction();
            System.out.println("-->Сохраняю работотника: " );
            session.save(woman);
            session.getTransaction().commit();
        }


    }
}
