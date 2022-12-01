package kz.iitu.manufactureservice.repository.specification;

import kz.iitu.manufactureservice.model.MarketMaterial;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Потом сделаем универсальным
 *
 * @author Orken
 **/
public class MarMatSpecification implements Specification<MarketMaterial> {
    private List<SearchCriteria> list;

    public MarMatSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<MarketMaterial> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        list.forEach(criteria -> {
            if (StringUtils.isNotBlank(criteria.toString())) {
                if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(
                            root.get(criteria.getKey()), criteria.getValue()));
                } else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                }
            }
        });
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
