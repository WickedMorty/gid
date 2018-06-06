package rest.service.impl;

import bitrix.entity.*;
import bitrix.entity.param.*;
import bitrix.repository.HouseRespository;
import bitrix.repository.IFileRepository;
import bitrix.repository.IblockRepository;
import bitrix.repository.ResidentialRepository;
import bitrix.repository.param.*;
import rest.service.ResidentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResidentialServiceImpl implements ResidentialService {


    @Autowired
    private ResidentialRepository residentialRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private SubwayStationRepository subwayStationRepository;

    @Autowired
    private ComfortRepository comfortRepository;

    @Autowired
    private ExternalsRepository externalsRepository;

    @Autowired
    private InternalsRepository internalsRepository;

    @Autowired
    private EcologicalsRepository ecologicalsRepository;

    @Autowired
    private SecuritiesRepository securitiesRepository;

    @Autowired
    private AccomplishmentsRepository accomplishmentsRepository;

    @Autowired
    private IblockRepository iblockRepository;

    @Autowired
    private IFileRepository iFileRepository;

    @Autowired
    private HouseRespository houseRespository;

    @Autowired
    private DeadlineRepository deadlineRepository;

    @Autowired
    private DecorationRepository decorationRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private BanksRepository banksRepository;

    @Override
    public List<ResidentialOut> findAll() {
        List<District> districtList = districtRepository.findAll();
        List<Developer> developerList = developerRepository.findAll();
        List<SubwayStation> subwayStationList = subwayStationRepository.findAll();
        List<Comfort> comfortList = comfortRepository.findAll();
        List<Externals> externalsList = externalsRepository.findAll();
        List<Internals> internalsList = internalsRepository.findAll();
        List<Ecologicals> ecologicalsList = ecologicalsRepository.findAll();
        List<Securities> securityList = securitiesRepository.findAll();
        List<Accomplishments> accomplishmentsList = accomplishmentsRepository.findAll();
        List<Deadline> deadlineList = deadlineRepository.findAll();
        List<Decoration> decorationList = decorationRepository.findAll();
        List<Material> materialList = materialRepository.findAll();
        List<Pay> payList = payRepository.findAll();
        List<Banks> banksList = banksRepository.findAll();


        List<Residential> residentialList = residentialRepository.findAll();
        List<ResidentialOut> residentialOutList = new ArrayList<>();

        for (Residential residential: residentialList) {
            ResidentialOut residentialOut = new ResidentialOut();
            residentialOut.setId(residential.getIBLOCK_ELEMENT_ID());
            residentialOut.setAddress(residential.getPROPERTY_1121());
            residentialOut.setCoordinates(residential.getPROPERTY_1120());
            residentialOut.setDistrict(residential.getPROPERTY_1122());
            residentialOut.setForm1(residential.getPROPERTY_1143());
            residentialOut.setForm2(residential.getPROPERTY_1144());

            if(residential.getPROPERTY_1122() != null) {
                for (District district: districtList) {
                    if(residential.getPROPERTY_1122().equals(district.getXmlId())) {
                        residentialOut.setDistrict(district.getName());
                    }
                }
            }

            if(residential.getPROPERTY_1123() != null) {
                for (Developer developer: developerList) {
                    if(residential.getPROPERTY_1123().equals(developer.getXmlId())) {
                        residentialOut.setDeveloper(developer.getName());
                    }
                }
            }

            if(residential.getPROPERTY_1124() != null) {
                for (SubwayStation subwayStation: subwayStationList) {
                    if(residential.getPROPERTY_1124().equals(subwayStation.getXmlId())) {
                        residentialOut.setSubway(subwayStation.getName());
                    }
                }
            }

            residentialOut.setSubwayMin(residential.getPROPERTY_1125());
            residentialOut.setBus(residential.getPROPERTY_1126());
            residentialOut.setBusMin(residential.getPROPERTY_1127());
            residentialOut.setParking(residential.getPROPERTY_1128());

            if(residential.getPROPERTY_1129() != null) {
                for (Comfort comfort: comfortList) {
                    if(residential.getPROPERTY_1129().equals(comfort.getXmlId())) {
                        residentialOut.setComfort(comfort.getName());
                    }
                }
            }

            if(residential.getPROPERTY_1130() != null) {
                Pattern p = Pattern.compile("ex(\\d*)");
                Matcher m = p.matcher(residential.getPROPERTY_1130());

                List<String> test = new ArrayList<>();
                while(m.find()) {
                    for (Externals externals: externalsList) {
                        if(residential.getPROPERTY_1130().substring(m.start(), m.end()).equals(externals.getXmlId())) {
                            test.add(externals.getName());
                        }
                    }
                }

                residentialOut.setExternals(test);
            }

            if(residential.getPROPERTY_1131() != null) {
                Pattern p = Pattern.compile("in(\\d*)");
                Matcher m = p.matcher(residential.getPROPERTY_1131());

                List<String> test = new ArrayList<>();
                while(m.find()) {
                    for (Internals internals: internalsList) {
                        if(residential.getPROPERTY_1131().substring(m.start(), m.end()).equals(internals.getXmlId())) {
                            test.add(internals.getName());
                        }
                    }
                }

                residentialOut.setInternals(test);
            }

            if(residential.getPROPERTY_1132() != null) {
                Pattern p = Pattern.compile("eg(\\d*)");
                Matcher m = p.matcher(residential.getPROPERTY_1132());

                List<String> test = new ArrayList<>();
                while(m.find()) {
                    for (Ecologicals ecologicals: ecologicalsList) {
                        if(residential.getPROPERTY_1132().substring(m.start(), m.end()).equals(ecologicals.getXmlId())) {
                            test.add(ecologicals.getName());
                        }
                    }
                }

                residentialOut.setEcologicals(test);
            }

            if(residential.getPROPERTY_1133() != null) {
                Pattern p = Pattern.compile("sc(\\d*)");
                Matcher m = p.matcher(residential.getPROPERTY_1133());

                List<String> test = new ArrayList<>();
                while(m.find()) {
                    for (Securities securities: securityList) {
                        if(residential.getPROPERTY_1133().substring(m.start(), m.end()).equals(securities.getXmlId())) {
                            test.add(securities.getName());
                        }
                    }
                }

                residentialOut.setSecurities(test);
            }

            if(residential.getPROPERTY_1134() != null) {
                Pattern p = Pattern.compile("ac(\\d*)");
                Matcher m = p.matcher(residential.getPROPERTY_1134());

                List<String> test = new ArrayList<>();
                while(m.find()) {
                    for (Accomplishments accomplishments: accomplishmentsList) {
                        if(residential.getPROPERTY_1134().substring(m.start(), m.end()).equals(accomplishments.getXmlId())) {
                            test.add(accomplishments.getName());
                        }
                    }
                }

                residentialOut.setAccomplishments(test);
            }

            Iblock iblock = iblockRepository.findOne(residentialOut.getId());
            if(iblock != null) {
                if(iblock.getActive().equals("N")) {
                    continue;
                }
                residentialOut.setDetailText(iblock.getDetailText());
                residentialOut.setPreviewText(iblock.getPreviewText());
                residentialOut.setName(iblock.getName());
                if(iblock.getPreviewPicture() != null) {
                    IFile iFile1 = iFileRepository.findOne(iblock.getPreviewPicture());
                    if (iFile1 != null) {
                        residentialOut.setImage(iFile1.getSubdir() + "/" + iFile1.getName());
                    }
                }

                if(iblock.getDetailPicture() != null) {
                    IFile iFile2 = iFileRepository.findOne(iblock.getDetailPicture());
                    if (iFile2 != null) {
                        residentialOut.setImageBig(iFile2.getSubdir() + "/" + iFile2.getName());
                    }
                }
            }


            List<House> houseList = houseRespository.findDeadline(residentialOut.getId());
            if(houseList != null && houseList.size() > 0) {
                for (Deadline deadline: deadlineList) {
                        if(houseList.get(0).getPROPERTY_1138().equals(deadline.getXmlId())) {
                            if(deadline.getName() != null) {
                                residentialOut.setDeadlin(deadline.getName());
                            }
                        }
                }
            }

            houseList = houseRespository.findFloor(residentialOut.getId());
            if(houseList != null && houseList.size() > 0) {
                if(houseList.get(0).getPROPERTY_1137() != null && houseList.get(0).getPROPERTY_1137() > 0) {
                    residentialOut.setFloor(houseList.get(0).getPROPERTY_1137());
                }
            }

            List<HouseOut> houseOutList = new ArrayList<>();
            for (House house: houseList) {
                HouseOut houseOut = new HouseOut();
                houseOut.setId(house.getId());
                houseOut.setName(house.getPROPERTY_1136());
                houseOut.setFloor(house.getPROPERTY_1137());
                for (Deadline deadline: deadlineList) {
                    if(house.getPROPERTY_1138().equals(deadline.getXmlId())) {
                        if(deadline.getName() != null) {
                            houseOut.setDeadline(deadline.getName());
                        }
                    }
                }
                for (Decoration decoration: decorationList) {
                    if(house.getPROPERTY_1139().equals(decoration.getXmlId())) {
                        if(decoration.getName() != null) {
                            houseOut.setDecoration(decoration.getName());
                        }
                    }
                }
                for (Material material: materialList) {
                    if(house.getPROPERTY_1140() != null && material.getXmlId() != null) {
                        if(house.getPROPERTY_1140().equals(material.getXmlId())) {
                            if(material.getName() != null) {
                                houseOut.setMaterial(material.getName());
                            }
                        }
                    }
                }

                if(house.getPROPERTY_1141() != null) {
                    Pattern p = Pattern.compile("pa(\\d*)");
                    Matcher m = p.matcher(house.getPROPERTY_1141());

                    List<String> test = new ArrayList<>();
                    while(m.find()) {
                        for (Pay pay: payList) {
                            if(house.getPROPERTY_1141().substring(m.start(), m.end()).equals(pay.getXmlId())) {
                                test.add(pay.getName());
                            }
                        }
                    }

                    houseOut.setPayment(test);
                }

                if(house.getPROPERTY_1142() != null) {
                    Pattern p = Pattern.compile("bk(\\d*)");
                    Matcher m = p.matcher(house.getPROPERTY_1142());

                    List<String> test = new ArrayList<>();
                    while(m.find()) {
                        for (Banks banks: banksList) {
                            if(house.getPROPERTY_1142().substring(m.start(), m.end()).equals(banks.getXmlId())) {
                                test.add(banks.getName());
                            }
                        }
                    }

                    houseOut.setBank(test);
                }

                houseOutList.add(houseOut);
            }

            residentialOut.setHouses(houseOutList);

            residentialOutList.add(residentialOut);
        }


        
        return residentialOutList;
    }
}
