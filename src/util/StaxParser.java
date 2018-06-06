package util;
import model.Author;
import model.Book;

import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
/**Класс парсера StAX
 * @version 1.0
 * @author Xolyspirit */
public class StaxParser {
    /**Книга {@link Book}*/
    private Book book;
    /**Автор {@link Author} книги */
    private Author author;
    /**Список книг {@link Book}*/
    private List<Book> books = new ArrayList<>();
    /**Парсим XML-файл найденный по принятому @param path пути
     * возвращаем @return books распарсенный список книг*/
    public List<Book> parse(String path){
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(path));
            System.out.println("Start parse XML with StAX parser...");
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Book")) {
                        book = new Book();
                    } else if (startElement.getName().getLocalPart().equals("id")) {
                        event = reader.nextEvent();
                        book.setId(Integer.parseInt(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("author")) {
                        author = new Author();
                    } else if (startElement.getName().getLocalPart().equals("firstName")) {
                        event = reader.nextEvent();
                        author.setFirstName(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("lastName")) {
                        event = reader.nextEvent();
                        author.setLastName(event.asCharacters().getData());
                        book.getAuthors().add(author);
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        event = reader.nextEvent();
                        book.setName(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("ISBN")) {
                        event = reader.nextEvent();
                        book.setISBN(new Integer(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("cost")) {
                        event = reader.nextEvent();
                        book.setCost(new Double(event.asCharacters().getData()));
                        books.add(book);
                    }
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    return books;
    }
}
