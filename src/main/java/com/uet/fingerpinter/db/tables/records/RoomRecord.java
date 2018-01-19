/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables.records;


import com.uet.fingerpinter.db.tables.Room;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class RoomRecord extends UpdatableRecordImpl<RoomRecord> implements Record3<Integer, String, Integer> {

    private static final long serialVersionUID = -2090518814;

    /**
     * Setter for <code>location_indoor.room.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>location_indoor.room.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>location_indoor.room.room_name</code>.
     */
    public void setRoomName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>location_indoor.room.room_name</code>.
     */
    public String getRoomName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>location_indoor.room.building_id</code>.
     */
    public void setBuildingId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>location_indoor.room.building_id</code>.
     */
    public Integer getBuildingId() {
        return (Integer) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Room.ROOM.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Room.ROOM.ROOM_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Room.ROOM.BUILDING_ID;
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
        return getRoomName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getBuildingId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomRecord value2(String value) {
        setRoomName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomRecord value3(Integer value) {
        setBuildingId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomRecord values(Integer value1, String value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RoomRecord
     */
    public RoomRecord() {
        super(Room.ROOM);
    }

    /**
     * Create a detached, initialised RoomRecord
     */
    public RoomRecord(Integer id, String roomName, Integer buildingId) {
        super(Room.ROOM);

        set(0, id);
        set(1, roomName);
        set(2, buildingId);
    }
}
