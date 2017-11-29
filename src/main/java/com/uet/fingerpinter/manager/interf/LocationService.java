package com.uet.fingerpinter.manager.interf;

import com.uet.fingerpinter.model.input.GetLocationRequest;
import com.uet.fingerpinter.model.input.gauss.ResponseFocus;
import com.uet.fingerpinter.model.response.BaseResponse;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;

import java.util.List;

public interface LocationService {
    BaseResponse<ResponseFocus> getLocation(GetLocationRequest request) throws CustomExceptionResponse;

    BaseResponse<List<GetLocationResponse>> getPath() throws CustomExceptionResponse;
}
