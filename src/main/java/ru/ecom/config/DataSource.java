package ru.ecom.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static PropertyLoader propertyLoader = PropertyLoader.getInstance();

    static {
        config.setJdbcUrl(propertyLoader.getProperty("jdbc.url"));
        config.setUsername(propertyLoader.getProperty("jdbc.user"));
        config.setPassword(propertyLoader.getProperty("jdbc.password"));
        config.setMaximumPoolSize(Integer.parseInt(propertyLoader.getProperty("hikari.maximum.pool.size")));
        config.setMinimumIdle(Integer.parseInt(propertyLoader.getProperty("hikari.minimum.idle")));
        config.addDataSourceProperty( "cachePrepStmts" , propertyLoader.getProperty("hikari.cachePrepStmts"));
        config.addDataSourceProperty( "prepStmtCacheSize" , propertyLoader.getProperty("hikari.prepStmtCacheSize") );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , propertyLoader.getProperty("hikari.prepStmtCacheSqlLimit") );
        config.addDataSourceProperty("useServerPrepStmts", propertyLoader.getProperty("hikari.useServerPrepStmts"));
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static synchronized Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}