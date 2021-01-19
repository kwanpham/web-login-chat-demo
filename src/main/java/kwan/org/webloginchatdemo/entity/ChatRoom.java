package kwan.org.webloginchatdemo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ip_chat_room")
@Data
public class ChatRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;


    @OneToMany(mappedBy = "roomId")
    private List<ChatMessage> listChatMessage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ip_group_members" ,
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "startDate")
    private Instant startDate;

    @Column(name = "type")
    private Byte type;


    @Column(name = "status")
    private Byte status;

}