package com.uet.fingerpinter.mobilecontroller;

import com.uet.fingerpinter.Constants;
import com.uet.fingerpinter.manager.interf.UploadInfoService;
import com.uet.fingerpinter.model.input.gauss.PostReferencePointGaussRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.uet.fingerpinter.model.response.*;
import com.uet.fingerpinter.model.input.*;

import java.util.List;


@RestController
@RequestMapping(value = Constants.API_URL_UPLOAD_INFO_MOBILE)
public class UploadInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadInfoController.class);

    private UploadInfoService service;

    @Autowired
    public UploadInfoController(UploadInfoService service) {
        this.service = service;
    }

    @PostMapping(value = Constants.URL_POST_REFERENCE_POINT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
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
            if (service == null) {
                LOG.info("null service");
            } else {
                LOG.info("not null service");
            }
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

    @PostMapping(value = Constants.URL_POST_REFERENCE_POINT_GAUSS, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public BaseResponse<String> postReferencePointGauss(
            @RequestBody PostReferencePointGaussRequest postReferencePointGaussRequest
    ) {
        try {
            return service.postReferencePointGauss(postReferencePointGaussRequest);
        } catch (CustomExceptionResponse e) {
            return BaseResponse.exceptionResponse(e.getMessage(), e.getErrorCode());
        }
    }


}
