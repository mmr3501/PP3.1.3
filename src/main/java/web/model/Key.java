package web.model;

import java.io.Serializable;
import java.util.Objects;

public class Key implements Serializable {
    private Long user_id;
    private Long role_id;

    public Key(Long user_id, Long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }
    public Key() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(user_id, key.user_id) && Objects.equals(role_id, key.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, role_id);
    }
}
