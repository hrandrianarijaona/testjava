package com.excilys.formation.webservice.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.dto.ThumbnailResponse;
import com.excilys.formation.model.Operation;
import com.excilys.formation.service.OperationService;
import com.excilys.formation.service.ThumbnailService;
import com.excilys.formation.webservice.ThumbnailWebService;

@Path("/thumbnails")
public class ThumbnailWebServiceImpl implements ThumbnailWebService {

	@Autowired
	ThumbnailService thumbnailService;
	
	@Autowired
	OperationService operationService;
	
	@POST
	@Path("/{width}/{height}")
	@Override
	public ThumbnailResponse createThumbnails(@PathParam("width") int width, @PathParam("height") int height) {
		// TODO Auto-generated method stub
		List<String> images = new ArrayList<String>(thumbnailService.processImages(width, height));
		ThumbnailResponse response = new ThumbnailResponse(images.size(), images);
		if(response!=null){
			Operation operation = new Operation();
			operation.setTime(new LocalDateTime(Calendar.getInstance().getTimeInMillis()));
			operation.setMessage("imageList: " + images + " - " + "width = "+ width + "/height = " + height);
			operationService.save(operation);
		}
		return response;
	}

	@POST
	@Path("/{width}/{height}/{limit}")
	@Override
	public ThumbnailResponse createThumbnails(@PathParam("width") int width, @PathParam("height") int height, @PathParam("limit") int limit) {
		// TODO Auto-generated method stub
		List<String> images = new ArrayList<String>(thumbnailService.processImages(width, height, limit));
		ThumbnailResponse response = new ThumbnailResponse(images.size(), images);
		if(response!=null){
			Operation operation = new Operation();
			operation.setTime(new LocalDateTime(Calendar.getInstance().getTimeInMillis()));
			operation.setMessage("imageList: " + images + " - " + "width = "+ width + "/height = " + height + "/limit = " + limit);
			operationService.save(operation);
		}
		return response;
	}

	@GET
	@Path("/{filename}")
	@Override
	public byte[] getThumbnail(@PathParam("filename") String fileName) {
		// TODO Auto-generated method stub
		return thumbnailService.getThumbnail(fileName);
	}

}
