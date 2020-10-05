package com.online.movie.ticket.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.movie.ticket.core.dto.BaseDto;
import com.online.movie.ticket.exception.ErrorCode;
import com.online.movie.ticket.exception.RestException;
import com.online.movie.ticket.model.MovieSnaps;
import com.online.movie.ticket.model.ScreenMaster;
import com.online.movie.ticket.util.TimeUtil;

import lombok.extern.log4j.Log4j2;

import com.online.movie.ticket.repository.MovieMasterRepository;
import com.online.movie.ticket.repository.MovieSnapsRepository;

@Service
@Log4j2
public class MovieSnapService {

	@Autowired 
	MovieSnapsRepository movieSnapsRepository;
	
	@Autowired 
	MovieMasterRepository movieMasterRepository;
	
	public BaseDto create(MultipartFile file, Long id) {
		log.debug("Create method is called....."+file.getOriginalFilename());

		BaseDto baseDto = new BaseDto();
		try {
			
			// Setting track time & user...
			MovieSnaps movieSnaps = new MovieSnaps();
			movieSnaps.setSystemTrack(TimeUtil.getCreateSystemTrack());
			movieSnaps.setImage(compressBytes(file.getBytes()));
			movieSnaps.setFileName(file.getOriginalFilename());
			movieSnaps.setFileType(file.getContentType());
			movieSnaps.setMovieMaster(movieMasterRepository.getOne(id));
			// Saving 
			MovieSnaps movieSnapsCreated = movieSnapsRepository.save(movieSnaps);
		
			log.debug("scheduleScreenShow Master Saved successfully.....[" + movieSnapsCreated.getId() + "]");

			baseDto.setResponseCode(ErrorCode.SUCCESS.getCode() );
			baseDto.setResponseObject("Success");

		} catch (RestException exception) {

			log.error("BadRequestException occured with error code ", exception.getMessage());
			baseDto.setResponse(exception.getError());
	

		} catch (Exception exception) {
			String exceptionCause1 = ExceptionUtils.getRootCauseMessage(exception);

			log.error("Exception Cause 1 ::: " + exceptionCause1);

			baseDto.setResponseCode(ErrorCode.ERROR_GENERIC.getCode());
			baseDto.setResponseDescription(exception.getMessage());
		
		}
		return baseDto;
	}
	

	public BaseDto getImageByMovieID(Long id) {
		BaseDto baseDto = new BaseDto();
		try
		{
			List<MovieSnaps> movieSnapsList  = movieSnapsRepository.findByMovieID(id);
			int i = 0;
			for (MovieSnaps temp : movieSnapsList) 
			{
		            temp.setImage(decompressBytes(temp.getImage()));
		            movieSnapsList.set(i, temp);
		            i++;
		    }

			baseDto.setResponse(ErrorCode.SUCCESS);
			baseDto.setResponseObject(movieSnapsList);
		} 
			catch (RestException re) {
			log.error("Exception in getAll method ", re.getMessage());
			baseDto.setResponse(re.getError());
			
		} catch (Exception e) {
			log.error("Exception in getAll method ", e);
			baseDto.setResponse(ErrorCode.FAILED);
			
		}
		return baseDto;		
		
	}
	
	// compress the image bytes before storing it in the database
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
		}
		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
}
