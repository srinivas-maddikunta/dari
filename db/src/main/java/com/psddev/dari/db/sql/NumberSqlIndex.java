package com.psddev.dari.db.sql;

import com.psddev.dari.db.ObjectIndex;
import com.psddev.dari.util.CompactMap;
import com.psddev.dari.util.ObjectUtils;
import org.jooq.Param;
import org.jooq.impl.DSL;

import java.util.Map;

class NumberSqlIndex extends AbstractSqlIndex {

    private final Param<Double> valueParam;

    public NumberSqlIndex(SqlSchema schema, String namePrefix, int version) {
        super(schema, namePrefix, version);

        this.valueParam = DSL.param("value", schema.doubleDataType());
    }

    @Override
    public Object valueParam() {
        return valueParam;
    }

    @Override
    public Map<String, Object> createBindValues(AbstractSqlDatabase database, SqlSchema schema, ObjectIndex index, int fieldIndex, Object value) {
        Double valueDouble = ObjectUtils.to(Double.class, value);

        if (valueDouble == null) {
            return null;

        } else {
            Map<String, Object> bindValues = new CompactMap<>();
            bindValues.put(valueParam.getName(), valueDouble);
            return bindValues;
        }
    }
}
