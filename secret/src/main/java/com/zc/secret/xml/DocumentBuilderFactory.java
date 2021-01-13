package com.zc.secret.xml;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhangchi
 */
public class DocumentBuilderFactory {
    public Document readXML(String path) throws ParserConfigurationException, IOException, SAXException {
        javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);
        //dbf.setExpandEntityReferences(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        // 读取xml文件内容
        FileInputStream fis = new FileInputStream(path);
        InputSource is = new InputSource(fis);
        Document document = builder.parse(is);
        fis.close();
        return document;
    }
}
