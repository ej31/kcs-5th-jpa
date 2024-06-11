package org.ej31.kcs5thjpa.repository;

import org.ej31.kcs5thjpa.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // 추가적인 쿼리 메소드 정의 가능
}
