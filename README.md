# CowinCo
Using cowin goverment api to list all the available slots on given date in particular area for Young and Old people.

Services:
 1. getAvailabilityByPinAndDate  
 Description: Get the available slots on a give date for a given pincode.   
 Endpoint: cowin/pin=<pin>/date=<date>  
 e.g. http://localhost:8083/cowin/pin=221001/date=07-05-2021  
 
 2. getAvailabilityByPinAndDateForYoung  
 Description: Get the available slots for Young (age between 18 to 45) on a given date for a list of given pincodes.  
 Endpoint: cowin/young/pins=pincode1,pincode2,pincode3/date=date1  
 e.g. http://localhost:8083/cowin/young/pins=221001,221002,221003,221004,221005/date=07-05-2021  
 
 3. getAvailabilityByPinAndDateForOld  
 Description: Get the available slots for Old (age 45+) on a given date for a list of given pincodes.  
 Endpoint: cowin/young/pins=pincode1,pincode2,pincode3/date=date1  
 e.g. http://localhost:8083/cowin/young/pins=221001,221002,221003,221004,221005/date=07-05-2021  

Tech Stack:  
 - Java 11
 - Spring boot 2.3.1_RELEASE
 - Apache HTTPClient 4.5.13
 - Maven 3.2.1

Reference: https://apisetu.gov.in/public/api/cowin#/
