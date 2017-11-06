/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables.records;


import com.uet.fingerpinter.db.tables.Building;

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
public class BuildingRecord extends UpdatableRecordImpl<BuildingRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 109264012;

    /**
     * Setter for <code>public.building.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.building.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.building.building_name</code>.
     */
    public void setBuildingName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.building.building_name</code>.
     */
    public String getBuildingName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.building.building_address</code>.
     */
    public void setBuildingAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.building.building_address</code>.
     */
    public String getBuildingAddress() {
        return (String) get(2);
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
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Building.BUILDING.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Building.BUILDING.BUILDING_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Building.BUILDING.BUILDING_ADDRESS;
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
        return getBuildingName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getBuildingAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingRecord value2(String value) {
        setBuildingName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingRecord value3(String value) {
        setBuildingAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BuildingRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BuildingRecord
     */
    public BuildingRecord() {
        super(Building.BUILDING);
    }

    /**
     * Create a detached, initialised BuildingRecord
     */
    public BuildingRecord(Integer id, String buildingName, String buildingAddress) {
        super(Building.BUILDING);

        set(0, id);
        set(1, buildingName);
        set(2, buildingAddress);
    }
}
