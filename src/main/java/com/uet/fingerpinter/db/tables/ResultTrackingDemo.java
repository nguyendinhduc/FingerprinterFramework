/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables;


import com.uet.fingerpinter.db.Keys;
import com.uet.fingerpinter.db.LocationIndoor;
import com.uet.fingerpinter.db.tables.records.ResultTrackingDemoRecord;
import com.uet.fingerpinter.jooq.convert.TypeFingerprinterInfoConvertter;
import com.uet.fingerpinter.jooq.type.TypeFingerprinterInfo;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class ResultTrackingDemo extends TableImpl<ResultTrackingDemoRecord> {

    private static final long serialVersionUID = -612871562;

    /**
     * The reference instance of <code>location_indoor.result_tracking_demo</code>
     */
    public static final ResultTrackingDemo RESULT_TRACKING_DEMO = new ResultTrackingDemo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ResultTrackingDemoRecord> getRecordType() {
        return ResultTrackingDemoRecord.class;
    }

    /**
     * The column <code>location_indoor.result_tracking_demo.id</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>location_indoor.result_tracking_demo.type_algorithm</code>.
     */
    public final TableField<ResultTrackingDemoRecord, TypeFingerprinterInfo> TYPE_ALGORITHM = createField("type_algorithm", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "", new TypeFingerprinterInfoConvertter());

    /**
     * The column <code>location_indoor.result_tracking_demo.index</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Integer> INDEX = createField("index", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>location_indoor.result_tracking_demo.x</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Double> X = createField("x", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>location_indoor.result_tracking_demo.y</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Double> Y = createField("y", org.jooq.impl.SQLDataType.FLOAT.nullable(false), this, "");

    /**
     * The column <code>location_indoor.result_tracking_demo.session_id</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Integer> SESSION_ID = createField("session_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>location_indoor.result_tracking_demo.delta</code>.
     */
    public final TableField<ResultTrackingDemoRecord, Double> DELTA = createField("delta", org.jooq.impl.SQLDataType.FLOAT, this, "");

    /**
     * Create a <code>location_indoor.result_tracking_demo</code> table reference
     */
    public ResultTrackingDemo() {
        this("result_tracking_demo", null);
    }

    /**
     * Create an aliased <code>location_indoor.result_tracking_demo</code> table reference
     */
    public ResultTrackingDemo(String alias) {
        this(alias, RESULT_TRACKING_DEMO);
    }

    private ResultTrackingDemo(String alias, Table<ResultTrackingDemoRecord> aliased) {
        this(alias, aliased, null);
    }

    private ResultTrackingDemo(String alias, Table<ResultTrackingDemoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return LocationIndoor.LOCATION_INDOOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ResultTrackingDemoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_RESULT_TRACKING_DEMO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ResultTrackingDemoRecord> getPrimaryKey() {
        return Keys.KEY_RESULT_TRACKING_DEMO_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ResultTrackingDemoRecord>> getKeys() {
        return Arrays.<UniqueKey<ResultTrackingDemoRecord>>asList(Keys.KEY_RESULT_TRACKING_DEMO_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultTrackingDemo as(String alias) {
        return new ResultTrackingDemo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ResultTrackingDemo rename(String name) {
        return new ResultTrackingDemo(name, null);
    }
}
