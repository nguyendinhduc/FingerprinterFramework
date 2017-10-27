/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables.records;


import com.uet.fingerpinter.db.tables.FingerprinterInfo;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
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
public class FingerprinterInfoRecord extends UpdatableRecordImpl<FingerprinterInfoRecord> implements Record8<Integer, String, String, Double, Integer, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1942493316;

    /**
     * Setter for <code>sql12200503.fingerprinter_info.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.ap_name</code>.
     */
    public void setApName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.ap_name</code>.
     */
    public String getApName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.mac_address</code>.
     */
    public void setMacAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.mac_address</code>.
     */
    public String getMacAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.rss</code>.
     */
    public void setRss(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.rss</code>.
     */
    public Double getRss() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.x</code>.
     */
    public void setX(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.x</code>.
     */
    public Integer getX() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.y</code>.
     */
    public void setY(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.y</code>.
     */
    public Integer getY() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.room_id</code>.
     */
    public void setRoomId(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.room_id</code>.
     */
    public Integer getRoomId() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>sql12200503.fingerprinter_info.building_id</code>.
     */
    public void setBuildingId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>sql12200503.fingerprinter_info.building_id</code>.
     */
    public Integer getBuildingId() {
        return (Integer) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Double, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Double, Integer, Integer, Integer, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return FingerprinterInfo.FINGERPRINTER_INFO.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return FingerprinterInfo.FINGERPRINTER_INFO.AP_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return FingerprinterInfo.FINGERPRINTER_INFO.MAC_ADDRESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Double> field4() {
        return FingerprinterInfo.FINGERPRINTER_INFO.RSS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return FingerprinterInfo.FINGERPRINTER_INFO.X;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return FingerprinterInfo.FINGERPRINTER_INFO.Y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return FingerprinterInfo.FINGERPRINTER_INFO.ROOM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return FingerprinterInfo.FINGERPRINTER_INFO.BUILDING_ID;
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
    public String value2() {
        return getApName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getMacAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double value4() {
        return getRss();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getRoomId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getBuildingId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value2(String value) {
        setApName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value3(String value) {
        setMacAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value4(Double value) {
        setRss(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value5(Integer value) {
        setX(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value6(Integer value) {
        setY(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value7(Integer value) {
        setRoomId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord value8(Integer value) {
        setBuildingId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FingerprinterInfoRecord values(Integer value1, String value2, String value3, Double value4, Integer value5, Integer value6, Integer value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FingerprinterInfoRecord
     */
    public FingerprinterInfoRecord() {
        super(FingerprinterInfo.FINGERPRINTER_INFO);
    }

    /**
     * Create a detached, initialised FingerprinterInfoRecord
     */
    public FingerprinterInfoRecord(Integer id, String apName, String macAddress, Double rss, Integer x, Integer y, Integer roomId, Integer buildingId) {
        super(FingerprinterInfo.FINGERPRINTER_INFO);

        set(0, id);
        set(1, apName);
        set(2, macAddress);
        set(3, rss);
        set(4, x);
        set(5, y);
        set(6, roomId);
        set(7, buildingId);
    }
}
