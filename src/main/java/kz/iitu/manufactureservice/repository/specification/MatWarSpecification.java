package kz.iitu.manufactureservice.repository.specification;

import kz.iitu.manufactureservice.model.Material;
import kz.iitu.manufactureservice.model.MaterialWarehouse;
import kz.iitu.manufactureservice.model.Warehouse;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class MatWarSpecification implements Specification<MaterialWarehouse> {
    private List<SearchCriteria> list;

    public MatWarSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<MaterialWarehouse> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        list.forEach(criteria -> {
            if (criteria.getValue() instanceof String && StringUtils.isNotBlank(criteria.toString())) {
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

                    String[] split = criteria.getKey().split("::");
                    if (split.length == 2) {
                        if ("material".equalsIgnoreCase(split[0])) {
                            Join<Material, MaterialWarehouse> materialJoin = root.join("material");
                            predicates.add(builder.equal(
                                    materialJoin.get(split[1]), criteria.getValue()));
                        }
                    } else {
                        predicates.add(builder.equal(
                                root.get(criteria.getKey()), criteria.getValue()));
                    }
                } else if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                }
            } else if (criteria.getOperation().equals(SearchOperation.IN) && criteria.getValue() instanceof List<?>) {
                Join<Warehouse, MaterialWarehouse> warehouseJoin = root.join("warehouse");
                CriteriaBuilder.In<String> inClause = builder.in(warehouseJoin.get("departmentId"));
                for (String v : (List<String>) criteria.getValue()) {
                    inClause.value(v);
                }
                predicates.add(inClause);
            }
        });
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
