package com.example.bp.ebookmanager.dataprovider.html;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class HTMLScraper {
    private Document document;
    private NodeList nodeList;
    private String source;

    public HTMLScraper(String source) {
        this.source = source;
        reset();
    }

    public void reset() {
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
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item == null)
                result.add(null);
            else
                result.add(item.getAttributes().getNamedItem(attribute).getNodeValue());
        }
        return result;
    }

    public ArrayList<String> getFirstChildList() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++)
            result.add(nodeList.item(i).getFirstChild().getNodeValue());
        return result;
    }

    public void switchToChildrenWith(String element, String attribute, String attributeValue) {
        ArrayNodeList newList = new ArrayNodeList();
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList childNodes = nodeList.item(i).getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node item = childNodes.item(j);
                if (item.getNodeName().equals(element)
                        && item.getAttributes().getNamedItem(attribute).getNodeValue().equals(attributeValue)) {
                    newList.add(item);
                }
            }
            if (newList.getLength() - 1 < i)
                newList.add(null);
        }
        nodeList = newList;
    }

    public void switchToChild(int index) {
        nodeList = nodeList.item(index).getChildNodes();
    }

    public String getFirstChild() {
        return nodeList.item(0).getFirstChild().getNodeValue();
    }

    private static class ArrayNodeList implements NodeList {
        ArrayList<Node> list = new ArrayList<>();

        @Override
        public Node item(int index) {
            return list.get(index);
        }

        @Override
        public int getLength() {
            return list.size();
        }

        public void add(Node node) {
            list.add(node);
        }
    }
}
