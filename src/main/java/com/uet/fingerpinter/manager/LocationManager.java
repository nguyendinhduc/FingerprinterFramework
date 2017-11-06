package com.uet.fingerpinter.manager;

import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.manager.interf.LocationService;
import com.uet.fingerpinter.model.Pair;
import com.uet.fingerpinter.model.input.GetLocationRequest;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.response.BaseResponse;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.uet.fingerpinter.db.Tables.FINGERPRINTER_INFO;

@Service
public class LocationManager implements LocationService {
    private static final Logger LOG = LoggerFactory.getLogger(LocationManager.class);
    private final DSLContext ktv;

    @Autowired
    public LocationManager(@Qualifier("dslContext") DSLContext ktv) {
        this.ktv = ktv;
    }

    @Override
    public BaseResponse<GetLocationResponse> getLocation(GetLocationRequest request) throws CustomExceptionResponse {
        Comparator<InfoReferencePointRequest> comparator = (infoOne, infoTwo) -> {
            if (infoOne.getRss() < infoTwo.getRss()) {
                return 1;
            } else {
                if (infoOne.getRss() > infoTwo.getRss()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        request.getInfos().sort(comparator);

        //old
        List<InfoReferencePointRequest> oldInfos = new ArrayList<>();
        oldInfos.addAll(request.getInfos());
        //end

        request.setInfos(
                request.getInfos().stream().filter(info -> {
                    if (info.getRss() < -85.0f) {
                        return false;
                    } else {
                        return true;
                    }
                }).collect(Collectors.toList())
        );


        final int size = request.getInfos().size();

        List<List<FingerprinterInfoRecord>> listResult = null;
        List<InfoReferencePointRequest> pointRequests = new ArrayList<>();
        pointRequests.addAll(request.getInfos());
        for (int i = 0; i < size * 2 / 3; i++) {
            listResult = getListFingerPrinter(request.getBuildingId(), request.getRoomId(), pointRequests);
            if (listResult.size() == 0) {
                pointRequests.remove(size - 1 - i);
            } else {
                break;
            }
        }


        if (listResult == null || listResult.size() == 0) {
            final int sizeOld = oldInfos.size();
            for (int i = 0; i < sizeOld * 2 / 3; i++) {
                listResult = getListFingerPrinter(request.getBuildingId(), request.getRoomId(), oldInfos);
                if (listResult.size() == 0) {
                    oldInfos.remove(sizeOld - 1 - i);
                } else {
                    break;
                }
            }
        }

        if (listResult == null || listResult.size() == 0) {
            throw new CustomExceptionResponse("buiding or room invalidate", CustomExceptionResponse.ERROR_PARAM);
        }
        List<Pair<Integer, Double>> pairs = new ArrayList<>();
        int index = 0;
        for (List<FingerprinterInfoRecord> infoRecords : listResult) {
            double resultValue = 0.0;
            for (FingerprinterInfoRecord infoRecord : infoRecords) {
                for (InfoReferencePointRequest infoReferencePointRequest : request.getInfos()) {
                    if (infoRecord.getMacAddress().equals(infoReferencePointRequest.getMacAddress())) {
                        resultValue = +(infoRecord.getRss() - infoReferencePointRequest.getRss()) * (infoRecord.getRss() - infoReferencePointRequest.getRss());
                        break;
                    }
                }
            }
            resultValue = Math.sqrt(resultValue);
            pairs.add(new Pair<>(index, resultValue));
            index++;
        }

        Pair<Integer, Double> min = pairs.get(0);
        for (int i = 1; i < pairs.size(); i++) {
            LOG.info("getLocation x = " + listResult.get(pairs.get(i).getKey()).get(0).getX() + ", y= "
                    + listResult.get(pairs.get(i).getKey()).get(0).getY());
            LOG.info("getLocation detal " + pairs.get(i).getValue());
            LOG.info("--------------------------------");
            if (pairs.get(i).getValue() < min.getValue()) {
                min = pairs.get(i);
            }
        }
        int x = listResult.get(min.getKey()).get(0).getX();
        int y = listResult.get(min.getKey()).get(0).getY();
        return new BaseResponse<>(new GetLocationResponse(x, y));
    }

    private List<List<FingerprinterInfoRecord>> getListFingerPrinter(int buildingId, int roomId, List<InfoReferencePointRequest> request) {
        List<String> macAddress = new ArrayList<>();
        for (InfoReferencePointRequest infoReferencePointRequest : request) {
            macAddress.add(infoReferencePointRequest.getMacAddress());
        }
        Result<Record3<Integer, Integer, Integer>> record3s =
                ktv.select(FINGERPRINTER_INFO.X, FINGERPRINTER_INFO.Y, DSL.count(FINGERPRINTER_INFO.MAC_ADDRESS).as("count")).from(FINGERPRINTER_INFO)
                        .where(
                                FINGERPRINTER_INFO.ROOM_ID.eq(roomId)
                                        .and(FINGERPRINTER_INFO.BUILDING_ID.eq(buildingId))
                                        .and(FINGERPRINTER_INFO.MAC_ADDRESS.in(macAddress))
                        )
                        .groupBy(FINGERPRINTER_INFO.X, FINGERPRINTER_INFO.Y)
                        .fetch();
        List<List<FingerprinterInfoRecord>> listResult = new ArrayList<>();
        for (Record3<Integer, Integer, Integer> record3 : record3s) {
            if (record3.value3() < request.size()) {
                continue;
            }
            listResult.add(
                    ktv.selectFrom(FINGERPRINTER_INFO)
                            .where(
                                    FINGERPRINTER_INFO.ROOM_ID.eq(roomId)
                                            .and(FINGERPRINTER_INFO.BUILDING_ID.eq(buildingId))
                                            .and(FINGERPRINTER_INFO.MAC_ADDRESS.in(macAddress))
                                            .and(FINGERPRINTER_INFO.X.eq(record3.value1()))
                                            .and(FINGERPRINTER_INFO.Y.eq(record3.value2()))
                            )
                            .fetch()
            );

        }
        return listResult;
    }
}
