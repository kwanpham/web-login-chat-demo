package kwan.org.webloginchatdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "ip_chat_message")
@Data
@Accessors(chain = true)
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 5393126870159716461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @CreationTimestamp
    @Column(name = "createdTime")
    private Instant createdTime;


    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name = "read_flag")
    private boolean readFlag;


    @Column(name = "roomId")
    private Integer roomId;



}