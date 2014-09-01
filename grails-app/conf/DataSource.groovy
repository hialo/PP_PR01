dataSource {
    pooled = true

     // Change the values of these properties to the username, password and hostname
    // for your SQL Server database
    //driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
    //dialect = "org.hibernate.dialect.SQLServerDialect"
  //driverClassName = "com.mysql.jdbc.Driver"
  //dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
  driverClassName = "org.postgresql.Driver"
  dialect = 'org.hibernate.dialect.PostgreSQLDialect'
    logSql = true

}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
          //url = "jdbc:sqlserver://172.25.13.222:1433;databaseName=master"
          //username = "root"
          //password = "root"
          //url = "jdbc:mysql://localhost/ProjectMe"
          //username = "root"
          //password = ""
          url = "jdbc:postgresql://localhost:5432/SAHH_WEB"
          username = "postgres"
          password = "postgres"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
          //url = "jdbc:sqlserver://172.25.13.222:1433;databaseName=master"
          //username = "root"
          //password = "root"
          //url = "jdbc:mysql://localhost/ProjectMe"
          //username = "root"
          //password = ""
          url = "jdbc:postgresql://localhost:5432/SAHH_WEB"
          username = "postgres"
          password = "postgres"
        }
    }
    production {
        dataSource {
          dbCreate = "update"
        //url = "jdbc:sqlserver://172.25.13.222:1433;databaseName=master"
        //username = "root"
        //password = "root"
        //url = "jdbc:mysql://localhost/ProjectMe"
        //username = "root"
        //password = ""
          url = "jdbc:postgresql://localhost:5432/SAHH_WEB"
          username = "postgres"
          password = "postgres"
        }
    }
}
