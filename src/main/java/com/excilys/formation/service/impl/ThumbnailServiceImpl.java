package com.excilys.formation.service.impl;

import com.excilys.formation.service.ThumbnailService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loic on 04/09/2014.
 */
@Service
public class ThumbnailServiceImpl implements ThumbnailService {

	private static final Logger logger = LoggerFactory.getLogger(ThumbnailService.class);

	@Value("${images.src.dir}")
	String imagesDirValue;

	@Value("${images.target.dir}")
	String thumbnailsDirValue;

	File imagesDir;
	File thumbnailsDir;

	@PostConstruct
	private void init() {
		System.out.println("init...");
		imagesDir = new File(imagesDirValue);
		thumbnailsDir = new File(thumbnailsDirValue);
	}

	@Override
	public List<String> processImages(int width, int height) {
		clearDirectory(thumbnailsDir);

		StringBuilder sb = new StringBuilder("Image processed: width: ")
		.append(width)
		.append(" height: ").append(height);

		logger.debug(sb.toString());

		return recursiveProcessImages(imagesDir, thumbnailsDir, new Integer(width), new Integer(height));

	}

	@Override
	public List<String> processImages(int width, int height, int limit) {
		System.out.println("thumbnailsDir: " + thumbnailsDir);
		System.out.println("imagesDir: " + imagesDir);
		clearDirectory(thumbnailsDir);

		StringBuilder sb = new StringBuilder("Image processed: width: ")
		.append(width)
		.append(" height: ").append(height)
		.append(" limit: ").append(limit);

		logger.debug(sb.toString());

		if(limit == 0)
			return recursiveProcessImages(imagesDir,thumbnailsDir,width,height);
		else
			return recursiveProcessImages(imagesDir, thumbnailsDir, width, height,limit);
	}

	@Override
	public byte[] getThumbnail(String fileName) {
		File thumbnail = new File(thumbnailsDir,fileName);
		StringBuilder sb = new StringBuilder("Thumbnail retrieved: filename: ")
		.append(fileName);

		logger.debug(sb.toString());

		if(!thumbnail.exists()) {
			logger.warn("Error in getThumbnail: file {} does not exist.",fileName);
			throw new IllegalStateException("Thumbnail file does not exist");
		}

		try {
			return IOUtils.toByteArray(new FileInputStream(thumbnail));
		} catch (IOException e) {
			logger.warn("Error in getThumbnail while reading file {}",fileName);
			throw new IllegalStateException(e);
		}
	}

	private void clearDirectory(File dir) {
		if(dir == null || dir.list() == null){
			System.out.println("dir est null!!!");
		}
		else{
			System.out.println("dir.listFiles(): " + dir.listFiles());
			for(File f : dir.listFiles()) {
				if(!f.isFile())
					clearDirectory(f);
				f.delete();
			}
		}
		
	}

	private List<String> recursiveProcessImages(File root, File thumbnailsDir, Integer width, Integer height) {
		//TODO
		List<String> imagesPath = new ArrayList<String>();
		BufferedImage bufferedImage;
		String newImagePath;
		// listing des images
		for(String content : root.list()){
			if(content.endsWith(".jpg")){
				try {
					bufferedImage = resizeImage(ImageIO.read(new File(content)), width, height, BufferedImage.TYPE_INT_ARGB);
					newImagePath = thumbnailsDirValue + content;
					ImageIO.write(bufferedImage, "jpg", new File(newImagePath)); 
					imagesPath.add(newImagePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("imagesPath = " + imagesPath);
		return imagesPath;
	}

	private List<String> recursiveProcessImages(File root, File thumbnailsDir, Integer width, Integer height, Integer limit) {
		//TODO
		int cpt = 0;
		List<String> imagesPath = new ArrayList<String>();
		BufferedImage bufferedImage;
		String newImagePath;
		if(root.exists()){
			System.out.println("root existe ");
		}
		else{
			System.out.println("root n'existe pas: " + root.getAbsolutePath());
		}
		System.out.println("root = " + root);
		System.out.println("root.list() = " + root.list());
		// listing des images
		for(String content : root.list()){
			if(cpt<limit){
				cpt++;
				if(content.endsWith(".jpg")){
					System.out.println("content to read: " + content);
					try {
						bufferedImage = resizeImage(ImageIO.read(new File(root + "/" + content)), width, height, BufferedImage.TYPE_INT_ARGB);
						newImagePath = thumbnailsDirValue + content;
						System.out.println("lien nouvelle image: " + newImagePath);
						ImageIO.write(bufferedImage, "jpg", new File(newImagePath)); 
						imagesPath.add(newImagePath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else{
				break;
			}
		}

		return imagesPath;
	}

	private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type){
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

}
