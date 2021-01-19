package kwan.org.webloginchatdemo.repo;

import kwan.org.webloginchatdemo.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by https://github.com/kwanpham
 */
public interface ChatRoomRepo extends JpaRepository<ChatRoom , Long> {



}
