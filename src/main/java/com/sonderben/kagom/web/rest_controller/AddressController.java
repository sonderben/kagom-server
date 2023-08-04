package com.sonderben.kagom.web.rest_controller;

import com.sonderben.kagom.entity.CustomerEntity;
import com.sonderben.kagom.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "customers")
public class CustomerController extends BaseControllerImpl<CustomerEntity,CustomerService> {



}