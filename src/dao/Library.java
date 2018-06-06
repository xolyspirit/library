package dao;
import model.Book;
import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import util.DomParser;
import util.HibernateSessionFactoryUtil;
import util.SaxParser;
import util.StaxParser;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;
import java.io.File;
import java.io.InputStream;
import java.util.List;
/**Класс библиотеки
 * @version 1.0
 * @author Xolyspirit */
public class Library {
    /**Ссылка на XML-файл в виде файла*/
    private File booksFile;
    /**Пусть к XML-файлу*/
    private String path;
    /**Переменная, нужна для формирования строки ответа*/
    private StringBuilder answer = new StringBuilder();
    /**Список книг полученных из XML-файла*/
    private List<Book> books;
    /**Конструктор принимающий @param path путь к XML-файлу, и создающий экземпляр File*/
    public Library(String path) {
        this.path = path;
        booksFile = new File(path);
    }
    /**Читаем с помощью SAX*/
    public String readBySAX() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SaxParser saxp = new SaxParser();
            parser.parse(booksFile, saxp);
            books =  saxp.getAnswer();

            for (Book book: books) {
                answer.append(book.toString() + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
    /**Читаем с помощью StAX*/
    public String readByStAX(){
        StaxParser parser = new StaxParser();
        books = parser.parse(path);
        answer.delete(0,answer.length());
        for (Book book: books) {
            answer.append(book.toString() + "\n");
        }
        return answer.toString();
    }
    /**Читаем с помощью DOM*/
    public String readByDOM(){
        DomParser parser = new DomParser();
        books = parser.parse(path);
        answer.delete(0,answer.length());
        for (Book book: books) {
            answer.append(book.toString() + "\n");
        }
        return answer.toString();
    }
    /**Читаем данные из БД */
    @SuppressWarnings("unchecked")
    public List<Book> readBD(){
        System.out.println("Reading from DB...");
        InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
        Session session = HibernateSessionFactoryUtil.getSessionFactory(is).openSession();
        session.beginTransaction();
        books = (List<Book>)session.createQuery("FROM model.Book").list();
        session.getTransaction().commit();
        session.close();
        return books;
    }
    /**Сохраняем XML-файл*/
    public void saveWithXML(List<Book> books){
        System.out.println("Saving with XML...");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element BookList = document.createElement("BookList");
            BookList.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
            BookList.setAttribute("xsi:noNamespaceSchemaLocation", "books.xsd");
            for (Book b : books) {
                Element book = document.createElement("Book");
                BookList.appendChild(book);
                Element id = document.createElement("id");
                id.setTextContent(String.valueOf(b.getId()));
                book.appendChild(id);

                for (int i = 0; i < b.getAuthors().size(); i++) {
                    Element author = document.createElement("author");

                    Element firstName = document.createElement("firstName");
                    firstName.setTextContent(b.getAuthors().get(i).getFirstName());
                    author.appendChild(firstName);

                    Element lastName = document.createElement("lastName");
                    lastName.setTextContent(b.getAuthors().get(i).getLastName());
                    author.appendChild(lastName);

                    book.appendChild(author);
                }

                Element name = document.createElement("name");
                name.setTextContent(String.valueOf(b.getName()));
                book.appendChild(name);

                Element ISBN = document.createElement("ISBN");
                ISBN.setTextContent(String.valueOf(b.getISBN()));
                book.appendChild(ISBN);

                Element cost = document.createElement("cost");
                cost.setTextContent(String.valueOf(b.getCost()));
                book.appendChild(cost);
            }
            document.appendChild(BookList);
            File file = new File("Books.xml");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(file));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("All done!");
    }
}


