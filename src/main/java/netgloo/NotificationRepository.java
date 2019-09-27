package netgloo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    @Query(value = "select * from Notification where RECEIVER = :name", nativeQuery = true)
    public Iterable<Notification> getByName(@Param("name") String name);


}
