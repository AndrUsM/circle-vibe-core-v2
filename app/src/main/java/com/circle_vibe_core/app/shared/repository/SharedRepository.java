package com.circle_vibe_core.app.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SharedRepository<T, ID>
        extends JpaRepository<T, ID> {
    
}
