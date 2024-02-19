package kz.nik.middle01testcontainers.repository;

import kz.nik.middle01testcontainers.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}