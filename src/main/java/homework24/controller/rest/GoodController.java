package homework24.controller.rest;

import homework24.model.Good;
import homework24.service.GoodService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/goods")
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @PostMapping
    public ResponseEntity<Good> save(@Valid @RequestBody Good good) {
        Good savedGood = goodService.save(good);

        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String savedGoodLocation = currentUri + "/" + savedGood.getId();

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, savedGoodLocation)
                .body(savedGood);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Good> getById(@PathVariable("id") Long id) {
        Good good = goodService.getById(id);

        return ResponseEntity.ok(good);
    }

    @GetMapping
    public ResponseEntity<List<Good>> getAll() {
        List<Good> goods = goodService.getAll();

        return ResponseEntity.ok(goods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Good> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Good good
    ) {
        Good updatedGood = goodService.update(id, good);

        return ResponseEntity.ok(updatedGood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        goodService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
