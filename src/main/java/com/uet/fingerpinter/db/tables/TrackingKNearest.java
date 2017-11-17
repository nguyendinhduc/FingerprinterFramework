/*
 * This file is generated by jOOQ.
*/
package com.uet.fingerpinter.db.tables;


import com.uet.fingerpinter.db.Keys;
import com.uet.fingerpinter.db.Public;
import com.uet.fingerpinter.db.tables.records.TrackingKNearestRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class TrackingKNearest extends TableImpl<TrackingKNearestRecord> {

    private static final long serialVersionUID = -966906024;

    /**
     * The reference instance of <code>public.tracking_k_nearest</code>
     */
    public static final TrackingKNearest TRACKING_K_NEAREST = new TrackingKNearest();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TrackingKNearestRecord> getRecordType() {
        return TrackingKNearestRecord.class;
    }

    /**
     * The column <code>public.tracking_k_nearest.id</code>.
     */
    public final TableField<TrackingKNearestRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('tracking_k_nearest_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.tracking_k_nearest.tracking_id</code>.
     */
    public final TableField<TrackingKNearestRecord, Integer> TRACKING_ID = createField("tracking_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.tracking_k_nearest.distribution</code>.
     */
    public final TableField<TrackingKNearestRecord, Double> DISTRIBUTION = createField("distribution", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.tracking_k_nearest.x</code>.
     */
    public final TableField<TrackingKNearestRecord, Integer> X = createField("x", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.tracking_k_nearest.y</code>.
     */
    public final TableField<TrackingKNearestRecord, Integer> Y = createField("y", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.tracking_k_nearest</code> table reference
     */
    public TrackingKNearest() {
        this("tracking_k_nearest", null);
    }

    /**
     * Create an aliased <code>public.tracking_k_nearest</code> table reference
     */
    public TrackingKNearest(String alias) {
        this(alias, TRACKING_K_NEAREST);
    }

    private TrackingKNearest(String alias, Table<TrackingKNearestRecord> aliased) {
        this(alias, aliased, null);
    }

    private TrackingKNearest(String alias, Table<TrackingKNearestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TrackingKNearestRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TRACKING_K_NEAREST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TrackingKNearestRecord> getPrimaryKey() {
        return Keys.TRACKING_K_NEAREST_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TrackingKNearestRecord>> getKeys() {
        return Arrays.<UniqueKey<TrackingKNearestRecord>>asList(Keys.TRACKING_K_NEAREST_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TrackingKNearestRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TrackingKNearestRecord, ?>>asList(Keys.TRACKING_K_NEAREST__TRACKING_K_NEAREST_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TrackingKNearest as(String alias) {
        return new TrackingKNearest(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TrackingKNearest rename(String name) {
        return new TrackingKNearest(name, null);
    }
}
