package com.mouritech.remotelearning.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mouritech.remotelearning.entity.User;
import com.mouritech.remotelearning.model.UserDTO;

@Component
public class UserMapper {

	@Autowired
	private ModelMapper mapper;

	public UserDTO userToUserDto(User user) {
		return mapper.map(user, UserDTO.class);
	}

	public User userDtoToUser(UserDTO userDTO) {
		return mapper.map(userDTO, User.class);
	}
}
