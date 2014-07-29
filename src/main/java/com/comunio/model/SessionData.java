package com.comunio.model;

import java.io.Serializable;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData implements Serializable {

    private long comunioId;
    private Comunio comunio;

    public SessionData() {
    }

    public long getComunioId() {
        return comunioId;
    }

    public void setComunioId(long comunioId) {
        this.comunioId = comunioId;
    }

    public Comunio getComunio() {
        return comunio;
    }

    public void setComunio(Comunio comunio) {
        this.comunio = comunio;
    }

    public Groupe getGroup(String groupName) {
        for (Groupe group : comunio.getGroups()) {
            if (group.getGroupName().equals(groupName)) {
                return group;
            }
        }
        throw new NoResultException();
    }
}
