package restaurant.infrastructure.repository;

import restaurant.domain.entity.SupportRequest;
import restaurant.domain.enums.SupportRequestStatus;

import java.util.List;

public class SupportRequestRepository extends Repository<SupportRequest, Integer>{
    public SupportRequestRepository(List<SupportRequest> list)
    {
        super(list);
    }

    public SupportRequest findById(int id)
    {
        return findById(id, SupportRequest::getRequestId);
    }

    public List<SupportRequest> findByTableId(int tableId)
    {
        return findBy(r -> r.getTableId() == tableId);
    }

    public List<SupportRequest> findByStatus(SupportRequestStatus status)
    {
        return findBy(r -> r.getStatus() == status);
    }
}
