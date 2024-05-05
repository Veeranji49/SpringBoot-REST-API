package com.example.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.system.SystemInfo;
import com.example.Service.system.SystemInfoService;

@RestController
@RequestMapping("/api/v1/system-details")
//@Api(tags = "SystemInfo Controller", description = "To checking System Resources")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    private static final Logger logger = LoggerFactory.getLogger(SystemInfoController.class);

    @GetMapping(value="/system-msg")
    //@ApiOperation("Greeting message by system")
    public String msg()
    {
        return "Welcome to the Information of the System";
    }

    @GetMapping("/systemInfo")
    //@ApiOperation("To Retrieving the system resources")
    public SystemInfo getSystemInfo() {
        return systemInfoService.getSystemInfo();
    }
}