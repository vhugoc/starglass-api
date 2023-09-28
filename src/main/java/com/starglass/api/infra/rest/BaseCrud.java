package com.starglass.api.infra.rest;

import com.starglass.api.infra.entity.BaseEntity;
import com.starglass.api.infra.service.BaseService;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class BaseCrud<T extends BaseEntity, B extends BaseEntity.Builder> {

    private final BaseService<T, B> service;

    @Autowired
    public BaseCrud(BaseService<T, B> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<BaseServiceResponse> findAll() {
        BaseServiceResponse<List<T>> entities = service.findAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseServiceResponse> findById(@PathVariable String id) {
        BaseServiceResponse<T> entity = service.findById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BaseServiceResponse> create(@RequestBody @Validated B entityBuilder) {
        BaseServiceResponse<T> save = service.save(entityBuilder);
        return ResponseEntity.status(save.getStatusCode()).body(save);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseServiceResponse> update(@PathVariable String id, @RequestBody @Validated B entityBuilder) {
        BaseServiceResponse<T> update = service.update(id, entityBuilder);
        return ResponseEntity.status(update.getStatusCode()).body(update);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BaseServiceResponse> delete(@PathVariable String id) {
        BaseServiceResponse<T> delete = service.delete(id);
        return ResponseEntity.status(delete.getStatusCode()).body(delete);
    }

}
