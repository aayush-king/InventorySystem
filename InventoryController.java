import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/deduct")
    public ResponseEntity<?> deductInventory(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            inventoryService.deductInventory(productId, quantity);
            return ResponseEntity.ok().build();
        } catch (InventoryException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInventory(@RequestParam Long productId, @RequestParam int quantity) {
        try {
            inventoryService.addInventory(productId, quantity);
            return ResponseEntity.ok().build();
        } catch (InventoryException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/transactions/{productId}")
    public ResponseEntity<List<InventoryTransaction>> getTransactionsByProductId(@PathVariable Long productId) {
        List<InventoryTransaction> transactions = inventoryService.getTransactionsByProductId(productId);
        return ResponseEntity.ok(transactions);
    }
}
