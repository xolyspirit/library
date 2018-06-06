package controller;
import dao.Library;
/**Класс библиотекаря
 * @version 1.0
 * @author Xolyspirit */
public class Librarian {
    /**Библиотека*/
    private Library library;
    /**Конструктор, принимающий @param library экземпляр библиотеки*/
    public Librarian(Library library) {
        this.library = library;
    }
    /**Читаем с помощью SAX*/
    public String readBySAX(){
        return library.readBySAX();
    }
    /**Читаем с помощью StAX*/
    public String readByStAX(){
        return library.readByStAX();
    }
    /**Читаем с помощью DOM*/
    public String readByDOM(){
        return library.readByDOM();
    }
    /**Читаем данные из БД и сохраняем XML-файл*/
    public void readBDandSaveInXML(){
        library.saveWithXML(library.readBD());
    }
}
