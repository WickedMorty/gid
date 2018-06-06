package rest.service;

import bitrix.entity.ResidentialOut;

import java.util.List;

public interface ResidentialService {
    List<ResidentialOut> findAll();
}
