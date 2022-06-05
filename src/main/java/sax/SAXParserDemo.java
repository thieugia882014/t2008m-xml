package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SAXParserDemo {

    public static void main(String[] args) {

        try {
            File inputFile = new File("input.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class UserHandler extends DefaultHandler {

    private List<UserHandler> userHandlers = new ArrayList<>();
    private UserHandler curreentUserHandler;
    boolean FirstName = false;
    boolean LastName = false;
    boolean NickName = false;
    boolean Marks = false;


    private void setLastName(String content) {
    }

    private void setFirstName(String content) {
    }

    private void setNickName(String content) {
    }

    private void setMarks(String content) {
    }


    public List<UserHandler> getUserHandlers() {
        return userHandlers;
    }

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        System.out.println("Gặp thẻ mở của thẻ :" + qName);

        if (qName.equals("student")) {
            curreentUserHandler = new UserHandler();
        } else if (qName.equalsIgnoreCase("firstname")) {
            FirstName = true;
        } else if (qName.equalsIgnoreCase("lastname")) {
            LastName = true;
        } else if (qName.equalsIgnoreCase("nickname")) {
            NickName = true;
        } else if (qName.equalsIgnoreCase("marks")) {
            Marks = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        System.out.println("Gặp thẻ đóng của thẻ :" + qName);
        if (qName.equals("student")) {
            userHandlers.add(curreentUserHandler);
        } else if (qName.equals("firstname")) {
            FirstName = false;
        } else if (qName.equals("lastname")) {
            LastName = false;
        } else if (qName.equals("nickname")) {
            NickName = false;
        } else if (qName.equals("marks")) {
            Marks = false;
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        String content = new String(ch, start, length);

        if (FirstName) {
            curreentUserHandler.setFirstName(content);
        } else if (LastName) {
            curreentUserHandler.setLastName(content);
        } else if (NickName) {
            curreentUserHandler.setNickName(content);
        } else if (Marks) {
            curreentUserHandler.setMarks(content);
        }
        System.out.println("Đây là nột dung bên trong thẻ: " + new String(ch, start, length));
    }


}
