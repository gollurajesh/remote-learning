package com.mouritech.remotelearning.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.remotelearning.entity.User;
import com.mouritech.remotelearning.exceptions.RecordNotFoundException;
import com.mouritech.remotelearning.mappers.ModelMapperUtil;
import com.mouritech.remotelearning.mappers.UserMapper;
import com.mouritech.remotelearning.model.UserDTO;
import com.mouritech.remotelearning.repository.UserRepository;
import com.mouritech.remotelearning.service.UserService;
import com.mouritech.remotelearning.utils.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapperUtil modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JwtTokenUtil jwtTokeUtil;

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		User map = modelMapper.map(userDTO, User.class);
		User userEntity = userRepository.save(map);
		return modelMapper.map(userEntity, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userEntity = userRepository.findAll();
		return userEntity.stream().map(data -> modelMapper.map(data, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public boolean userExistsByUserEmail(UserDTO userDTO) {
		Optional<User> user = userRepository.findUserByuserEmail(userDTO.getUserEmail());
		boolean returnType = false;
		return user.isPresent() ? !returnType : returnType;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		Optional<User> userEntity = userRepository.findById(id);
		if (!userEntity.isPresent()) {
			throw new RecordNotFoundException("User not found with this id " + id);
		}
		return modelMapper.map(userEntity.get(), UserDTO.class);
	}

	@Override
	public void updatePassword(String updatedPassword, int userId) {
		Optional<User> userRetrieved = userRepository.findById(userId);
		if (userRetrieved.isPresent()) {
			userRetrieved.get().setPassword(updatedPassword);
			userRepository.save(userRetrieved.get());
		}
	}

	@Override
	public UserDTO findOneUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return userMapper.userToUserDto(user.get());
		}
		return null;
	}
}
