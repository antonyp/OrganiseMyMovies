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
   
  /**
   * @param args the command line arguments
   */
  def main(args: Array[String]) :Unit = {
    var dir = new File(MOVIE_DIR)
    organiseDir(dir)
  }



  def organiseDir(dir: File) :List[Movie] = {
    var movies = List[Movie]()
        
    if (!dir.isDirectory) {
        throw new IllegalArgumentException("File passed is not a directory: " + dir.getAbsoluteFile)
    }
    
    dir.listFiles.foreach(sub => {

            sub.isDirectory match {
                case true => movies = movies ::: handleDir(sub)
                case false => movies += (handleFile(sub))
                case _ => handleUnknown(sub)
            }
        }
    ) 
    movies
        
  }

  def handleDir(dir: File) :List[Movie] = {
      organiseDir(dir)
  }

  def handleFile(file: File) : Movie  = {
      var movie = new Movie(file)
      if (movie.isMovie) {
        println(movie)
	MovieDao.save(movie)
      } else {
        println("Not a movie: " + file)
      }
      movie
  }

  def handleUnknown(unknown: File) = {
      print ("Unknown: " + unknown.getName)
  }


 
 

}
