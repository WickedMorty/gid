package rest.service;

import bitrix.entity.Residential;
import rest.entity.ResidentialCache;
import rest.entity.controller.DataToFastSelect;
import rest.entity.controller.ResidentialMin;
import rest.entity.controller.SearchData;

import java.util.List;

public interface ResidentialService {
    List<ResidentialCache> findAll();
    List<DataToFastSelect> getAllParam();
    List<ResidentialMin> getResidentialMin();
    List<ResidentialMin> getResidentialMinBySearch(SearchData[] searchData);
}
