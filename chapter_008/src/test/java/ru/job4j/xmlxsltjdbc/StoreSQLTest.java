package ru.job4j.xmlxsltjdbc;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StoreSQLTest {

    private StoreSQL getStoreSql() {
        Config config = new Config();
        config.init();
        StoreSQL storeSQL = new StoreSQL(config);
        storeSQL.init();
        return storeSQL;
    }

    @Test
    public void init() {
        Config config = new Config();
        config.init();
        StoreSQL storeSQL = new StoreSQL(config);
        assertTrue(storeSQL.init());
    }

    @Test
    public void generate() throws SQLException {
        StoreSQL sql = getStoreSql();
        sql.generate(5);
        List<StoreSQL.Entry> list = sql.load();
        sql.clear();
        assertThat(list.size(), Is.is(5));
    }
}