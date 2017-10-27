package com.uet.fingerpinter.manager;

import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.manager.interf.UploadInfoService;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.uet.fingerpinter.model.response.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uet.fingerpinter.db.Tables.BUILDING;
import static com.uet.fingerpinter.db.Tables.FINGERPRINTER_INFO;
import static com.uet.fingerpinter.db.Tables.ROOM;


@Service
public class UploadInfoManager implements UploadInfoService {
    private DSLContext ktv;

    @Autowired
    public UploadInfoManager(@Qualifier(value = "dslContext") DSLContext ktv) {
        this.ktv = ktv;
    }

    @Override
    public BaseResponse<String> postReferencePoint(int buildingId, int roomId, int x, int y, List<InfoReferencePointRequest> infos) throws CustomExceptionResponse {
        if (!ktv.fetchExists(BUILDING.join(ROOM).on(BUILDING.ID.eq(ROOM.BUILDING_ID)),
                BUILDING.ID.eq(buildingId).and(ROOM.ID.eq(roomId)))) {
            throw new CustomExceptionResponse("builing hoặc room không tồn tại", CustomExceptionResponse.ERROR_PARAM);
        }
        for (InfoReferencePointRequest info : infos) {
            FingerprinterInfoRecord record = ktv.selectFrom(FINGERPRINTER_INFO)
                    .where(
                            FINGERPRINTER_INFO.X.eq(x)
                                    .and(FINGERPRINTER_INFO.Y.eq(y))
                                    .and(FINGERPRINTER_INFO.ROOM_ID.eq(roomId))
                                    .and(FINGERPRINTER_INFO.MAC_ADDRESS.eq(info.getMacAddress()))
                                    .and(FINGERPRINTER_INFO.BUILDING_ID.eq(buildingId))
                    )
                    .fetchAny();
            if (record == null) {
                ktv.insertInto(FINGERPRINTER_INFO,
                        FINGERPRINTER_INFO.AP_NAME, FINGERPRINTER_INFO.MAC_ADDRESS, FINGERPRINTER_INFO.RSS, FINGERPRINTER_INFO.ROOM_ID, FINGERPRINTER_INFO.BUILDING_ID, FINGERPRINTER_INFO.X, FINGERPRINTER_INFO.Y)
                        .values(info.getName(), info.getMacAddress(), (double) info.getRss(), roomId, buildingId, x, y)
                        .execute();
            } else {
                ktv.update(FINGERPRINTER_INFO)
                        .set(FINGERPRINTER_INFO.RSS, record.getRss() * 0.9f + info.getRss() * 0.1f)
                        .execute();
            }

        }

        return new BaseResponse<>("success");
    }

    @Override
    public BaseResponse<List<BuildingModel>> getInfoBuilding() throws CustomExceptionResponse {
        return new BaseResponse<>(
                ktv.selectFrom(BUILDING)
                        .fetch()
                        .map(record -> {
                            BuildingModel buildingModel = new BuildingModel();
                            buildingModel.setBuildingId(record.getId());
                            buildingModel.setBuildingAddress(record.getBuildingAddress());
                            buildingModel.setBuildingName(record.getBuildingName());
                            return buildingModel;
                        })
        );
    }

    @Override
    public BaseResponse<List<RoomModel>> getInfoRoom(int buidingId) throws CustomExceptionResponse {
        if (!ktv.fetchExists(BUILDING, BUILDING.ID.eq(buidingId))) {
            throw new CustomExceptionResponse("not found this buiding", CustomExceptionResponse.ERROR_PARAM);
        }
        return new BaseResponse<>(
                ktv.selectFrom(ROOM).where(ROOM.BUILDING_ID.eq(buidingId))
                        .fetch()
                        .map(record -> {
                            RoomModel model = new RoomModel();
                            model.setBuildingId(record.getBuildingId());
                            model.setRoomId(record.getId());
                            model.setRoomName(record.getRoomName());
                            return model;
                        })
        );
    }
}
