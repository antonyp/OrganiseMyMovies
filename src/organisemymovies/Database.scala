package organisemymovies

import com.mongodb.Mongo
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.DB

object Database {

    def getDatabase() :DB = {
	val m = new Mongo( "192.168.1.15" )
	
	m.getDB( "MyMedia" )
    }

    def testWrite() = {
        
        val col1 = getDatabase().getCollection("test");

        val doc = new BasicDBObject();

	doc.put("name", "test")
	doc.put("type", "fun")

	col1.insert(doc)
	doc
    }
} 



