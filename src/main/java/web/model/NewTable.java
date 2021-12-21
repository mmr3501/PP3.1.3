package web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@IdClass(Key.class)
public class NewTable {

    @Id
    private Long user_id;

    @Id
    private Long role_id;

    public Long getUser_id() {
        return user_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public NewTable(Long user_id, Long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public NewTable() {
    }
}