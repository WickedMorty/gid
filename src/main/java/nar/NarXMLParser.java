package nar;

import nar.entity.Apartment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NarXMLParser {
    public static List<Apartment> parsingXmlFile(String file) {

        List<Apartment> apartmentList = new ArrayList<Apartment>();

        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("offer");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Apartment apartment = new Apartment();
                    apartment.setBuilding(eElement.getElementsByTagName("building-name").item(0).getTextContent());
                    apartment.setAddress(eElement.getElementsByTagName("address").item(0).getTextContent());
                    apartment.setFloor(eElement.getElementsByTagName("floor").item(0).getTextContent());
                    apartment.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
                    apartment.setRooms(eElement.getElementsByTagName("rooms").item(0).getTextContent());

                    Node nArea = eElement.getElementsByTagName("area").item(0);
                    if(nArea.getNodeType() == Node.ELEMENT_NODE) {
                        Element tElement = (Element) nArea;
                        apartment.setArea(tElement.getElementsByTagName("value").item(0).getTextContent());
                    }

                    Node nPrice = eElement.getElementsByTagName("price").item(0);
                    if(nPrice.getNodeType() == Node.ELEMENT_NODE) {
                        Element tElement = (Element) nPrice;
                        apartment.setPrice(tElement.getElementsByTagName("value").item(0).getTextContent());
                    }

                    apartmentList.add(apartment);
                }

            }


        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return apartmentList;
    }
}
