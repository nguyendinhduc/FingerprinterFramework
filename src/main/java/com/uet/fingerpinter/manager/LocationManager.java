package com.uet.fingerpinter.manager;

import com.uet.fingerpinter.db.tables.FingerprinterInfo;
import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.manager.interf.LocationService;
import com.uet.fingerpinter.model.input.GetLocationRequest;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.response.BaseResponse;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;
import com.uet.fingerpinter.utils.ThreeValue;
import javafx.util.Pair;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.uet.fingerpinter.db.Tables.FINGERPRINTER_INFO;

@Service
public class LocationManager implements LocationService {
    private final DSLContext ktv;

    @Autowired
    public LocationManager(@Qualifier("dslContext") DSLContext ktv) {
        this.ktv = ktv;
    }

    @Override
    public BaseResponse<GetLocationResponse> getLocation(GetLocationRequest request) throws CustomExceptionResponse {
        List<String> macAddress = new ArrayList<>();
        for (InfoReferencePointRequest infoReferencePointRequest : request.getInfos()) {
            macAddress.add(infoReferencePointRequest.getMacAddress());
        }
        Table<Record2<String, Integer>> record2Table = ktv.select(FINGERPRINTER_INFO.MAC_ADDRESS, DSL.count(FINGERPRINTER_INFO.MAC_ADDRESS).as("count")).from(FINGERPRINTER_INFO)
                .where(
                        FINGERPRINTER_INFO.ROOM_ID.eq(request.getRoomId())
                                .and(FINGERPRINTER_INFO.BUILDING_ID.eq(request.getBuidingId())
                                        .and(FINGERPRINTER_INFO.MAC_ADDRESS.in(macAddress)))
                )
                .groupBy(FINGERPRINTER_INFO.MAC_ADDRESS)
                .asTable();

        Result<Record2<String, Integer>> record2s = ktv.selectFrom(record2Table)
                .where(
                        DSL.cast(record2Table.field("count"), Integer.class).ge(
                                request.getInfos().size())
                )
                .fetch();
        if (record2s.size() == 0) {
            throw new CustomExceptionResponse("buiding or room invalidate", CustomExceptionResponse.ERROR_PARAM);
        }
        List<List<FingerprinterInfoRecord>> listResult = new ArrayList<>();
        for (Record2<String, Integer> record2 : record2s) {
            List<FingerprinterInfoRecord> infoRecords = ktv.selectFrom(FINGERPRINTER_INFO)
                    .where(
                            FINGERPRINTER_INFO.MAC_ADDRESS.eq(record2.value1())
                                    .and(FINGERPRINTER_INFO.BUILDING_ID.eq(request.getBuidingId())
                                            .and(FINGERPRINTER_INFO.ROOM_ID.eq(request.getRoomId())))
                    )
                    .fetch();
            Collections.shuffle(infoRecords);
            listResult.add(infoRecords.subList(0, request.getInfos().size()));
        }
        List<ThreeValue<Integer, Integer, Double>> threes = new ArrayList<>();
        for (List<FingerprinterInfoRecord> infoRecords : listResult) {

        }


        return null;
    }
}
