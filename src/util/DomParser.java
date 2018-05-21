package util;

import model.Author;
import model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

    public List<Book> parse(String path){
        List<Book> books = new  ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(path);
            NodeList nodeList = doc.getElementsByTagName("Book");

            System.out.println("Start parse XML with DOM parser...");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Book book = new Book();
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element)node;
                    book.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    for (int j = 0; j < element.getElementsByTagName("author").getLength(); j++) {
                        Author author = new Author();
                        Element el = (Element)element.getElementsByTagName("author").item(j);
                        author.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                        author.setLastName(el.getElementsByTagName("lastName").item(0).getTextContent());
                        book.getAuthors().add(author);
                    }
                    book.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    book.setISBN(Integer.parseInt(element.getElementsByTagName("ISBN").item(0).getTextContent()));
                    book.setCost(Double.parseDouble(element.getElementsByTagName("cost").item(0).getTextContent()));
                }
                books.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }
}
