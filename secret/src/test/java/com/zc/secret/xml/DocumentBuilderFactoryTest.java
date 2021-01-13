package com.zc.secret.xml;


import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DocumentBuilderFactoryTest {

    private DocumentBuilderFactory documentBuilderFactory;

    @Before
    public void setUp() {
        documentBuilderFactory = new DocumentBuilderFactory();
    }

    @Test
    public void readXML4ReadFile() throws IOException, SAXException, ParserConfigurationException {
        Document document = documentBuilderFactory.readXML("src/test/resources/Payload.xml");

        NodeList bookList = document.getElementsByTagName("name");
        for(int i=0;i<bookList.getLength();i++){
            if(bookList.item(i).getFirstChild().getNodeValue() != null){
                System.out.println("文件被读取");
            }else{
                System.out.println("文件未被读取");
            }
        }
    }

    @Test
    public void readXML4HostDetection() throws IOException, SAXException, ParserConfigurationException {
        documentBuilderFactory.readXML("src/test/resources/HostDetection.xml");
    }
    @Test
    public void readXML4ExecuteCommand() throws IOException, SAXException, ParserConfigurationException {
        documentBuilderFactory.readXML("src/test/resources/ExecuteCommand.xml");
    }
    @Test
    public void readXML4DDOS() throws IOException, SAXException, ParserConfigurationException {
        documentBuilderFactory.readXML("src/test/resources/DDOS.xml");
    }
}