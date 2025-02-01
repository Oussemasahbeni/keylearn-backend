package com.oussema.keylearn.shared.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

  private static final SortDirection DEFAULT_SORT_DIRECTION = SortDirection.ASC;

  // Prevent instantiation
  private PaginationUtils() {}

  /**
   * Creates a Pageable object with the given parameters and default sorting direction if necessary.
   *
   * @param page the page number
   * @param size the page size
   * @param sort the sorting field
   * @param sortDirection the sorting direction
   * @return a Pageable object
   */
  public static Pageable createPageable(int page, int size, String sort, String sortDirection) {

    Sort sortOrder =
        getSorDirection(sortDirection) == SortDirection.ASC
            ? Sort.by(sort).ascending()
            : Sort.by(sort).descending();

    return PageRequest.of(page, size, sortOrder);
  }

  private static SortDirection getSorDirection(String sortDirection) {
    try {
      return SortDirection.valueOf(sortDirection.toUpperCase());
    } catch (IllegalArgumentException e) {
      return DEFAULT_SORT_DIRECTION;
    }
  }
}
