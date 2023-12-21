package edii.tescrud.khadafi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edii.tescrud.khadafi.dto.UserDTO;
import edii.tescrud.khadafi.dto.UserIdDTO;
import edii.tescrud.khadafi.entity.UserEntity;
import edii.tescrud.khadafi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Map<String, Object> getData(UserIdDTO userIdDto){
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(userIdDto.getUserid().equals("all") || userIdDto.getUserid().equals("All") || userIdDto.getUserid().equals("ALL")) {
			response.put("status-code", 200);
			response.put("message", "success");
			response.put("data", userRepository.findAll());
		} else { 
			UserEntity data = userRepository.filter(Integer.parseInt(userIdDto.getUserid()));
			response.put("status-code", 200);
			response.put("message", "success");
			response.put("data", data);
		}

		return response;
	}
	
	@Transactional
	public Map<String, Object> tambahUser(UserDTO userDto){
		Map<String, Object> response = new HashMap<String, Object>();
		
		UserEntity userEntity = new UserEntity();
		
		if(
			userDto.getNamalengkap() != null &&
			!userDto.getNamalengkap().isEmpty() &&
			userDto.getUsername() != null &&
			!userDto.getUsername().isEmpty() &&
			userDto.getPassword() != null &&
			!userDto.getPassword().isEmpty() &&
			userDto.getStatus() != null &&
			!userDto.getStatus().isEmpty()
		) {
			userEntity.setNamalengkap(userDto.getNamalengkap());
			userEntity.setUsername(userDto.getUsername());
			userEntity.setPassword(userDto.getPassword());
			userEntity.setStatus(userDto.getStatus());
			
			response.put("status-code", 200);
			response.put("message", "success");
			response.put("data", userRepository.save(userEntity));
		} else {
			response.put("status-code", 500);
			response.put("message", "Data harus diisi");
		}
		
		
		return response;
	}
	
	@Transactional
	public Map<String, Object> hapusUser(int userid){
		Optional<UserEntity> data = userRepository.findById(userid);
		Map<String, Object> response = new HashMap<String, Object>();
		
		if(data.isEmpty()) {
			response.put("status-code", 404);
			response.put("message", "Data tidak ditemukan");
		} else {
			userRepository.deleteById(userid);
			
			response.put("status-code", 200);
			response.put("message", "Data berhasil dihapus");
		}
		
		return response;
	}
	
}
