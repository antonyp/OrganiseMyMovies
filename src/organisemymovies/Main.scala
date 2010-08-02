/*
 * Main.scala
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package organisemymovies

import java.io.File
import org.apache.commons.io.FileUtils

object Main {

  val MOVIE_DIR = "/mnt/movies"

  val AviFileMatcher = """(.*)\.avi""".r
  val Mp4FileMatcher = """(.*)\.mp4""".r
  val MkvFileMatcher = """(.*)\.mkv""".r

  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]) :Unit = {
    var dir = new File(MOVIE_DIR)
    organiseDir(dir)
  }



  def organiseDir(dir: File) :Unit = {
    
        
    if (!dir.isDirectory) {
        throw new IllegalArgumentException("File passed is not a directory: " + dir.getAbsoluteFile)
    }
    
        
    dir.listFiles.foreach(sub => {

            sub.isDirectory match {
                case true => handleDir(sub)
                case false => handleFile(sub)
                case _ => handleUnknown(sub)
            }
        }
    )

        
  }

  def handleDir(dir: File) = {
      organiseDir(dir)
  }


  def handleFile(file: File) = {
     
      file.getName match {
        case AviFileMatcher(filename) => {
                println("Avi File Found: " + filename)
                moveMovieIfRequired(file, filename)
        }
        case Mp4FileMatcher(filename) => {
                println("AAC File Found: " + filename)
                moveMovieIfRequired(file, filename)
        }
        case MkvFileMatcher(filename) => {
                println("MKV File Found: " + filename)
                moveMovieIfRequired(file, filename)
        }
        case _ => println("Unknown File Found: " + file.getName)
      }
  }

  def handleUnknown(unknown: File) = {
      print ("Unknown: " + unknown.getName)
  }

  def moveMovieIfRequired(file :File, filename :String) :File = {
      val movieDir = getMovieDir(filename)

      if (!file.getParent.equals(movieDir.getAbsolutePath)) {
                     
          println("Moving Movie: " + file.getAbsolutePath + " to " + movieDir.getAbsolutePath)

          
          FileUtils.copyFileToDirectory(file, movieDir)

          val newFile = new File(movieDir, file.getName)

          if (newFile.exists && newFile.length == file.length) {
              println("Removing Old File")
              file.delete
          }

          
          newFile
          
      } else {
	  println("No moving required")
          file
      }
      
  }

  def isMovie(file :File) :Boolean = {
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

  def getMovieDir(filename :String) :File = {
      val movieDir = new File(MOVIE_DIR, filename)

        if (!movieDir.exists()) {
            movieDir.mkdir
        }

        movieDir
  }

}
