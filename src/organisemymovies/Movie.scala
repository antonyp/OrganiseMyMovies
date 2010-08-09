/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisemymovies

import java.io.File
import java.io.InputStream
import java.io.FileInputStream
import java.security.MessageDigest

import scala.collection.mutable.HashMap
import com.mongodb.BasicDBObject


case class Movie (file: File){
 
  val AviFileMatcher = """(.*)\.avi""".r
  val Mp4FileMatcher = """(.*)\.mp4""".r
  val MkvFileMatcher = """(.*)\.mkv""".r
  
  def calcChecksum() :String = {
    Sha1Helper.getSha1(file)
  }

  def isMovie() :Boolean = {
    file.getName match {
      case AviFileMatcher(filename) => {
          return true
        }
      case Mp4FileMatcher(filename) => {
          return true
        }
      case MkvFileMatcher(filename) => {
          return true
        }
      case _ => {
          return false
        }
    }
  }

  def getParentDirectory() :File = {
    val movieDir = file.getParentFile()

    if (!movieDir.exists()) {
      movieDir.mkdir
    }

    movieDir
  }
}


object MovieBuilder {

  def fromMap(map :HashMap[String,String]) : Movie = {
    new Movie(new File(map("file")))
  }  

  def toDBObject(movie :Movie) :BasicDBObject = {
    val row = new BasicDBObject
    row.append("file", movie.file.getAbsolutePath())
    row.append("checkSum", movie.calcChecksum())
    row
  }
}


