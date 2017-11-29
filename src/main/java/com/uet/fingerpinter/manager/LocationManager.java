package com.uet.fingerpinter.manager;

import com.uet.fingerpinter.db.tables.records.FingerprinterInfoGaussRecord;
import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.db.tables.records.TrackingKNearestRecord;
import com.uet.fingerpinter.db.tables.records.TrackingRecord;
import com.uet.fingerpinter.manager.interf.LocationService;
import com.uet.fingerpinter.model.input.GetLocationRequest;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.input.gauss.DistributionGauss;
import com.uet.fingerpinter.model.input.gauss.ItemFocusPosition;
import com.uet.fingerpinter.model.input.gauss.ResponseFocus;
import com.uet.fingerpinter.model.response.BaseResponse;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;
import com.uet.fingerpinter.model.response.ItemPositionKNearestGauss;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.uet.fingerpinter.db.Tables.*;

@Service
public class LocationManager implements LocationService {
    private int K_NEAREST = 4;
    private int MAX_MOVE_SECOND = 2;
    private static final double DELTA = 1.96;
    private static final Logger LOG = LoggerFactory.getLogger(LocationManager.class);
    private final DSLContext ktv;

    @Autowired
    public LocationManager(@Qualifier("dslContext") DSLContext ktv) {
        this.ktv = ktv;
    }

    @Override
    public BaseResponse<ResponseFocus> getLocation(GetLocationRequest request) throws CustomExceptionResponse {
        return getLocationGauss(request);
//        Comparator<InfoReferencePointRequest> comparator = (infoOne, infoTwo) -> {
//            if (infoOne.getRss() < infoTwo.getRss()) {
//                return 1;
//            } else {
//                if (infoOne.getRss() > infoTwo.getRss()) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        };
//        request.getInfos().sort(comparator);
//
//        //old
//        List<InfoReferencePointRequest> oldInfos = new ArrayList<>();
//        oldInfos.addAll(request.getInfos());
//        //end
//
//        request.setInfos(
//                request.getInfos().stream().filter(info -> {
//                    if (info.getRss() < -85.0f) {
//                        return false;
//                    } else {
//                        return true;
//                    }
//                }).collect(Collectors.toList())
//        );
//
//
//        final int size = request.getInfos().size();
//
//        List<List<FingerprinterInfoRecord>> listResult = null;
//        List<InfoReferencePointRequest> pointRequests = new ArrayList<>();
//        pointRequests.addAll(request.getInfos());
//        for (int i = 0; i < size * 2 / 3; i++) {
//            listResult = getListFingerPrinter(request.getBuildingId(), request.getRoomId(), pointRequests);
//            if (listResult.size() == 0) {
//                pointRequests.remove(size - 1 - i);
//            } else {
//                break;
//            }
//        }
//
//
//        if (listResult == null || listResult.size() == 0) {
//            final int sizeOld = oldInfos.size();
//            for (int i = 0; i < sizeOld * 2 / 3; i++) {
//                listResult = getListFingerPrinter(request.getBuildingId(), request.getRoomId(), oldInfos);
//                if (listResult.size() == 0) {
//                    oldInfos.remove(sizeOld - 1 - i);
//                } else {
//                    break;
//                }
//            }
//        }
//
//        if (listResult == null || listResult.size() == 0) {
//            throw new CustomExceptionResponse("buiding or room invalidate", CustomExceptionResponse.ERROR_PARAM);
//        }
//        List<Pair<Integer, Double>> pairs = new ArrayList<>();
//        int index = 0;
//        for (List<FingerprinterInfoRecord> infoRecords : listResult) {
//            double resultValue = 0.0;
//            for (FingerprinterInfoRecord infoRecord : infoRecords) {
//                for (InfoReferencePointRequest infoReferencePointRequest : request.getInfos()) {
//                    if (infoRecord.getMacAddress().equals(infoReferencePointRequest.getMacAddress())) {
//                        resultValue = +(infoRecord.getRss() - infoReferencePointRequest.getRss()) * (infoRecord.getRss() - infoReferencePointRequest.getRss());
//                        break;
//                    }
//                }
//            }
//            resultValue = Math.sqrt(resultValue);
//            pairs.add(new Pair<>(index, resultValue));
//            index++;
//        }
//
//        Pair<Integer, Double> min = pairs.get(0);
//        for (int i = 1; i < pairs.size(); i++) {
//            LOG.info("getLocation x = " + listResult.get(pairs.get(i).getKey()).get(0).getX() + ", y= "
//                    + listResult.get(pairs.get(i).getKey()).get(0).getY());
//            LOG.info("getLocation detal " + pairs.get(i).getValue());
//            LOG.info("--------------------------------");
//            if (pairs.get(i).getValue() < min.getValue()) {
//                min = pairs.get(i);
//            }
//        }
//        int x = listResult.get(min.getKey()).get(0).getX();
//        int y = listResult.get(min.getKey()).get(0).getY();
//        return new BaseResponse<>(new GetLocationResponse(x, y));
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


    private BaseResponse<ResponseFocus> getLocationGauss(GetLocationRequest request) throws CustomExceptionResponse {
        LOG.info("getLocationGauss isFirst: " + request.getExtendGetLocationModel().isFirst());
        LOG.info("getLocationGauss transacsionId: " + request.getExtendGetLocationModel().getTransactionId());
        LOG.info("getLocationGauss x: " + request.getExtendGetLocationModel().getX());
        LOG.info("getLocationGauss y: " + request.getExtendGetLocationModel().getY());
        LOG.info("getLocationGauss room_id: " + request.getRoomId());
        if (request.getExtendGetLocationModel().isFirst()) {
            int transactionId = crateTransactionIdTracking();
            int trackingId = ktv.insertInto(TRACKING)
                    .set(TRACKING.ROOM_ID, request.getRoomId())
                    .set(TRACKING.SESSION_ID, transactionId)
                    .set(TRACKING.X, request.getExtendGetLocationModel().getX())
                    .set(TRACKING.Y, request.getExtendGetLocationModel().getY())
                    .set(TRACKING.CREATED_TIME, LocalDateTime.now())
                    .returning(TRACKING.ID).fetchOne().getId();

            ktv.insertInto(TRACKING_K_NEAREST)
                    .set(TRACKING_K_NEAREST.TRACKING_ID, trackingId)
                    .set(TRACKING_K_NEAREST.DISTRIBUTION, 1.0)
                    .set(TRACKING_K_NEAREST.X, request.getExtendGetLocationModel().getX())
                    .set(TRACKING_K_NEAREST.Y, request.getExtendGetLocationModel().getY())
                    .execute();

//            GetLocationResponse response = new GetLocationResponse(request.getExtendGetLocationModel().getX(),
//                    request.getExtendGetLocationModel().getY());
//            response.setTransactionId(transactionId);

            ResponseFocus response = new ResponseFocus(request.getExtendGetLocationModel().getX(), request.getExtendGetLocationModel().getY(), transactionId);
            return new BaseResponse<>(response);
        }
        request.getInfos().sort((o1, o2) -> {
            if (o1.getRss() < o2.getRss()) {
                return 1;
            } else {
                if (o1.getRss() > o2.getRss()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        List<GetLocationResponse> getLocationResponses =
                ktv.select(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y)
                        .from(FINGERPRINTER_INFO_GAUSS)
                        .where(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(request.getRoomId()))
                        .groupBy(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y)
                        .fetch()
                        .map(record -> new GetLocationResponse(record.get(FINGERPRINTER_INFO_GAUSS.X), record.get(FINGERPRINTER_INFO_GAUSS.Y)));

        List<DistributionGauss> distributionGausses = new ArrayList<>();
        for (GetLocationResponse getLocation : getLocationResponses) {
            List<FingerprinterInfoGaussRecord> infoGaussRecords =
                    ktv.selectFrom(FINGERPRINTER_INFO_GAUSS)
                            .where(FINGERPRINTER_INFO_GAUSS.X.eq(getLocation.getX())
                                    .and(FINGERPRINTER_INFO_GAUSS.Y.eq(getLocation.getY()))
                                    .and(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(request.getRoomId()))
                            )
                            .orderBy(FINGERPRINTER_INFO_GAUSS.MEASURES)
                            .fetch();
            double distributionRss = 1;

            int miss = 0;
            for (InfoReferencePointRequest infoReferencePointRequest : request.getInfos()) {
                boolean isMiss = false;
                for (FingerprinterInfoGaussRecord infoGaussRecord : infoGaussRecords) {
                    if (infoGaussRecord.getMacAddress().equals(infoReferencePointRequest.getMacAddress())) {
                        if (infoGaussRecord.getMean() - DELTA * infoGaussRecord.getStandardDeviation() < infoReferencePointRequest.getRss()
                                && infoGaussRecord.getMean() + DELTA * infoGaussRecord.getStandardDeviation() > infoReferencePointRequest.getRss()) {
                            double temDis;
                            if (infoReferencePointRequest.getRss() < infoGaussRecord.getMean()) {
                                temDis = 1.0 - new NormalDistribution(infoGaussRecord.getMean(), infoGaussRecord.getStandardDeviation())
                                        .cumulativeProbability(infoReferencePointRequest.getRss());
                            } else {
                                temDis = new NormalDistribution(infoGaussRecord.getMean(), infoGaussRecord.getStandardDeviation())
                                        .cumulativeProbability(infoReferencePointRequest.getRss());
                            }
                            distributionRss *= temDis;
                        }
                        isMiss = true;
                        break;
                    }

                }
                if (!isMiss) {
                    miss++;
                }
            }
            distributionGausses.add(new DistributionGauss(getLocation.getX(), getLocation.getY(), distributionRss, miss));
        }

        distributionGausses.sort((o1, o2) -> {
            if (o1.getDistribution() > o2.getDistribution()) {
                return 1;
            } else {
                if (o1.getDistribution() < o2.getDistribution()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for (DistributionGauss distributionGauss : distributionGausses) {
            LOG.info("getLocationGauss " + "x = " + distributionGauss.getX() + " y = " + distributionGauss.getY() + " ,miss: " + distributionGauss.getNumberMiss() + ", distribution: " + distributionGauss.getDistribution());
        }
        LOG.info("getLocationGauss -------------------------------------------------focus-----------------------------------------");


//        return new BaseResponse<>(new GetLocationResponse(
//                distributionGausses.get(0).getX(),
//                distributionGausses.get(0).getY(),
//                request.getExtendGetLocationModel().getTransactionId())
//        );


        ResponseFocus position = getPositionFocus(distributionGausses, request.getExtendGetLocationModel().getTransactionId());
        int trackingId = ktv.insertInto(TRACKING,
                TRACKING.ROOM_ID, TRACKING.CREATED_TIME, TRACKING.X, TRACKING.Y, TRACKING.SESSION_ID
        ).values(request.getRoomId(), LocalDateTime.now(), (int) position.getX(), (int) position.getY(), position.getTransactionId())
                .returning(TRACKING.ID).fetchOne().getId();
        int max = 4;
        if (distributionGausses.size() < 4) {
            max = distributionGausses.size();
        }
        for (int i = 0; i < max; i++) {
            DistributionGauss distributionGauss = distributionGausses.get(i);
            ktv.insertInto(TRACKING_K_NEAREST,
                    TRACKING_K_NEAREST.X, TRACKING_K_NEAREST.Y, TRACKING_K_NEAREST.DISTRIBUTION, TRACKING_K_NEAREST.TRACKING_ID)
                    .values(distributionGauss.getX(), distributionGauss.getY(), distributionGauss.getDistribution(), trackingId)
                    .execute();
        }
        return new BaseResponse<>(position);

//        return kNearestHistory(request, distributionGausses);


    }

    private BaseResponse<GetLocationResponse> kNearestHistory(GetLocationRequest request, List<DistributionGauss> distributionGausses) {
        //algorithm k nearest
        int transactionId = request.getExtendGetLocationModel().getTransactionId();
        LocalDateTime maxTime =
                ktv.select(DSL.max(TRACKING.CREATED_TIME))
                        .from(TRACKING)
                        .where(TRACKING.SESSION_ID.eq(transactionId))
                        .fetchAny()
                        .value1();

        TrackingRecord trackingRecordOld = ktv.selectFrom(TRACKING)
                .where(TRACKING.CREATED_TIME.eq(maxTime)
                        .and(TRACKING.ROOM_ID.eq(request.getRoomId()))
                        .and(TRACKING.SESSION_ID.eq(transactionId)))
                .fetchAny();


        List<TrackingKNearestRecord> nearestRecordsOld =
                ktv.selectFrom(TRACKING_K_NEAREST)
                        .where(TRACKING_K_NEAREST.TRACKING_ID.eq(trackingRecordOld.getId()))
                        .fetch();
        final int indexMax;
        if (distributionGausses.size() < K_NEAREST) {
            indexMax = distributionGausses.size();
        } else {
            indexMax = K_NEAREST;
        }
        List<ItemPositionKNearestGauss> resultKNearst = new ArrayList<>();
        for (int i = 0; i < indexMax; i++) {
            DistributionGauss distributionGauss = distributionGausses.get(i);
            for (TrackingKNearestRecord trackingKNearestRecord : nearestRecordsOld) {
                double distance = distance2D(distributionGauss.getX(), distributionGauss.getY(),
                        trackingKNearestRecord.getX(), trackingKNearestRecord.getY());
                ItemPositionKNearestGauss gauss = new ItemPositionKNearestGauss();

                gauss.setDistance(distance);
                gauss.setDistribution(distributionGauss.getDistribution());
                gauss.setX(distributionGauss.getX());
                gauss.setY(distributionGauss.getY());
                gauss.setMiss(distributionGauss.getNumberMiss());
                gauss.setToX(trackingKNearestRecord.getX());
                gauss.setToY(trackingKNearestRecord.getY());

                resultKNearst.add(gauss);
            }
        }

        resultKNearst.sort((o1, o2) -> {
            double deltaOne = Math.abs(o1.getDistance() - MAX_MOVE_SECOND);
            double deltaTwo = Math.abs(o2.getDistance() - MAX_MOVE_SECOND);
            if (deltaOne > deltaTwo) {
                return 1;
            } else {
                if (deltaOne < deltaTwo) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        LOG.info("getLocationGauss----------------------------------------------------------- start k nearest");
        for (ItemPositionKNearestGauss itemPositionKNearestGauss : resultKNearst) {
            LOG.info("getLocationGauss knearest" + "x = " + itemPositionKNearestGauss.getX() + " y = " + itemPositionKNearestGauss.getY() + " ,miss: " + itemPositionKNearestGauss.getMiss() + ", distribution: " + itemPositionKNearestGauss.getDistribution());
        }


        int newTrackingId = ktv.insertInto(TRACKING,
                TRACKING.CREATED_TIME, TRACKING.ROOM_ID, TRACKING.X, TRACKING.Y, TRACKING.SESSION_ID)
                .values(LocalDateTime.now(), request.getRoomId(), resultKNearst.get(0).getX(), resultKNearst.get(0).getY(), transactionId)
                .returning(TRACKING.ID).fetchOne().value1();
        for (int i = 0; i < indexMax; i++) {
            DistributionGauss distributionGauss = distributionGausses.get(i);
            ktv.insertInto(TRACKING_K_NEAREST,
                    TRACKING_K_NEAREST.X, TRACKING_K_NEAREST.Y, TRACKING_K_NEAREST.DISTRIBUTION, TRACKING_K_NEAREST.TRACKING_ID)
                    .values(distributionGauss.getX(), distributionGauss.getY(), distributionGauss.getDistribution(), newTrackingId)
                    .execute();
        }


        LOG.info("getLocationGauss----------------------------------------------------------- end k nearest");
        LOG.info("getLocationGauss----------------------------------------------------------- end k nearest");
        LOG.info("getLocationGauss----------------------------------------------------------- end k nearest");
        LOG.info("getLocationGauss----------------------------------------------------------- end k nearest");

        return new BaseResponse<>(new GetLocationResponse(
                resultKNearst.get(0).getX(),
                resultKNearst.get(0).getY(),
                request.getExtendGetLocationModel().getTransactionId())
        );
    }

    private ResponseFocus getPositionFocus(List<DistributionGauss> distributionGausses, int transactionId) {
        //forcus
        int max = 4;
        if (distributionGausses.size() < 4) {
            max = distributionGausses.size();
        }

        double xFocus = 0;
        double yFocus = 0;
        double totalDistribution = 0;
        for (int i = 0; i < max; i++) {
            xFocus += (1.0 - distributionGausses.get(i).getDistribution()) * distributionGausses.get(i).getX();
            yFocus += (1.0 - distributionGausses.get(i).getDistribution()) * distributionGausses.get(i).getY();
            totalDistribution += (1.0 - distributionGausses.get(i).getDistribution());
        }
        xFocus = xFocus / totalDistribution;
        yFocus = yFocus / totalDistribution;
        return new ResponseFocus((float) xFocus, (float) yFocus, transactionId);

//        List<ItemFocusPosition> itemFocusPositions = new ArrayList<>();
//        for (int i = 0; i < max; i++) {
//            ItemFocusPosition position = new ItemFocusPosition();
//            position.setX(distributionGausses.get(i).getX());
//            position.setY(distributionGausses.get(i).getY());
//            position.setDistribution(distributionGausses.get(i).getDistribution());
//            position.setDeltaX(xFocus - distributionGausses.get(i).getX());
//            position.setDeltaY(yFocus - distributionGausses.get(i).getY());
//            position.setMiss(distributionGausses.get(i).getNumberMiss());
//
//            double distance = Math.sqrt(position.getDeltaX() * position.getDeltaX() + position.getDeltaY() * position.getDeltaY());
//            position.setDistanceWithFocus(distance);
//            itemFocusPositions.add(position);
//        }
//        itemFocusPositions.sort((o1, o2) -> {
//            if (o1.getDistanceWithFocus() > o2.getDistanceWithFocus()) {
//                return 1;
//            } else {
//                if (o1.getDistanceWithFocus() < o2.getDistanceWithFocus()) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//            }
//        });
//        for (ItemFocusPosition itemFocusPosition : itemFocusPositions) {
//            LOG.info("getLocationGauss " + "x = " + itemFocusPosition.getX() + " y = " + itemFocusPosition.getY() + " ,miss: " + itemFocusPosition.getMiss() + ", distribution: " + itemFocusPosition.getDistribution() + ", distance: " + itemFocusPosition.getDistanceWithFocus());
//        }
//        LOG.info("getLocationGauss -------------------------------------------------focus");
//
//        return itemFocusPositions.get(0);
    }

    private int crateTransactionIdTracking() {
        Record1<Integer> maxTransaction = ktv.select(DSL.max(TRACKING.SESSION_ID))
                .from(TRACKING)
                .fetchAny();
        if (maxTransaction == null || maxTransaction.value1() == null) {
            return 1;
        } else {
            return maxTransaction.value1() + 1;
        }
    }

    public static double distance2D(int x1, int y1, int x2, int y2) {
        double result = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return Math.sqrt(result);
    }

    @Override
    public BaseResponse<List<GetLocationResponse>> getPath() throws CustomExceptionResponse {
        List<GetLocationResponse> point =
                ktv.selectFrom(TRACK_REAL)
                        .orderBy(TRACK_REAL.ID.asc())
                        .fetch()
                        .map(record -> new GetLocationResponse(record.getX(), record.getY()));

        return new BaseResponse<>(point);
    }
}
