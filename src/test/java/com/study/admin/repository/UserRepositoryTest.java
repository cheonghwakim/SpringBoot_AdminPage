package com.study.admin.repository;

import com.study.admin.AdminApplicationTests;
import com.study.admin.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest extends AdminApplicationTests {

    // Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-2222-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User u = User.builder()   // Builder 쓰면 중간에 몇 개 빼먹어도 됨
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();


        User newUser = userRepository.save(user);
        assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {


                System.out.println("----------------주문 묶음---------------------");
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("수령지: " + orderGroup.getRevAddress());
                System.out.println("총금액: " + orderGroup.getTotalPrice());
                System.out.println("총수량: " + orderGroup.getTotalQuantity());

                System.out.println("----------------주문 상세---------------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품: " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문 상태: " + orderDetail.getStatus());
                    System.out.println("도착예정일자: " + orderDetail.getArrivalDate());
                });
            });
        }

        assertNotNull(user);
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> { // 만약 selectUser가 있으면 밑의 문장을 실행하겠다.
//            selectUser.setId(3L);  // 이렇게 하면 만약 3번이 존재한다면 3번인 애가 바뀜!!(주의)
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        assertTrue(user.isPresent()); // true


        user.ifPresent(selectUser -> { // 만약 selectUser가 있으면 밑의 문장을 실행하겠다.
//            selectUser.setId(3L);  // 이렇게 하면 만약 3번이 존재한다면 3번인 애가 삭제됨!!(주의)
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        assertFalse(deleteUser.isPresent()); // false
    }

}
