package org.ej31.kcs5thjpa.services;

import org.ej31.kcs5thjpa.models.Department;
import org.ej31.kcs5thjpa.models.User;
import org.ej31.kcs5thjpa.repository.DepartmentRepository;
import org.ej31.kcs5thjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Object createDepartment(String departmentName, User user) {
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        User savedUser;
        savedUser = userByEmail
                // 이미 존재하는 User 객체 사용하거나..
                .orElseGet(
                        // 유저가 존재 하지 않는 경우에는 이 코드를 탐. User 객체 저장 후 해당 객체를 리턴 받아서 사용
                        () -> userRepository.save(user)
                );

        if (savedUser.getDepartment() == null) {
            //  저장된 User 객체를 사용하여 Department 객체를 생성하고 저장합니다.
            Department department = new Department(departmentName, savedUser);
            return departmentRepository.save(department);
        }
        return "이미 소속이 있습니다";
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Transactional
    public Department updateDepartment(Long id, String departmentName, User user) {
        return departmentRepository.findById(id).map(department -> {
            department.setDepartmentName(departmentName);
            department.setUser(user);
            return departmentRepository.save(department);
        }).orElse(null);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
