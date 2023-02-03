package com.example.librarymanagement.DataBase;

import com.example.librarymanagement.Model.AvailabilityStatus;
import com.example.librarymanagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book b where b.id=:id and availability_status=:status", nativeQuery = true)
    Book findByIdAndAvailability(Integer id, String status);

    @Query(value = "select * from book b where b.title like %?1%", nativeQuery = true)
    List<Book> findByTitle(String name);


}
