package com.sanjeevaniehr.mongodbconnectionandfilesvc;

import java.io.File;

/**
 * @author pradyumna
 *
 */

import java.io.IOException;
import java.io.InputStream;

import org.bson.types.ObjectId;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class ImageService {
	
	MongoClient mongoClient = new MongoClient();
	DB db = mongoClient.getDB("db1");
	GridFS gridFs = new GridFS(db);
	
	ImageService() {
	}
	
	InputStream getImageByIdFromDatabase(String id){
			GridFSDBFile imageForOutput = gridFs.findOne(new ObjectId(id));
			return imageForOutput.getInputStream();
		
	}
	
	String saveImageIntoDataBase(InputStream stream, String name) throws IOException{
		GridFSInputFile gridInputFile = gridFs.createFile(stream);
		gridInputFile.setFilename(name);
		gridInputFile.save();
		return gridInputFile.getId().toString();
	}

}
