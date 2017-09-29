package com.uet.fingerpinter.mobilecontroller;

import com.uet.fingerpinter.Constants;
import com.uet.fingerpinter.manager.interf.UploadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.uet.fingerpinter.model.response.*;
import com.uet.fingerpinter.model.input.*;

import java.util.List;


@RestController
@RequestMapping(value = Constants.API_URL_UPLOAD_INFO_MOBILE)
public class UploadInfoController {

    private UploadInfoService service;

    @Autowired
    public UploadInfoController(UploadInfoService service) {
        this.service = service;
    }

    @PostMapping(value = Constants.URL_POST_REFERENCE_POINT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces =  {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public BaseResponse postReferencePoint(
            @RequestBody PostReferencePointRequest body
    ) {
        try {
            return service.postReferencePoint(body.getBuildingId(), body.getRoomId(), body.getX(), body.getY(), body.getInfos());
        } catch (CustomExceptionResponse e) {
            return BaseResponse.exceptionResponse(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = Constants.URL_GET_INFO_BUILDING)
    public BaseResponse getInfoBuilding() {
        try {
            return service.getInfoBuilding();
        } catch (CustomExceptionResponse e) {
            return BaseResponse.exceptionResponse(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = Constants.URL_GET_INFO_ROOM)
    public BaseResponse<List<RoomModel>> getInfoRoom(
            @RequestParam(value = "buildingId") int buidingId
    ) {
        try {
            return service.getInfoRoom(buidingId);
        } catch (CustomExceptionResponse e) {
            return BaseResponse.exceptionResponse(e.getMessage(), e.getErrorCode());
        }
    }
}
