package com.gac.xs6.bigdata.dao

import java.sql.{Connection, DriverManager}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: mysql连接器
  */
object MysqlUtil {

  private val config = ConfigFactory.load()
  lazy val cassConfig = config.getConfig("mysql")
  lazy val mysqlUrl = cassConfig.getString("mysql.url")
  lazy val username = cassConfig.getString("mysql.username")
  lazy val password = cassConfig.getString("mysql.password")
  val logger = LoggerFactory.getLogger(this.getClass)

  def getConnection:Connection ={
    var conn:Connection=null
    try{
      Class.forName("com.mysql.jdbc.Driver")
      conn=DriverManager.getConnection(mysqlUrl, username, password);
    } catch {
      case exception:Exception=>
        logger.warn("Error in creation of connection pool"+exception.printStackTrace())
        exception.printStackTrace
    }

    conn
  }
  def closeConnection(connection:Connection): Unit = {
    if(null!=connection) {
         connection.close()
    }
  }
}