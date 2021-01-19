package kwan.org.webloginchatdemo.entity;

import kwan.org.webloginchatdemo.entity.key.RelationshipId;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by https://github.com/kwanpham
 */
@Data
@Entity
@Table(name = "ip_relationship")
@IdClass(RelationshipId.class)
public class RelationShip {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_one_id" , referencedColumnName = "id")
    private User userOne;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_two_id" , referencedColumnName = "id")
    private User userTwo;

    @Column(name = "status")
    private byte status;

    @ManyToOne
    @JoinColumn(name = "action_one_id" , referencedColumnName = "id")
    private User actionUser;


}
