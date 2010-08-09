package organisemymovies

import com.mongodb.Mongo
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.DB

object MovieDao {

  def save(movie :Movie) = {
    val col = Database.getDatabase().getCollection("Movies")
    val doc = MovieBuilder.toDBObject(movie)
    col.insert(doc)
  }


}

