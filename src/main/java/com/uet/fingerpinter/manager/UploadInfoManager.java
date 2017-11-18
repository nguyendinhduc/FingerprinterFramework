package com.uet.fingerpinter.manager;

import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.jooq.type.TypeFingerprinterInfo;
import com.uet.fingerpinter.manager.interf.UploadInfoService;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.input.gauss.ItemPostReferencePointGaussRequest;
import com.uet.fingerpinter.model.input.gauss.PostReferencePointGaussRequest;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.uet.fingerpinter.model.response.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.uet.fingerpinter.db.Tables.*;


@Service
public class UploadInfoManager implements UploadInfoService {
    private static final Logger LOG = LoggerFactory.getLogger("UploadInfoManager");
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
        List<BuildingModel> buildingModels =
                ktv.selectFrom(BUILDING)
                        .fetch()
                        .map(record -> {
                            BuildingModel buildingModel = new BuildingModel();
                            buildingModel.setBuildingId(record.getId());
                            buildingModel.setBuildingAddress(record.getBuildingAddress());
                            buildingModel.setBuildingName(record.getBuildingName());
                            return buildingModel;
                        });
        BuildingModel buildingModel = buildingModels.remove(buildingModels.size() - 1);
        buildingModels.add(0, buildingModel);
        return new BaseResponse<>(buildingModels);
    }

    @Override
    public BaseResponse<List<RoomModel>> getInfoRoom(int buidingId) throws CustomExceptionResponse {
        if (!ktv.fetchExists(BUILDING, BUILDING.ID.eq(buidingId))) {
            throw new CustomExceptionResponse("not found this buiding", CustomExceptionResponse.ERROR_PARAM);
        }
        List<RoomModel> roomModels = ktv.selectFrom(ROOM).where(ROOM.BUILDING_ID.eq(buidingId))
                .fetch()
                .map(record -> {
                    RoomModel model = new RoomModel();
                    model.setBuildingId(record.getBuildingId());
                    model.setRoomId(record.getId());
                    model.setRoomName(record.getRoomName());
                    return model;
                });
        Collections.reverse(roomModels);
        return new BaseResponse<>(
                roomModels
        );
    }

    @Override
    public BaseResponse<String> postReferencePointGauss(PostReferencePointGaussRequest postReferencePointGaussRequest) throws CustomExceptionResponse {
        postReferencePointGaussRequest.getItemPostReferencePointGaussRequests().sort((o1, o2) -> Integer.compare(o2.getListRss().size(), o1.getListRss().size()));
//        int maxNumber = postReferencePointGaussRequest.getItemPostReferencePointGaussRequests().get(0).getListRss().size();
        OK ok = new OK();
        ok.message = "Error";
        ktv.transaction(configuration -> {
            DSLContext dslContext = DSL.using(configuration);
            int min = 4;
            postReferencePointGaussRequest.setItemPostReferencePointGaussRequests(
                    postReferencePointGaussRequest.getItemPostReferencePointGaussRequests().stream()
                            .filter(res -> {
                                if (res.getListRss().size() < min) {
                                    return false;
                                } else {
                                    return true;
                                }
                            }).collect(Collectors.toList())
            );
            for (ItemPostReferencePointGaussRequest item : postReferencePointGaussRequest.getItemPostReferencePointGaussRequests()) {
                double mean = 0.0;
                double deviation = 0.0;
                if (dslContext.fetchExists(FINGERPRINTER_INFO_GAUSS,
                        FINGERPRINTER_INFO_GAUSS.X.eq(postReferencePointGaussRequest.getX())
                                .and(FINGERPRINTER_INFO_GAUSS.Y.eq(postReferencePointGaussRequest.getY()))
                                .and(FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS.eq(item.getMacAddress()))
                                .and(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(postReferencePointGaussRequest.getRoomId()))
                )) {
                    int gaussId = dslContext.select(FINGERPRINTER_INFO_GAUSS.ID)
                            .from(FINGERPRINTER_INFO_GAUSS)
                            .where(
                                    FINGERPRINTER_INFO_GAUSS.X.eq(postReferencePointGaussRequest.getX())
                                            .and(FINGERPRINTER_INFO_GAUSS.Y.eq(postReferencePointGaussRequest.getY()))
                                            .and(FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS.eq(item.getMacAddress()))
                                            .and(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(postReferencePointGaussRequest.getRoomId()))
                            ).fetchAny().value1();
                    List<Float> oldRss = dslContext.select(FINGERPRINTER_INFO_DETAIL.RSS)
                            .from(FINGERPRINTER_INFO_DETAIL)
                            .where(
                                    FINGERPRINTER_INFO_DETAIL.REFERENCE_ID.eq(gaussId)
                                            .and(FINGERPRINTER_INFO_DETAIL.TYPE.eq(TypeFingerprinterInfo.GAUSS))
                            )
                            .fetch()
                            .map(record -> record.value1().floatValue());

                    //insert finger printer detail
                    for (Float value : item.getListRss()) {
                        dslContext.insertInto(FINGERPRINTER_INFO_DETAIL,
                                FINGERPRINTER_INFO_DETAIL.REFERENCE_ID, FINGERPRINTER_INFO_DETAIL.RSS, FINGERPRINTER_INFO_DETAIL.TYPE)
                                .values(gaussId, (double) value, TypeFingerprinterInfo.GAUSS)
                                .execute();
                    }
                    item.getListRss().addAll(oldRss);
                    //mean
                    for (Float value : item.getListRss()) {
                        mean += value;
                    }
                    mean = mean / item.getListRss().size();

                    //deviation
                    for (Float value : item.getListRss()) {
                        deviation += (value - mean) * (value - mean);
                    }
                    deviation = Math.sqrt(deviation / item.getListRss().size());

                    //update
                    dslContext.update(FINGERPRINTER_INFO_GAUSS)
                            .set(FINGERPRINTER_INFO_GAUSS.MEAN, mean)
                            .set(FINGERPRINTER_INFO_GAUSS.STANDARD_DEVIATION, deviation)
                            .set(FINGERPRINTER_INFO_GAUSS.MEASURES, item.getListRss().size())
                            .where(FINGERPRINTER_INFO_GAUSS.ID.eq(gaussId))
                            .execute();

                } else {
                    for (Float value : item.getListRss()) {
                        mean += value;
                    }
                    mean = mean / item.getListRss().size();

                    for (Float value : item.getListRss()) {
                        deviation += (value - mean) * (value - mean);
                    }
                    deviation = Math.sqrt(deviation / item.getListRss().size());
                    //insert fingerprinter gauss
                    LOG.info("postReferencePointGauss " + "appname: " + item.getAppName());
                    int gaussId = dslContext.insertInto(FINGERPRINTER_INFO_GAUSS,
                            FINGERPRINTER_INFO_GAUSS.ROOM_ID, FINGERPRINTER_INFO_GAUSS.AP_NAME, FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS,
                            FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y, FINGERPRINTER_INFO_GAUSS.MEAN, FINGERPRINTER_INFO_GAUSS.STANDARD_DEVIATION,
                            FINGERPRINTER_INFO_GAUSS.MEASURES)
                            .values(postReferencePointGaussRequest.getRoomId(), item.getAppName(), item.getMacAddress(),
                                    postReferencePointGaussRequest.getX(), postReferencePointGaussRequest.getY(), mean, deviation, item.getListRss().size())
                            .returning(FINGERPRINTER_INFO_GAUSS.ID).fetchOne().getId();
                    //insert db finger printerinfo detail
                    for (Float value : item.getListRss()) {
                        dslContext.insertInto(FINGERPRINTER_INFO_DETAIL,
                                FINGERPRINTER_INFO_DETAIL.REFERENCE_ID, FINGERPRINTER_INFO_DETAIL.RSS, FINGERPRINTER_INFO_DETAIL.TYPE)
                                .values(gaussId, (double) value, TypeFingerprinterInfo.GAUSS)
                                .execute();
                    }
                }

            }

            ok.message = "Success";
        });


        return new BaseResponse(ok.message);
    }

    public static class OK {
        private String message;

    }
}
