package kwan.org.webloginchatdemo.repo;

import kwan.org.webloginchatdemo.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by https://github.com/kwanpham
 */
public interface ChatMessageRepo extends JpaRepository<ChatMessage , Long> {

    Page<ChatMessage> findByRoomId(Long roomId ,Pageable pageable);
}

