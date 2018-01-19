package com.uet.fingerpinter.mobilecontroller;

import com.uet.fingerpinter.db.tables.records.FingerprinterInfoGaussRecord;
import com.uet.fingerpinter.db.tables.records.TrackingKNearestRecord;
import com.uet.fingerpinter.db.tables.records.TrackingRecord;
import com.uet.fingerpinter.jooq.type.TypeFingerprinterInfo;
import com.uet.fingerpinter.manager.LocationManager;
import com.uet.fingerpinter.model.Pair;
import com.uet.fingerpinter.model.input.InfoReferencePointRequest;
import com.uet.fingerpinter.model.input.gauss.DistributionGauss;
import com.uet.fingerpinter.model.input.gauss.ItemFocusPosition;
import com.uet.fingerpinter.model.response.CustomExceptionResponse;
import com.uet.fingerpinter.model.response.GetLocationResponse;
import com.uet.fingerpinter.model.response.ItemPositionKNearestGauss;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.uet.fingerpinter.db.Tables.*;


@RestController
public class Demo {
    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);
    private static final double DELTA = 1.96;
    private static final int K_NEAREST = 4;
    private static final int MAX_MOVE_SECOND = 2;

    private final DSLContext ktv;
    private List<GetLocationResponse> realLocation;

    @Autowired
    public Demo(DSLContext ktv) {
        this.ktv = ktv;
        realLocation = new ArrayList<>();
        realLocation.add(new GetLocationResponse(10, 7));
        realLocation.add(new GetLocationResponse(8, 7));
        realLocation.add(new GetLocationResponse(6, 7));
        realLocation.add(new GetLocationResponse(6, 5));
        realLocation.add(new GetLocationResponse(6, 3));
        realLocation.add(new GetLocationResponse(8, 3));
        realLocation.add(new GetLocationResponse(6, 3));
        realLocation.add(new GetLocationResponse(4, 3));
        realLocation.add(new GetLocationResponse(2, 3));
        realLocation.add(new GetLocationResponse(0, 3));
        realLocation.add(new GetLocationResponse(-2, 4));
        realLocation.add(new GetLocationResponse(-4, 4));
        realLocation.add(new GetLocationResponse(-5, 5));
        realLocation.add(new GetLocationResponse(-5, 7));
        realLocation.add(new GetLocationResponse(-5, 9));
        realLocation.add(new GetLocationResponse(-5, 11));
        realLocation.add(new GetLocationResponse(-5, 13));
        realLocation.add(new GetLocationResponse(-5, 15));
        realLocation.add(new GetLocationResponse(-5, 17));
    }

    @GetMapping(value = "/")
    public String get() {
        return "Hello FingerPrinnter wifi location";
    }

    @GetMapping(value = "/fingerpringer_info")
    public String updateFingerprinterInfo() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "fingerprinter_info")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(FINGERPRINTER_INFO, FINGERPRINTER_INFO.ID.eq(id))) {
                ktv.insertInto(FINGERPRINTER_INFO,
                        FINGERPRINTER_INFO.ID, FINGERPRINTER_INFO.AP_NAME, FINGERPRINTER_INFO.MAC_ADDRESS, FINGERPRINTER_INFO.RSS, FINGERPRINTER_INFO.X, FINGERPRINTER_INFO.Y, FINGERPRINTER_INFO.ROOM_ID, FINGERPRINTER_INFO.BUILDING_ID)
                        .values(id, content[1], content[2], Double.parseDouble(content[3]), Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]), Integer.parseInt(content[7]))
                        .execute();
            }
        }
        return "success";
    }

    @GetMapping(value = "/fingerprinter_info_gauss")
    public String updateFingerprinterInfoGauss() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "fingerprinter_info_gauss")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(FINGERPRINTER_INFO_GAUSS, FINGERPRINTER_INFO_GAUSS.ID.eq(id))) {
                ktv.insertInto(FINGERPRINTER_INFO_GAUSS,
                        FINGERPRINTER_INFO_GAUSS.ID, FINGERPRINTER_INFO_GAUSS.ROOM_ID, FINGERPRINTER_INFO_GAUSS.AP_NAME, FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS, FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y, FINGERPRINTER_INFO_GAUSS.MEAN, FINGERPRINTER_INFO_GAUSS.STANDARD_DEVIATION, FINGERPRINTER_INFO_GAUSS.MEASURES)
                        .values(id, Integer.parseInt(content[1]), content[2], content[3], Integer.parseInt(content[4]), Integer.parseInt(content[5]), Double.parseDouble(content[6]), Double.parseDouble(content[7]), Integer.parseInt(content[8]))
                        .execute();
            }
        }
        return "success";
    }

    @GetMapping(value = "/fingerprinter_tracking")
    public String updateFingerprinterTracking() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "fingerprinter_tracking")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(FINGERPRINTER_TRACKING, FINGERPRINTER_TRACKING.ID.eq(id))) {
                ktv.insertInto(FINGERPRINTER_TRACKING,
                        FINGERPRINTER_TRACKING.ID, FINGERPRINTER_TRACKING.SESSION_ID, FINGERPRINTER_TRACKING.INDEX, FINGERPRINTER_TRACKING.RSS, FINGERPRINTER_TRACKING.MAC_ADDRESS, FINGERPRINTER_TRACKING.AP_NAME)
                        .values(id, Integer.parseInt(content[1]), Integer.parseInt(content[2]), Double.parseDouble(content[3]), content[4], content[5])
                        .execute();
            }
        }
        return "success";
    }

    @GetMapping(value = "/tracking_k_nearest")
    public String updateTrankingKNrearest() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "tracking_k_nearest")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(TRACKING_K_NEAREST, TRACKING_K_NEAREST.ID.eq(id))) {
                ktv.insertInto(TRACKING_K_NEAREST,
                        TRACKING_K_NEAREST.ID, TRACKING_K_NEAREST.TRACKING_ID, TRACKING_K_NEAREST.DISTRIBUTION, TRACKING_K_NEAREST.X, TRACKING_K_NEAREST.Y)
                        .values(id, Integer.parseInt(content[1]), Double.parseDouble(content[2]), Integer.parseInt(content[3]), Integer.parseInt(content[4]))
                        .execute();
            }
        }
        return "success";
    }

    @GetMapping(value = "/fingerprinter_info_detail")
    public String updateFingerprinterInfoDetail() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "fingerprinter_info_detail")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(FINGERPRINTER_INFO_DETAIL, FINGERPRINTER_INFO_DETAIL.ID.eq(id))) {
                if (Math.abs(Double.parseDouble(content[2])) > Math.abs(Double.parseDouble(content[3]))) {
                    ktv.insertInto(FINGERPRINTER_INFO_DETAIL,
                            FINGERPRINTER_INFO_DETAIL.ID, FINGERPRINTER_INFO_DETAIL.REFERENCE_ID, FINGERPRINTER_INFO_DETAIL.RSS, FINGERPRINTER_INFO_DETAIL.TYPE)
                            .values(id, Integer.parseInt(content[1]), Double.parseDouble(content[2]), TypeFingerprinterInfo.findByValue(Integer.parseInt(content[3])))
                            .execute();
                } else {
                    ktv.insertInto(FINGERPRINTER_INFO_DETAIL,
                            FINGERPRINTER_INFO_DETAIL.ID, FINGERPRINTER_INFO_DETAIL.REFERENCE_ID, FINGERPRINTER_INFO_DETAIL.RSS, FINGERPRINTER_INFO_DETAIL.TYPE)
                            .values(id, Integer.parseInt(content[1]), Double.parseDouble(content[3]), TypeFingerprinterInfo.findByValue(Integer.parseInt(content[2])))
                            .execute();
                }

            }
        }
        return "success";
    }

    @GetMapping(value = "/tracking")
    public String tracking() throws IOException {
        File file = new DefaultResourceLoader().getResource(
                "tracking")
                .getFile();
        RandomAccessFile rd = new RandomAccessFile(file, "rw");
        while (rd.getFilePointer() < rd.length()) {
            String line = rd.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            if (!ktv.fetchExists(TRACKING, TRACKING.ID.eq(id))) {
                try {
                    ktv.insertInto(TRACKING,
                            TRACKING.ID, TRACKING.ROOM_ID, TRACKING.SESSION_ID, TRACKING.X, TRACKING.Y, TRACKING.CREATED_TIME)
                            .values(id, Integer.parseInt(content[1]), Integer.parseInt(content[2]), Integer.parseInt(content[3]), Integer.parseInt(content[4]),
                                    LocalDateTime.parse(content[5], DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.SS")))
                            .execute();
                } catch (DateTimeParseException e) {
                    try {
                        ktv.insertInto(TRACKING,
                                TRACKING.ID, TRACKING.ROOM_ID, TRACKING.SESSION_ID, TRACKING.X, TRACKING.Y, TRACKING.CREATED_TIME)
                                .values(id, Integer.parseInt(content[1]), Integer.parseInt(content[2]), Integer.parseInt(content[3]), Integer.parseInt(content[4]),
                                        LocalDateTime.parse(content[5], DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.SSS")))
                                .execute();
                    } catch (DateTimeParseException e1) {
                        ktv.insertInto(TRACKING,
                                TRACKING.ID, TRACKING.ROOM_ID, TRACKING.SESSION_ID, TRACKING.X, TRACKING.Y, TRACKING.CREATED_TIME)
                                .values(id, Integer.parseInt(content[1]), Integer.parseInt(content[2]), Integer.parseInt(content[3]), Integer.parseInt(content[4]),
                                        LocalDateTime.parse(content[5], DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.S")))
                                .execute();
                    }
                }

            }
        }
        return "success";
    }

    @GetMapping("/updateDemoDataBaseGetLocation")
    public void updateDemoDataBaseGetLocation() {
        ktv.transaction(config -> {
            DSLContext dsl = DSL.using(config);
            dsl.delete(RESULT_TRACKING_DEMO)
                    .execute();
            int sessionId = dsl.select(DSL.max(FINGERPRINTER_TRACKING.SESSION_ID))
                    .from(FINGERPRINTER_TRACKING).fetchAny().value1();

//            nearest(dsl, sessionId);
            gauss(dsl, sessionId);

        });
    }

    private void gauss(DSLContext dsl, int sessionId) {
        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.GAUSS_NORMAL, 10.0, 7.0, 0.0)
                .execute();

        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_HISTORY, 10.0, 7.0, 0.0)
                .execute();

        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_CENTER, 10.0, 7.0, 0.0)
                .execute();

        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_CENTER_NEAR, 10.0, 7.0, 0.0)
                .execute();

        List<Integer> listIndex = dsl.select(FINGERPRINTER_TRACKING.INDEX)
                .from(FINGERPRINTER_TRACKING)
                .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId))
                .groupBy(FINGERPRINTER_TRACKING.INDEX)
                .fetch()
                .map(Record1::value1);
        int roomId = -1;
        List<LocalDateTime> listTimeTracking =
                ktv.select(TRACKING.CREATED_TIME)
                        .from(TRACKING)
                        .where(TRACKING.SESSION_ID.eq(sessionId))
                        .orderBy(TRACKING.CREATED_TIME.asc())
                        .fetch()
                        .map(Record1::value1);
        int totalCount = 0;
        double valueMean = 0;
        int indexTime = listTimeTracking.size() - listIndex.size();
        for (Integer indexPosition : listIndex) {
            if (indexPosition == 0) {
                continue;
            }
            List<InfoReferencePointRequest> infos = dsl.selectFrom(FINGERPRINTER_TRACKING)
                    .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId)
                            .and(FINGERPRINTER_TRACKING.INDEX.eq(indexPosition)))
                    .fetch()
                    .map(record -> {
                        InfoReferencePointRequest request = new InfoReferencePointRequest();
                        request.setMacAddress(record.getMacAddress());
                        request.setName(record.getApName());
                        request.setRss(record.getRss().floatValue());
                        return request;
                    });
            if (roomId == -1) {
                roomId = dsl.select(FINGERPRINTER_TRACKING.ROOM_ID)
                        .from(FINGERPRINTER_TRACKING)
                        .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId)
                                .and(FINGERPRINTER_TRACKING.INDEX.eq(indexPosition)))
                        .fetchAny().value1();
            }
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
            infos.sort(comparator);

            List<GetLocationResponse> getLocationResponses =
                    dsl.select(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y)
                            .from(FINGERPRINTER_INFO_GAUSS)
                            .where(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(roomId))
                            .groupBy(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y)
                            .fetch()
                            .map(record -> new GetLocationResponse(record.get(FINGERPRINTER_INFO_GAUSS.X), record.get(FINGERPRINTER_INFO_GAUSS.Y)));

            List<DistributionGauss> distributionGausses = new ArrayList<>();
            for (GetLocationResponse getLocation : getLocationResponses) {
                List<FingerprinterInfoGaussRecord> infoGaussRecords =
                        dsl.selectFrom(FINGERPRINTER_INFO_GAUSS)
                                .where(FINGERPRINTER_INFO_GAUSS.X.eq(getLocation.getX())
                                        .and(FINGERPRINTER_INFO_GAUSS.Y.eq(getLocation.getY()))
                                        .and(FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(roomId))
                                )
                                .orderBy(FINGERPRINTER_INFO_GAUSS.MEASURES)
                                .fetch();
                double distributionRss = 1;

                int miss = 0;
                for (InfoReferencePointRequest infoReferencePointRequest : infos) {
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
                LOG.info("gauss: " + "x: " + distributionGauss.getX() + ", y: " + distributionGauss.getY() + ", dis: " + distributionGauss.getDistribution());
            }
            LOG.info("-----------------------------");
            LOG.info("-----------------------------");
            LOG.info("-----------------------------");

//            //gauss normal
//            double detal = LocationManager.distance2D(distributionGausses.get(0).getX(), distributionGausses.get(0).getY(),
//                    realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY());
//            totalCount++;
//            valueMean += detal;
//            dsl.insertInto(RESULT_TRACKING_DEMO,
//                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
//                    .values(indexPosition, TypeFingerprinterInfo.GAUSS_NORMAL, sessionId, distributionGausses.get(0).getX() * 1.0, distributionGausses.get(0).getY() * 1.0, detal)
//                    .execute();

//            //history


//            TrackingRecord trackingRecordOld = ktv.selectFrom(TRACKING)
//                    .where(TRACKING.CREATED_TIME.eq(listTimeTracking.get(indexTime))
//                            .and(TRACKING.ROOM_ID.eq(roomId))
//                            .and(TRACKING.SESSION_ID.eq(sessionId)))
//                    .fetchAny();
//
//
//            List<TrackingKNearestRecord> nearestRecordsOld =
//                    ktv.selectFrom(TRACKING_K_NEAREST)
//                            .where(TRACKING_K_NEAREST.TRACKING_ID.eq(trackingRecordOld.getId()))
//                            .fetch();
//            final int indexMax;
//            if (distributionGausses.size() < K_NEAREST) {
//                indexMax = distributionGausses.size();
//            } else {
//                indexMax = K_NEAREST;
//            }
//            List<ItemPositionKNearestGauss> resultKNearst = new ArrayList<>();
//            for (int i = 0; i < indexMax; i++) {
//                DistributionGauss distributionGauss = distributionGausses.get(i);
//                for (TrackingKNearestRecord trackingKNearestRecord : nearestRecordsOld) {
//                    double distance = LocationManager.distance2D(distributionGauss.getX(), distributionGauss.getY(),
//                            trackingKNearestRecord.getX(), trackingKNearestRecord.getY());
//                    ItemPositionKNearestGauss gauss = new ItemPositionKNearestGauss();
//
//                    gauss.setDistance(distance);
//                    gauss.setDistribution(distributionGauss.getDistribution());
//                    gauss.setX(distributionGauss.getX());
//                    gauss.setY(distributionGauss.getY());
//                    gauss.setMiss(distributionGauss.getNumberMiss());
//                    gauss.setToX(trackingKNearestRecord.getX());
//                    gauss.setToY(trackingKNearestRecord.getY());
//
//                    resultKNearst.add(gauss);
//                }
//            }
//
//            resultKNearst.sort((o1, o2) -> {
//                double deltaOne = Math.abs(o1.getDistance() - MAX_MOVE_SECOND);
//                double deltaTwo = Math.abs(o2.getDistance() - MAX_MOVE_SECOND);
//                if (deltaOne > deltaTwo) {
//                    return 1;
//                } else {
//                    if (deltaOne < deltaTwo) {
//                        return -1;
//                    } else {
//                        return 0;
//                    }
//                }
//            });
//
//            dsl.insertInto(RESULT_TRACKING_DEMO,
//                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y)
//                    .values(indexPosition, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_HISTORY, resultKNearst.get(0).getX() * 1.0, resultKNearst.get(0).getY() * 1.0)
//                    .execute();

            //end
//
//            //center
//            //forcus
            int max = 4;
            if (distributionGausses.size() < 4) {
                max = distributionGausses.size();
            }
//
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
//            double detal = LocationManager.distance2D(xFocus, yFocus,
//                    realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY());
//            valueMean+=detal;
//            totalCount++;
//            dsl.insertInto(RESULT_TRACKING_DEMO,
//                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
//                    .values(indexPosition, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_CENTER, xFocus, yFocus, detal)
//                    .execute();
//            //end
//
//            //forcus nearest
            List<ItemFocusPosition> itemFocusPositions = new ArrayList<>();
            for (int i = 0; i < max; i++) {
                ItemFocusPosition position = new ItemFocusPosition();
                position.setX(distributionGausses.get(i).getX());
                position.setY(distributionGausses.get(i).getY());
                position.setDistribution(distributionGausses.get(i).getDistribution());
                position.setDeltaX(xFocus - distributionGausses.get(i).getX());
                position.setDeltaY(yFocus - distributionGausses.get(i).getY());
                position.setMiss(distributionGausses.get(i).getNumberMiss());

                double distance = Math.sqrt(position.getDeltaX() * position.getDeltaX() + position.getDeltaY() * position.getDeltaY());
                position.setDistanceWithFocus(distance);
                itemFocusPositions.add(position);
            }
            itemFocusPositions.sort((o1, o2) -> {
                if (o1.getDistanceWithFocus() > o2.getDistanceWithFocus()) {
                    return 1;
                } else {
                    if (o1.getDistanceWithFocus() < o2.getDistanceWithFocus()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
//            for (ItemFocusPosition itemFocusPosition : itemFocusPositions) {
//                LOG.info("getLocationGauss " + "x = " + itemFocusPosition.getX() + " y = " + itemFocusPosition.getY() + " ,miss: " + itemFocusPosition.getMiss() + ", distribution: " + itemFocusPosition.getDistribution() + ", distance: " + itemFocusPosition.getDistanceWithFocus());
//            }
//            LOG.info("getLocationGauss -------------------------------------------------focus");
            double detal = LocationManager.distance2D(itemFocusPositions.get(0).getX(), itemFocusPositions.get(0).getY(),
                    realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY());
            totalCount++;
            valueMean += detal;
            dsl.insertInto(RESULT_TRACKING_DEMO,
                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                    .values(indexPosition, sessionId, TypeFingerprinterInfo.GAUSS_K_NEAREST_CENTER_NEAR, itemFocusPositions.get(0).getX() * 1.0, itemFocusPositions.get(0).getY() * 1.0, detal)
                    .execute();
//
            indexTime++;
        }
        LOG.info("Gauss normal: " + (valueMean / totalCount));
    }

    private void nearest(DSLContext dsl, int sessionId) throws CustomExceptionResponse {
        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.NEAREST, 10.0, 7.0, 0.0)
                .execute();

        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.K_NEAREST_CENTER, 10.0, 7.0, 0.0)
                .execute();

        dsl.insertInto(RESULT_TRACKING_DEMO,
                RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.DELTA)
                .values(0, sessionId, TypeFingerprinterInfo.K_NEAREST_CENTER_NEAR, 10.0, 7.0, 0.0)
                .execute();

        List<Integer> listIndex = dsl.select(FINGERPRINTER_TRACKING.INDEX)
                .from(FINGERPRINTER_TRACKING)
                .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId))
                .groupBy(FINGERPRINTER_TRACKING.INDEX)
                .fetch()
                .map(Record1::value1);
        int roomId = -1;
        double detailNN = 0;
        int numberNN = 0;

        double detailKNearest = 0;
        int numberKNearest = 0;


        double detailKNearestCenter = 0;
        int numberKNearestCenter = 0;
        for (Integer indexPosition : listIndex) {
            if (indexPosition == 0) {
                continue;
            }
            List<InfoReferencePointRequest> infos = dsl.selectFrom(FINGERPRINTER_TRACKING)
                    .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId)
                            .and(FINGERPRINTER_TRACKING.INDEX.eq(indexPosition)))
                    .fetch()
                    .map(record -> {
                        InfoReferencePointRequest request = new InfoReferencePointRequest();
                        request.setMacAddress(record.getMacAddress());
                        request.setName(record.getApName());
                        request.setRss(record.getRss().floatValue());
                        return request;
                    });
            if (roomId == -1) {
                roomId = dsl.select(FINGERPRINTER_TRACKING.ROOM_ID)
                        .from(FINGERPRINTER_TRACKING)
                        .where(FINGERPRINTER_TRACKING.SESSION_ID.eq(sessionId)
                                .and(FINGERPRINTER_TRACKING.INDEX.eq(indexPosition)))
                        .fetchAny().value1();
            }


            Comparator<InfoReferencePointRequest> comparator = (infoOne, infoTwo) -> {
                if (infoOne.getRss() > infoTwo.getRss()) {
                    return 1;
                } else {
                    if (infoOne.getRss() > infoTwo.getRss()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };
            infos.sort(comparator);

            //old
            List<InfoReferencePointRequest> oldInfos = new ArrayList<>();
            oldInfos.addAll(infos);
            //end

            infos =
                    infos.stream().filter(info -> {
                        if (info.getRss() < -85.0f) {
                            return false;
                        } else {
                            return true;
                        }
                    }).collect(Collectors.toList());


            final int size = infos.size();

            List<List<FingerprinterInfoGaussRecord>> listResult = null;
            List<InfoReferencePointRequest> pointRequests = new ArrayList<>();
            pointRequests.addAll(infos);
            for (int i = 0; i < size - 1; i++) {
                listResult = getListFingerPrinter(dsl, roomId, pointRequests);
                if (listResult.size() == 0) {
                    pointRequests.remove(pointRequests.size() - 1);
                } else {
                    break;
                }
            }


            if (listResult == null || listResult.size() == 0) {
                LOG.info("nearest index loss: " + indexPosition);
                continue;
            }
            List<Pair<Integer, Double>> pairs = new ArrayList<>();
            int index = 0;
            for (List<FingerprinterInfoGaussRecord> infoRecords : listResult) {
                double resultValue = 0.0;
                for (FingerprinterInfoGaussRecord infoRecord : infoRecords) {
                    for (InfoReferencePointRequest infoReferencePointRequest : infos) {
                        if (infoRecord.getMacAddress().equals(infoReferencePointRequest.getMacAddress())) {
                            resultValue = +(infoRecord.getMean() - infoReferencePointRequest.getRss()) * (infoRecord.getMean() - infoReferencePointRequest.getRss());
                            break;
                        }
                    }
                }
                resultValue = Math.sqrt(resultValue);
                pairs.add(new Pair<>(index, resultValue));
                index++;
            }

            Comparator<Pair<Integer, Double>> comparMin = (p1, p2) -> {
                if (p1.getValue() - p2.getValue() > 0) {
                    return 1;
                } else {
                    if (p1.getValue() - p2.getValue() < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            };
            pairs.sort(comparMin);


            int x = listResult.get(pairs.get(0).getKey()).get(0).getX();
            int y = listResult.get(pairs.get(0).getKey()).get(0).getY();

            //neart
//            double detal = LocationManager.distance2D(realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY(), x, y);
//            detailNN+=detal;
//            numberNN++;
//            dsl.insertInto(RESULT_TRACKING_DEMO,
//                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.DELTA)
//                    .values(indexPosition, sessionId, (double) x, (double) y, TypeFingerprinterInfo.NEAREST, detal)
//                    .execute();
            //neareat center
            int k = 4;
            if (pairs.size() < k) {
                k = pairs.size();
            }
            double totalX = 0;
            double totalY = 0;
            for (int i = 0; i < k; i++) {
                totalX = +listResult.get(pairs.get(i).getKey()).get(0).getX();
                totalY = +listResult.get(pairs.get(i).getKey()).get(0).getY();
            }
            totalX = totalX / k;
            totalY = totalY / k;
//            double detalNeart = LocationManager.distance2D(realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY(), totalX, totalY);
//            detailKNearest += detalNeart;
//            numberKNearest++;
//            dsl.insertInto(RESULT_TRACKING_DEMO,
//                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.DELTA)
//                    .values(indexPosition, sessionId, totalX, totalY, TypeFingerprinterInfo.K_NEAREST_CENTER, detalNeart)
//                    .execute();
//            //neareat center nearst
            double minX = listResult.get(pairs.get(0).getKey()).get(0).getX();
            double minY = listResult.get(pairs.get(0).getKey()).get(0).getY();
            double minDetalDistance = (totalX - minX) * (totalX - minX) + (totalY - minY) * (totalY - minY);
            minDetalDistance = Math.sqrt(minDetalDistance);

            for (int i = 1; i < k; i++) {
                double minPX = listResult.get(pairs.get(i).getKey()).get(0).getX();
                double minPY = listResult.get(pairs.get(i).getKey()).get(0).getY();
                double distance = (minPX - totalX) * (minPX - totalX) + (minPY - totalY) * (minPY - totalY);
                if (minDetalDistance > distance) {
                    minDetalDistance = distance;
                    minX = minPX;
                    minY = minPY;
                }
            }

            double detalNeartCener = LocationManager.distance2D(realLocation.get(indexPosition).getX(), realLocation.get(indexPosition).getY(), totalX, totalY);
            detailKNearestCenter += detalNeartCener;
            numberKNearestCenter++;
            dsl.insertInto(RESULT_TRACKING_DEMO,
                    RESULT_TRACKING_DEMO.INDEX, RESULT_TRACKING_DEMO.SESSION_ID, RESULT_TRACKING_DEMO.X, RESULT_TRACKING_DEMO.Y, RESULT_TRACKING_DEMO.TYPE_ALGORITHM, RESULT_TRACKING_DEMO.DELTA)
                    .values(indexPosition, sessionId, minX, minY, TypeFingerprinterInfo.K_NEAREST_CENTER_NEAR, detalNeartCener)
                    .execute();

        }

//        LOG.info("nearest detail neam KK: " + detailNN/numberNN);
//        LOG.info("nearest detail neam KK: " + (detailKNearest / numberKNearest));
        LOG.info("nearest detail neam KK center: " + (detailKNearestCenter / numberKNearestCenter));
    }

    private List<List<FingerprinterInfoGaussRecord>> getListFingerPrinter(DSLContext dsl, int roomId, List<InfoReferencePointRequest> request) {
        List<String> macAddress = new ArrayList<>();
        for (InfoReferencePointRequest infoReferencePointRequest : request) {
            macAddress.add(infoReferencePointRequest.getMacAddress());
        }
        Result<Record3<Integer, Integer, Integer>> record3s =
                dsl.select(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y, DSL.count(FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS).as("count")).from(FINGERPRINTER_INFO_GAUSS)
                        .where(
                                FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(roomId)
                                        .and(FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS.in(macAddress))
                        )
                        .groupBy(FINGERPRINTER_INFO_GAUSS.X, FINGERPRINTER_INFO_GAUSS.Y)
                        .fetch();
        List<List<FingerprinterInfoGaussRecord>> listResult = new ArrayList<>();
        for (Record3<Integer, Integer, Integer> record3 : record3s) {
            if (record3.value3() < request.size()) {
                continue;
            }
            listResult.add(
                    dsl.selectFrom(FINGERPRINTER_INFO_GAUSS)
                            .where(
                                    FINGERPRINTER_INFO_GAUSS.ROOM_ID.eq(roomId)
                                            .and(FINGERPRINTER_INFO_GAUSS.MAC_ADDRESS.in(macAddress))
                                            .and(FINGERPRINTER_INFO_GAUSS.X.eq(record3.value1()))
                                            .and(FINGERPRINTER_INFO_GAUSS.Y.eq(record3.value2()))
                            )
                            .fetch()
            );
        }
        return listResult;
    }
}
