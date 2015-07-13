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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.excilys.formation.dto.ThumbnailResponse;
import com.excilys.formation.model.Operation;
import com.excilys.formation.service.OperationService;
import com.excilys.formation.service.ThumbnailService;
import com.excilys.formation.webservice.ThumbnailWebService;

@Controller
@RequestMapping(value = "/thumbnails")
public class ThumbnailWebServiceImpl implements ThumbnailWebService {

	@Autowired
	ThumbnailService thumbnailService;
	
	@Autowired
	OperationService operationService;
	
	@RequestMapping(value = "/{width}/{height}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public ThumbnailResponse createThumbnails(@PathVariable int width, @PathVariable int height) {
		// TODO Auto-generated method stub
		if(width<=0 || height<=0){
			throw new BadParamException();
		}
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

	@RequestMapping(value = "/{width}/{height}/{limit}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public ThumbnailResponse createThumbnails(@PathVariable int width, @PathVariable int height, @PathVariable int limit) {
		// TODO Auto-generated method stub
		if(width<=0 || height<=0){
			throw new BadParamException();
		}
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

	@RequestMapping(value = "/{filename}", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public byte[] getThumbnail(@PathVariable("filename") String fileName) {
		// TODO Auto-generated method stub
		return thumbnailService.getThumbnail(fileName);
	}
	
	public class BadParamException extends RuntimeException{
	}
	

}
