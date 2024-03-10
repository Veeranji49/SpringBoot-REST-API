package com.example.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.SystemInfo;
import com.example.Service.SystemInfoService;

@RestController
@RequestMapping("/api/v1/system-details")
@Api(tags = "SystemInfo Controller", description = "To checking System Resources")
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @GetMapping(value="/system-msg")
    @ApiOperation("Greeting message by system")
    public String msg()
    {
        return "Welcome to the Information of the System";
    }

    @GetMapping("/systemInfo")
    @ApiOperation("To Retrieving the system resources")
    public SystemInfo getSystemInfo() {
        return systemInfoService.getSystemInfo();
    }
}