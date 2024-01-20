package com.todoApp.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.todoApp.entity.Employee;
import com.todoApp.entity.Role;
import com.todoApp.entity.RolePermissionMapper;
import com.todoApp.repository.EmployeeRepository;
import com.todoApp.repository.RolePermissionMapperRepository;
import com.todoApp.service.CacheOperationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private RolePermissionMapperRepository rolePermissionRepo;

	@Autowired
	private CacheOperationService cache;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee emp = null;
		try {
			if (!cache.isKeyExist(username, username)) {

				emp = empRepo.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + username));
				cache.addInCache(username, username, emp.toString());

			} else {
				String empString = (String) cache.getFromCache(username, username);
				try {
					String[] parts = empString.split(", ");
					Integer id = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1));
					String name = parts[1].substring(parts[1].indexOf('=') + 1);
					String email = parts[2].substring(parts[2].indexOf('=') + 1);
					String password = parts[3].substring(parts[3].indexOf('=') + 1, parts[3].length() - 1);
					emp = new Employee();
					emp.setId(id);
					emp.setEmail(email);
					emp.setName(name);
					emp.setPassword(password);
				} catch (Exception e) {
					System.out.println("EEOR " + e);
				}
			}
			return new CustomeUserDetail(emp, getAuthority(emp.getId()));

		} catch (RedisConnectionFailureException e) { 
			log.error("Redis client not connected");
		}
		emp = empRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + username));

		return new CustomeUserDetail(emp, getAuthority(emp.getId()));

	}

	public Set<SimpleGrantedAuthority> getAuthority(Integer userId) {

		Set<SimpleGrantedAuthority> authorities1 = new HashSet<>();
		String auth = null;

		try {

			if (!cache.isKeyExist(userId + "permissions", userId + "permission")) {

				Role role = empRepo.findById(userId).get().getRole();

				authorities1.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));

				for (RolePermissionMapper map : rolePermissionRepo.findByRole(role)) {
					authorities1.add(new SimpleGrantedAuthority(map.getPermission().getAction()));
				}
				System.err.println("come from db");
				cache.addInCache(userId + "permissions", userId + "permission", authorities1.toString());
			} else {
				auth = (String) cache.getFromCache(userId + "permissions", userId + "permission");
				String[] authorityArray = auth.replaceAll("\\[|\\]", "").split(",\\s*");

				for (int i = 0; i < authorityArray.length; i++) {
					authorities1.add(new SimpleGrantedAuthority(authorityArray[i]));
				}
			}

			return authorities1;

		} catch (RedisConnectionFailureException e) {
			log.error("Redis client not connected", e);
		}
		
		Role role = empRepo.findById(userId).get().getRole();
		authorities1.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));

		for (RolePermissionMapper map : rolePermissionRepo.findByRole(role)) {
			authorities1.add(new SimpleGrantedAuthority(map.getPermission().getAction()));
		}

		return authorities1;
	}
}
