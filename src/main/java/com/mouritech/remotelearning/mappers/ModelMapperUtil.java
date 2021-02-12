package com.mouritech.remotelearning.mappers;

/***
 * @author deepakp
 */

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

	public <S, D> D map(S sourceClass, Class<D> destinationClass) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(sourceClass, destinationClass);
	}
}
