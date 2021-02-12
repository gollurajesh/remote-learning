package com.mouritech.remotelearning.service;

import java.util.List;

import com.mouritech.remotelearning.model.UserDTO;

public interface UserService {

	public UserDTO saveUser(UserDTO userDTO);

	public List<UserDTO> getAllUsers();

	public void updatePassword(String updatedPassword, int userId);

	public UserDTO getUserById(Integer id);

	public boolean userExistsByUserEmail(UserDTO userDTO);

	public UserDTO findOneUser(int id);
}
