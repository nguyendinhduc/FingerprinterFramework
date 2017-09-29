package com.uet.fingerpinter.manager.interf;

import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.response.*;

import java.util.List;

public interface UploadInfoService {
    BaseResponse<String> postReferencePoint(int buildingId, int roomId, int x, int y, List<InfoReferencePointRequest> infos) throws CustomExceptionResponse;

    BaseResponse<List<BuildingModel>> getInfoBuilding() throws CustomExceptionResponse;

    BaseResponse<List<RoomModel>> getInfoRoom(int buidingId) throws CustomExceptionResponse;
}
