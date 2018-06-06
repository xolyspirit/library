package util;
import model.Author;
import model.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.io.InputStream;
import java.util.Properties;
/**Класс конфигурации Hibernate
 * @version 1.0
 * @author Xolyspirit */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){}
    public static SessionFactory getSessionFactory(InputStream is) {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.load(is);
                Configuration configuration = new Configuration();
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Book.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Something wrong with DB session" + "\n" + e);
            }
        }
        return sessionFactory;
    }
}
