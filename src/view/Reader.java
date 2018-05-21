package view;

import controller.Librarian;

public class Reader {
    private Librarian librarian;

    public Reader(Librarian librarian) {
        this.librarian = librarian;
    }

    public void readBySAX(){
        System.out.println(librarian.readBySAX());
    }
    public void readByStAX(){
        System.out.println(librarian.readByStAX());
    }
    public void readByDOM(){
        System.out.println(librarian.readByDOM());
    }
    public void readBDandSaveInXML(){
        librarian.readBDandSaveInXML();
    }
}
