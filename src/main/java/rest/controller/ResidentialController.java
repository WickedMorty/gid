package rest.controller;

import bitrix.entity.HouseOut;
import bitrix.entity.ResidentialOut;
import bitrix.repository.param.*;
import rest.service.ResidentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/residential")
public class ResidentialController {

    @Autowired
    ResidentialService residentialService;

    private List<ResidentialOut> residentialCache = null;
    //private List<ResidentialOut> residentialMinCache = null;

    @GetMapping("/all")
    public List<ResidentialOut> getAll() {
        if(residentialCache == null) {
            residentialCache = residentialService.findAll();

            //residentialMinCache = residentialCache;

//            for (ResidentialOut residential: residentialMinCache) {
//                residential.toMin();
//            }
        }

//        return residentialMinCache;
        return residentialCache;
    }

    @GetMapping("/searchparam/")
    public List<ResidentialOut> searchByParam(
            @RequestParam(required = false, value="districts[]") String[] districts,
            @RequestParam(required = false, value="developers[]") String[] developers,
            @RequestParam(required = false, value="residentials[]") String[] residentials,
            @RequestParam(required = false, value="completion_decade[]") String[] completion_decade,
            @RequestParam(required = false, value="payment_methods[]") String[] payment_methods,
            @RequestParam(required = false, value="decorations[]") String[] decorations,
            @RequestParam(required = false, value="metro[]") String[] metro,
            @RequestParam(required = false, value="materials[]") String[] materials,
            @RequestParam(required = false, value="banks[]") String[] banks,
            @RequestParam(required = false, value="internal[]") String[] internal,
            @RequestParam(required = false, value="external[]") String[] external,
            @RequestParam(required = false, value="has_underground_parking") Integer parking,
            @RequestParam(required = false, value="ecologic[]") String[] ecologic,
            @RequestParam(required = false, value="securities[]") String[] securities,
            @RequestParam(required = false, value="comfort_classes[]") String[] comfort_classes,
            @RequestParam(required = false, value="accomplishments[]") String[] accomplishments
    ) {
        if(residentialCache == null) {
            residentialCache = residentialService.findAll();
        }

        List<ResidentialOut> residentialOutList = new ArrayList<>();

        for (ResidentialOut residential: residentialCache) {
            boolean bDistricts = true;
            boolean bDevelopers = true;
            boolean bResidentials = true;
            boolean bCompletion_decade = true;
            boolean bPayment_methods = true;
            boolean bDecorations = true;
            boolean bMetro = true;
            boolean bMaterials = true;
            boolean bBanks = true;
            boolean bInternal = true;
            boolean bExternal = true;
            boolean bParking = true;
            boolean bEcologic = true;
            boolean bSecurities = true;
            boolean bComfort_classes = true;
            boolean bAccomplishments = true;

            if(districts != null) {
                bDistricts = false;
                for (String item: districts) {
                    item = URLDecoder.decode(item);
                    if(residential.getDistrict() != null && item != null) {
                        if (residential.getDistrict().equals(item)) {
                            bDistricts = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(developers != null) {
                bDevelopers = false;
                for (String item: developers) {
                    item = URLDecoder.decode(item);
                    if(residential.getDeveloper() != null && item != null) {
                        if (residential.getDeveloper().equals(item)) {
                            bDevelopers = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(residentials != null) {
                bResidentials = false;
                for (String item: residentials) {
                    item = URLDecoder.decode(item);
                    if(residential.getName() != null && item != null) {
                        if (residential.getName().equals(item)) {
                            bResidentials = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(completion_decade != null) {
                bCompletion_decade = false;
                for(String item: completion_decade) {
                    item = URLDecoder.decode(item);
                    if(residential.getDeadlin() != null && item != null) {
                        if (residential.getDeadlin().equals(item)) {
                            bCompletion_decade = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(payment_methods != null) {
                bPayment_methods = false;
                for(String item: payment_methods) {
                    item = URLDecoder.decode(item);
                    if(residential.getHouses() != null && item != null) {
                        for (HouseOut house : residential.getHouses()) {
                            if(house != null && house.getPayment() != null) {
                                for (String pay : house.getPayment()) {
                                    if(pay != null) {
                                        if (pay.equals(item)) {
                                            bPayment_methods = true;
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(banks != null) {
                bBanks = false;
                for(String item: banks) {
                    item = URLDecoder.decode(item);
                    if(residential.getHouses() != null && item != null) {
                        for (HouseOut house : residential.getHouses()) {
                            for (String bank : house.getBank()) {
                                if (bank.equals(item)) {
                                    bBanks = true;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(decorations != null) {
                bDecorations = false;
                for(String item: decorations) {
                    item = URLDecoder.decode(item);
                    if(residential.getHouses() != null && item != null) {
                        for (HouseOut house : residential.getHouses()) {
                            if (house.getDecoration().equals(item)) {
                                bDecorations = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(metro != null) {
                bMetro = false;
                for(String item: metro) {
                    item = URLDecoder.decode(item);
                    if(residential.getSubway() != null && item != null) {
                        if (residential.getSubway().equals(item)) {
                            bMetro = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(materials != null) {
                bMaterials = false;
                for(String item: materials) {
                    item = URLDecoder.decode(item);
                    if(residential.getHouses() != null && item != null) {
                        for (HouseOut house : residential.getHouses()) {
                            if (house.getMaterial().equals(item)) {
                                bMaterials = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(internal != null) {
                bInternal = false;
                for(String item: internal) {
                    item = URLDecoder.decode(item);
                    if(residential.getInternals() != null && item != null) {
                        for (String inter : residential.getInternals()) {
                            if (inter.equals(item)) {
                                bInternal = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(external != null) {
                bExternal = false;
                for(String item: external) {
                    item = URLDecoder.decode(item);
                    if(residential.getExternals() != null && item != null) {
                        for (String exter : residential.getExternals()) {
                            if (exter.equals(item)) {
                                bExternal = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(ecologic != null) {
                bEcologic = false;
                for(String item: ecologic) {
                    item = URLDecoder.decode(item);
                    if(residential.getEcologicals() != null && item != null) {
                        for (String eco : residential.getEcologicals()) {
                            if (eco.equals(item)) {
                                bEcologic = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(securities != null) {
                bSecurities = false;
                for(String item: securities) {
                    item = URLDecoder.decode(item);
                    if(residential.getSecurities() != null && item != null) {
                        for (String sec : residential.getSecurities()) {
                            if (sec.equals(item)) {
                                bSecurities = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(accomplishments != null) {
                bAccomplishments = false;
                for(String item: accomplishments) {
                    item = URLDecoder.decode(item);
                    if(residential.getAccomplishments() != null && item != null) {
                        for (String aco : residential.getAccomplishments()) {
                            if (aco.equals(item)) {
                                bAccomplishments = true;
                                continue;
                            }
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            if(comfort_classes != null) {
                bComfort_classes = false;
                for(String item: comfort_classes) {
                    item = URLDecoder.decode(item);
                    if(residential.getComfort() != null && item != null) {
                        if (residential.getComfort().equals(item)) {
                            bComfort_classes = true;
                            continue;
                        }
                    }
                }
            }

            if(!bDistricts || !bDevelopers || !bResidentials || !bCompletion_decade || !bPayment_methods || !bDecorations || !bMetro || !bMaterials || !bBanks || !bInternal || !bExternal || !bParking || !bEcologic || !bSecurities || !bComfort_classes || !bAccomplishments) {
                continue;
            }

            residentialOutList.add(residential);
        }

        return residentialOutList;
    }


    @GetMapping("/search/{body}")
    public List<ResidentialOut> search(@PathVariable final String body) {

        String body2 = URLDecoder.decode(body);

        if(residentialCache == null) {
            residentialCache = residentialService.findAll();

//            residentialMinCache = residentialCache;

//            for (ResidentialOut residential: residentialMinCache) {
//                residential.toMin();
//            }
        }

        List<ResidentialOut> residentialOutList = new ArrayList<>();

        for (ResidentialOut residential: residentialCache ) {
            if(residential.search(body2)) {
                //residential.toMin();

                residentialOutList.add(residential);
            }
        }

        return residentialOutList;
    }

    @GetMapping("/id/{id}")
    public ResidentialOut getBuId(@PathVariable final Integer id) {
        if(residentialCache == null) {
            residentialCache = residentialService.findAll();
        }

        for (ResidentialOut residential: residentialCache) {
            if(residential.getId().equals(id)) {
                return residential;
            }
        }

        return null;
    }
}
