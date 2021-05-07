package ank.cowin.controller;

import ank.cowin.model.Sessions;
import ank.cowin.service.CowinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
     * Returns the List of Sessions object for Age Limit 18-44 on that date in those areas given by pincodes.
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
