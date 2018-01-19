/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db;


import com.uet.fingerpinter.db.tables.Building;
import com.uet.fingerpinter.db.tables.FingerprinterInfo;
import com.uet.fingerpinter.db.tables.FingerprinterInfoDetail;
import com.uet.fingerpinter.db.tables.FingerprinterInfoGauss;
import com.uet.fingerpinter.db.tables.FingerprinterTracking;
import com.uet.fingerpinter.db.tables.ResultTrackingDemo;
import com.uet.fingerpinter.db.tables.Room;
import com.uet.fingerpinter.db.tables.SessionTracking;
import com.uet.fingerpinter.db.tables.TrackReal;
import com.uet.fingerpinter.db.tables.Tracking;
import com.uet.fingerpinter.db.tables.TrackingKNearest;
import com.uet.fingerpinter.db.tables.User;
import com.uet.fingerpinter.db.tables.records.BuildingRecord;
import com.uet.fingerpinter.db.tables.records.FingerprinterInfoDetailRecord;
import com.uet.fingerpinter.db.tables.records.FingerprinterInfoGaussRecord;
import com.uet.fingerpinter.db.tables.records.FingerprinterInfoRecord;
import com.uet.fingerpinter.db.tables.records.FingerprinterTrackingRecord;
import com.uet.fingerpinter.db.tables.records.ResultTrackingDemoRecord;
import com.uet.fingerpinter.db.tables.records.RoomRecord;
import com.uet.fingerpinter.db.tables.records.SessionTrackingRecord;
import com.uet.fingerpinter.db.tables.records.TrackRealRecord;
import com.uet.fingerpinter.db.tables.records.TrackingKNearestRecord;
import com.uet.fingerpinter.db.tables.records.TrackingRecord;
import com.uet.fingerpinter.db.tables.records.UserRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>location_indoor</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<BuildingRecord, Integer> IDENTITY_BUILDING = Identities0.IDENTITY_BUILDING;
    public static final Identity<FingerprinterInfoRecord, Integer> IDENTITY_FINGERPRINTER_INFO = Identities0.IDENTITY_FINGERPRINTER_INFO;
    public static final Identity<FingerprinterInfoDetailRecord, Integer> IDENTITY_FINGERPRINTER_INFO_DETAIL = Identities0.IDENTITY_FINGERPRINTER_INFO_DETAIL;
    public static final Identity<FingerprinterInfoGaussRecord, Integer> IDENTITY_FINGERPRINTER_INFO_GAUSS = Identities0.IDENTITY_FINGERPRINTER_INFO_GAUSS;
    public static final Identity<FingerprinterTrackingRecord, Integer> IDENTITY_FINGERPRINTER_TRACKING = Identities0.IDENTITY_FINGERPRINTER_TRACKING;
    public static final Identity<ResultTrackingDemoRecord, Integer> IDENTITY_RESULT_TRACKING_DEMO = Identities0.IDENTITY_RESULT_TRACKING_DEMO;
    public static final Identity<RoomRecord, Integer> IDENTITY_ROOM = Identities0.IDENTITY_ROOM;
    public static final Identity<SessionTrackingRecord, Integer> IDENTITY_SESSION_TRACKING = Identities0.IDENTITY_SESSION_TRACKING;
    public static final Identity<TrackingRecord, Integer> IDENTITY_TRACKING = Identities0.IDENTITY_TRACKING;
    public static final Identity<TrackingKNearestRecord, Integer> IDENTITY_TRACKING_K_NEAREST = Identities0.IDENTITY_TRACKING_K_NEAREST;
    public static final Identity<TrackRealRecord, Integer> IDENTITY_TRACK_REAL = Identities0.IDENTITY_TRACK_REAL;
    public static final Identity<UserRecord, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BuildingRecord> KEY_BUILDING_PRIMARY = UniqueKeys0.KEY_BUILDING_PRIMARY;
    public static final UniqueKey<FingerprinterInfoRecord> KEY_FINGERPRINTER_INFO_PRIMARY = UniqueKeys0.KEY_FINGERPRINTER_INFO_PRIMARY;
    public static final UniqueKey<FingerprinterInfoDetailRecord> KEY_FINGERPRINTER_INFO_DETAIL_PRIMARY = UniqueKeys0.KEY_FINGERPRINTER_INFO_DETAIL_PRIMARY;
    public static final UniqueKey<FingerprinterInfoGaussRecord> KEY_FINGERPRINTER_INFO_GAUSS_PRIMARY = UniqueKeys0.KEY_FINGERPRINTER_INFO_GAUSS_PRIMARY;
    public static final UniqueKey<FingerprinterTrackingRecord> KEY_FINGERPRINTER_TRACKING_PRIMARY = UniqueKeys0.KEY_FINGERPRINTER_TRACKING_PRIMARY;
    public static final UniqueKey<ResultTrackingDemoRecord> KEY_RESULT_TRACKING_DEMO_PRIMARY = UniqueKeys0.KEY_RESULT_TRACKING_DEMO_PRIMARY;
    public static final UniqueKey<RoomRecord> KEY_ROOM_PRIMARY = UniqueKeys0.KEY_ROOM_PRIMARY;
    public static final UniqueKey<SessionTrackingRecord> KEY_SESSION_TRACKING_PRIMARY = UniqueKeys0.KEY_SESSION_TRACKING_PRIMARY;
    public static final UniqueKey<TrackingRecord> KEY_TRACKING_PRIMARY = UniqueKeys0.KEY_TRACKING_PRIMARY;
    public static final UniqueKey<TrackingKNearestRecord> KEY_TRACKING_K_NEAREST_PRIMARY = UniqueKeys0.KEY_TRACKING_K_NEAREST_PRIMARY;
    public static final UniqueKey<TrackRealRecord> KEY_TRACK_REAL_PRIMARY = UniqueKeys0.KEY_TRACK_REAL_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<FingerprinterInfoRecord, RoomRecord> FK_FINGERPRINGER_INFO_2 = ForeignKeys0.FK_FINGERPRINGER_INFO_2;
    public static final ForeignKey<FingerprinterInfoRecord, BuildingRecord> FK_FINGERPRINGER_INFO_1 = ForeignKeys0.FK_FINGERPRINGER_INFO_1;
    public static final ForeignKey<FingerprinterInfoGaussRecord, RoomRecord> FINGERPRINTER_INFO_GAUSS_1 = ForeignKeys0.FINGERPRINTER_INFO_GAUSS_1;
    public static final ForeignKey<RoomRecord, BuildingRecord> FK_ROOM_1 = ForeignKeys0.FK_ROOM_1;
    public static final ForeignKey<SessionTrackingRecord, TrackingRecord> SESSION_TRACKING_1 = ForeignKeys0.SESSION_TRACKING_1;
    public static final ForeignKey<SessionTrackingRecord, TrackingRecord> SESSION_TRACKING_2 = ForeignKeys0.SESSION_TRACKING_2;
    public static final ForeignKey<TrackingRecord, RoomRecord> TRACKING_GAUSS_1 = ForeignKeys0.TRACKING_GAUSS_1;
    public static final ForeignKey<TrackingKNearestRecord, TrackingRecord> TRACKING_K_NEAREST = ForeignKeys0.TRACKING_K_NEAREST;
    public static final ForeignKey<TrackRealRecord, RoomRecord> KEY_ROOM_ID = ForeignKeys0.KEY_ROOM_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<BuildingRecord, Integer> IDENTITY_BUILDING = createIdentity(Building.BUILDING, Building.BUILDING.ID);
        public static Identity<FingerprinterInfoRecord, Integer> IDENTITY_FINGERPRINTER_INFO = createIdentity(FingerprinterInfo.FINGERPRINTER_INFO, FingerprinterInfo.FINGERPRINTER_INFO.ID);
        public static Identity<FingerprinterInfoDetailRecord, Integer> IDENTITY_FINGERPRINTER_INFO_DETAIL = createIdentity(FingerprinterInfoDetail.FINGERPRINTER_INFO_DETAIL, FingerprinterInfoDetail.FINGERPRINTER_INFO_DETAIL.ID);
        public static Identity<FingerprinterInfoGaussRecord, Integer> IDENTITY_FINGERPRINTER_INFO_GAUSS = createIdentity(FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS, FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS.ID);
        public static Identity<FingerprinterTrackingRecord, Integer> IDENTITY_FINGERPRINTER_TRACKING = createIdentity(FingerprinterTracking.FINGERPRINTER_TRACKING, FingerprinterTracking.FINGERPRINTER_TRACKING.ID);
        public static Identity<ResultTrackingDemoRecord, Integer> IDENTITY_RESULT_TRACKING_DEMO = createIdentity(ResultTrackingDemo.RESULT_TRACKING_DEMO, ResultTrackingDemo.RESULT_TRACKING_DEMO.ID);
        public static Identity<RoomRecord, Integer> IDENTITY_ROOM = createIdentity(Room.ROOM, Room.ROOM.ID);
        public static Identity<SessionTrackingRecord, Integer> IDENTITY_SESSION_TRACKING = createIdentity(SessionTracking.SESSION_TRACKING, SessionTracking.SESSION_TRACKING.ID);
        public static Identity<TrackingRecord, Integer> IDENTITY_TRACKING = createIdentity(Tracking.TRACKING, Tracking.TRACKING.ID);
        public static Identity<TrackingKNearestRecord, Integer> IDENTITY_TRACKING_K_NEAREST = createIdentity(TrackingKNearest.TRACKING_K_NEAREST, TrackingKNearest.TRACKING_K_NEAREST.ID);
        public static Identity<TrackRealRecord, Integer> IDENTITY_TRACK_REAL = createIdentity(TrackReal.TRACK_REAL, TrackReal.TRACK_REAL.ID);
        public static Identity<UserRecord, Integer> IDENTITY_USER = createIdentity(User.USER, User.USER.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<BuildingRecord> KEY_BUILDING_PRIMARY = createUniqueKey(Building.BUILDING, "KEY_building_PRIMARY", Building.BUILDING.ID);
        public static final UniqueKey<FingerprinterInfoRecord> KEY_FINGERPRINTER_INFO_PRIMARY = createUniqueKey(FingerprinterInfo.FINGERPRINTER_INFO, "KEY_fingerprinter_info_PRIMARY", FingerprinterInfo.FINGERPRINTER_INFO.ID);
        public static final UniqueKey<FingerprinterInfoDetailRecord> KEY_FINGERPRINTER_INFO_DETAIL_PRIMARY = createUniqueKey(FingerprinterInfoDetail.FINGERPRINTER_INFO_DETAIL, "KEY_fingerprinter_info_detail_PRIMARY", FingerprinterInfoDetail.FINGERPRINTER_INFO_DETAIL.ID);
        public static final UniqueKey<FingerprinterInfoGaussRecord> KEY_FINGERPRINTER_INFO_GAUSS_PRIMARY = createUniqueKey(FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS, "KEY_fingerprinter_info_gauss_PRIMARY", FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS.ID);
        public static final UniqueKey<FingerprinterTrackingRecord> KEY_FINGERPRINTER_TRACKING_PRIMARY = createUniqueKey(FingerprinterTracking.FINGERPRINTER_TRACKING, "KEY_fingerprinter_tracking_PRIMARY", FingerprinterTracking.FINGERPRINTER_TRACKING.ID);
        public static final UniqueKey<ResultTrackingDemoRecord> KEY_RESULT_TRACKING_DEMO_PRIMARY = createUniqueKey(ResultTrackingDemo.RESULT_TRACKING_DEMO, "KEY_result_tracking_demo_PRIMARY", ResultTrackingDemo.RESULT_TRACKING_DEMO.ID);
        public static final UniqueKey<RoomRecord> KEY_ROOM_PRIMARY = createUniqueKey(Room.ROOM, "KEY_room_PRIMARY", Room.ROOM.ID);
        public static final UniqueKey<SessionTrackingRecord> KEY_SESSION_TRACKING_PRIMARY = createUniqueKey(SessionTracking.SESSION_TRACKING, "KEY_session_tracking_PRIMARY", SessionTracking.SESSION_TRACKING.ID);
        public static final UniqueKey<TrackingRecord> KEY_TRACKING_PRIMARY = createUniqueKey(Tracking.TRACKING, "KEY_tracking_PRIMARY", Tracking.TRACKING.ID);
        public static final UniqueKey<TrackingKNearestRecord> KEY_TRACKING_K_NEAREST_PRIMARY = createUniqueKey(TrackingKNearest.TRACKING_K_NEAREST, "KEY_tracking_k_nearest_PRIMARY", TrackingKNearest.TRACKING_K_NEAREST.ID);
        public static final UniqueKey<TrackRealRecord> KEY_TRACK_REAL_PRIMARY = createUniqueKey(TrackReal.TRACK_REAL, "KEY_track_real_PRIMARY", TrackReal.TRACK_REAL.ID);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<FingerprinterInfoRecord, RoomRecord> FK_FINGERPRINGER_INFO_2 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_ROOM_PRIMARY, FingerprinterInfo.FINGERPRINTER_INFO, "fk_fingerpringer_info_2", FingerprinterInfo.FINGERPRINTER_INFO.ROOM_ID);
        public static final ForeignKey<FingerprinterInfoRecord, BuildingRecord> FK_FINGERPRINGER_INFO_1 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_BUILDING_PRIMARY, FingerprinterInfo.FINGERPRINTER_INFO, "fk_fingerpringer_info_1", FingerprinterInfo.FINGERPRINTER_INFO.BUILDING_ID);
        public static final ForeignKey<FingerprinterInfoGaussRecord, RoomRecord> FINGERPRINTER_INFO_GAUSS_1 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_ROOM_PRIMARY, FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS, "fingerprinter_info_gauss_1", FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS.ROOM_ID);
        public static final ForeignKey<RoomRecord, BuildingRecord> FK_ROOM_1 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_BUILDING_PRIMARY, Room.ROOM, "fk_room_1", Room.ROOM.BUILDING_ID);
        public static final ForeignKey<SessionTrackingRecord, TrackingRecord> SESSION_TRACKING_1 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_TRACKING_PRIMARY, SessionTracking.SESSION_TRACKING, "session_tracking_1", SessionTracking.SESSION_TRACKING.TRACKING_START_ID);
        public static final ForeignKey<SessionTrackingRecord, TrackingRecord> SESSION_TRACKING_2 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_TRACKING_PRIMARY, SessionTracking.SESSION_TRACKING, "session_tracking_2", SessionTracking.SESSION_TRACKING.TRACKING_END_ID);
        public static final ForeignKey<TrackingRecord, RoomRecord> TRACKING_GAUSS_1 = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_ROOM_PRIMARY, Tracking.TRACKING, "tracking_gauss_1", Tracking.TRACKING.ROOM_ID);
        public static final ForeignKey<TrackingKNearestRecord, TrackingRecord> TRACKING_K_NEAREST = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_TRACKING_PRIMARY, TrackingKNearest.TRACKING_K_NEAREST, "tracking_k_nearest", TrackingKNearest.TRACKING_K_NEAREST.TRACKING_ID);
        public static final ForeignKey<TrackRealRecord, RoomRecord> KEY_ROOM_ID = createForeignKey(com.uet.fingerpinter.db.Keys.KEY_ROOM_PRIMARY, TrackReal.TRACK_REAL, "key_room_id", TrackReal.TRACK_REAL.ROOM_ID);
    }
}
