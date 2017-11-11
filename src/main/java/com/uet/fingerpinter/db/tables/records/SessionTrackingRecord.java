/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables.records;


import com.uet.fingerpinter.db.tables.SessionTracking;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SessionTrackingRecord extends UpdatableRecordImpl<SessionTrackingRecord> implements Record5<Integer, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = -391501432;

    /**
     * Setter for <code>location_indoor.session_tracking.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>location_indoor.session_tracking.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>location_indoor.session_tracking.tracking_start_id</code>.
     */
    public void setTrackingStartId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>location_indoor.session_tracking.tracking_start_id</code>.
     */
    public Integer getTrackingStartId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>location_indoor.session_tracking.tracking_end_id</code>.
     */
    public void setTrackingEndId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>location_indoor.session_tracking.tracking_end_id</code>.
     */
    public Integer getTrackingEndId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>location_indoor.session_tracking.created_time</code>.
     */
    public void setCreatedTime(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>location_indoor.session_tracking.created_time</code>.
     */
    public Timestamp getCreatedTime() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>location_indoor.session_tracking.update_time</code>.
     */
    public void setUpdateTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>location_indoor.session_tracking.update_time</code>.
     */
    public Timestamp getUpdateTime() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return SessionTracking.SESSION_TRACKING.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return SessionTracking.SESSION_TRACKING.TRACKING_START_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return SessionTracking.SESSION_TRACKING.TRACKING_END_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return SessionTracking.SESSION_TRACKING.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return SessionTracking.SESSION_TRACKING.UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getTrackingStartId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getTrackingEndId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord value2(Integer value) {
        setTrackingStartId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord value3(Integer value) {
        setTrackingEndId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord value4(Timestamp value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord value5(Timestamp value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionTrackingRecord values(Integer value1, Integer value2, Integer value3, Timestamp value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SessionTrackingRecord
     */
    public SessionTrackingRecord() {
        super(SessionTracking.SESSION_TRACKING);
    }

    /**
     * Create a detached, initialised SessionTrackingRecord
     */
    public SessionTrackingRecord(Integer id, Integer trackingStartId, Integer trackingEndId, Timestamp createdTime, Timestamp updateTime) {
        super(SessionTracking.SESSION_TRACKING);

        set(0, id);
        set(1, trackingStartId);
        set(2, trackingEndId);
        set(3, createdTime);
        set(4, updateTime);
    }
}