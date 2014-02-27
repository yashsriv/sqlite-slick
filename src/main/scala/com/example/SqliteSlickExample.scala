package com.example

import scala.slick.session.Database
import Database.threadLocalSession
import scala.slick.jdbc.StaticQuery
import scala.slick.driver.SQLiteDriver.simple._
import java.sql.Timestamp

object SqliteSlickExample {
  def main(args: Array[String]) {

    Database.forURL("jdbc:sqlite:database.db", driver = "org.sqlite.JDBC") withSession {
      Posts.ddl.create

      Posts.insertAll(
        (1, 100001, new Timestamp(System.currentTimeMillis() - 100000), "Title 1"),
        (2, 100001, new Timestamp(System.currentTimeMillis() - 90000), "Title 2"),
        (3, 100002, new Timestamp(System.currentTimeMillis() - 80000), "Title 3"),
        (4, 100002, new Timestamp(System.currentTimeMillis() - 70000), "Title 4"),
        (5, 100001, new Timestamp(System.currentTimeMillis() - 60000), "Title 5"),
        (6, 100003, new Timestamp(System.currentTimeMillis() - 50000), "Title 6"),
        (7, 100004, new Timestamp(System.currentTimeMillis() - 40000), "Title 7"),
        (8, 100004, new Timestamp(System.currentTimeMillis() - 30000), "Title 8"),
        (9, 100002, new Timestamp(System.currentTimeMillis() - 20000), "Title 9"),
        (10, 100002, new Timestamp(System.currentTimeMillis() - 10000), "Title 10"),
        (11, 100001, new Timestamp(System.currentTimeMillis() - 9000), "Title 11"),
        (12, 100001, new Timestamp(System.currentTimeMillis() - 8000), "Title 12"),
        (13, 100001, new Timestamp(System.currentTimeMillis() - 7000), "Title 13"),
        (14, 100005, new Timestamp(System.currentTimeMillis() - 6000), "Title 14"),
        (15, 100001, new Timestamp(System.currentTimeMillis()), "Title 15"))

      val q = Query(Posts).filter(_.id > 10).foreach(println _)

    }

  }
}

object Posts extends Table[(Int, Int, Timestamp, String)]("posts") {
  def id = column[Int]("id", O.PrimaryKey)
  def userId = column[Int]("user_id")
  def createdOn = column[Timestamp]("created_on")
  def title = column[String]("title")
  def * = id ~ userId ~ createdOn ~ title
}