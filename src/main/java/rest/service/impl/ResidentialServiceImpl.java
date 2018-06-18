package rest.service.impl;

import bitrix.entity.*;
import bitrix.repository.HouseRespository;
import bitrix.repository.IFileRepository;
import bitrix.repository.IblockRepository;
import bitrix.repository.ResidentialRepository;
import nar.NarXMLParser;
import nar.entity.Apartment;
import other.DownloadFileFromUrl;
import rest.entity.HouseCache;
import rest.entity.ResidentialCache;
import rest.entity.controller.CustomParameter;
import rest.entity.controller.DataToFastSelect;
import rest.entity.controller.ResidentialMin;
import rest.entity.controller.SearchData;
import rest.service.ResidentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResidentialServiceImpl implements ResidentialService {

    /* Create local cache -- start */
    private HashMap<String, CustomParameter> cacheParameter = new HashMap<>();
    private HashMap<Integer, Iblock> cacheIBlock = new HashMap<>();
    private HashMap<Integer, IFile> cacheIFile = new HashMap<>();
    private HashMap<Integer, House> cacheHouse = new HashMap<>();

    private String[] paramTables = {"accomplishments", "banks", "comfort", "deadline", "decoration", "developer",
            "district", "ecologicals", "externals", "internals", "material", "pay", "securities", "subway_station"};
    private HashMap<Integer, ResidentialCache> cache = new HashMap<>();
    /* Create local cache -- end */


    @Autowired
    private ResidentialRepository residentialRepository;

    @Override
    public List<ResidentialMin> getResidentialMin() {
        checkUpdate();

        List<ResidentialMin> res = new ArrayList<>();

        for (Map.Entry<Integer, ResidentialCache> item: cache.entrySet()) {
            res.add(new ResidentialMin(item.getValue()));
        }

        return res;
    }

    @Override
    public List<DataToFastSelect> getAllParam() {
        checkUpdate();

        List<DataToFastSelect> data = new ArrayList<>();
        for (HashMap.Entry<String, CustomParameter> entry : cacheParameter.entrySet()) {
            CustomParameter value = entry.getValue();
            data.add(new DataToFastSelect(value.getName(), value.getXmlId()));
        }

        for (Map.Entry<Integer, ResidentialCache> item: cache.entrySet()) {
            data.add(new DataToFastSelect(item.getValue().getName(), item.getValue().getId().toString()));
        }

        return data;
    }

    private void checkUpdate() {
        if(cacheParameter.isEmpty() || cacheIBlock.isEmpty() || cacheIFile.isEmpty() || cacheHouse.isEmpty() || cache.isEmpty()) {
            this.updater();
        }
    }

    private void updater() {
        updateIBlock();
        updateFile();
        updateHouse();

        List<Apartment> apartmentList = null;

        String outputFile = System.getProperty("user.dir") + "\\apartment.xml";
        if (DownloadFileFromUrl.DownloadFileFromUrl("http://mls-nsk.ru/files/newbuilding_export.yml", outputFile)) {
            apartmentList = NarXMLParser.parsingXmlFile(outputFile);
        }


        List<Residential> residentialList = residentialRepository.findAll();

        for (Residential residential : residentialList) {
            ResidentialCache cacheItem = new ResidentialCache(residential);

            cacheItem.setDistrict(setParam2(residential.getPROPERTY_1122()));
            if(cacheItem.getDistrict() == null) {
                System.out.println("Null district, element id: " + cacheItem.getId());
                continue;
            }

            cacheItem.setDeveloper(setParam2(residential.getPROPERTY_1123()));
            if(cacheItem.getDeveloper() == null) {
                System.out.println("Null district, developer id: " + cacheItem.getId());
                continue;
            }

            cacheItem.setSubway(setParam2(residential.getPROPERTY_1124()));
            cacheItem.setComfort(setParam2(residential.getPROPERTY_1129()));

            cacheItem.setExternals(setParam2(residential.getPROPERTY_1130(), "ex"));
            cacheItem.setInternals(setParam2(residential.getPROPERTY_1131(), "in"));
            cacheItem.setEcologicals(setParam2(residential.getPROPERTY_1132(), "eg"));
            cacheItem.setSecurities(setParam2(residential.getPROPERTY_1133(), "sc"));
            cacheItem.setAccomplishments(setParam2(residential.getPROPERTY_1134(), "ac"));

            // Пропуск не активных ЖК
            if(cacheIBlock.get(cacheItem.getId()) == null) {
                continue;
            }

            cacheItem.setDetailText(cacheIBlock.get(cacheItem.getId()).getDetailText());
            cacheItem.setPreviewText(cacheIBlock.get(cacheItem.getId()).getPreviewText());
            cacheItem.setName(cacheIBlock.get(cacheItem.getId()).getName());
            cacheItem.setImage(cacheIFile.get(cacheIBlock.get(cacheItem.getId()).getPreviewPicture()));
            cacheItem.setImageBig(cacheIFile.get(cacheIBlock.get(cacheItem.getId()).getDetailPicture()));

            List<HouseCache> houses = new ArrayList<>();
            for (Map.Entry<Integer, House> house: cacheHouse.entrySet()) {
                if(!house.getValue().getPROPERTY_1135().equals(cacheItem.getId())) continue;

                HouseCache houseOut = new HouseCache(house.getValue());
                houseOut.setDeadline(setParam2(house.getValue().getPROPERTY_1138()));
                houseOut.setDecoration(setParam2(house.getValue().getPROPERTY_1139()));
                houseOut.setMaterial(setParam2(house.getValue().getPROPERTY_1140()));
                houseOut.setPayment(setParam2(house.getValue().getPROPERTY_1141(), "pa"));
                houseOut.setBank(setParam2(house.getValue().getPROPERTY_1142(), "bk"));



//                List<Apartment> apartmentsByDeveloper = apartmentList.stream()
//                        .filter(item -> item.getDeveloper().equals(residentialOut.getName()))
//                        .collect(Collectors.toList());
////
//                houseOut.setApartments(apartmentsByDeveloper.stream()
//                        .filter(item -> item.getAddress().equals(houseOut.getName()))
//                        .collect(Collectors.toList())
//                );

                houses.add(houseOut);
            }

            cacheItem.setHouses(houses);

            cache.put(cacheItem.getId(), cacheItem);
        }

    }

    @Override
    public List<ResidentialMin> getResidentialMinBySearch(SearchData[] searchData) {
        checkUpdate();

        return null;
    }

    @Override
    public List<ResidentialCache> findAll() {
        checkUpdate();

        List<ResidentialCache> residentialCaches = new ArrayList<>();
        for(Map.Entry<Integer, ResidentialCache> item: cache.entrySet()) {
            residentialCaches.add(item.getValue());
        }

        return residentialCaches;
    }

    private String setParam(String xmlId) {
        if (cacheParameter.size() == 0) {
            updateParameters();
        }

        if (xmlId == null) {
            return null;
        }

        if (xmlId.isEmpty()) {
            return null;
        }

        return cacheParameter.get(xmlId).getName();
    }

    private CustomParameter setParam2(String xmlId) {
        if (cacheParameter.size() == 0) {
            updateParameters();
        }

        return cacheParameter.get(xmlId);
    }

    private List<CustomParameter> setParam2(String param, String pattern) {
        if (param == null) return null;
        if (param.isEmpty()) return null;
        Pattern p = Pattern.compile(pattern + "(\\d*)");
        Matcher m = p.matcher(param);

        List<CustomParameter> res = new ArrayList<>();
        while (m.find()) {
            res.add(setParam2(param.substring(m.start(), m.end())));
        }

        return res;
    }

    private void updateParameters() {
        try {
            String url = "jdbc:mysql://mdn1.timeweb.ru:3306/bgnsk_newbgnsk?characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "bgnsk_newbgnsk", "3FM82BlmwL4uxd9jyiku");
            Statement stmt = conn.createStatement();


            for (String table : paramTables) {
                ResultSet rs;
                rs = stmt.executeQuery("SELECT ID, UF_NAME, UF_XML_ID FROM " + table);
                while (rs.next()) {
                    Integer id = rs.getInt("ID");
                    String uf_name = rs.getString("UF_NAME");
                    String uf_xml_id = rs.getString("UF_XML_ID");

                    if (uf_xml_id == null) continue;
                    if (uf_xml_id.isEmpty()) continue;

                    cacheParameter.put(uf_xml_id, new CustomParameter(id, uf_name, uf_xml_id));
                }
            }

            conn.close();

        } catch (Exception e) {
            System.err.println("updateParameters error! ");
            System.err.println(e.getMessage());
        }
    }

    private void updateIBlock() {
        try {
            String url = "jdbc:mysql://mdn1.timeweb.ru:3306/bgnsk_newbgnsk?characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "bgnsk_newbgnsk", "3FM82BlmwL4uxd9jyiku");
            Statement stmt = conn.createStatement();


            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM b_iblock_element WHERE IBLOCK_ID = 79 AND active = 'Y'");
            while (rs.next()) {
                Iblock iblock = new Iblock();
                iblock.setId(rs.getInt("ID"));
                iblock.setActive(rs.getString("ACTIVE"));
                iblock.setName(rs.getString("NAME"));
                iblock.setPreviewPicture(rs.getInt("PREVIEW_PICTURE"));
                iblock.setPreviewText(rs.getString("PREVIEW_TEXT"));
                iblock.setDetailPicture(rs.getInt("DETAIL_PICTURE"));
                iblock.setDetailText(rs.getString("DETAIL_TEXT"));

                cacheIBlock.put(iblock.getId(), iblock);
            }

            conn.close();

        } catch (Exception e) {
            System.err.println("updateParameters error! ");
            System.err.println(e.getMessage());
        }
    }

    private void updateFile() {
        try {
            String url = "jdbc:mysql://mdn1.timeweb.ru:3306/bgnsk_newbgnsk?characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "bgnsk_newbgnsk", "3FM82BlmwL4uxd9jyiku");
            Statement stmt = conn.createStatement();


            ResultSet rs;
            rs = stmt.executeQuery("SELECT ID, SUBDIR, FILE_NAME FROM b_file");
            while (rs.next()) {
                IFile iFile = new IFile();
                iFile.setId(rs.getInt("ID"));
                iFile.setName(rs.getString("FILE_NAME"));
                iFile.setSubdir(rs.getString("SUBDIR"));

                cacheIFile.put(iFile.getId(), iFile);
            }

            conn.close();

        } catch (Exception e) {
            System.err.println("updateParameters error! ");
            System.err.println(e.getMessage());
        }
    }

    private void updateHouse() {
        try {
            String url = "jdbc:mysql://mdn1.timeweb.ru:3306/bgnsk_newbgnsk?characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "bgnsk_newbgnsk", "3FM82BlmwL4uxd9jyiku");
            Statement stmt = conn.createStatement();


            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM b_iblock_element_prop_s80;");
            while (rs.next()) {
                House house = new House();
                house.setId(rs.getInt("IBLOCK_ELEMENT_ID"));
                house.setPROPERTY_1135(rs.getInt("PROPERTY_1135"));
                house.setPROPERTY_1136(rs.getString("PROPERTY_1136"));
                house.setPROPERTY_1137(rs.getInt("PROPERTY_1137"));
                house.setPROPERTY_1138(rs.getString("PROPERTY_1138"));
                house.setPROPERTY_1139(rs.getString("PROPERTY_1139"));
                house.setPROPERTY_1140(rs.getString("PROPERTY_1140"));
                house.setPROPERTY_1141(rs.getString("PROPERTY_1141"));
                house.setPROPERTY_1142(rs.getString("PROPERTY_1142"));

                cacheHouse.put(house.getId(), house);
            }

            conn.close();

        } catch (Exception e) {
            System.err.println("updateParameters error! ");
            System.err.println(e.getMessage());
        }
    }

}
