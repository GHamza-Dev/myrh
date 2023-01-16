package flat.io.myrh.application.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long>, JpaRepository<Offer, Long>{

    @Query("SELECT o FROM Offer o WHERE (?1 IS NULL OR LOWER(o.title) LIKE %?1%) AND (?2 IS NULL OR o.jobTitle.id = ?2) AND (?3 IS NULL OR LOWER(o.city) LIKE %?3%)")
    Page<Offer> searchByFilter(String title, Long jobTitleId, String city, Pageable pageable);
}