package RVM.RuleEngine;

import java.util.Objects;

public class RulesRVM {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RulesRVM rules = (RulesRVM) o;
        return id == rules.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
