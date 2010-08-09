/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package organisemymovies

abstract class MovieEncoding {

  def getEncodingName() : String

}

class AviMovieEncoding extends MovieEncoding {

  override def getEncodingName() : String = "AVI"

}

class Mp4MovieEncoding extends MovieEncoding {

  override def getEncodingName() : String = "MP4"

}

class MkvMovieEncoding extends MovieEncoding {

  override def getEncodingName() : String = "MKV"

}