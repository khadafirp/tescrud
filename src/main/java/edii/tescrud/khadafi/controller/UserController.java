package edii.tescrud.khadafi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edii.tescrud.khadafi.dto.UserDTO;
import edii.tescrud.khadafi.dto.UserIdDTO;
import edii.tescrud.khadafi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getUser")
	@ResponseBody
	public Map<String, Object> getData(@RequestBody UserIdDTO userIdDto){
		Map<String, Object> response = userService.getData(userIdDto);
		return response;
	}
	
	@PostMapping("/tambahUser")
	@ResponseBody
	public Map<String, Object> tambahUser(@RequestBody UserDTO userDto){
		Map<String, Object> response = userService.tambahUser(userDto);
		return response;
	}
	
	@DeleteMapping("/hapusUser")
	@ResponseBody
	public Map<String, Object> hapusUser(@RequestParam(name = "userid") int userid){
		Map<String, Object> response = userService.hapusUser(userid);
		return response;
	}
	
}
