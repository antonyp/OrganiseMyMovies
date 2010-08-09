package organisemymovies

import scala.io.Source
import java.io.File

object Sha1Helper {
  def exec(cmd:String) = Runtime.getRuntime exec cmd

  val Sha1RegEx = """SHA1\((.*)\)=\s(.*)\n""".r

  def exec2String(cmd:String) :String = {
    val process = exec (cmd)
    val input = process.getInputStream
    val source = Source fromInputStream input
    source mkString 
  }

  def getSha1(file:File) : String = {
    val output = exec2String("openssl dgst -sha1 -hex -c " + file.getAbsolutePath() )
   
    val Sha1RegEx(filepath, sha1) = output
    sha1
  }
}
