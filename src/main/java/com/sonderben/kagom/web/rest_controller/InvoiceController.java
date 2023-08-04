package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.AddressEntity;
import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.service.AddressService;
import com.sonderben.kagom.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "addresses")
public class AddressController extends BaseControllerImpl<AddressEntity,AddressService> {



}