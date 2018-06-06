package util;
import model.Author;
import model.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
/**Класс парсера SAX
 * @version 1.0
 * @author Xolyspirit */
public class SaxParser extends DefaultHandler {
    /**Техническая переменная. необходимая для работы парсера*/
    private String thisElement;
    /**Список книг {@link Book}*/
    private List<Book> books = new ArrayList<>();
    /**Книга {@link Book}*/
    private Book book;
    /**Автор {@link Author} книги */
    private Author author;
    /**Переопреденные методы, для работы парсера*/
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML wit SAX parser...");
    }
    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("Book")) {
            book = new Book();
            books.add(book);
        }
        else if (thisElement.equals("id")) {
            book.setId(Integer.parseInt(new String(ch, start,length)));
        }
        else if (thisElement.equals("author")) {
            author = new Author();
        }
        else if (thisElement.equals("firstName")) {
            author.setFirstName(new String(ch, start,length));
        }
        else if (thisElement.equals("lastName")) {
            author.setLastName(new String(ch, start,length));
            book.getAuthors().add(author);
        }
        else if (thisElement.equals("name")) {
            book.setName(new String(ch, start,length));
        }
        else if (thisElement.equals("ISBN")) {
            book.setISBN(new Integer(new String(ch, start,length)));
        }
        else if (thisElement.equals("cost")) {
            book.setCost(new Double(new String(ch, start,length)));
        }
    }
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }
    /**Выдаем @return books результат*/
    public List<Book> getAnswer() {
        return books;
    }
}

