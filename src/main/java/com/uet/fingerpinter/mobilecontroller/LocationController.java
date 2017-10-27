package com.uet.fingerpinter.mobilecontroller;

import com.uet.fingerpinter.Constants;
import com.uet.fingerpinter.manager.interf.LocationService;
import com.uet.fingerpinter.model.input.GetLocationRequest;
import com.uet.fingerpinter.model.response.BaseResponse;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.API_URL_GET_LOCATION)
public class LocationController {
    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping(value = Constants.URL_GET_LOCATION)
    @ResponseBody
    public BaseResponse<GetLocationResponse> getLocation(@RequestBody GetLocationRequest request) {
        try {
            return service.getLocation(request);
        } catch (CustomExceptionResponse e) {
            return BaseResponse.exceptionResponse(e.getMessage(), e.getErrorCode());
        }

    }

    @GetMapping(value = "get")
    public String get(){
        return "ahihi";
    }
}
