package com.sanjeevaniehr.mongodbconnectionandfilesvc;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;




@Path("/images")
public class ImageResource {
	
	ImageService imageService = new ImageService();
			
	@GET
	@Path("/hello")
	public String helloMethod(){
		return "Pradyumna";
	}
	
	@GET
	@Produces("image/png")
	@Path("/download/image/{id}")
	public   InputStream getImageById(@PathParam("id") String id){
		System.out.println("In Image method");
		return imageService.getImageByIdFromDatabase(id);
	}
	
	@POST 
    @Path("/upload")  
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public Response uploadFile(  
            @FormDataParam("file") InputStream inputStream,  
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
		String message = null;
		try {
			message = imageService.saveImageIntoDataBase(inputStream,fileDetail.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity("ID: "+ message).build();
	}

}
