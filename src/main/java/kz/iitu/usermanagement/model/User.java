package kz.iitu.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private User parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<User> children;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;
}
