package com.oussema.keylearn.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kc")
@RequiredArgsConstructor
public class KcSyncController {

  private final KcSyncService kcSyncService;

  @PostMapping("/sync")
  public ResponseEntity<Boolean> sync(@RequestBody KcUser request) {
    return ResponseEntity.ok(kcSyncService.syncUser(request));
  }
}
