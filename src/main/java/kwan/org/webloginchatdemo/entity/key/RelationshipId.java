package kwan.org.webloginchatdemo.entity.key;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by https://github.com/kwanpham
 */
@Data
public class RelationshipId implements Serializable {

    private Long userOne;

    private Long userTwo;

}
