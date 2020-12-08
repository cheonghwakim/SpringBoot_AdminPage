package com.study.admin.repository;

import com.study.admin.AdminApplicationTests;
import com.study.admin.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminUserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        assertNotNull(newAdminUser);
    }
}
