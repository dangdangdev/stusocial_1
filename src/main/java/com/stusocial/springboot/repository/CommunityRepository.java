package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Community;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;


import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Integer> {
    public List<Community> findCommunitiesByUnversityOrderById(String university, Pageable pageable);

    public List<Community> findCommunitiesByCategoryOrderById(String category, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Community> findById(Integer id);

}
