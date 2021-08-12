package ank.cowin.controller;

import ank.cowin.model.Sessions;
import ank.cowin.service.CowinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cowin")
public class CowinController {

    @Autowired
    private CowinService cowinService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        System.out.println("HELLO!!");
        return "HELLO!!";
    }

    @ResponseBody
    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public Map<String, String> getSystemDetails(){

        Map<String, String> map = new HashMap<>();
        SystemInfo sysInfo = new SystemInfo();
        map.put("OS", sysInfo.getOperatingSystem().toString());
        HardwareAbstractionLayer layer = sysInfo.getHardware();

        map.put("HardwareUUID", layer.getComputerSystem().getHardwareUUID());
        map.put("Manufacturer", layer.getComputerSystem().getManufacturer());
        map.put("Model", layer.getComputerSystem().getModel());
        map.put("Firmware", layer.getComputerSystem().getFirmware().getName());

        map.put("TotalMemory", (layer.getMemory().getTotal()/(1024*1024))+"KB");
        map.put("AvailableMemory", layer.getMemory().getAvailable()/(1024*1024)+"KB");
        map.put("PhysicalMemory", layer.getMemory().getPhysicalMemory().toString());
        map.put("VirtualMemory", layer.getMemory().getVirtualMemory().toString());

        try {
            Process pc = Runtime.getRuntime().exec("sc query \"wuauserv\"");
            BufferedReader reader=new BufferedReader(new InputStreamReader(pc.getInputStream()));

            String line;
            while((line = reader.readLine())!=null) {
                System.out.println(line);
                if (line.trim().startsWith("STATE")) {
                    map.put("Service_wuauserv", line);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Returns the Sessions object and print the number of active sessions on that date in that area given by pincode.
     * Request Example: http://localhost:8083/cowin/pin=221001/date=07-05-2021
     * @param pincode
     * @param date
     */
    @ResponseBody
    @RequestMapping(value="pin={pincode}/date={date}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sessions getAvailabilityByPinAndDate(@PathVariable String pincode, @PathVariable String date){
        Sessions sessions = null;
        try {
            sessions = cowinService.getAvailabilityByPin(pincode, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    /**
     * Returns the List of Sessions object for Age Limit 18-44 on that date in those areas given by pincodes.
     * Request Example: http://localhost:8083/cowin/young/pins=221001,221002,221003,221004,221005/date=07-05-2021
     * @param pincodes
     * @param date
     */
    @ResponseBody
    @RequestMapping(value="/young/pins={pincodes}/date={date}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sessions getAvailabilityByPinAndDateForYoung(@PathVariable String[] pincodes, @PathVariable String date){
        Sessions sessions = null;
        try {
            sessions = cowinService.getAvailableSlotForYoung(pincodes, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    /**
     * Returns the List of Sessions object for Age Limit 45+ on that date in those areas given by pincodes.
     * Request Example: http://localhost:8083/cowin/old/pins=221001,221002,221003,221004,221005/date=07-05-2021
     * @param pincodes
     * @param date
     */
    @ResponseBody
    @RequestMapping(value="/old/pins={pincodes}/date={date}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Sessions getAvailabilityByPinAndDateForOld(@PathVariable String[] pincodes, @PathVariable String date){
        Sessions sessions = null;
        try {
            sessions = cowinService.getAvailableSlotForOld(pincodes, date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }
}
