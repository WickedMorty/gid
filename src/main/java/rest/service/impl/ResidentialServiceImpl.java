package rest.service.impl;

import bitrix.entity.*;
import bitrix.repository.ResidentialRepository;
import freemarker.template.utility.HtmlEscape;
import nar.NarXMLParser;
import nar.entity.Apartment;
import org.springframework.web.util.HtmlUtils;
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

import java.io.File;
import java.security.Key;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResidentialServiceImpl implements ResidentialService {

    /* Create local cache -- start */
    private HashMap<String, CustomParameter> cacheParameter = new HashMap<>();
    private HashMap<Integer, Iblock> cacheIBlock = new HashMap<>();
    private HashMap<Integer, IFile> cacheIFile = new HashMap<>();
    private HashMap<Integer, House> cacheHouse = new HashMap<>();
    private List<Apartment> cacheApartment = new ArrayList<>();
    private List<String> error = new ArrayList<>();

    private HashMap<String, HashSet<ResidentialCache>> xmlIdAndCache = new HashMap<>();
    private HashMap<String, HashSet<CustomParameter>> cpCache = new HashMap<>();

    private String[] paramTables = {"accomplishments", "banks", "comfort", "deadline", "decoration", "developer",
            "district", "ecologicals", "externals", "internals", "material", "pay", "securities", "subway_station"};
    private HashMap<Integer, ResidentialCache> cache = new HashMap<>();
    /* Create local cache -- end */


    @Autowired
    private ResidentialRepository residentialRepository;

    @Override
    public ResidentialCache getById(Integer id) {
        checkUpdate();

        return cache.get(id);
    }

    @Override
    public List<ResidentialMin> getResidentialMin() {
        checkUpdate();

        List<ResidentialMin> res = new ArrayList<>();

        for (Map.Entry<Integer, ResidentialCache> item : cache.entrySet()) {
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

        for (Map.Entry<Integer, ResidentialCache> item : cache.entrySet()) {
            data.add(new DataToFastSelect(item.getValue().getName(), item.getValue().getId().toString()));
        }

        return data;
    }

    private void checkUpdate() {
        if (cacheParameter.isEmpty() || cacheIBlock.isEmpty() || cacheIFile.isEmpty() || cacheHouse.isEmpty() || cache.isEmpty()) {
            this.updater();
        }
    }

    private void insertToXmlCache(CustomParameter customParameter, ResidentialCache value) {
        if (value == null || customParameter == null) return;
        if (customParameter.getXmlId().isEmpty()) return;

        if (xmlIdAndCache.get(customParameter.getXmlId()) != null) {
            xmlIdAndCache.get(customParameter.getXmlId()).add(value);
        } else {
            HashSet<ResidentialCache> objects = new HashSet<>();
            objects.add(value);
            xmlIdAndCache.put(customParameter.getXmlId(), objects);
        }

        String key = customParameter.getXmlId().replaceAll("[0-9]", "");

        if (cpCache.get(key) != null) {
            cpCache.get(key).add(customParameter);
        } else {
            HashSet<CustomParameter> customParameters = new HashSet<>();
            customParameters.add(customParameter);
            cpCache.put(key, customParameters);
        }
    }

    private void updater() {
        updateParameters();

        List<Residential> residentialList = residentialRepository.findAll();

        for (Residential residential : residentialList) {
            ResidentialCache cacheItem = new ResidentialCache(residential);

            cacheItem.setDistrict(setParam(residential.getPROPERTY_1122()));
            if (cacheItem.getDistrict() == null) {
                error.add("Null district, element id: " + cacheItem.getId());
                continue;
            }

            cacheItem.setDeveloper(setParam(residential.getPROPERTY_1123()));
            if (cacheItem.getDeveloper() == null) {
                error.add("Null district, developer id: " + cacheItem.getId());
                continue;
            }

            cacheItem.setSubway(setParam(residential.getPROPERTY_1124()));
            cacheItem.setComfort(setParam(residential.getPROPERTY_1129()));

            cacheItem.setExternals(setParam(residential.getPROPERTY_1130(), "ex"));
            cacheItem.setInternals(setParam(residential.getPROPERTY_1131(), "in"));
            cacheItem.setEcologicals(setParam(residential.getPROPERTY_1132(), "eg"));
            cacheItem.setSecurities(setParam(residential.getPROPERTY_1133(), "sc"));
            cacheItem.setAccomplishments(setParam(residential.getPROPERTY_1134(), "ac"));

            // Пропуск не активных ЖК
            if (cacheIBlock.get(cacheItem.getId()) == null) {
                continue;
            }

            cacheItem.setDetailText(cacheIBlock.get(cacheItem.getId()).getDetailText());
            cacheItem.setPreviewText(cacheIBlock.get(cacheItem.getId()).getPreviewText());
            cacheItem.setName(cacheIBlock.get(cacheItem.getId()).getName());
            cacheItem.setImage(cacheIFile.get(cacheIBlock.get(cacheItem.getId()).getPreviewPicture()));
            cacheItem.setImageBig(cacheIFile.get(cacheIBlock.get(cacheItem.getId()).getDetailPicture()));

            List<HouseCache> houses = new ArrayList<>();
            for (Map.Entry<Integer, House> house : cacheHouse.entrySet()) {
                if (!house.getValue().getPROPERTY_1135().equals(cacheItem.getId())) continue;

                HouseCache houseOut = new HouseCache(house.getValue());
                houseOut.setDeadline(setParam(house.getValue().getPROPERTY_1138()));
                houseOut.setDecoration(setParam(house.getValue().getPROPERTY_1139()));
                houseOut.setMaterial(setParam(house.getValue().getPROPERTY_1140()));
                houseOut.setPayment(setParam(house.getValue().getPROPERTY_1141(), "pa"));
                houseOut.setBank(setParam(house.getValue().getPROPERTY_1142(), "bk"));

//
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

            List<Apartment> apartments = new ArrayList<>();
            for (Apartment ap : cacheApartment) {
                if (ap.getBuilding().equals(cacheItem.getName())) {
                    apartments.add(ap);
                }
            }

            cacheItem.setApartments(apartments);

            cache.put(cacheItem.getId(), cacheItem);

            /*Update xmlIdAndCache*/
            insertToXmlCache(cacheItem.getSubway(), cacheItem);
            insertToXmlCache(cacheItem.getDistrict(), cacheItem);
            insertToXmlCache(cacheItem.getDeveloper(), cacheItem);
            insertToXmlCache(cacheItem.getComfort(), cacheItem);
            for (CustomParameter cp : cacheItem.getSecurities()) {
                insertToXmlCache(cp, cacheItem);
            }
            for (CustomParameter cp : cacheItem.getEcologicals()) {
                insertToXmlCache(cp, cacheItem);
            }
            for (CustomParameter cp : cacheItem.getInternals()) {
                insertToXmlCache(cp, cacheItem);
            }
            for (CustomParameter cp : cacheItem.getExternals()) {
                insertToXmlCache(cp, cacheItem);
            }
            for (CustomParameter cp : cacheItem.getAccomplishments()) {
                insertToXmlCache(cp, cacheItem);
            }

            for (HouseCache house : cacheItem.getHouses()) {
                if(house.getBank() != null) {
                    for (CustomParameter cp : house.getBank()) {
                        insertToXmlCache(cp, cacheItem);
                    }
                }

                if(house.getPayment() != null) {
                    for (CustomParameter cp : house.getPayment()) {
                        insertToXmlCache(cp, cacheItem);
                    }
                }

                insertToXmlCache(house.getDeadline(), cacheItem);
                insertToXmlCache(house.getDecoration(), cacheItem);
                insertToXmlCache(house.getMaterial(), cacheItem);
            }
            /*End update xmlIdAndCache*/
        }

    }

    private List<String> filterSearchData(SearchData[] searchData, String pattern) {
        List<String> list = new ArrayList<>();

        for (SearchData sd : searchData) {
            if (sd.getValue().contains(pattern)) {
                list.add(sd.getValue());
            }
        }

        return list;
    }

    private List<Integer> filterSearchData(SearchData[] searchData) {
        List<Integer> list = new ArrayList<>();

        for (SearchData sd : searchData) {
            if (sd.getValue().matches("[0-9]+")) {
                list.add(Integer.valueOf(sd.getValue()));
            }
        }

        return list;
    }

    private List<ResidentialCache> unionList(List<ResidentialCache> result, Set<ResidentialCache> l2) {

        if (result == null) return null;
        if (result.isEmpty()) return null;

        List<ResidentialCache> l2List = new ArrayList<>();
        for (ResidentialCache item : l2) {
            l2List.add(item);
        }

        if (l2List.isEmpty()) return result;

        List<ResidentialCache> res = new ArrayList<>();

        HashMap<Integer, ResidentialCache> l1hm = new HashMap<>();
        for (ResidentialCache rc : result) {
            l1hm.put(rc.getId(), rc);
        }

        for (ResidentialCache rc : l2List) {
            if (rc.equals(l1hm.get(rc.getId()))) {
                res.add(rc);
            }
        }

        return res;
    }

    @Override
    public List<ResidentialMin> getResidentialMinBySearch(SearchData[] searchData) {
        checkUpdate();

        // все это гавнище нужно рефракторить, но похоже после релиза

        List<ResidentialCache> res = new ArrayList<>();

        for (Map.Entry<Integer, ResidentialCache> entry : cache.entrySet()) {
            res.add(entry.getValue());
        }

        Set<ResidentialCache> residential = new LinkedHashSet<>();

        for (Integer id : filterSearchData(searchData)) {
            residential.add(cache.get(id));
        }
        res = unionList(res, residential);

        String[] patterns = {"su", "dt", "dv", "cf", "sc", "eg", "in", "ex", "ac", "bk", "pa", "dl", "dc", "mt"};

        for (String pattern : patterns) {
            for (String item : filterSearchData(searchData, pattern)) {
                res = unionList(res, xmlIdAndCache.get(item));
            }
        }


        List<ResidentialMin> endRes = new ArrayList<>();
        for (ResidentialCache rc : res) {
            endRes.add(new ResidentialMin(rc));
        }


        return endRes;
    }

    @Override
    public List<ResidentialCache> findAll() {
        checkUpdate();

        List<ResidentialCache> residentialCaches = new ArrayList<>();
        for (Map.Entry<Integer, ResidentialCache> item : cache.entrySet()) {
            residentialCaches.add(item.getValue());
        }

        return residentialCaches;
    }

    private CustomParameter setParam(String xmlId) {
        if (cacheParameter.size() == 0) {
            updateParameters();
        }

        return cacheParameter.get(xmlId);
    }

    private List<CustomParameter> setParam(String param, String pattern) {
        if (param == null) return null;
        if (param.isEmpty()) return null;
        Pattern p = Pattern.compile(pattern + "(\\d*)");
        Matcher m = p.matcher(param);

        List<CustomParameter> res = new ArrayList<>();
        while (m.find()) {
            res.add(setParam(param.substring(m.start(), m.end())));
        }

        return res;
    }

    private void updateParameters() {
        try {
            String url = "jdbc:mysql://mdn1.timeweb.ru:3306/bgnsk_newbgnsk?characterEncoding=UTF-8&autoReconnect=true";
            Connection conn = DriverManager.getConnection(url, "bgnsk_newbgnsk", "3FM82BlmwL4uxd9jyiku");
            Statement stmt = conn.createStatement();

            /* updateParameters */
            ResultSet rs;
            for (String table : paramTables) {
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

            /*updateIBlock*/
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

            /* updateFile */
            rs = stmt.executeQuery("SELECT ID, SUBDIR, FILE_NAME FROM b_file");
            while (rs.next()) {
                IFile iFile = new IFile();
                iFile.setId(rs.getInt("ID"));
                iFile.setName(rs.getString("FILE_NAME"));
                iFile.setSubdir(rs.getString("SUBDIR"));

                cacheIFile.put(iFile.getId(), iFile);
            }

            /* updateHouse */
            rs = stmt.executeQuery("SELECT * FROM b_iblock_element_prop_s80");
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

            /* updateApartment */

            String outputFile = System.getProperty("user.dir") + "\\apartment.xml";

            //Check last modified date
            File file = new File(outputFile);
            SimpleDateFormat sdf = new SimpleDateFormat("dd");

            Calendar cal = Calendar.getInstance();
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);


            if (dayOfMonth != Integer.valueOf(sdf.format(file.lastModified()))) {
                DownloadFileFromUrl.DownloadFileFromUrl("http://mls-nsk.ru/files/newbuilding_export.yml", outputFile);
            }

            cacheApartment = NarXMLParser.parsingXmlFile(outputFile);

        } catch (Exception e) {
            System.err.println("updateParameters error! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public DataToFastSelect getParameterValue(DataToFastSelect data) {
        checkUpdate();

        if (data.getText().equals("bank")) {
            for (Map.Entry<Integer, ResidentialCache> rc : cache.entrySet()) {
                for (HouseCache hc : rc.getValue().getHouses()) {
                    if (hc.getId() == Integer.parseInt(data.getValue())) {
                        DataToFastSelect dtfs = new DataToFastSelect();
                        dtfs.setText(hc.banksToString());
                        return dtfs;
                    }
                }
            }
        }


        DataToFastSelect res = new DataToFastSelect();
        ResidentialCache item = cache.get(Integer.parseInt(data.getValue()));
        if (item == null) {
            return null;
        }

        switch (data.getText()) {
            case "detailText":
                res.setText(item.getDetailText());
                break;
            case "form1":
                res.setText(item.getForm1());
                break;
            case "form2":
                res.setText(item.getForm2());
                break;
            case "percent":
                res.setText(item.getPercent());
                break;
            case "files":
                res.setText(item.getFiles());
                break;
        }

        return res;
    }
}