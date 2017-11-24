/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db;


import com.uet.fingerpinter.db.tables.Building;
import com.uet.fingerpinter.db.tables.FingerprinterInfo;
import com.uet.fingerpinter.db.tables.FingerprinterInfoDetail;
import com.uet.fingerpinter.db.tables.FingerprinterInfoGauss;
import com.uet.fingerpinter.db.tables.Room;
import com.uet.fingerpinter.db.tables.SessionTracking;
import com.uet.fingerpinter.db.tables.TrackReal;
import com.uet.fingerpinter.db.tables.Tracking;
import com.uet.fingerpinter.db.tables.TrackingKNearest;
import com.uet.fingerpinter.db.tables.User;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.building</code>.
     */
    public static final Building BUILDING = com.uet.fingerpinter.db.tables.Building.BUILDING;

    /**
     * The table <code>public.fingerprinter_info</code>.
     */
    public static final FingerprinterInfo FINGERPRINTER_INFO = com.uet.fingerpinter.db.tables.FingerprinterInfo.FINGERPRINTER_INFO;

    /**
     * The table <code>public.fingerprinter_info_detail</code>.
     */
    public static final FingerprinterInfoDetail FINGERPRINTER_INFO_DETAIL = com.uet.fingerpinter.db.tables.FingerprinterInfoDetail.FINGERPRINTER_INFO_DETAIL;

    /**
     * The table <code>public.fingerprinter_info_gauss</code>.
     */
    public static final FingerprinterInfoGauss FINGERPRINTER_INFO_GAUSS = com.uet.fingerpinter.db.tables.FingerprinterInfoGauss.FINGERPRINTER_INFO_GAUSS;

    /**
     * The table <code>public.room</code>.
     */
    public static final Room ROOM = com.uet.fingerpinter.db.tables.Room.ROOM;

    /**
     * The table <code>public.session_tracking</code>.
     */
    public static final SessionTracking SESSION_TRACKING = com.uet.fingerpinter.db.tables.SessionTracking.SESSION_TRACKING;

    /**
     * The table <code>public.track_real</code>.
     */
    public static final TrackReal TRACK_REAL = com.uet.fingerpinter.db.tables.TrackReal.TRACK_REAL;

    /**
     * The table <code>public.tracking</code>.
     */
    public static final Tracking TRACKING = com.uet.fingerpinter.db.tables.Tracking.TRACKING;

    /**
     * The table <code>public.tracking_k_nearest</code>.
     */
    public static final TrackingKNearest TRACKING_K_NEAREST = com.uet.fingerpinter.db.tables.TrackingKNearest.TRACKING_K_NEAREST;

    /**
     * The table <code>public.user</code>.
     */
    public static final User USER = com.uet.fingerpinter.db.tables.User.USER;
}
