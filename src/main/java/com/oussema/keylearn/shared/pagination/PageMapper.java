package com.oussema.keylearn.shared.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public class PageMapper {

  private static final String ID = "id";

  private PageMapper() {
    // Prevent instantiation
  }

  public static <T> CustomPage<T> toCustomPage(Page<T> page) {
    Sort.Order order = page.getSort().stream().findFirst().orElse(Sort.Order.asc(ID));
    SortDirection direction =
        order.getDirection().isAscending() ? SortDirection.ASC : SortDirection.DESC;
    return new CustomPage<>(
        page.getContent(),
        page.getTotalElements(),
        page.getTotalPages(),
        page.getNumber(),
        page.getSize(),
        page.isFirst(),
        page.isLast(),
        page.isEmpty(),
        new CustomSortInfo(page.getSort().isSorted(), page.getSort().isUnsorted(), direction));
  }
}
