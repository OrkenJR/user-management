package kz.iitu.usermanagement.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * dummy-user entity
 *
 * @author Orken
 * @version 1.0
 * @since 1.0
 **/

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String username;

    private String password;

}
