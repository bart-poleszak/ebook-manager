package com.example.bp.ebookmanager.dataprovider.html;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by bp on 12.06.16.
 */
public class HTMLScraper {
    private Document document;
    private NodeList nodeList;

    public HTMLScraper(String source) {
        reset(source);
    }

    public void reset(String source) {
        nodeList = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(source)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void evaluateXPathExpression(String expression) {
        try {
            XPathExpression xpath = XPathFactory.newInstance().newXPath().compile(expression);
            nodeList = (NodeList) xpath.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public boolean evaluationSuccessful() {
        return nodeList != null && nodeList.getLength() > 0;
    }

    public String getAttributeValue(String attribute) {
        return nodeList.item(0).getAttributes().getNamedItem(attribute).getNodeValue();
    }

    public ArrayList<String> getAttributeValueList(String attribute) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++)
            result.add(nodeList.item(i).getAttributes().getNamedItem(attribute).getNodeValue());
        return result;
    }

    public ArrayList<String> getFirstChildList() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++)
            result.add(nodeList.item(i).getFirstChild().getNodeValue());
        return result;
    }
}
